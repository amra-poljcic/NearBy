package com.personal.nearby.request;

import java.util.UUID;

public record ProductRequest(String name, String description, UUID categoryId, double price, String gpsCoordinates,
                             String image) {
}
