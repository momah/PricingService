package es.ecommerce.pricing;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PriceControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testRequest_At10AM_onDay14_Product35455_Brand1() {
        ResponseEntity<String> response = restTemplate.getForEntity("/api/price?applicationDate=2020-06-14-10.00.00&productId=35455&brandId=1", String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testRequest_At4PM_onDay14_Product35455_Brand1() {
        ResponseEntity<String> response = restTemplate.getForEntity("/api/price?applicationDate=2020-06-14-16.00.00&productId=35455&brandId=1", String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testRequest_At9PM_onDay14_Product35455_Brand1() {
        ResponseEntity<String> response = restTemplate.getForEntity("/api/price?applicationDate=2020-06-14-21.00.00&productId=35455&brandId=1", String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testRequest_At10AM_onDay15_Product35455_Brand1() {
        ResponseEntity<String> response = restTemplate.getForEntity("/api/price?applicationDate=2020-06-15-10.00.00&productId=35455&brandId=1", String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testRequest_At9PM_onDay16_Product35455_Brand1() {
        ResponseEntity<String> response = restTemplate.getForEntity("/api/price?applicationDate=2020-06-16-21.00.00&productId=35455&brandId=1", String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}