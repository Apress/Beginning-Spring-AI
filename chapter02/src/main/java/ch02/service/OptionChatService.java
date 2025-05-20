package ch02.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class OptionChatService extends FirstChatService {

    OptionChatService(ChatClient.Builder builder) {
        super(builder);
    }

    public final String query(String query, OpenAiChatOptions options) {
        Objects.requireNonNull(options);
        Objects.requireNonNull(query);

        var prompt = new Prompt(query, options);

        var request = client
                .prompt(prompt);

        var responseSpecification = request.call();

        return responseSpecification.content();
    }
}
