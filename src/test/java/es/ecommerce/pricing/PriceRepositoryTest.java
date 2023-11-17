package es.ecommerce.pricing;



import es.ecommerce.pricing.adapter.repository.PriceRepository;
import es.ecommerce.pricing.domain.model.Prices;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class PriceRepositoryTest {
    @Autowired
    private PriceRepository priceRepository;


    @Test
    public void testPriceRepository() {
        if (this.priceRepository.findAll().size() > 0) {
            this.priceRepository.deleteAll();
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-H.mm.ss");
        LocalDateTime start_date = LocalDateTime.parse("2020-06-14-00.00.00", formatter);
        LocalDateTime end_date = LocalDateTime.parse("2020-12-31-23.59.59", formatter);
        Prices prices1 = new Prices(1L, 1L, start_date, end_date, 1, "35455", 0, 35.5, "EUR");
        this.priceRepository.save(prices1);
        start_date = LocalDateTime.parse("2020-06-14-15.00.00", formatter);
        end_date = LocalDateTime.parse("2020-06-14-18.30.00", formatter);
        Prices prices2 = new Prices(2L, 1L, start_date, end_date, 2, "35456", 0, 25.45, "EUR");
        this.priceRepository.save(prices2);
        start_date = LocalDateTime.parse("2020-06-15-00.00.00", formatter);
        end_date = LocalDateTime.parse("2020-06-15-11.00.00", formatter);
        Prices prices3 = new Prices(3L, 1L, start_date, end_date, 3, "35455", 0, 30.5, "EUR");
        this.priceRepository.save(prices3);
        start_date = LocalDateTime.parse("2020-06-15-16.00.00", formatter);
        end_date = LocalDateTime.parse("2020-12-31-23.59.59", formatter);
        Prices prices4 = new Prices(4L, 1L, start_date, end_date, 4, "35456", 0, 38.95, "EUR");
        this.priceRepository.save(prices4);
        List<Prices> prices = this.priceRepository.findAll();
        Assertions.assertNotNull(prices1, "No se ha creado el precio");
        Assertions.assertFalse(prices.isEmpty(), "No se han guardado precios");
        Assertions.assertEquals(4, prices.size(), "No se han guardado los precios que se han creado, ahora hay: " + prices.size());
    }



    @Test
    public void testUpdatePrice() {
        // Obtener un precio existente con otro ID (en este caso, ID 2)
        Prices existingPrice = priceRepository.findById(2L).orElse(null);
        Assertions.assertNotNull(existingPrice);

        // Modificar el precio
        existingPrice.setPrice(40.0);
        priceRepository.save(existingPrice);

        // Verificar si se ha actualizado correctamente
        Prices updatedPrice = priceRepository.findById(2L).orElse(null);
        Assertions.assertNotNull(updatedPrice);
        Assertions.assertEquals(40.0, updatedPrice.getPrice());
    }

    @Test
    public void testDeletePrice() {
        // Eliminar un precio existente
        priceRepository.deleteById(1L);

        // Verificar que se haya eliminado
        Prices deletedPrice = priceRepository.findById(1L).orElse(null);
        Assertions.assertNull(deletedPrice);
    }

}