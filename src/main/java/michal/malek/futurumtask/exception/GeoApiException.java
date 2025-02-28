package michal.malek.futurumtask.exception;

import org.springframework.http.HttpStatusCode;

public class GeoApiException extends RuntimeException {
    public GeoApiException(HttpStatusCode statusCode) {
        super();
    }
}
