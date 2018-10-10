package com.assesment.campaigns.repository;

import com.assesment.campaigns.CampaignsApplication;
import com.assesment.campaigns.domain.Campaign;
import com.assesment.campaigns.service.CampaignService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataMongoTest
@ContextConfiguration(classes = {CampaignsApplication.class, CampaignService.class})
public class CampaignRepositoryTest {

    @Autowired
    private CampaignRepository campaignRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    @Before
    public void clear() throws Exception {
        mongoTemplate.dropCollection(Campaign.class);
    }

    @Test
    public void saveCampaign_returnCampaignDetails_andDelete_expectNoCampaign() throws Exception {
        Campaign savedCampaign = new Campaign(100000004L, "Test Ad", "Increase Reach", 120, "Delivering", null);
        campaignRepository.save(savedCampaign);
        Campaign campaign = campaignRepository.findById(100000004L);

        assertThat(campaign.getId()).isEqualTo(savedCampaign.getId());
        assertThat(campaign.getName()).isEqualTo(savedCampaign.getName());
        assertThat(campaign.getGoal()).isEqualTo(savedCampaign.getGoal());
        assertThat(campaign.getTotalBudget()).isEqualTo(savedCampaign.getTotalBudget());
        assertThat(campaign.getStatus()).isEqualTo(savedCampaign.getStatus());
        assertThat(campaign.getPlatforms()).isNull();

        campaignRepository.delete(campaign);
        Campaign campaignAfterDelete = campaignRepository.findById(100000004L);
        assertThat(campaignAfterDelete).isNull();
    }

    @Test
    public void saveAllCampaigns_returnAllCampaignDetails_andDeleteAll_expectNoCampaign() throws Exception {
        List<Campaign> savedCampaigns = Arrays.asList(new Campaign(100000005L, "Test Ad", "Increase Reach", 120, "Delivering", null), new Campaign(100000006L, "Test Ad 2", "Raise Awareness", 360, "Ended", null));
        campaignRepository.saveAll(savedCampaigns);
        List<Campaign> campaigns = campaignRepository.findAll();

        assertThat(campaigns.size()).isEqualTo(savedCampaigns.size());
        assertThat(campaigns.get(0).getId()).isEqualTo(savedCampaigns.get(0).getId());
        assertThat(campaigns.get(1).getId()).isEqualTo(savedCampaigns.get(1).getId());

        campaignRepository.deleteAll();
        List<Campaign> campaignsAfterDelete = campaignRepository.findAll();
        assertThat(campaignsAfterDelete.size()).isEqualTo(0);
    }
}
