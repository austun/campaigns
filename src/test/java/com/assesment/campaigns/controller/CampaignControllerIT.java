package com.assesment.campaigns.controller;

import com.assesment.campaigns.domain.Campaign;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CampaignControllerIT {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getCampaign_expectCampaign() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Campaign> entity = new HttpEntity<>(null, headers);

        ResponseEntity<Campaign> response = restTemplate.exchange("/campaign/100000001", HttpMethod.GET, entity, Campaign.class);

        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertNotNull(response.getBody());

        Assert.assertEquals(response.getBody().getId(), (Long) 100000001L);
        Assert.assertEquals(response.getBody().getName(), "Test Ad 1");
        Assert.assertEquals(response.getBody().getGoal(), "Increase Reach");
        Assert.assertEquals(response.getBody().getTotalBudget(), 120);
        Assert.assertEquals(response.getBody().getStatus(), "Delivering");
        Assert.assertNotNull(response.getBody().getPlatforms());
    }

    @Test
    public void getAllCampaigns_expectAllCampaigns() throws Exception {
        ResponseEntity<Campaign[]> response = restTemplate.getForEntity("/campaign/all", Campaign[].class);

        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertNotNull(response.getBody());

        Campaign[] campaigns = response.getBody();

        Assert.assertEquals(campaigns[0].getId(), (Long) 100000001L);
        Assert.assertEquals(campaigns[1].getId(), (Long) 100000002L);
        Assert.assertEquals(campaigns[2].getId(), (Long) 100000003L);
    }
}
