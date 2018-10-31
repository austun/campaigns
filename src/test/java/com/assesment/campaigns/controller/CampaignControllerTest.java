package com.assesment.campaigns.controller;

import com.assesment.campaigns.domain.Campaign;
import com.assesment.campaigns.exception.CampaignNotFoundException;
import com.assesment.campaigns.service.CampaignService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CampaignController.class)
public class CampaignControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CampaignService campaignService;

    @Test
    public void getCampaign_shouldReturnCampaign() throws Exception {
        given(campaignService.getCampaign(anyLong())).willReturn(new Campaign(100000001L, "Test Ad", "Increase Reach", 120, "Delivering", null));
        mockMvc.perform(MockMvcRequestBuilders.get("/campaign/100000001")).andExpect(status().isOk())
                .andExpect(jsonPath("id").value(100000001L))
                .andExpect(jsonPath("name").value("Test Ad"))
                .andExpect(jsonPath("goal").value("Increase Reach"))
                .andExpect(jsonPath("total_budget").value(120))
                .andExpect(jsonPath("status").value("Delivering"))
                .andExpect(jsonPath("platforms").doesNotExist());
    }

    @Test
    public void getAllCampaign_shouldReturnAllCampaigns() throws Exception {
        List<Campaign> campaigns = Arrays.asList(new Campaign(100000001L, "Test Ad", "Increase Reach", 120, "Delivering", null), new Campaign(100000002L, "Test Ad 2", "Raise Awareness", 360, "Ended", null));
        given(campaignService.getAllCampaigns()).willReturn(campaigns);
        mockMvc.perform(MockMvcRequestBuilders.get("/campaign/all")).andExpect(status().isOk())
                .andExpect((jsonPath("$", hasSize(campaigns.size()))))
                .andExpect(jsonPath("$[0].id").value(100000001L))
                .andExpect(jsonPath("$[1].id").value(100000002L));
    }

    @Test
    public void getCampaign_notFound() throws Exception {
        given(campaignService.getCampaign(anyLong())).willThrow(new CampaignNotFoundException());
        mockMvc.perform(MockMvcRequestBuilders.get("/campaign/100000001")).andExpect(status().isNotFound());
    }

    @Test
    public void getAllCampaigns_notFound() throws Exception {
        given(campaignService.getAllCampaigns()).willThrow(new CampaignNotFoundException());
        mockMvc.perform(MockMvcRequestBuilders.get("/campaign/all")).andExpect(status().isNotFound());
    }
}
