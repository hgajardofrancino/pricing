package com.inditex.pricing.infrastructure.controllers;

import com.inditex.pricing.application.getprice.GetPriceQuery;
import com.inditex.pricing.application.getprice.GetPriceUseCase;
import com.inditex.pricing.infrastructure.adapters.presenters.GetPriceRestPresenter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/prices")
public class GetPriceController {

    private final GetPriceUseCase getPriceUseCase;

    public GetPriceController(GetPriceUseCase getPriceUseCase) {
        this.getPriceUseCase = getPriceUseCase;
    }

    @GetMapping
    public ResponseEntity<?> getPrice(
            @RequestParam Long productId,
            @RequestParam Long brandId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date) {

        GetPriceQuery query = new GetPriceQuery(productId, brandId, date);

        GetPriceRestPresenter getPricePresenter = new GetPriceRestPresenter();

        getPriceUseCase.execute(query, getPricePresenter);

        return getPricePresenter.getResponse();
    }
}
