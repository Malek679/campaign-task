package michal.malek.futurumtask.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GeoApiTownsResponse {

    @JsonProperty("metadata")
    private Metadata metadata;
    @JsonProperty("data")
    private List<TownResponse> towns;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Metadata {
        private int currentOffset;
        private int totalCount;
    }
}
