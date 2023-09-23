package com.personal.nearby.model;

import java.time.Instant;
import java.util.UUID;

public class PriceHistory {
    private final UUID id;
    private final Product product;
    private final double price;
    private final Instant timestamp;

    public PriceHistory(final Product product, final double price) {
        this(null, product, price, Instant.now());
    }

    public PriceHistory(final UUID id, final Product product, final double price, final Instant timestamp) {
        this.id = id;
        this.product = product;
        this.price = price;
        this.timestamp = timestamp;
    }

    public UUID getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public double getPrice() {
        return price;
    }

    public Instant getTimestamp() {
        return timestamp;
    }
}
