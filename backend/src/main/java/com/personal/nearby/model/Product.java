package com.personal.nearby.model;

import java.util.UUID;

public class Product {
    private final UUID id;
    private final String name;
    private final String description;
    private final Category category;
    private final double price;
    private final String gpsCoordinates;
    private final long views;
    private final String image;

    public Product(final String name,
                   final String description,
                   final Category category,
                   final double price,
                   final String gpsCoordinates,
                   final String image) {
        this(null, name, description, category, price, gpsCoordinates, 0, image);
    }

    public Product(final UUID id,
                   final String name,
                   final String description,
                   final Category category,
                   final double price,
                   final String gpsCoordinates,
                   final long views,
                   final String image) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.price = price;
        this.gpsCoordinates = gpsCoordinates;
        this.views = views;
        this.image = image;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Category getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public String getGpsCoordinates() {
        return gpsCoordinates;
    }

    public long getViews() {
        return views;
    }

    public String getImage() {
        return image;
    }
}
