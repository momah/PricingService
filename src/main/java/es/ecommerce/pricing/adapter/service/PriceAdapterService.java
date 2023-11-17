package es.ecommerce.pricing.adapter.service;

import es.ecommerce.pricing.adapter.repository.PriceRepository;
import es.ecommerce.pricing.domain.model.PriceResponse;
import es.ecommerce.pricing.domain.model.Prices;
import es.ecommerce.pricing.domain.port.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Service
@Primary
public class PriceAdapterService implements PriceService {
    private final PriceRepository priceRepository;

    @Autowired
    public PriceAdapterService(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    @Override
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
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "PriceAdapterService error: ", exc);
        }
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

    private Prices selectPrice(List<Prices> pricesList) {
        // Filtrar los precios con la fecha más cercana y luego obtener el de mayor prioridad
        List<Prices> filteredPrices = pricesList.stream()
                .sorted(Comparator.comparing(Prices::getPriority).reversed())
                .toList();

        return filteredPrices.get(0); // Tomar el precio con mayor prioridad después de ordenar
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
