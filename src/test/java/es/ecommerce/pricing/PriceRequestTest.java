package es.ecommerce.pricing;


import es.ecommerce.pricing.domain.model.PriceRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class PriceRequestTest {

    @Test
    void testPriceRequestToString() {
        LocalDateTime applicationDate = LocalDateTime.of(2023, 11, 14, 10, 0, 0);
        PriceRequest request = new PriceRequest(applicationDate, "35455", 1L);
        String expectedToString = "Prices{, applicationDate=2023-11-14T10:00, product_id=35455', brandId=1}";
        assertEquals(expectedToString, request.toString());
    }

    @Test
    void testPriceRequestGettersAndSetters() {
        LocalDateTime applicationDate = LocalDateTime.of(2023, 11, 14, 10, 0, 0);
        PriceRequest request = new PriceRequest();
        request.setApplicationDate(applicationDate);
        request.setProductId("35455");
        request.setBrandId(1L);

        Assertions.assertEquals(applicationDate, request.getApplicationDate());
        Assertions.assertEquals("35455", request.getProductId());
        Assertions.assertEquals(1L, request.getBrandId());
    }
}
