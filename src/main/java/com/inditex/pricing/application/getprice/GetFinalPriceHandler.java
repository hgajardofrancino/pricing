package com.inditex.pricing.application.getprice;

public class GetFinalPriceHandler implements GetPriceUseCase {

    private final GetPriceValidator getPriceValidator;
    private final GetPriceRepository repository;

    public GetFinalPriceHandler(GetPriceValidator getPriceValidator,
                                GetPriceRepository repository) {
        this.getPriceValidator = getPriceValidator;
        this.repository = repository;
    }

    @Override
    public void execute(GetPriceQuery query, GetPricePresenter getPricePresenter) {

        try {
            getPriceValidator.validate(query);

            repository
                    .get(query)
                    .ifPresentOrElse(
                            getPricePresenter::presentSuccess,
                            () -> {
                                String message = String.format("Price not found for brand_id = %d, product_id = %d, date = %s",
                                        query.brandId(),
                                        query.productId(),
                                        query.date().toString());

                                getPricePresenter.presentNotFound(message);
                            }
                    );

        } catch (Exception e) {
            getPricePresenter.presentSystemError("Failed to get price: " + e.getMessage());
        }
    }
}
