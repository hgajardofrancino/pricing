package com.inditex.pricing.infrastructure.adapters.repositories;

import com.inditex.pricing.application.getprice.GetPriceQuery;
import com.inditex.pricing.application.getprice.GetPriceRepository;
import com.inditex.pricing.domain.models.Price;
import com.inditex.pricing.infrastructure.persistence.jpa.entities.PriceEntity;
import com.inditex.pricing.infrastructure.persistence.jpa.mappers.PriceEntityMapper;
import com.inditex.pricing.infrastructure.persistence.jpa.repositories.JpaPriceRepository;

import java.util.Comparator;
import java.util.Optional;

public class GetPriorityPriceFromJpaAdapter implements GetPriceRepository {

    private final JpaPriceRepository jpaRepository;

    public GetPriorityPriceFromJpaAdapter(JpaPriceRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Optional<Price> get(GetPriceQuery query) {

        /*
         * return jpaRepository.findPriorityPrice(
                        query.productId(),
                        query.brandId(),
                        query.date().atStartOfDay()
                )
                .map(PriceEntityMapper::toDomain); */

        return jpaRepository.findPrices(
                        query.productId(),
                        query.brandId(),
                        query.date().atStartOfDay()
                )
                .stream()
                .max(Comparator.comparingInt(PriceEntity::getPriority))
                .map(PriceEntityMapper::toDomain);
    }
}
