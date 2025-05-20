package ch03.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.model.Generation;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Primary
@Service
public class RequestChatService {
    protected final ChatClient client;

    RequestChatService(ChatClient.Builder builder) {
        client = builder.build();
    }

    public OpenAiChatOptions buildOptions() {
        return new OpenAiChatOptions
                .Builder()
                .withFunction("RequestLightStatusService")
                .build();
    }

    public List<Generation> converse(
            List<Message> messages
    ) {
        var prompt = new Prompt(messages, buildOptions());
        return client
                .prompt(prompt)
                .call()
                .chatResponse()
                .getResults();
    }
}
