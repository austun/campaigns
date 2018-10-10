package com.assesment.campaigns.controller;

import com.assesment.campaigns.domain.Campaign;
import com.assesment.campaigns.exception.CampaignNotFoundException;
import com.assesment.campaigns.exception.FileNotFoundException;
import com.assesment.campaigns.service.CampaignService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CampaignController {
    private CampaignService campaignService;

    public CampaignController(CampaignService campaignService){
        this.campaignService = campaignService;
    }

    @CrossOrigin
    @GetMapping("/campaign/{id}")
    private Campaign getCampaign(@PathVariable Long id) {
        return campaignService.getCampaign(id);
    }

    @CrossOrigin
    @GetMapping("/campaign/all")
    private List<Campaign> getAllCampaigns() {
        return campaignService.getAllCampaigns();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private void campaignNotFoundhandler(CampaignNotFoundException exception){

    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private void fileNotFoundHandler(FileNotFoundException exception){

    }
}
