package io.bootify.java_spring_boot.service.ai;

import ai.djl.ModelException;
import ai.djl.inference.Predictor;
import ai.djl.modality.Classifications;
import ai.djl.modality.cv.Image;
import ai.djl.modality.cv.ImageFactory;
import ai.djl.translate.TranslateException;
import ai.djl.repository.zoo.Criteria;
import ai.djl.repository.zoo.ModelZoo;
import ai.djl.repository.zoo.ZooModel;

import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ImageClassificationService {

    public String classifyImage(String imageUrl) throws ModelException, TranslateException, IOException {
        Criteria<Image, Classifications> criteria = Criteria.builder()
            .setTypes(Image.class, Classifications.class)
            .optArtifactId("resnet")
            .build();

        try (ZooModel<Image, Classifications> model = ModelZoo.loadModel(criteria);
             Predictor<Image, Classifications> predictor = model.newPredictor()
        ) {
            Image img = ImageFactory.getInstance().fromUrl(imageUrl);
            Classifications classifications = predictor.predict(img);
            return classifications.toString();
        }
    }
}
