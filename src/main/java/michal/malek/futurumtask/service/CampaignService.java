package michal.malek.futurumtask.service;

import lombok.RequiredArgsConstructor;
import michal.malek.futurumtask.exception.CampaignNotFoundException;
import michal.malek.futurumtask.mapper.KeywordMapper;
import michal.malek.futurumtask.mapper.TownMapper;
import michal.malek.futurumtask.model.entity.Campaign;
import michal.malek.futurumtask.model.request.CampaignRequest;
import michal.malek.futurumtask.repository.CampaignRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CampaignService {
    private final CampaignRepository campaignRepository;
    private final KeywordMapper keywordMapper;
    private final TownMapper townMapper;

    public List<Campaign> getCampaigns() {
        return campaignRepository.findAll();
    }

    public Campaign getCampaignById(Long id) {
        return campaignRepository.findById(id)
                .orElseThrow(() -> new CampaignNotFoundException(id));
    }

    public void createCampaign(CampaignRequest campaign) {
        Campaign build = Campaign.builder()
                .campaignName(campaign.getCampaignName())
                .campaignFound(campaign.getCampaignFound())
                .keywords(keywordMapper.map(campaign.getKeywords()))
                .bidAmount(campaign.getBidAmount())
                .campaignFound(campaign.getCampaignFound())
                .status(campaign.isStatus())
                .town(townMapper.RequestToTown(campaign.getTown()))
                .radius(campaign.getRadius())
                .build();

        campaignRepository.saveAndFlush(build);
    }

    public Campaign updateCampaign(Long id, CampaignRequest campaignRequest) {
        Campaign existingCampaign = campaignRepository.findById(id)
                .orElseThrow(() -> new CampaignNotFoundException(id));

        existingCampaign.setCampaignName(campaignRequest.getCampaignName());
        existingCampaign.setCampaignFound(campaignRequest.getCampaignFound());
        existingCampaign.setKeywords(keywordMapper.map(campaignRequest.getKeywords()));
        existingCampaign.setBidAmount(campaignRequest.getBidAmount());
        existingCampaign.setStatus(campaignRequest.isStatus());
        existingCampaign.setTown(townMapper.RequestToTown(campaignRequest.getTown()));
        existingCampaign.setRadius(campaignRequest.getRadius());

        return campaignRepository.saveAndFlush(existingCampaign);
    }

    public Campaign partialUpdateCampaign(Long id, Map<String, Object> updates) {
        Campaign existingCampaign = campaignRepository.findById(id)
                .orElseThrow(() -> new CampaignNotFoundException(id));

        updates.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Campaign.class, key);
            if (field != null) {
                field.setAccessible(true);
                ReflectionUtils.setField(field, existingCampaign, value);
            }
        });

        return campaignRepository.saveAndFlush(existingCampaign);
    }

    public void deleteCampaign(Long id) {
        Campaign existingCampaign = campaignRepository.findById(id)
                .orElseThrow(() -> new CampaignNotFoundException(id));

        campaignRepository.delete(existingCampaign);
    }


}
