package com.inditex.pricing.domain.models;

import java.util.Objects;

public record PriceList(Long id) {
    public PriceList {
        Objects.requireNonNull(id, "Id value must be not null");
        if (id <= 0) {
            throw new IllegalArgumentException("Id value must be greater than zero");
        }
    }

}
