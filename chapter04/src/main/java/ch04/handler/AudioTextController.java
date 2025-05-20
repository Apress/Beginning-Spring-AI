package ch04.handler;

import ch04.model.TextToSpeechRequest;
import ch04.service.TextToSpeechService;
import ch04.service.TranscribeService;
import ch04.service.VoiceAssistantService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class AudioTextController {

    private final TextToSpeechService textToSpeechService;

    private final TranscribeService transcribeService;

    private final VoiceAssistantService voiceAssistantService;

    public AudioTextController(TextToSpeechService textToSpeechService, TranscribeService transcribeService, VoiceAssistantService voiceAssistantService) {
        this.textToSpeechService = textToSpeechService;
        this.transcribeService = transcribeService;
        this.voiceAssistantService = voiceAssistantService;
    }

    @PostMapping("/tts")
    public ResponseEntity<byte[]> handleTextToSpeech(@RequestBody TextToSpeechRequest textToSpeechRequest) {
        byte[] speechResult = textToSpeechService.processText(textToSpeechRequest.text(), null);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=output.mp3");
        headers.add(HttpHeaders.CONTENT_TYPE, "audio/mpeg");

        return new ResponseEntity<>(speechResult, headers, HttpStatus.OK);
    }

    @PostMapping("/transcribe")
    public ResponseEntity<String> handleAudioUpload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return new ResponseEntity<>("No file uploaded", HttpStatus.BAD_REQUEST);
        }

        try {
            var response = transcribeService.transcribeAudio(new ByteArrayResource(file.getBytes()), null);

            return new ResponseEntity<>(response.getResult().getOutput(), HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>("Failed to upload file", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/assistant")
    public ResponseEntity<byte[]> handleVoiceAssistantRequest(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No file uploaded");
        }

        try {
            var response = voiceAssistantService.issueCommand(new ByteArrayResource(file.getBytes()));

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to upload file");
        }
    }
}
