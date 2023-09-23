package com.personal.nearby.entity;

import com.personal.nearby.model.PriceHistory;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "price_history")
public class PriceHistoryEntity {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity product;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "timestamp", nullable = false)
    private Instant timestamp;

    public PriceHistoryEntity() {
    }

    public PriceHistoryEntity(final UUID id, final ProductEntity product, final double price, final Instant timestamp) {
        this.id = id;
        this.product = product;
        this.price = price;
        this.timestamp = timestamp;
    }

    public static PriceHistoryEntity fromDomainModel(final PriceHistory priceHistory) {
        return new PriceHistoryEntity(
                priceHistory.getId(),
                ProductEntity.fromDomainModel(priceHistory.getProduct()),
                priceHistory.getPrice(),
                priceHistory.getTimestamp()
        );
    }

    public PriceHistory toDomainModel() {
        return new PriceHistory(id, product.toDomainModel(), price, timestamp);
    }

    public UUID getId() {
        return id;
    }

    public void setId(final UUID id) {
        this.id = id;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(final ProductEntity product) {
        this.product = product;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(final double price) {
        this.price = price;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(final Instant timestamp) {
        this.timestamp = timestamp;
    }
}
