package com.inditex.pricing.domain.models;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Data
public class Price {
    private PriceList priceList;
    private ProductId productId;
    private BrandId brandId;
    private BigDecimal amount;
    private String currency;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Integer priority;

    public Price(PriceList priceList,
                 ProductId productId,
                 BrandId brandId,
                 BigDecimal amount,
                 String currency,
                 LocalDateTime startDate,
                 LocalDateTime endDate,
                 Integer priority) {

        this.priceList = Objects.requireNonNull(priceList);
        this.productId = Objects.requireNonNull(productId);
        this.brandId = Objects.requireNonNull(brandId);
        this.amount = Objects.requireNonNull(amount);
        this.currency = Objects.requireNonNull(currency);
        this.startDate = Objects.requireNonNull(startDate);
        this.endDate = Objects.requireNonNull(endDate);
        this.priority = Objects.requireNonNull(priority);

        validate();
    }

    private void validate() {
        if (endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("End date must be after start date");
        }
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Price amount must be greater than zero");
        }
    }

    public boolean isActiveAt(LocalDateTime date) {
        return !date.isBefore(startDate) && !date.isAfter(endDate);
    }
}
