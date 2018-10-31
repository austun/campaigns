package com.assesment.campaigns.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.FileCopyUtils;

import java.util.Base64;

@RunWith(MockitoJUnitRunner.class)
public class ImageServiceTest {

    private ImageService imageService;

    private static String originalImage = null;

    @Before
    public void setUp_loadOriginalImage() throws Exception {
        this.imageService = new ImageService();
        String imageFilePath = "/static/images/img1.jpg";
        byte[] imageContent = FileCopyUtils.copyToByteArray(new ClassPathResource(imageFilePath).getInputStream());
        originalImage = Base64.getEncoder().encodeToString(imageContent);
    }

    @Test
    public void getImage_returnEncodedImage() throws Exception {
        String fetchedImage = imageService.getImage("img1.jpg");
        Assert.assertEquals(fetchedImage, originalImage);
    }
}
