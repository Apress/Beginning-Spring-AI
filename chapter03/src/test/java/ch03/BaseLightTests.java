package ch03;

import ch03.model.Light;
import ch03.service.LightService;
import org.junit.jupiter.api.BeforeEach;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public abstract class BaseLightTests {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    LightService lightService;

    @BeforeEach
    void turnAllLightsOff() {
        lightService
                .getLights()
                .forEach(light -> {
                    light.setOn(false);
                });
    }

    final void assertState(String color, boolean on) {
        var light = lightService.getLight(color);
        assertTrue(light.isPresent(), color + " light is present");
        assertEquals(on, light.get().isOn(), "light state");
    }

    final Map<String, Boolean> mapToStatus(List<Light> response) {
        return response
                .stream()
                .collect(
                        Collectors.toMap(Light::getColor, Light::isOn)
                );
    }
}
