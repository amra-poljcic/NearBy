package com.personal.nearby.controller;

import com.personal.nearby.model.Category;
import com.personal.nearby.model.Product;
import com.personal.nearby.request.ProductRequest;
import com.personal.nearby.service.api.CategoryService;
import com.personal.nearby.service.api.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
@RequestMapping("api/v1/product")
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;

    public ProductController(final ProductService productService, final CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public Page<Product> list(final Pageable pageable) {
        return productService.list(pageable);
    }

    @GetMapping("/{id}")
    public Product getById(@PathVariable final UUID id) {
        return productService.getById(id);
    }

    @PostMapping
    public Product save(@RequestBody final ProductRequest productRequest) {
        final Category category = categoryService.findById(productRequest.categoryId())
                .orElseThrow(IllegalArgumentException::new);

        return productService.save(new Product(
                productRequest.name(),
                productRequest.description(),
                category,
                productRequest.price(),
                productRequest.gpsCoordinates(),
                productRequest.image()
        ));
    }

    @PutMapping("/{id}")
    public Product update(@PathVariable final UUID id, @RequestBody final ProductRequest productRequest) {
        final Category category = categoryService.findById(productRequest.categoryId())
                .orElseThrow(IllegalArgumentException::new);

        return productService.update(
                id,
                new Product(
                        productRequest.name(),
                        productRequest.description(),
                        category,
                        productRequest.price(),
                        productRequest.gpsCoordinates(),
                        productRequest.image()
                )
        );
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable final UUID id) {
        productService.delete(id);
    }
}
