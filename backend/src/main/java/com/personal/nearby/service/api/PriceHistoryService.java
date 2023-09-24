package com.personal.nearby.service.api;

import com.personal.nearby.model.PriceHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface PriceHistoryService {
    Page<PriceHistory> listByProductId(final UUID id, final Pageable pageable);

    PriceHistory save(final PriceHistory priceHistory);
}
