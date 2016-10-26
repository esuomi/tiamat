package org.rutebanken.tiamat.importers;

import com.google.common.util.concurrent.Striped;
import org.rutebanken.netex.model.*;
import org.rutebanken.tiamat.model.*;
import org.rutebanken.tiamat.model.LocationStructure;
import org.rutebanken.tiamat.model.Quay;
import org.rutebanken.tiamat.model.SiteFrame;
import org.rutebanken.tiamat.model.StopPlace;
import org.rutebanken.tiamat.netexmapping.NetexIdMapper;
import org.rutebanken.tiamat.pelias.CountyAndMunicipalityLookupService;
import org.rutebanken.tiamat.repository.QuayRepository;
import org.rutebanken.tiamat.repository.StopPlaceRepository;
import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

@Component
@Qualifier("defaultStopPlaceImporter")
public class DefaultStopPlaceImporter implements StopPlaceImporter {


    private static final Logger logger = LoggerFactory.getLogger(DefaultStopPlaceImporter.class);

    private TopographicPlaceCreator topographicPlaceCreator;

    private CountyAndMunicipalityLookupService countyAndMunicipalityLookupService;

    private QuayRepository quayRepository;

    private StopPlaceRepository stopPlaceRepository;

    private StopPlaceFromOriginalIdFinder stopPlaceFromOriginalIdFinder;

    private NearbyStopPlaceFinder nearbyStopPlaceFinder;

    private KeyValueAppender keyValueAppender;

    private static DecimalFormat format = new DecimalFormat("#.#");

    private Striped<Semaphore> stripedSemaphores = Striped.lazyWeakSemaphore(Integer.MAX_VALUE, 1);


    @Autowired
    public DefaultStopPlaceImporter(TopographicPlaceCreator topographicPlaceCreator,
                                    CountyAndMunicipalityLookupService countyAndMunicipalityLookupService,
                                    QuayRepository quayRepository, StopPlaceRepository stopPlaceRepository,
                                    StopPlaceFromOriginalIdFinder stopPlaceFromOriginalIdFinder,
                                    NearbyStopPlaceFinder nearbyStopPlaceFinder, KeyValueAppender keyValueAppender) {
        this.topographicPlaceCreator = topographicPlaceCreator;
        this.countyAndMunicipalityLookupService = countyAndMunicipalityLookupService;
        this.quayRepository = quayRepository;
        this.stopPlaceRepository = stopPlaceRepository;
        this.stopPlaceFromOriginalIdFinder = stopPlaceFromOriginalIdFinder;
        this.nearbyStopPlaceFinder = nearbyStopPlaceFinder;
        this.keyValueAppender = keyValueAppender;
    }

    private StopPlace findNearbyOrExistingStopPlace(StopPlace newStopPlace) {
        final StopPlace existingStopPlace = stopPlaceFromOriginalIdFinder.find(newStopPlace);
        if (existingStopPlace != null) {
            return existingStopPlace;
        }

        if (newStopPlace.getName() != null) {
            final StopPlace nearbyStopPlace = nearbyStopPlaceFinder.find(newStopPlace);
            if (nearbyStopPlace != null) {
                logger.debug("Found nearby stop place with name: {}, id: {}", nearbyStopPlace.getName(), nearbyStopPlace.getId());
                return nearbyStopPlace;
            }
        }
        return null;
    }

    private Semaphore getStripedSemaphore(StopPlace stopPlace) {
        final String semaphoreKey;
        if (stopPlace.getCentroid() != null && stopPlace.getCentroid().getLocation() != null) {
            LocationStructure location = stopPlace.getCentroid().getLocation();
            semaphoreKey = locationSemaphore(location);
        } else
        if (stopPlace.getId() != null) {
            semaphoreKey = "new-stop-place-"+stopPlace.getId();
        } else if (stopPlace.getName() != null
                && stopPlace.getName().getValue() != null
                && !stopPlace.getName().getValue().isEmpty()){
            semaphoreKey = "name-"+stopPlace.getName().getValue();
        } else {
            semaphoreKey = "all";
        }
        return stripedSemaphores.get(semaphoreKey);
    }

    private String locationSemaphore(LocationStructure location) {
        return "location-"+ format.format(location.getLongitude())+"-"+ format.format(location.getLatitude());
    }

    @Transactional
    @Override
    public StopPlace importStopPlace(StopPlace newStopPlace, SiteFrame siteFrame,
                                     AtomicInteger topographicPlacesCreatedCounter) throws InterruptedException, ExecutionException {
        if (newStopPlace.getCentroid() == null
                || newStopPlace.getCentroid().getLocation() == null
                || newStopPlace.getCentroid().getLocation().getGeometryPoint() == null) {
            logger.info("Ignoring stop place {} - {} because it lacks geometry", newStopPlace.getName(), newStopPlace.getId());
            return null;
        }

        Semaphore semaphore = getStripedSemaphore(newStopPlace);
        semaphore.acquire();

        try {
            logger.info("Import stop place. Current ID: {}, Name: '{}', Quays: {}",
                    newStopPlace.getId(), newStopPlace.getName() != null ? newStopPlace.getName() : "",
                    newStopPlace.getQuays() != null ? newStopPlace.getQuays().size() : 0);

            final StopPlace foundStopPlace = findNearbyOrExistingStopPlace(newStopPlace);

            if(foundStopPlace != null) {
                Set<Quay> quaysToAdd = determineQuaysToAdd(newStopPlace, foundStopPlace);

                quaysToAdd.forEach(quay -> {
                    logger.debug("Saving quay {}, {}", quay.getId(), quay.getName());
                    foundStopPlace.getQuays().add(quay);
                    quayRepository.save(quay);
                });

                logger.info("Found existing stop place {}. Adding {} quays to it", foundStopPlace.getId(), quaysToAdd.size());

                keyValueAppender.appendToOriginalId(NetexIdMapper.ORIGINAL_ID_KEY, newStopPlace, foundStopPlace);
                stopPlaceRepository.save(foundStopPlace);
                return initializeLazyReferences(foundStopPlace);
            }
            // TODO: Hack to avoid 'detached entity passed to persist'.
            newStopPlace.getCentroid().getLocation().setId(0);

            if (siteFrame.getTopographicPlaces() != null
                    && siteFrame.getTopographicPlaces().getTopographicPlace() != null
                    && !siteFrame.getTopographicPlaces().getTopographicPlace().isEmpty()) {
                topographicPlaceCreator.setTopographicReference(newStopPlace,
                        siteFrame.getTopographicPlaces().getTopographicPlace(),
                        topographicPlacesCreatedCounter);
            } else {
                lookupCountyAndMunicipality(newStopPlace, topographicPlacesCreatedCounter);
            }
            Long originalId = newStopPlace.getId();

            if (newStopPlace.getQuays() != null) {
                logger.debug("Stop place has {} quays", newStopPlace.getQuays().size());
                newStopPlace.getQuays().forEach(quay -> {
                    if (quay.getCentroid() == null) {
                        logger.warn("Centroid is null for quay with id {}. Ignoring it.", quay.getId());
                    } else if (quay.getCentroid().getLocation() == null) {
                        logger.warn("Location for centroid of quay with id {} is null. Ignoring it.", quay.getId());
                    } else {
                        quay.getCentroid().setId(null);
                        quay.getCentroid().getLocation().setId(0);
                        quayRepository.save(quay);
                        logger.debug("Saved quay. Got id {} back", quay.getId());
                    }
                });
            }

            stopPlaceRepository.save(newStopPlace);
            stopPlaceFromOriginalIdFinder.update(originalId, newStopPlace.getId());
            nearbyStopPlaceFinder.update(newStopPlace);
            logger.info("Saved stop place {} {} with {} quays", newStopPlace.getName(), newStopPlace.getId(), newStopPlace.getQuays() != null ? newStopPlace.getQuays().size() : 0);

            return initializeLazyReferences(newStopPlace);
        }
        finally {
            semaphore.release();
        }
    }

    private void lookupCountyAndMunicipality(StopPlace stopPlace, AtomicInteger topographicPlacesCreatedCounter) {
        try {
            countyAndMunicipalityLookupService.populateCountyAndMunicipality(stopPlace, topographicPlacesCreatedCounter);
        } catch (IOException|InterruptedException e) {
            logger.warn("Could not lookup county and municipality for stop place with id {}", stopPlace.getId());
        }
    }

    private StopPlace initializeLazyReferences(StopPlace stopPlace) {
        if(stopPlace != null) {
            Hibernate.initialize(stopPlace.getLevels());
            Hibernate.initialize(stopPlace.getOtherTransportModes());
        }
        return stopPlace;
    }

    /**
     * Inspect quays from incoming stop place. If they do not exist from before, add them.
     */
    public Set<Quay> determineQuaysToAdd(StopPlace newStopPlace, StopPlace foundStopPlace) {

        logger.debug("About to compare quays for {}", foundStopPlace.getId());

        if (foundStopPlace.getQuays() == null) {
            foundStopPlace.setQuays(new ArrayList<>());
        }

        Set<Quay> quaysToAdd = new HashSet<>();
        if (foundStopPlace.getQuays().isEmpty() && newStopPlace.getQuays() != null) {
            logger.debug("Existing stop place {} does not have any quays, using all quays from incoming stop {}, {}",
                    foundStopPlace.getId(), newStopPlace.getId(), newStopPlace.getName());
            quaysToAdd.addAll(newStopPlace.getQuays());
        } else if (newStopPlace.getQuays() != null && !newStopPlace.getQuays().isEmpty()) {
            logger.debug("Existing stop {} has {} quays. Incoming stop {} has {} quays. Removing/ignoring quays that has matching coordinates",
                    foundStopPlace.getId(), foundStopPlace.getQuays().size(),
                    newStopPlace.getId(), newStopPlace.getQuays().size());

            for(Quay newQuay : newStopPlace.getQuays()) {

                Optional<Quay> existingQuay = findQuayWithCoordinates(newQuay, foundStopPlace.getQuays(), quaysToAdd);

                if(existingQuay.isPresent()) {
                    logger.debug("Found matching quay {} for incoming quay {}. Appending original ID to the key {}", existingQuay.get(), newQuay, NetexIdMapper.ORIGINAL_ID_KEY);
                    keyValueAppender.appendToOriginalId(NetexIdMapper.ORIGINAL_ID_KEY, newQuay, existingQuay.get());
                } else {
                    logger.debug("Incoming quay {} does not match any existing quays for stop place {}. Adding it.", newQuay, foundStopPlace);
                    quaysToAdd.add(newQuay);
                }
            }
        }
        logger.debug("Found {} quays to add to existing stop place {}, {}",  quaysToAdd.size(), foundStopPlace.getId(), foundStopPlace.getName());
        return quaysToAdd;
    }

    /**
     * Find first matching quay that has the same coordinates as the new Quay.
     */
    public Optional<Quay> findQuayWithCoordinates(Quay newQuay, Collection<Quay> existingQuays, Collection<Quay> quaysToAdd) {
        return Stream.concat(existingQuays.stream(), quaysToAdd.stream())
                .filter(alreadyAddedOrExistingQuay-> hasSameCoordinates(alreadyAddedOrExistingQuay, newQuay))
                .findFirst();
    }

    public boolean hasSameCoordinates(Quay quay1, Quay quay2) {
        if (quay1.getCentroid() == null || quay2.getCentroid() == null) {
            return false;
        }
        return (quay1.getCentroid().getLocation().getGeometryPoint()
                .distance(quay2.getCentroid().getLocation().getGeometryPoint()) == 0.0);
    }



}
