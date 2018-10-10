package com.assesment.campaigns.repository;

import com.assesment.campaigns.domain.Campaign;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CampaignRepository extends MongoRepository<Campaign, String> {
    Campaign findById(final Long id);
}
