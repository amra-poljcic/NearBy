package com.personal.nearby.model;

import java.util.UUID;

public class Category {
    private final UUID id;
    private final String name;

    public Category(final String name) {
        this(null, name);
    }

    public Category(final UUID id, final String name) {
        this.id = id;
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
