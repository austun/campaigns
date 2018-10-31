package com.assesment.campaigns.repository;

import com.assesment.campaigns.CampaignsApplication;
import com.assesment.campaigns.domain.User;
import com.assesment.campaigns.service.CampaignService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataMongoTest
@ContextConfiguration(classes = {CampaignsApplication.class, CampaignService.class})
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    @Before
    public void clear() throws Exception {
        mongoTemplate.dropCollection(User.class);
    }

    @Test
    public void saveUser_returnUserCredentials_andDelete_expectNoUser() throws Exception {
        User savedUser = new User("10000001", "test", "1234abc");
        userRepository.save(savedUser);
        User user = userRepository.findByUsernameAndPassword(savedUser.getUsername(), savedUser.getPassword());

        assertThat(user.getId()).isEqualTo(savedUser.getId());
        assertThat(user.getUsername()).isEqualTo(savedUser.getUsername());
        assertThat(user.getPassword()).isEqualTo(savedUser.getPassword());

        userRepository.delete(user);
        User userAfterDelete = userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
        assertThat(userAfterDelete).isNull();
    }

}

