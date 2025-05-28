package com.inditex.pricing.application.getprice;

import com.inditex.pricing.domain.exceptions.ValidatorException;
import com.inditex.pricing.domain.models.BrandId;
import com.inditex.pricing.domain.models.Price;
import com.inditex.pricing.domain.models.PriceList;
import com.inditex.pricing.domain.models.ProductId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import static org.mockito.Mockito.*;

class GetFinalPriceHandlerTest {

    private GetPricePresenter presenter;
    private GetPriceValidator validator;
    private GetPriceRepository repository;
    private GetFinalPriceHandler handler;


    @BeforeEach
    void setUp() {
        presenter = Mockito.mock(GetPricePresenter.class);
        validator = Mockito.mock(GetPriceValidator.class);
        repository = Mockito.mock(GetPriceRepository.class);
        handler = new GetFinalPriceHandler(validator, repository);
    }

    private GetPriceQuery createValidQuery() {
        return new GetPriceQuery(
                1L,
                35455L,
                LocalDateTime.parse("2023-06-14T00:00:00")
        );
    }

    private Price createValidPrice() {
        return new Price (
                new PriceList(1L),
                new ProductId(1L),
                new BrandId(1L),
                BigDecimal.valueOf(2.5),
                "EUR",
                LocalDateTime.parse("2024-10-02T00:00:00"),
                LocalDateTime.parse("2024-10-07T00:00:00"),
                1
        );
    }


    @Test
    void givenValidQuery_whenExecute_thenPresentPrice() throws ValidatorException {

        GetPriceQuery query = createValidQuery();
        Price expectedPrice = createValidPrice();

        when(repository.get(query)).thenReturn(Optional.of(expectedPrice));

        handler.execute(query, presenter);

        verify(validator, times(1)).validate(query);
        verify(repository).get(query);
        verify(presenter).presentSuccess(expectedPrice);
        verify(presenter, never()).presentNotFound(anyString());
        verify(presenter, never()).presentSystemError(anyString());
    }

    @Test
    void givenValidQuery_whenPriceNotFound_thenPresentNotFound() {
        GetPriceQuery query = createValidQuery();
        when(repository.get(query)).thenReturn(Optional.empty());

        handler.execute(query, presenter);

        verify(presenter).presentNotFound(contains("Price not found for"));
        verify(presenter, never()).presentSuccess(any());
        verify(presenter, never()).presentSystemError(anyString());
    }

    @Test
    void givenValidationFails_whenExecute_thenPresentSystemError() throws ValidatorException {
        GetPriceQuery query = createValidQuery();

        doThrow(new IllegalArgumentException("Invalid query"))
                .when(validator).validate(query);

        handler.execute(query, presenter);

        verify(validator).validate(query);
        verify(repository, never()).get(any());
        verify(presenter).presentSystemError("Failed to get price: Invalid query");
    }

    @Test
    void givenRepositoryThrowsException_whenExecute_thenPresentSystemError() {

        GetPriceQuery query = createValidQuery();

        when(repository.get(query)).thenThrow(new RuntimeException("Database error"));

        handler.execute(query, presenter);

        verify(presenter).presentSystemError(contains("Failed to get price: Database error"));
        verify(presenter, never()).presentSuccess(any());
        verify(presenter, never()).presentNotFound(anyString());
    }

    @Test
    void givenNullQuery_whenExecute_thenPresentSystemError() {
        handler.execute(null, presenter);
        verify(presenter).presentSystemError(contains("Failed to get price"));
    }
}