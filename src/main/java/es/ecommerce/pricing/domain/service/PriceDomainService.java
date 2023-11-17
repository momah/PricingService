package es.ecommerce.pricing.domain.service;

import es.ecommerce.pricing.adapter.repository.PriceRepository;
import es.ecommerce.pricing.domain.model.PriceResponse;
import es.ecommerce.pricing.domain.model.Prices;
import es.ecommerce.pricing.domain.port.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PriceDomainService implements PriceService {
    private final PriceRepository priceRepository;

    @Autowired
    public PriceDomainService(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    @Override
    @Transactional(readOnly = true) // Agregar anotación para operaciones de solo lectura
    public List<Prices> getAllPrices() {
        try {
            return priceRepository.findAll(); // Utiliza el método findAll de tu repositorio para obtener todos los precios
        } catch (Exception exc) {
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Error fetching all prices", exc);
        }
    }

    @Override
    @Transactional(readOnly = true) // Agregar anotación para operaciones de solo lectura
    public PriceResponse getPriceByApplicationDate(LocalDateTime applicationDate, String productId, long brandId) {
        try {
            List<Prices> pricesList = priceRepository.findPricesByProductAndBrandAndDate(productId, brandId, applicationDate);

            if (!pricesList.isEmpty()) {
                Prices selectedPrice = selectPrice(pricesList); // Aquí se llama al método para seleccionar el precio adecuado
                return mapPricesToPriceResponse(selectedPrice); // Mapear el objeto Prices a PriceResponse
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No price found for the given parameters");
            }
        } catch (Exception exc) {
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "PriceDomainService error: ", exc);
        }
    }

    private Prices selectPrice(List<Prices> pricesList) {
        // Lógica para seleccionar el precio (en este caso, selecciona el precio más alto)
        Prices selectedPrice = pricesList.get(0);
        for (Prices price : pricesList) {
            if (price.getPrice() > selectedPrice.getPrice()) {
                selectedPrice = price;
            }
        }
        return selectedPrice;
    }

    // Método para mapear el objeto Prices a PriceResponse
    private PriceResponse mapPricesToPriceResponse(Prices prices) {
        return new PriceResponse(
                prices.getProductId(),
                prices.getBrandId(),
                prices.getPriceList(),
                prices.getStartDate(),
                prices.getEndDate(),
                prices.getPrice(),
                prices.getCurr()
        );
    }
}