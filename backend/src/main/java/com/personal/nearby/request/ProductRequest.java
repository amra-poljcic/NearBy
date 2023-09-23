package com.personal.nearby.request;

import com.personal.nearby.util.GeometryUtil;
import org.locationtech.jts.geom.Point;

import java.util.UUID;

public record ProductRequest(String name, String description, UUID categoryId, double price, double lat, double lon,
                             String image) {

    public Point gpsCoordinates() {
        return GeometryUtil.createPoint(lat, lon);
    }
}
