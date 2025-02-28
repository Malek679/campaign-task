package michal.malek.futurumtask.service;

import lombok.RequiredArgsConstructor;
import michal.malek.futurumtask.exception.GeoApiException;
import michal.malek.futurumtask.model.response.GeoApiTownsResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;


@Service
@RequiredArgsConstructor
public class GeoApiService {

    private final RestTemplate restTemplate;

    @Value("${geoapi.base.url}")
    private String baseUrl;
    @Value("${geoapi.key}")
    private String apiKey;
    @Value("${geoapi.host}")
    private String apiHost;

    private final String KEY_HEADER_NAME = "X-RapidAPI-Key";
    private final String HOST_HEADER_NAME = "X-RapidAPI-Host";
    private final String COUNTRY_ID_PARAM_NAME = "countryIds";
    private final String MIN_POPULATION_PARAM_NAME = "minPopulation";
    private final String OFFSET_PARAM_NAME = "offset";
    private final String TYPE_PARAM_NAME = "types";
    private final String TYPE_PARAM_VALUE = "CITY";

    /**
     * This method collects cities from external API based on params. Can collect only 5 because of free plan.
     * @param countryId Country id, for Poland is "PL".
     * @param minPopulation min population.
     * @param offset offset to collect different towns in few requests.
     * @return returns DTO with collection of Towns and metadata from API.
     */
    public GeoApiTownsResponse getCities(String countryId, int minPopulation, int offset) throws GeoApiException {
        String url = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .queryParam(COUNTRY_ID_PARAM_NAME, countryId)
                .queryParam(MIN_POPULATION_PARAM_NAME, minPopulation)
                .queryParam(OFFSET_PARAM_NAME, offset)
                .queryParam(TYPE_PARAM_NAME, TYPE_PARAM_VALUE)
                .toUriString();

        HttpHeaders headers = new HttpHeaders();
        headers.set(KEY_HEADER_NAME, apiKey);
        headers.set(HOST_HEADER_NAME, apiHost);

        HttpEntity<?> entity = new HttpEntity<>(headers);

        ResponseEntity<GeoApiTownsResponse> response =
                restTemplate.exchange(url, HttpMethod.GET, entity, GeoApiTownsResponse.class);

        if(response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            throw new GeoApiException(response.getStatusCode());
        }
    }
}

