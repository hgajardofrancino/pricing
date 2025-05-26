package com.inditex.pricing.infrastructure.adapters.validators;

import com.inditex.pricing.application.getprice.GetPriceQuery;
import com.inditex.pricing.application.getprice.GetPriceValidator;
import com.inditex.pricing.domain.exceptions.ValidatorException;

public class GetPriceDummyValidator implements GetPriceValidator {
    @Override
    public void validate(GetPriceQuery query) throws ValidatorException {
        return;
    }
}
