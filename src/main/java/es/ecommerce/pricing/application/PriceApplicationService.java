package es.ecommerce.pricing.application;

import es.ecommerce.pricing.domain.model.PriceResponse;
import es.ecommerce.pricing.domain.model.Prices;
import es.ecommerce.pricing.domain.port.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PriceApplicationService {
    private final PriceService priceService;

    @Autowired
    public PriceApplicationService(@Qualifier("priceAdapterService") PriceService priceService) {
        this.priceService = priceService;
    }


    // Método para obtener todos los precios
    public List<Prices> getAllPrices() {
        return priceService.getAllPrices();
    }


    // Parsear la fecha y llamar al método del dominio
    public PriceResponse getPriceByApplicationDate(String applicationDate, String productId, long brandId) {

        LocalDateTime parsedApplicationDate = LocalDateTime.parse(applicationDate);

        return priceService.getPriceByApplicationDate(parsedApplicationDate, productId, brandId);
    }
}