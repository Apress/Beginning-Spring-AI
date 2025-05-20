package ch05;

import ch05.service.ImageRecognitionService;
import org.junit.jupiter.api.Test;
import org.springframework.ai.model.Media;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.util.MimeTypeUtils;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class ImageRecognitionTest {

    @Autowired
    ImageRecognitionService imageRecognitionService;

    @Test
    void runImageRecognitionQuery() {
        var imageResource = new FileSystemResource("rendered_fruit_bowl.png");
        Media renderedFruitBowl = new Media(MimeTypeUtils.IMAGE_PNG, imageResource);
        String recognition = imageRecognitionService.identify(
                "In a single sentence explain what is in this picture and identify every item.",
                renderedFruitBowl);

        System.out.println(recognition);
        assertTrue(recognition.contains("banana"));
        assertTrue(recognition.contains("apple"));
        assertTrue(recognition.contains("kiwi"));
    }

}
