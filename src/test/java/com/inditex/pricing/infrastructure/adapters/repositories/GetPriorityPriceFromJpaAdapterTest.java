package com.inditex.pricing.infrastructure.adapters.repositories;

import com.inditex.pricing.application.getprice.GetPriceQuery;
import com.inditex.pricing.domain.models.Price;
import com.inditex.pricing.infrastructure.persistence.jpa.entities.PriceEntity;
import com.inditex.pricing.infrastructure.persistence.jpa.repositories.JpaPriceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class GetPriorityPriceFromJpaAdapterTest {

    private JpaPriceRepository jpaRepository;
    private GetPriorityPriceFromJpaAdapter adapter;

    @BeforeEach
    void setUp() {
        jpaRepository = Mockito.mock(JpaPriceRepository.class);
        adapter = new GetPriorityPriceFromJpaAdapter(jpaRepository);
    }

    private PriceEntity createTestEntity(Long priceList,
                                         BigDecimal amount,
                                         Integer priority) {
        return new PriceEntity(
                priceList,
                1L,
                1L,
                amount,
                "EUR",
                LocalDateTime.parse("2025-05-14T00:00:00"),
                LocalDateTime.parse("2025-06-14T23:59:59"),
                priority
        );
    }

    @Test
    void givenListPrices_whenGet_thenReturnsHighestPriorityPrice() {

        GetPriceQuery query = new GetPriceQuery(1L, 1L, LocalDate.parse("2025-06-10"));

        PriceEntity lowPriority = createTestEntity(1L, new BigDecimal("35.50"), 1);
        PriceEntity middlePriority = createTestEntity(2L, new BigDecimal("68.95"), 2);
        PriceEntity highPriority = createTestEntity(3L, new BigDecimal("38.95"), 3);

        when(jpaRepository.findPrices(anyLong(), anyLong(), any()))
                .thenReturn(List.of(lowPriority, highPriority, middlePriority));


        Optional<Price> result = adapter.get(query);


        assertTrue(result.isPresent());
        assertEquals(new BigDecimal("38.95"), result.get().getAmount());
        verify(jpaRepository, times(1)).findPrices(
                eq(1L),
                eq(1L),
                eq(LocalDate.parse("2025-06-10").atStartOfDay())
        );
    }

    @Test
    void givenSinglePrice_whenGet_thenReturnsPrice() {

        GetPriceQuery query = new GetPriceQuery(1L, 1L, LocalDate.parse("2025-06-10"));
        PriceEntity singlePrice = createTestEntity(1L, new BigDecimal("35.50"), 1);

        when(jpaRepository.findPrices(anyLong(), anyLong(), any()))
                .thenReturn(List.of(singlePrice));

        Optional<Price> result = adapter.get(query);

        assertTrue(result.isPresent());
        assertEquals(new BigDecimal("35.50"), result.get().getAmount());
        verify(jpaRepository, times(1)).findPrices(
                eq(1L),
                eq(1L),
                eq(LocalDate.parse("2025-06-10").atStartOfDay())
        );
    }

    @Test
    void givenEmptyList_whenGet_thenReturnsEmpty() {
        GetPriceQuery query = new GetPriceQuery(1L, 1L, LocalDate.parse("2025-06-10"));

        when(jpaRepository.findPrices(anyLong(), anyLong(), any()))
                .thenReturn(List.of());

        Optional<Price> result = adapter.get(query);

        assertTrue(result.isEmpty());
    }

    @Test
    void givenNullQuery_whenGet_thenThrowsException() {
        assertThrows(NullPointerException.class, () -> adapter.get(null));
        verifyNoInteractions(jpaRepository);
    }

    @Test
    void givenEqualPriorities_whenGet_thenReturnsFirstFound() {
        GetPriceQuery query = new GetPriceQuery(35455L, 1L, LocalDate.parse("2023-06-14"));

        PriceEntity firstPrice = createTestEntity(1L, new BigDecimal("35.50"), 1);
        PriceEntity secondPrice = createTestEntity(2L, new BigDecimal("38.95"), 1);

        when(jpaRepository.findPrices(anyLong(), anyLong(), any()))
                .thenReturn(List.of(firstPrice, secondPrice));

        Optional<Price> result = adapter.get(query);

        assertTrue(result.isPresent());
        assertEquals(new BigDecimal("35.50"), result.get().getAmount());
    }
}