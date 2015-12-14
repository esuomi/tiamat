package no.rutebanken.tiamat.repository.ifopt;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import no.rutebanken.tiamat.TiamatApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import uk.org.netex.netex.SimplePoint;
import uk.org.netex.netex.StopPlace;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TiamatApplication.class)
@ActiveProfiles("geodb")
public class StopPlaceRepositoryImplTest {

    @Autowired
    private StopPlaceRepository stopPlaceRepository;

    @Autowired
    private GeometryFactory geometryFactory;

    @Test
    public void testFindStopPlacesWithin() throws Exception {
        StopPlace stopPlace = new StopPlace();
        SimplePoint centroid = new SimplePoint();

        double southEastLatitude = 59.875649;
        double southEastLongitude = 10.500340;

        double northWestLatitude = 59.875924;
        double northWestLongitude = 10.500699;

        double latitude = 59.875679;
        double longitude = 10.500430;

        centroid.setLocation(geometryFactory.createPoint(new Coordinate(longitude, latitude)));

        stopPlace.setCentroid(centroid);
        stopPlaceRepository.save(stopPlace);

        List<StopPlace> result = stopPlaceRepository.findStopPlacesWithin(southEastLongitude, southEastLatitude, northWestLongitude, northWestLatitude);

        assertThat(result).isNotEmpty();
        assertThat(result.get(0).getId()).isEqualTo(stopPlace.getId());
    }
}