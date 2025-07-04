package com.inditex.pricing.application.getprice;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class GetPriceQueryTest {

    @Test
    void givenQueryWithNullParameters_whenExecute_thenThrowException() {
        assertThrows(NullPointerException.class, () ->
                new GetPriceQuery(null, null, null)
        );
    }

    @Test
    void givenQueryWithNullParameter_WhenInstanced_thenThrowException() {
        assertAll(
                () -> assertThrows(NullPointerException.class, () ->
                        new GetPriceQuery(null, 1L, LocalDateTime.now())),
                () -> assertThrows(NullPointerException.class, () ->
                        new GetPriceQuery(1L, null, LocalDateTime.now())),
                () -> assertThrows(NullPointerException.class, () ->
                        new GetPriceQuery(1L, 1L, null))
        );
    }
}