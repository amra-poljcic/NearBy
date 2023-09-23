package com.personal.nearby.repository;

import com.personal.nearby.entity.ProductEntity;
import org.locationtech.jts.geom.Point;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, UUID> {

    @Query(value = """
               SELECT * FROM (
                   SELECT *
                   FROM product
                   WHERE (:name IS NULL OR lower(name) LIKE '%' || lower(:name) || '%')
                     AND (:categoryIds IS NULL OR category_id IN :categoryIds)
                     AND (:priceMin IS NULL OR price >= :priceMin)
                     AND (:priceMax IS NULL OR price <= :priceMax)
                   ORDER BY CASE WHEN CAST(:gpsCoordinates AS geometry) IS NOT NULL THEN gps_coordinates <-> :gpsCoordinates ELSE 0 END
               ) sorted_product
            """,
            countQuery = """
               SELECT COUNT(*)
               FROM product
               WHERE (:name IS NULL OR lower(name) LIKE '%' || lower(:name) || '%')
                   AND (:categoryIds IS NULL OR category_id IN :categoryIds)
                   AND (:priceMin IS NULL OR price >= :priceMin)
                   AND (:priceMax IS NULL OR price <= :priceMax)
                   AND :gpsCoordinates = :gpsCoordinates
            """, nativeQuery = true)
    Page<ProductEntity> findAll(final String name,
                                final Set<UUID> categoryIds,
                                final Double priceMin,
                                final Double priceMax,
                                final Point gpsCoordinates,
                                final Pageable pageable);
}
