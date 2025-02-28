package michal.malek.futurumtask.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import michal.malek.futurumtask.model.entity.Campaign;
import michal.malek.futurumtask.model.request.CampaignRequest;
import michal.malek.futurumtask.service.CampaignService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/campaigns")
@RequiredArgsConstructor
public class CampaignController {
    private final CampaignService campaignService;

    @GetMapping("/get-all")
    public ResponseEntity<List<Campaign>> getAllCampaigns() {
        List<Campaign> campaigns = campaignService.getCampaigns();
        return ResponseEntity.ok(campaigns);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Campaign> getCampaignById(@PathVariable Long id) {
        Campaign campaign = campaignService.getCampaignById(id);
        return ResponseEntity.ok(campaign);
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createCampaign(@RequestBody @Valid CampaignRequest campaignRequest) {
        campaignService.createCampaign(campaignRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Campaign> updateCampaign(@PathVariable Long id, @RequestBody @Valid CampaignRequest campaignRequest) {
        Campaign campaign = campaignService.updateCampaign(id, campaignRequest);
        return ResponseEntity.ok(campaign);
    }

    @PatchMapping("/patch/{id}")
    public ResponseEntity<Campaign> partialUpdateCampaign(
            @PathVariable Long id,
            @RequestBody Map<String, Object> updates) {

        Campaign updatedCampaign = campaignService.partialUpdateCampaign(id, updates);
        return ResponseEntity.ok(updatedCampaign);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCampaign(@PathVariable Long id) {
        campaignService.deleteCampaign(id);
        return ResponseEntity.noContent().build();
    }
}
