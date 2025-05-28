package com.inditex.pricing.infrastructure.adapters.presenters;

import com.inditex.pricing.application.getprice.GetPricePresenter;
import com.inditex.pricing.application.getprice.GetPriceResponse;
import com.inditex.pricing.domain.models.Price;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class GetPriceRestPresenter implements GetPricePresenter {

    ResponseEntity<?> response;

    @Override
    public void presentSuccess(Price price) {
        this.response = ResponseEntity.ok(
                GetPriceResponse.fromDomain(price)
        );
    }

    @Override
    public void presentNotFound(String message) {

        this.response = ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(message);
    }

    @Override
    public void presentSystemError(String message) {
        this.response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(message);
    }

    @Override
    public ResponseEntity<?> getResponse() {
        return response;
    }
}
