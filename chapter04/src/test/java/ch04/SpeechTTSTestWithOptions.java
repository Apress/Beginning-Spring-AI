package ch04;

import ch04.service.TextToSpeechService;
import org.junit.jupiter.api.Test;
import org.springframework.ai.openai.OpenAiAudioSpeechOptions;
import org.springframework.ai.openai.api.OpenAiAudioApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class SpeechTTSTestWithOptions {

    @Autowired
    TextToSpeechService textToSpeechService;

    @Test
    void runTTSQuery() throws IOException {
        var responseAsBytes = textToSpeechService.processText("""
 Daisy, Daisy,
 Give me your answer, do!
 I'm half crazy,
 All for the love of you!
 It won't be a stylish marriage,
 I can't afford a carriage,
 But you'll look sweet upon the seat
 Of a bicycle built for two!
""", new OpenAiAudioSpeechOptions.Builder()
                .withModel(OpenAiAudioApi.TtsModel.TTS_1_HD.value)
                .withVoice(OpenAiAudioApi.SpeechRequest.Voice.SHIMMER)
                .withSpeed(0.7f)
        );

        Files.write(Paths.get("./daisybell.mp3"), responseAsBytes);

        assertNotNull(responseAsBytes);
    }

}
