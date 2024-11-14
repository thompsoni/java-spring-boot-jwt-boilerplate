package io.bootify.java_spring_boot.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ai.djl.ModelException;
import ai.djl.translate.TranslateException;
import io.bootify.java_spring_boot.service.ai.ImageClassificationService;

import java.io.IOException;

@RestController
public class ImageClassificationResource {

    @Autowired
    private ImageClassificationService imageClassificationService;

    @GetMapping("/classify-image")
    public String classifyImage(@RequestParam String imageUrl) {
        try {
            return imageClassificationService.classifyImage(imageUrl);
        } catch (ModelException | TranslateException | IOException e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }
}
