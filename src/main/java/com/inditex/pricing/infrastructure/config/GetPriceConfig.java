package com.inditex.pricing.infrastructure.config;

import com.inditex.pricing.application.getprice.GetFinalPriceHandler;
import com.inditex.pricing.application.getprice.GetPriceRepository;
import com.inditex.pricing.application.getprice.GetPriceUseCase;
import com.inditex.pricing.application.getprice.GetPriceValidator;
import com.inditex.pricing.infrastructure.adapters.repositories.GetPriorityPriceFromJpaAdapter;
import com.inditex.pricing.infrastructure.adapters.validators.GetPriceDummyValidator;
import com.inditex.pricing.infrastructure.persistence.jpa.repositories.JpaPriceRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GetPriceConfig {

    @Bean
    public GetPriceUseCase getFinalPrice(GetPriceValidator validator,
                                         GetPriceRepository repository) {
        return new GetFinalPriceHandler(validator, repository);
    }

    @Bean GetPriceValidator getDummyPriceValidator() {
        return new GetPriceDummyValidator();
    }

    @Bean GetPriceRepository getPriceJpaAdapter(JpaPriceRepository repository) {
        return new GetPriorityPriceFromJpaAdapter(repository);
    }
}
