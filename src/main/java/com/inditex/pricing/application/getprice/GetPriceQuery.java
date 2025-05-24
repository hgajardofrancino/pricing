package com.inditex.pricing.application.getprice;

import java.time.LocalDate;
import java.util.Objects;

public record GetPriceQuery(Long productId,
                            Long brandId,
                            LocalDate date) {
    public GetPriceQuery {
        Objects.requireNonNull(productId, "Product ID cannot be null");
        Objects.requireNonNull(brandId, "Brand ID cannot be null");
        Objects.requireNonNull(date, "Date cannot be null");
    }
}
