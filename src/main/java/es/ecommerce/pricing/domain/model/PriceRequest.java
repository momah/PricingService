package es.ecommerce.pricing.domain.model;

import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;
@Data
public class PriceRequest {
    private LocalDateTime applicationDate;
    private String productId;
    private long brandId;


    public PriceRequest() {
    }

    public PriceRequest(LocalDateTime applicationDate, String productId, long brandId) {
        this.applicationDate = applicationDate;
        this.productId = productId;
        this.brandId = brandId;
    }


    public String toString() {
        return "Prices{, applicationDate=" + this.applicationDate + ", product_id=" + this.productId + "', brandId=" + this.brandId + "}";
    }
}

