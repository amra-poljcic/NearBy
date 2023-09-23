package com.personal.nearby.service.api;

import com.personal.nearby.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface CategoryService {
    Page<Category> list(final Pageable pageable);

    Optional<Category> findById(final UUID id);

    Category save(final Category category);

    Category update(final UUID id, final Category category);

    void delete(final UUID id);
}
