package com.personal.nearby.service.impl;

import com.personal.nearby.entity.PriceHistoryEntity;
import com.personal.nearby.model.PriceHistory;
import com.personal.nearby.repository.PriceHistoryRepository;
import com.personal.nearby.service.api.PriceHistoryService;
import org.springframework.stereotype.Service;

@Service
public class PriceHistoryServiceImpl implements PriceHistoryService {
    private final PriceHistoryRepository priceHistoryRepository;

    public PriceHistoryServiceImpl(final PriceHistoryRepository priceHistoryRepository) {
        this.priceHistoryRepository = priceHistoryRepository;
    }

    @Override
    public PriceHistory save(final PriceHistory priceHistory) {
        return priceHistoryRepository.save(PriceHistoryEntity.fromDomainModel(priceHistory)).toDomainModel();
    }
}
