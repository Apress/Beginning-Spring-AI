package ch04;

import ch04.service.TextToSpeechService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class SpeechTTSTest {

    @Autowired
    TextToSpeechService textToSpeechService;

    @Test
    void runTTSQuery() {
        var responseAsBytes = textToSpeechService.processText("""
 Daisy, Daisy,
 Give me your answer, do!
 I'm half crazy,
 All for the love of you!
 It won't be a stylish marriage,
 I can't afford a carriage,
 But you'll look sweet upon the seat
 Of a bicycle built for two!
""", null);

        assertNotNull(responseAsBytes);
    }

}
