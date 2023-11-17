package es.ecommerce.pricing;


import es.ecommerce.pricing.application.PriceApplicationService;
import es.ecommerce.pricing.domain.model.PriceResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@SpringBootTest
public class PriceServiceTest {

    @Autowired
    private PriceApplicationService priceApplicationService;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss");


    @Test
    public void testPriceAtSpecificTime1() {

        // Test 1: 10:00 del día 14
        LocalDateTime applicationDate1 = LocalDateTime.parse("2020-06-14-10.00.00", formatter);
        PriceResponse priceResponse1 = priceApplicationService.getPriceByApplicationDate(String.valueOf(applicationDate1), "35455", 1);
        Assertions.assertEquals(35.5, priceResponse1.getPrice(), "Incorrect price for " + applicationDate1);
    }

    @Test
    public void testPriceAtSpecificTime2() {
        // Test 2: 16:00 del día 14
        LocalDateTime applicationDate2 = LocalDateTime.parse("2020-06-14-16.00.00", formatter);
        PriceResponse priceResponse2 = priceApplicationService.getPriceByApplicationDate(String.valueOf(applicationDate2), "35455", 1);
        Assertions.assertEquals(25.45, priceResponse2.getPrice(), "Incorrect price for " + applicationDate2);
    }

    @Test
    public void testPriceAtSpecificTime3() {
        DateTimeFormatter formatter33 = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss");
        // Test 3: 21:00 del día 14
        LocalDateTime applicationDate3 = LocalDateTime.parse("2020-06-14-21.00.00", formatter);
        PriceResponse priceResponse3 = priceApplicationService.getPriceByApplicationDate(String.valueOf(applicationDate3), "35455", 1);
        Assertions.assertEquals(35.5, priceResponse3.getPrice(), "Incorrect price for " + applicationDate3);
    }

    @Test
    public void testPriceAtSpecificTime4() {
        // Test 4: 10:00 del día 15
        LocalDateTime applicationDate4 = LocalDateTime.parse("2020-06-15-10.00.00", formatter);
        PriceResponse priceResponse4 = priceApplicationService.getPriceByApplicationDate(String.valueOf(applicationDate4), "35455", 1);
        Assertions.assertEquals(30.5, priceResponse4.getPrice(), "Incorrect price for " + applicationDate4);
    }

    @Test
    public void testPriceAtSpecificTime5() {
        // Test 5: 21:00 del día 16
        LocalDateTime applicationDate5 = LocalDateTime.parse("2020-06-16-21.00.00", formatter);
        PriceResponse priceResponse5 = priceApplicationService.getPriceByApplicationDate(String.valueOf(applicationDate5), "35455", 1);
        Assertions.assertEquals(38.95, priceResponse5.getPrice(), "Incorrect price for " + applicationDate5);
    }


}