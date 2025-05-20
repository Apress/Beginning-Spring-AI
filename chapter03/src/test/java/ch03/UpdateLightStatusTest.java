package ch03;

import ch03.service.UpdateChatService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class UpdateLightStatusTest extends BaseLightTests {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    UpdateChatService lightConversationService;

    @Test
    void changeLightStatus() {
        var response = lightConversationService.converse(List.of(
                new UserMessage("Turn the yellow light on. " +
                        "Then show the state of the red," +
                        " green, blue, purple, and yellow lights.")
        ));
        logger.info("Response from service: {}",
                response.getFirst().getOutput().getContent());
        assertState("yellow", true);
        assertState("red", false);
    }
}
