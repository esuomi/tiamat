package no.rutebanken.tiamat.repository;

import com.vividsolutions.jts.geom.Point;
import org.springframework.cache.annotation.CachePut;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import no.rutebanken.tiamat.model.StopPlace;

public interface StopPlaceRepository extends PagingAndSortingRepository<StopPlace, String>, StopPlaceRepositoryCustom {

    Page<StopPlace> findAllByOrderByChangedDesc(Pageable pageable);

    StopPlace findByNameValueAndCentroidLocationGeometryPoint(String name, Point geometryPoint);

    Page<StopPlace> findByNameValueContainingIgnoreCaseOrderByChangedDesc(String name, Pageable pageable);

    @CachePut(value = "findNearbyStopPlace", key = "#p0.getName().getValue()")
    StopPlace save(StopPlace stopPlace);
}

