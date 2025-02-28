package michal.malek.futurumtask.model.response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TownResponse {
    private Long id;
    private String wikiDataId;
    private String type;
    private String city;
    private String name;
    private String country;
    private String countryCode;
    private String region;
    private String regionCode;
    private double latitude;
    private double longitude;
    private int population;
}
