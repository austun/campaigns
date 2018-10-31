package com.assesment.campaigns.controller;

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
public class UserControllerIT {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void signupUser_loginWithCorrectCredentials_succeed_loginWithWrongCredentials_fail() throws Exception {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        //given - user with credentials
        HttpEntity<String> entity = new HttpEntity<>("{\"username\":\"test\",\"password\":\"1234abc\"}", headers);

        //when - call signup
        ResponseEntity<Boolean> signupResponse = restTemplate.exchange("/user/signup", HttpMethod.POST, entity, Boolean.class);

        //expect - success
        Assert.assertNotNull(signupResponse);
        Assert.assertEquals(HttpStatus.OK, signupResponse.getStatusCode());
        Assert.assertEquals(true, signupResponse.getBody());

        //given - correct credentials
        //when - call login
        ResponseEntity<Boolean> loginResponse = restTemplate.exchange("/user/login", HttpMethod.POST, entity, Boolean.class);

        //expect - success
        Assert.assertNotNull(loginResponse);
        Assert.assertEquals(HttpStatus.OK, loginResponse.getStatusCode());
        Assert.assertEquals(true, loginResponse.getBody());

        //given - wrong credentials
        //when - call login
        ResponseEntity<Boolean> failResponse = restTemplate.exchange("/user/login", HttpMethod.POST, new HttpEntity<>("{\"username\":\"WRONG\",\"password\":\"WRONG\"}", headers), Boolean.class);

        //expect - fail
        Assert.assertNotNull(failResponse);
        Assert.assertEquals(HttpStatus.OK, failResponse.getStatusCode());
        Assert.assertEquals(false, failResponse.getBody());

    }
}
