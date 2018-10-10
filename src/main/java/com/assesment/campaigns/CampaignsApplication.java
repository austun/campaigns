package com.assesment.campaigns;

import com.assesment.campaigns.service.CampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CampaignsApplication implements CommandLineRunner {

	@Autowired
	private CampaignService campaignService;

	public static void main(String[] args) {
		SpringApplication.run(CampaignsApplication.class, args);
	}

	@Override
	public void run(String[] strings) {
		campaignService.deleteAll();
		campaignService.prePopulateMongo();
	}
}
