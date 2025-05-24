package com.inditex.pricing.application.getprice;

import com.inditex.pricing.domain.models.BrandId;
import com.inditex.pricing.domain.models.Price;
import com.inditex.pricing.domain.models.PriceList;
import com.inditex.pricing.domain.models.ProductId;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class GetPriceResponseTest {

    @Test
    void givenValidPrice_whenFromDomain_thenReturnCorrectResponse() {

        LocalDateTime startDate = LocalDateTime.parse("2023-06-14T00:00:00");
        LocalDateTime endDate = LocalDateTime.parse("2023-06-14T23:59:59");
        Price price = new Price(
                new PriceList(1L),
                new ProductId(1L),
                new BrandId(1L),
                BigDecimal.valueOf(44.5),
                "eur",
                startDate,
                endDate,
                1
        );

        GetPriceResponse response = GetPriceResponse.fromDomain(price);

        assertAll(
                () -> assertEquals(1L, response.productId()),
                () -> assertEquals(1L, response.brandId()),
                () -> assertEquals(1L, response.priceList()),
                () -> assertEquals(startDate, response.startDate()),
                () -> assertEquals(endDate, response.endDate()),
                () -> assertEquals(new BigDecimal("44.5"), response.amount()),
                () -> assertEquals("EUR", response.currency())
        );
    }

}