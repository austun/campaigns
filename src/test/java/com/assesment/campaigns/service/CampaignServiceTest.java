package com.assesment.campaigns.service;

import com.assesment.campaigns.domain.Campaign;
import com.assesment.campaigns.repository.CampaignRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CampaignServiceTest {

    @Mock
    private CampaignRepository campaignRepository;

    private CampaignService campaignService;

    @Before
    public void setUp() throws Exception {
        this.campaignService = new CampaignService(campaignRepository);
    }

    @Test
    public void getCampaign_returnCampaignInfo() throws Exception {
        given(campaignRepository.findById(100000001L)).willReturn(new Campaign(100000001L, "Test Ad", "Increase Reach", 120, "Delivering", null));
        Campaign campaign = campaignService.getCampaign(100000001L);

        assertThat(campaign.getId()).isEqualTo(100000001L);
        assertThat(campaign.getName()).isEqualTo("Test Ad");
        assertThat(campaign.getGoal()).isEqualTo("Increase Reach");
        assertThat(campaign.getTotalBudget()).isEqualTo(120);
        assertThat(campaign.getStatus()).isEqualTo("Delivering");
        assertThat(campaign.getPlatforms()).isNull();
    }

    @Test
    public void getAllCampaigns_returnAllCampaignsInfo() throws Exception {
        List<Campaign> campaigns = Arrays.asList(new Campaign(100000001L, "Test Ad", "Increase Reach", 120, "Delivering", null), new Campaign(100000002L, "Test Ad 2", "Raise Awareness", 360, "Ended", null));
        given(campaignRepository.findAll()).willReturn(campaigns);
        List<Campaign> campaignList = campaignService.getAllCampaigns();

        assertThat(campaignList.size()).isEqualTo(campaigns.size());
        assertThat(campaignList.get(0).getId()).isEqualTo(campaigns.get(0).getId());
        assertThat(campaignList.get(1).getId()).isEqualTo(campaigns.get(1).getId());
    }

    @Test
    public void deleteAllCampaigns_returnNoCampaignInfo() throws Exception {
        campaignService.deleteAll();
        verify(campaignRepository,times(1)).deleteAll();
    }
}
