package es.ecommerce.pricing.domain.port;

import es.ecommerce.pricing.domain.model.PriceResponse;
import es.ecommerce.pricing.domain.model.Prices;

import java.time.LocalDateTime;
import java.util.List;

public interface PriceService {

    List<Prices> getAllPrices();
    PriceResponse getPriceByApplicationDate(LocalDateTime applicationDate, String productId, long brandId);
}
