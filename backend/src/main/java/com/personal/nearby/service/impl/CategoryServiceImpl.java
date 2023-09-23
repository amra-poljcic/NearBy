package com.personal.nearby.service.impl;

import com.personal.nearby.entity.CategoryEntity;
import com.personal.nearby.model.Category;
import com.personal.nearby.repository.CategoryRepository;
import com.personal.nearby.service.api.CategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(final CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Page<Category> list(final Pageable pageable) {
        return categoryRepository.findAll(pageable).map(CategoryEntity::toDomainModel);
    }

    @Override
    public Optional<Category> findById(final UUID id) {
        return categoryRepository.findById(id).map(CategoryEntity::toDomainModel);
    }

    @Override
    public Category save(final Category category) {
        return categoryRepository.save(CategoryEntity.fromDomainModel(category)).toDomainModel();
    }

    @Override
    public Category update(final UUID id, final Category category) {
        final CategoryEntity categoryEntity = categoryRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);
        categoryEntity.setName(category.getName());

        return categoryRepository.save(categoryEntity).toDomainModel();
    }

    @Override
    public void delete(final UUID id) {
        categoryRepository.deleteById(id);
    }
}
