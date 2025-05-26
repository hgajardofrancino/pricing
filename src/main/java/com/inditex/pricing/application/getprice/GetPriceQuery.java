package com.inditex.pricing.application.getprice;

import java.time.LocalDateTime;
import java.util.Objects;

public record GetPriceQuery(Long productId,
                            Long brandId,
                            LocalDateTime date) {
    public GetPriceQuery {
        Objects.requireNonNull(productId, "Product ID cannot be null");
        Objects.requireNonNull(brandId, "Brand ID cannot be null");
        Objects.requireNonNull(date, "Date cannot be null");
    }
}
