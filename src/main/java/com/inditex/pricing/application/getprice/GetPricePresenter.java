package com.inditex.pricing.application.getprice;

import com.inditex.pricing.domain.models.Price;

public interface GetPricePresenter {
    void present(Price price);
    void presentNotFound(String message);
    void presentSystemError(String message);
}
