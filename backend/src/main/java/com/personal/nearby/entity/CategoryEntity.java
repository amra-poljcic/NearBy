package com.personal.nearby.entity;

import com.personal.nearby.model.Category;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "category")
public class CategoryEntity {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    public CategoryEntity() {
    }

    public CategoryEntity(final UUID id, final String name) {
        this.id = id;
        this.name = name;
    }

    public static CategoryEntity fromDomainModel(final Category category) {
        return new CategoryEntity(category.getId(), category.getName());
    }

    public Category toDomainModel() {
        return new Category(id, name);
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
}
