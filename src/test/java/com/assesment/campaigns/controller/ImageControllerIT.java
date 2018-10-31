package com.assesment.campaigns.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.FileCopyUtils;

import java.util.Base64;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ImageControllerIT {
    @Autowired
    TestRestTemplate restTemplate;

    private static byte[] imageContent = null;

    @Before
    public void loadOriginalImage() throws Exception {
        String imageFilePath = "/static/images/img1.jpg";
        imageContent = FileCopyUtils.copyToByteArray(new ClassPathResource(imageFilePath).getInputStream());
    }

    @Test
    public void getImage_compareWithOriginal_expectTrue() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange("/image/img1.jpg", HttpMethod.GET, entity, String.class);

        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertNotNull(response.getBody());

        byte[] decodedBytes = Base64.getDecoder().decode(response.getBody());
        Assert.assertArrayEquals(imageContent, decodedBytes);
    }
}
