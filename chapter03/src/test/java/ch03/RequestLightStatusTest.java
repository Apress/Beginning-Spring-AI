package ch03;

import ch03.service.RequestChatService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class RequestLightStatusTest extends BaseLightTests {
    Logger logger= LoggerFactory.getLogger(this.getClass());
    @Autowired
    RequestChatService lightQueryService;

    @Test
    void queryLightStatus() {
        var response = lightQueryService.converse(List.of(
                new UserMessage("Can you tell me the status of the lights named 'yellow' and 'purple'?")
        ));

        var content=response.getFirst().getOutput().getContent();

        logger.info("Response: {}", content);
        assertTrue(content.contains("off"));
    }
}
