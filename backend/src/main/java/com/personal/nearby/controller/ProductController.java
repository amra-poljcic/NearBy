package com.personal.nearby.controller;

import com.personal.nearby.controller.params.ProductParams;
import com.personal.nearby.model.Category;
import com.personal.nearby.model.PriceHistory;
import com.personal.nearby.model.Product;
import com.personal.nearby.request.ProductRequest;
import com.personal.nearby.service.api.CategoryService;
import com.personal.nearby.service.api.PriceHistoryService;
import com.personal.nearby.service.api.ProductService;
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
@RequestMapping("api/v1/product")
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;
    private final PriceHistoryService priceHistoryService;

    public ProductController(final ProductService productService,
                             final CategoryService categoryService,
                             final PriceHistoryService priceHistoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.priceHistoryService = priceHistoryService;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('product:read')")
    public Page<Product> list(final ProductParams params, final Pageable pageable) {
        return productService.list(
                params.name(),
                params.categoryIds(),
                params.minPrice(),
                params.maxPrice(),
                params.gpsCoordinates(),
                params.excludeIds(),
                pageable
        );
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('product:read')")
    public Product getById(@PathVariable final UUID id) {
        return productService.getById(id);
    }

    @GetMapping("/{id}/price-history")
    @PreAuthorize("hasAuthority('product:read')")
    public Page<PriceHistory> listPriceHistory(@PathVariable final UUID id, final Pageable pageable) {
        return priceHistoryService.listByProductId(id, pageable);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('product:write')")
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
    @PreAuthorize("hasAuthority('product:write')")
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
    @PreAuthorize("hasAuthority('product:write')")
    public void delete(@PathVariable final UUID id) {
        productService.delete(id);
    }
}
