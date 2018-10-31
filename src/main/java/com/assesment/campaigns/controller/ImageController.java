package com.assesment.campaigns.controller;

import com.assesment.campaigns.service.ImageService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/image")
public class ImageController {

    private ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @CrossOrigin
    @GetMapping("/{name}")
    private String getImage(@PathVariable String name) {
        return imageService.getImage(name);
    }

}
