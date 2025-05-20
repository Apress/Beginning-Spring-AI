package ch03.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UpdateStructuredChatService {
    private final ChatClient client;

    public record LightWithXYZ(String color, boolean on, Double x, Double y, Double z) {
    }

    public record LightWithXYZList(List<LightWithXYZ> lights) {
    }

    public UpdateStructuredChatService(ChatClient.Builder builder) {
        client = builder.build();
    }

    public OpenAiChatOptions buildOptions() {
        return new OpenAiChatOptions
                .Builder()
                .withFunction("RequestLightStatusService")
                .withFunction("ChangeLightStatusService")
                .build();
    }

    public LightWithXYZList converse(List<Message> messages) {
        var localMessages = new ArrayList<Message>(messages);
        localMessages.addFirst(new SystemMessage(
                "Add the CIE 1931 color representation of each light if possible."));
        return client
                .prompt()
                .messages(localMessages)
                .options(buildOptions())
                .call()
                .entity(LightWithXYZList.class);
    }
}
