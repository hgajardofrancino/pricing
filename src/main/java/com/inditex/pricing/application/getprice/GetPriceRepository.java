package com.inditex.pricing.application.getprice;

import com.inditex.pricing.domain.models.Price;

import java.time.LocalDate;
import java.util.Optional;

public interface GetPriceRepository {
    Optional<Price> get(GetPriceQuery query);
}
