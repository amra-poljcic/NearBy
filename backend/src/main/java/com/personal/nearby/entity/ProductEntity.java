package com.personal.nearby.entity;

import com.personal.nearby.model.Product;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import org.locationtech.jts.geom.Point;

import java.util.UUID;

@Entity
@Table(name = "product")
public class ProductEntity {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private CategoryEntity category;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "gpsCoordinates", nullable = false)
    private Point gpsCoordinates;

    @Column(name = "views", nullable = false)
    private long views;

    @Column(name = "image")
    private String image;

    public ProductEntity() {
    }

    public ProductEntity(final UUID id,
                         final String name,
                         final String description,
                         final CategoryEntity category,
                         final double price,
                         final Point gpsCoordinates,
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

    public static ProductEntity fromDomainModel(final Product product) {
        return new ProductEntity(
                product.getId(),
                product.getName(),
                product.getDescription(),
                CategoryEntity.fromDomainModel(product.getCategory()),
                product.getPrice(),
                product.getGpsCoordinates(),
                product.getViews(),
                product.getImage()
        );
    }

    public Product toDomainModel() {
        return new Product(id, name, description, category.toDomainModel(), price, gpsCoordinates, views, image);
    }

    public UUID getId() {
        return id;
    }

    public void setId(final UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(final CategoryEntity category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(final double price) {
        this.price = price;
    }

    public Point getGpsCoordinates() {
        return gpsCoordinates;
    }

    public void setGpsCoordinates(final Point gpsCoordinates) {
        this.gpsCoordinates = gpsCoordinates;
    }

    public long getViews() {
        return views;
    }

    public void setViews(final long views) {
        this.views = views;
    }

    public String getImage() {
        return image;
    }

    public void setImage(final String image) {
        this.image = image;
    }
}
