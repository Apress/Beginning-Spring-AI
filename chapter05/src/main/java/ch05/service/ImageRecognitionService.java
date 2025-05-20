package ch05.service;

import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.model.Media;
import org.springframework.stereotype.Service;

@Service
public class ImageRecognitionService {

    private final ChatModel chatModel;

    public ImageRecognitionService(ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    public String identify(String prompt, Media media) {
        var userMessage = new UserMessage(
                prompt,
                media);

        ChatResponse response = chatModel.call(new Prompt(userMessage));

        return response.getResult().getOutput().getContent();
    }
}
