package no.rutebanken.tiamat.dtoassembling.disassembler;

import no.rutebanken.tiamat.dtoassembling.dto.StopPlaceDto;
import no.rutebanken.tiamat.model.MultilingualString;
import no.rutebanken.tiamat.model.SimplePoint;
import no.rutebanken.tiamat.model.StopPlace;
import no.rutebanken.tiamat.model.StopTypeEnumeration;
import no.rutebanken.tiamat.repository.StopPlaceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Disassembles the StopPlaceDto to update a StopPlace.
 */
@Component
public class StopPlaceDisassembler {

    private static final Logger logger = LoggerFactory.getLogger(StopPlaceDisassembler.class);

    @Autowired
    private SimplePointDisassembler simplePointDisassembler;

    @Autowired
    private StopPlaceRepository stopPlaceRepository;


    @Autowired
    private QuayDisassembler quayDisassembler;

    public StopPlace disassemble(StopPlace destination, StopPlaceDto simpleStopPlaceDto) {

        if(destination == null) {
            return null;
        }

        logger.debug("Disassemble simpleStopPlaceDto with id {}", simpleStopPlaceDto.id);

        destination.setName(new MultilingualString(simpleStopPlaceDto.name, "no", ""));
        destination.setChanged(ZonedDateTime.now());
        destination.setShortName(new MultilingualString(simpleStopPlaceDto.shortName, "no", ""));
        destination.setDescription(new MultilingualString(simpleStopPlaceDto.description, "no", ""));

        destination.setStopPlaceType(Optional.ofNullable(simpleStopPlaceDto.stopPlaceType)
                .filter(type -> !type.isEmpty())
                .map(StopTypeEnumeration::fromValue)
                .orElse(null));

        if(simpleStopPlaceDto.centroid != null) {
            if (destination.getCentroid() == null) {
                destination.setCentroid(new SimplePoint());
            }

            destination.setCentroid(simplePointDisassembler.disassemble(simpleStopPlaceDto.centroid));
        }

        destination.setAllAreasWheelchairAccessible(simpleStopPlaceDto.allAreasWheelchairAccessible);


        destination.setQuays(simpleStopPlaceDto.quays
                .stream()
                .filter(Objects::nonNull)
                .map(quay -> quayDisassembler.disassemble(quay))
                .collect(Collectors.toList()));

       return destination;

    }
}
