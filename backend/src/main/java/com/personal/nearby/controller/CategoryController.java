package com.personal.nearby.controller;

import com.personal.nearby.model.Category;
import com.personal.nearby.request.CategoryRequest;
import com.personal.nearby.service.api.CategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/category")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(final CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('category:read')")
    public Page<Category> list(final Pageable pageable) {
        return categoryService.list(pageable);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('category:write')")
    public Category save(@RequestBody final CategoryRequest categoryRequest) {
        return categoryService.save(new Category(categoryRequest.name()));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('category:write')")
    public Category update(@PathVariable final UUID id, @RequestBody final CategoryRequest categoryRequest) {
        return categoryService.update(id, new Category(categoryRequest.name()));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('category:write')")
    public void delete(@PathVariable final UUID id) {
        categoryService.delete(id);
    }
}
