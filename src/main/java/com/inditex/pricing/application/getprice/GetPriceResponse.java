package com.inditex.pricing.application.getprice;

import com.inditex.pricing.domain.models.Price;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record GetPriceResponse(Long productId,
                               Long brandId,
                               Long priceList,
                               LocalDateTime startDate,
                               LocalDateTime endDate,
                               BigDecimal amount,
                               String currency) {
    public static GetPriceResponse fromDomain(Price price) {
        return new GetPriceResponse(
                price.getProductId().id(),
                price.getBrandId().id(),
                price.getPriceList().id(),
                price.getStartDate(),
                price.getEndDate(),
                price.getAmount(),
                price.getCurrency().toUpperCase()
        );
    }
}
