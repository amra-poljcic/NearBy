package com.personal.nearby.service.impl;

import com.personal.nearby.entity.PriceHistoryEntity;
import com.personal.nearby.model.PriceHistory;
import com.personal.nearby.repository.PriceHistoryRepository;
import com.personal.nearby.service.api.PriceHistoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PriceHistoryServiceImpl implements PriceHistoryService {
    private final PriceHistoryRepository priceHistoryRepository;

    public PriceHistoryServiceImpl(final PriceHistoryRepository priceHistoryRepository) {
        this.priceHistoryRepository = priceHistoryRepository;
    }

    @Override
    public Page<PriceHistory> listByProductId(final UUID id, final Pageable pageable) {
        return priceHistoryRepository.findAllByProductId(id, pageable).map(PriceHistoryEntity::toDomainModel);
    }

    @Override
    public PriceHistory save(final PriceHistory priceHistory) {
        return priceHistoryRepository.save(PriceHistoryEntity.fromDomainModel(priceHistory)).toDomainModel();
    }
}
