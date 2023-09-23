package com.personal.nearby.util;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.PrecisionModel;

public class GeometryUtil {
    private static final GeometryFactory GEOMETRY_FACTORY = new GeometryFactory(new PrecisionModel(), 4326);

    public static Point createPoint(final double lat, final double lon) {
        return GEOMETRY_FACTORY.createPoint(new Coordinate(lat, lon));
    }
}
