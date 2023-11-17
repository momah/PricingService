package es.ecommerce.pricing;


import es.ecommerce.pricing.adapter.repository.PriceRepository;
import es.ecommerce.pricing.domain.port.PriceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
class ContextLoadTest {
    @Autowired
    private PriceRepository priceRepository;
    @Autowired
    private PriceService priceService;

    @Test
    void contextLoad() {
        Assert.notNull(this.priceRepository, "Repository is null");
        Assert.notNull(this.priceService, "Service is null");
    }

    @Test
    void priceRepositoryInstanceTest() {
        Assert.isTrue(this.priceRepository != null, "Invalid instance of PriceRepository");
    }

    @Test
    void priceServiceInstanceTest() {
        Assert.isTrue(this.priceService != null, "Invalid instance of PriceService");
    }

}