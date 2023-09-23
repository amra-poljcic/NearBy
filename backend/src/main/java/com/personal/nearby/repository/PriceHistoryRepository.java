package com.personal.nearby.repository;

import com.personal.nearby.entity.PriceHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PriceHistoryRepository extends JpaRepository<PriceHistoryEntity, UUID> {
}
