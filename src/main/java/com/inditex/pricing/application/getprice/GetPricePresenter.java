package com.inditex.pricing.application.getprice;

import com.inditex.pricing.application.common.NotFoundPresenter;
import com.inditex.pricing.application.common.SuccessPresenter;
import com.inditex.pricing.application.common.SystemErrorPresenter;
import com.inditex.pricing.domain.models.Price;

public interface GetPricePresenter
        extends SuccessPresenter<Price>,
                NotFoundPresenter,
                SystemErrorPresenter {

    Object getResponse();

}
