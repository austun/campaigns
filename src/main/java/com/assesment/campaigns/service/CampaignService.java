package com.assesment.campaigns.service;

import com.assesment.campaigns.domain.Campaign;
import com.assesment.campaigns.repository.CampaignRepository;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Component
public class CampaignService {

    private static Logger logger = LoggerFactory.getLogger(CampaignService.class);

    private static final String dataFile = "data.json";
    private CampaignRepository campaignRepository;

    public CampaignService(CampaignRepository campaignRepository){
        this.campaignRepository = campaignRepository;
    }

    public List<Campaign> getAllCampaigns() {
        return campaignRepository.findAll();
    }

    public Campaign getCampaign(Long id) {
        return campaignRepository.findById(id);
    }

    public void deleteAll(){
        campaignRepository.deleteAll();
    }

    public void prePopulateMongo(){
        try {
            byte[] byteData = FileCopyUtils.copyToByteArray(new ClassPathResource(dataFile).getInputStream());
            String data = new String(byteData, StandardCharsets.UTF_8);
            ObjectMapper mapper = new ObjectMapper(new JsonFactory());
            List<Campaign> campaigns = mapper.readValue(data, new TypeReference<List<Campaign>>(){});
            campaignRepository.saveAll(campaigns);

        } catch (IOException e) {
            logger.error("Could not read from data.json file", e);
            e.printStackTrace();
        }
    }
}
