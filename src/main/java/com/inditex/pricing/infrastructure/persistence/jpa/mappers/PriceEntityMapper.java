package com.inditex.pricing.infrastructure.persistence.jpa.mappers;

import com.inditex.pricing.domain.models.BrandId;
import com.inditex.pricing.domain.models.Price;
import com.inditex.pricing.domain.models.PriceList;
import com.inditex.pricing.domain.models.ProductId;
import com.inditex.pricing.infrastructure.persistence.jpa.entities.PriceEntity;

public class PriceEntityMapper {

    public static PriceEntity fromDomain(Price price) {
        return new PriceEntity(
                price.getPriceList().id(),
                price.getBrandId().id(),
                price.getProductId().id(),
                price.getAmount(),
                price.getCurrency(),
                price.getStartDate(),
                price.getEndDate(),
                price.getPriority()
        );
    }

    public static Price toDomain(PriceEntity entity) {
        return new Price(
                new PriceList(entity.getPriceList()),
                new ProductId(entity.getProductId()),
                new BrandId(entity.getBrandId()),
                entity.getAmount(),
                entity.getCurrency(),
                entity.getStartDate(),
                entity.getEndDate(),
                entity.getPriority()
        );
    }
}
