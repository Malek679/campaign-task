package michal.malek.futurumtask.model.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CampaignRequest {
    @NotBlank(message = "name cannot be blank")
    private String campaignName;

    @NotEmpty(message = "keywords cannot be empty")
    private List<KeywordRequest> keywords;

    @Min(value = 1, message = "bid amount must be more than 0")
    private int bidAmount;

    @Min(value = 1, message = "campaign found must be more than 0")
    private int campaignFound;

    private boolean status;

    @NotNull(message = "town must be chosen")
    private TownRequest town;

    @Min(value = 0, message = "radius found must be more than 0")
    private int radius;
}
