package com.inditex.pricing.application.getprice;

import com.inditex.pricing.domain.exceptions.ValidatorException;

public interface GetPriceValidator {
    void validate(GetPriceQuery query) throws ValidatorException;
}
