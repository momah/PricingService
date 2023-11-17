package es.ecommerce.pricing.adapter.repository;

import es.ecommerce.pricing.domain.model.Prices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PriceRepository extends JpaRepository<Prices, Long> {

    @Query("SELECT p FROM Prices p " +
            "WHERE p.productId = :productId " +
            "AND p.brandId = :brandId " +
            "AND :applicationDate BETWEEN p.startDate AND p.endDate")
    List<Prices> findPricesByProductAndBrandAndDate(
            @Param("productId") String productId,
            @Param("brandId") long brandId,
            @Param("applicationDate") LocalDateTime applicationDate
    );
}