package com.personal.nearby.service.api;

import com.personal.nearby.model.Product;
import org.locationtech.jts.geom.Point;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Set;
import java.util.UUID;

public interface ProductService {
    Page<Product> list(final String name,
                       final Set<UUID> categoryIds,
                       final Double minPrice,
                       final Double maxPrice,
                       final Point gpsCoordinates,
                       final Set<UUID> excludeIds,
                       final Pageable pageable);

    Product getById(final UUID id);

    Product save(final Product product);

    Product update(final UUID id, final Product product);

    void delete(final UUID id);
}
