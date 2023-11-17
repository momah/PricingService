package es.ecommerce.pricing.domain.model;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class PriceResponse {
    private String productId;
    private long brandId;
    private int priceList;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private double price;

    private String curr;


    public PriceResponse() {
    }

    public PriceResponse(String productId, long brandId, int priceList, LocalDateTime startDate, LocalDateTime endDate, double price, String curr) {
        this.productId = productId;
        this.brandId = brandId;
        this.priceList = priceList;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
        this.curr = curr;
    }

    public String toString() {
        return "Prices{, product_id=" + this.productId + ", brandId=" + this.brandId + ", price_list=" + this.priceList + ", start_date=" + this.startDate + ", end_date=" + this.endDate + ", price=" + this.price + "'}";
    }
}

