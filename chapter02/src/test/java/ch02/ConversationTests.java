package ch02;

import ch02.service.ConversationChatService;
import org.apache.commons.text.WordUtils;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.Generation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ConversationTests {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    ConversationChatService conversationChatService;

    /**
     * This method extracts the `AssistantMessage` from the generated LLM output
     * @param output the results of the call to the LLM
     * @return the first `AssistantMessage` in the output
     */
    private AssistantMessage getAssistantMessage(List<Generation> output) {
        return output.getFirst().getOutput();
    }

    /**
     * This method simply wraps the content and dumps it to a logger.
     * @param content A string to display
     */
    private void display(String content) {
        var lines = WordUtils
                .wrap(content, 62, "\n", true)
                .split("\\n");
        for (String line : lines) {
            log.info(line);
        }
        log.info("-----");
    }

    @Test
    @Order(1)
    void simpleConversation() {
        var conversation = conversationChatService.converse(List.of(
                new UserMessage("What is the slope of y=x*1.2/z if z=2?")
        ));
        var output = getAssistantMessage(conversation);

        display(output.getContent());
        assertTrue(output.getContent().contains("0.6"));
    }

    @Test
    @Order(2)
    void interactiveConversation() {
        // we want to make a mutable list, because we're adding context.
        List<Message> messages = new ArrayList<>();
        messages.add(
                new UserMessage("What is the slope of y=x*1.2/z if z=2?")
        );
        var conversation = conversationChatService.converse(messages);
        var output = getAssistantMessage(conversation);

        display(output.getContent());
        assertTrue(output.getContent().contains("0.6"));

        // we want to establish the context of the first answer.
        messages.add(output);

        // now we want to add some extra context of our own...
        messages.add(
                new UserMessage("And if z=3?")
        );
        conversation = conversationChatService.converse(messages);
        output = getAssistantMessage(conversation);
        display(output.getContent());

        assertTrue(output.getContent().contains("0.4"));
    }
}
