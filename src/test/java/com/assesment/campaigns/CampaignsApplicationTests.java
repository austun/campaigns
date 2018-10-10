package com.assesment.campaigns;

import com.assesment.campaigns.domain.Campaign;
import com.assesment.campaigns.service.CampaignService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CampaignsApplicationTests {

    @Autowired
    private CommandLineRunner commandLineRunner;

    @Autowired
    private CampaignService campaignService;

    @Test
    public void thatCommandLineRunnerDoesStuff() throws Exception {
        this.commandLineRunner.run();

        List<Campaign> campaigns = campaignService.getAllCampaigns();
        assertThat(campaigns).isNotEmpty();
        assertThat(campaigns.size()).isEqualTo(3);
    }
}
