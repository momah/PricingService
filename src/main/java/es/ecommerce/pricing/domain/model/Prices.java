package es.ecommerce.pricing.domain.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@Entity
public class Prices {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long brandId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private int priceList;
    private String productId;
    private int priority;
    private double price;
    private String curr;

    public Prices() {
    }

    public Prices(long id, long brandId, LocalDateTime startDate, LocalDateTime endDate, int priceList, String productId, int priority, double price, String curr) {
        this.id = id;
        this.brandId = brandId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.priceList = priceList;
        this.productId = productId;
        this.priority = priority;
        this.price = price;
        this.curr = curr;
    }


    @Override
    public String toString() {
        return "Prices{" +
                "id=" + id +
                ", brandId=" + brandId +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", priceList=" + priceList +
                ", productId='" + productId + '\'' +
                ", priority=" + priority +
                ", price=" + price +
                ", curr='" + curr + '\'' +
                '}';
    }


}