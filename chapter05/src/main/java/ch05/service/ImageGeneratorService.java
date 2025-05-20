package ch05.service;

import org.springframework.ai.image.Image;
import org.springframework.ai.image.ImageOptionsBuilder;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.openai.OpenAiImageModel;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.stereotype.Service;

@Service
public class ImageGeneratorService {

    private final OpenAiImageModel openAiImageModel;

    public ImageGeneratorService(OpenAiImageModel openAiImageModel) {
        this.openAiImageModel = openAiImageModel;
    }

    public Image processPrompt(String prompt, OpenAiImageOptions.Builder imageOptions) {
        var imagePrompt = imageOptions != null ? new ImagePrompt(prompt, imageOptions.build()) : new ImagePrompt(prompt,
                ImageOptionsBuilder.builder().withWidth(1024).withHeight(1024).build());
        ImageResponse response = openAiImageModel.call(imagePrompt);
        return response.getResult().getOutput();
    }
}
