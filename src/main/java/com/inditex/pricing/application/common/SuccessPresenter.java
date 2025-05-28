package com.inditex.pricing.application.common;

import com.inditex.pricing.domain.models.Price;

public interface SuccessPresenter<T> {
    void presentSuccess(T data);
}
