package es.ecommerce.pricing.adapter.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import es.ecommerce.pricing.application.PriceApplicationService;
import es.ecommerce.pricing.domain.model.PriceResponse;
import es.ecommerce.pricing.domain.model.Prices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping({"/api"})
public class PriceController {
    private final PriceApplicationService priceApplicationService;

    public PriceController(PriceApplicationService priceApplicationService) {
        this.priceApplicationService = priceApplicationService;
    }

    // Método para verificar si la API está funcionando correctamente
    @RequestMapping
    @ResponseBody
    public String runningOk() {
        return "Test API ok";
    }


    // Método para obtener todos los precios
    @GetMapping({"/allPrices"})
    public ResponseEntity<List<Prices>> getAll() {
        List<Prices> pricesList = priceApplicationService.getAllPrices();
        return new ResponseEntity<>(pricesList, HttpStatus.OK);
    }

    @GetMapping("/price")
    public ResponseEntity<PriceResponse> getPrice(
            @RequestParam String applicationDate,
            @RequestParam String productId,
            @RequestParam long brandId) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss");
            LocalDateTime parsedApplicationDate = LocalDateTime.parse(applicationDate, formatter);

            PriceResponse priceResponse = priceApplicationService.getPriceByApplicationDate(String.valueOf(parsedApplicationDate), productId, brandId);

            return new ResponseEntity<>(priceResponse, HttpStatus.OK);
        } catch (Exception exc) {
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "PriceController error: ", exc);
        }
    }
}
