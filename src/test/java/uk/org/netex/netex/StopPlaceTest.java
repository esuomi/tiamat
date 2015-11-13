package uk.org.netex.netex;

import no.rutebanken.tiamat.TiamatApplication;
import no.rutebanken.tiamat.repository.ifopt.StopPlaceRepository;
import no.rutebanken.tiamat.repository.ifopt.TariffZoneRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TiamatApplication.class)
public class StopPlaceTest {

    @Inject
    private StopPlaceRepository stopPlaceRepository;

    @Inject
    private TariffZoneRepository tariffZoneRepository;

    @Test
    public void persistStopPlace() {

        StopPlace stopPlace = new StopPlace();
        stopPlace.setPublicCode("public-code");
        MultilingualString shortName = new MultilingualString();
        shortName.setLang("no");
        shortName.setValue("Skjervik");
        stopPlace.setShortName(shortName);
        stopPlace.setPublicCode("publicCode");
        stopPlace.getId();

        stopPlace.setStopPlaceType(StopTypeEnumeration.RAIL_STATION);

        stopPlace.setTransportMode(VehicleModeEnumeration.RAIL);
        stopPlace.setAirSubmode(AirSubmodeEnumeration.UNDEFINED);
        stopPlace.setCoachSubmode(CoachSubmodeEnumeration.REGIONAL_COACH);
        stopPlace.setFunicularSubmode(FunicularSubmodeEnumeration.UNKNOWN);
        stopPlace.getOtherTransportModes().add(VehicleModeEnumeration.AIR);

        StopPlace anotherStopPlace = new StopPlace();
        anotherStopPlace.setStopPlaceType(StopTypeEnumeration.BUS_STATION);

        stopPlaceRepository.save(anotherStopPlace);

        StopPlaceReference stopPlaceReference = new StopPlaceReference();
        stopPlaceReference.setReference(anotherStopPlace);

        stopPlace.setParentStopPlaceReference(stopPlaceReference);

        TariffZone tariffZone = new TariffZone();
        tariffZone.setShortName(new MultilingualString("V2", "no", "type"));

        tariffZoneRepository.save(tariffZone);

        assertThat(tariffZone.getId()).isNotEmpty();

        TariffZoneRef tariffZoneRef = new TariffZoneRef();
        tariffZoneRef.setCreated(new Date());
        tariffZoneRef.setChanged(new Date());
        tariffZoneRef.setReference(tariffZone);

        List<TariffZoneRef> tariffZoneRefs = new ArrayList<>();
        tariffZoneRefs.add(tariffZoneRef);
        stopPlace.setTariffZones(tariffZoneRefs);

        stopPlace.setWeighting(InterchangeWeightingEnumeration.RECOMMENDED_INTERCHANGE);

        stopPlaceRepository.save(stopPlace);


        StopPlace actualStopPlace = stopPlaceRepository.findById(stopPlace.getId());

        assertThat(actualStopPlace.getPublicCode()).isEqualTo(stopPlace.getPublicCode());
        assertThat(actualStopPlace.getStopPlaceType()).isEqualTo(stopPlace.getStopPlaceType());

        assertThat(actualStopPlace.getId()).isEqualTo(stopPlace.getId());
        assertThat(actualStopPlace.getTransportMode()).isEqualTo(stopPlace.getTransportMode());
        assertThat(actualStopPlace.getAirSubmode()).isEqualTo(stopPlace.getAirSubmode());
        assertThat(actualStopPlace.getCoachSubmode()).isEqualTo(stopPlace.getCoachSubmode());
        assertThat(actualStopPlace.getFunicularSubmode()).isEqualTo(stopPlace.getFunicularSubmode());
        assertThat(actualStopPlace.getOtherTransportModes()).contains(VehicleModeEnumeration.AIR);
        assertThat(actualStopPlace.getTariffZones()).isNotEmpty();
        assertThat(actualStopPlace.getWeighting()).isEqualTo(stopPlace.getWeighting());
        assertThat(actualStopPlace.getTariffZones().get(0).getChanged()).hasSameTimeAs(tariffZoneRef.getChanged());
        assertThat(actualStopPlace.getParentStopPlaceReference().getReference().getId()).isEqualTo(anotherStopPlace.getId());
    }

    @Test
    public void findAllStopPlaces() {
        stopPlaceRepository.findAll();
    }
}