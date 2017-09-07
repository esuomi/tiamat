package org.rutebanken.tiamat.rest.graphql;

import com.vividsolutions.jts.geom.Coordinate;
import org.junit.Test;
import org.rutebanken.tiamat.model.*;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;

public class GraphQLResourceQuayMergerTest extends AbstractGraphQLResourceIntegrationTest {

    @Test
    public void testMergeQuaysUsingGraphQL() {

        StopPlace stopPlace = new StopPlace();
        stopPlace.setName(new EmbeddableMultilingualString("Name"));
        stopPlace.setCentroid(geometryFactory.createPoint(new Coordinate(11.1, 60.1)));
        stopPlace.getOriginalIds().add("TEST:StopPlace:1234");
        stopPlace.getOriginalIds().add("TEST:StopPlace:5678");

        Quay fromQuay = new Quay();
        fromQuay.setCompassBearing(new Float(90));
        fromQuay.setCentroid(geometryFactory.createPoint(new Coordinate(11.2, 60.2)));
        fromQuay.getOriginalIds().add("TEST:Quay:123401");
        fromQuay.getOriginalIds().add("TEST:Quay:567801");


        Quay toQuay = new Quay();
        toQuay.setCompassBearing(new Float(90));
        toQuay.setCentroid(geometryFactory.createPoint(new Coordinate(11.21, 60.21)));
        toQuay.getOriginalIds().add("TEST:Quay:432101");
        toQuay.getOriginalIds().add("TEST:Quay:876501");

        Quay quayToKeepUnaltered = new Quay();
        quayToKeepUnaltered.setCompassBearing(new Float(180));
        quayToKeepUnaltered.setCentroid(geometryFactory.createPoint(new Coordinate(11.211, 60.211)));
        quayToKeepUnaltered.getOriginalIds().add("TEST:Quay:432102");

        stopPlace.getQuays().add(fromQuay);
        stopPlace.getQuays().add(toQuay);
        stopPlace.getQuays().add(quayToKeepUnaltered);

        stopPlace = saveStopPlaceTransactional(stopPlace);

        assertThat(stopPlace.getQuays()).hasSize(3);

        //Calling GraphQL-api to merge Quays
        String graphQlJsonQuery = "{" +
                "\"query\":\"mutation { " +
                "  stopPlace: mergeQuays (" +
                "          stopPlaceId:\\\"" + stopPlace.getNetexId() + "\\\", " +
                "          fromQuayId:\\\"" + fromQuay.getNetexId() + "\\\"" +
                "          toQuayId:\\\"" + toQuay.getNetexId() + "\\\"" +
                "       ) { " +
                "  id " +
                "  importedId " +
                "  name { value } " +
                "  quays {" +
                "    id " +
                "    geometry { type coordinates } " +
                "    compassBearing " +
                "    importedId " +
                "  } " +
                " } " +
                "}\",\"variables\":\"\"}";


        Set<String> originalIds = new HashSet<>();
        originalIds.addAll(toQuay.getOriginalIds());
        originalIds.addAll(fromQuay.getOriginalIds());

        executeGraphQL(graphQlJsonQuery)
                .body("data.stopPlace.id", comparesEqualTo(stopPlace.getNetexId()))
                .body("data.stopPlace.quays", hasSize(2))
                .root("data.stopPlace.quays.find { it.id == '" +toQuay.getNetexId() + "'}")
                .body("importedId", containsInAnyOrder(originalIds.toArray()))
                .root("data.stopPlace.quays.find { it.id == '" + quayToKeepUnaltered.getNetexId() + "'}")
                .body("importedId", containsInAnyOrder(quayToKeepUnaltered.getOriginalIds().toArray()))
        ;
    }

}
