package ch04;

import ch04.service.TranscribeService;
import org.junit.jupiter.api.Test;
import org.springframework.ai.audio.transcription.AudioTranscriptionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class TranscribeTest {

    @Autowired
    TranscribeService transcribeService;

    @Test
    void transcribeQuery() {
        Resource daisyBellResource = new ClassPathResource("Daisy_Bell_sung_by_DECtalk.flac");
        AudioTranscriptionResponse response = transcribeService.transcribeAudio(daisyBellResource, null);
        System.out.println(response.getResult().getOutput());
        assertTrue(response.getResult().getOutput().contains("the seat of a bicycle built for two"));
    }
}
