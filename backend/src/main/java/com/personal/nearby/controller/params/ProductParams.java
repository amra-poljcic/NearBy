package com.personal.nearby.controller.params;

import com.personal.nearby.util.GeometryUtil;
import org.locationtech.jts.geom.Point;

import java.util.Set;
import java.util.UUID;

public record ProductParams(String name, Set<UUID> categoryIds, Double minPrice, Double maxPrice, Double lat,
                            Double lon) {

    public Point gpsCoordinates() {
        if (lat == null || lon == null) {
            return null;
        }

        return GeometryUtil.createPoint(lat, lon);
    }
}
