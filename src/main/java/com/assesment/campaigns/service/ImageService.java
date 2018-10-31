package com.assesment.campaigns.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.util.Base64;

@Component
public class ImageService {

    private static Logger logger = LoggerFactory.getLogger(ImageService.class);

    private static final String imageFilePath = "/static/images/";

    public String getImage(String name) {
        String encodedString = null;
        try {
            String imageFile = imageFilePath + name;
            byte[] imageContent = FileCopyUtils.copyToByteArray(new ClassPathResource(imageFile).getInputStream());
            encodedString = Base64.getEncoder().encodeToString(imageContent);

        } catch (IOException e) {
            logger.error("Could not fetch image with given name {}", name, e);
            e.printStackTrace();
        }
        return encodedString;
    }

}
