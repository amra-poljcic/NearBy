package com.personal.nearby.service.impl;

import com.personal.nearby.entity.CategoryEntity;
import com.personal.nearby.entity.ProductEntity;
import com.personal.nearby.model.PriceHistory;
import com.personal.nearby.model.Product;
import com.personal.nearby.repository.ProductRepository;
import com.personal.nearby.service.api.PriceHistoryService;
import com.personal.nearby.service.api.ProductService;
import org.locationtech.jts.geom.Point;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Set;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final PriceHistoryService priceHistoryService;

    public ProductServiceImpl(final ProductRepository productRepository,
                              final PriceHistoryService priceHistoryService) {
        this.productRepository = productRepository;
        this.priceHistoryService = priceHistoryService;
    }

    @Override
    public Page<Product> list(final String name,
                              final Set<UUID> categoryIds,
                              final Double minPrice,
                              final Double maxPrice,
                              final Point gpsCoordinates,
                              final Set<UUID> excludeIds,
                              final Pageable pageable) {
        return productRepository.findAll(
                        name,
                        categoryIds == null ? Collections.emptySet() : categoryIds,
                        minPrice,
                        maxPrice,
                        gpsCoordinates,
                        excludeIds == null ? Collections.emptySet() : excludeIds,
                        pageable
                )
                .map(ProductEntity::toDomainModel);
    }

    @Override
    public Product getById(final UUID id) {
        final ProductEntity productEntity = productRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);

        productEntity.setViews(productEntity.getViews() + 1);

        return productRepository.save(productEntity).toDomainModel();
    }

    @Override
    public Product save(final Product product) {
        return productRepository.save(ProductEntity.fromDomainModel(product)).toDomainModel();
    }

    @Override
    public Product update(final UUID id, final Product product) {
        final ProductEntity productEntity = productRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);

        if (Double.compare(productEntity.getPrice(), product.getPrice()) != 0) {
            priceHistoryService.save(new PriceHistory(productEntity.toDomainModel(), productEntity.getPrice()));
        }

        productEntity.setName(product.getName());
        productEntity.setDescription(product.getDescription());
        productEntity.setCategory(CategoryEntity.fromDomainModel(product.getCategory()));
        productEntity.setPrice(product.getPrice());
        productEntity.setGpsCoordinates(product.getGpsCoordinates());
        productEntity.setImage(product.getImage());

        return productRepository.save(productEntity).toDomainModel();
    }

    @Override
    public void delete(final UUID id) {
        productRepository.deleteById(id);
    }
}
