package michal.malek.futurumtask.integration;

import michal.malek.futurumtask.model.response.GeoApiTownsResponse;
import michal.malek.futurumtask.service.GeoApiService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class GeoApiServiceIntegrationTest {

    @Autowired
    private GeoApiService geoApiService;

    @Test
    public void testGetCitiesIntegration() {
        GeoApiTownsResponse response = geoApiService.getCities("PL", 10000, 0);
        assertNotNull(response);
        assertThat(response.getMetadata().getTotalCount()).isGreaterThan(1000);
        response.getTowns().forEach(System.out::println);
    }
}