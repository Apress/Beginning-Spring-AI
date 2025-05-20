package ch03;

import ch03.service.LightService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class LightServiceTests extends BaseLightTests {
    @Autowired
    LightService lightService;

    @Test
    void testLights() {
        // we expect four lights in our configuration.
        assertEquals(4, lightService.getLights().size(), "count of lights");
    }

    @Test
    void findLight() {
        // we need to be able to find a specific light.
        assertTrue(lightService.getLight("yellow").isPresent());
    }

    @Test
    void failToFindMissingLight() {
        // we need to be able to make sure a light doesn't exist.
        assertTrue(lightService.getLight("purple").isEmpty());
    }

    @Test
    void changeLight() {
        assertState("yellow", false);
        // set the light on
        lightService.setLight("yellow", true);
        assertState("yellow", true);

        // turn it back off, restoring original state
        lightService.setLight("yellow", false);
        assertState("yellow", false);
    }
}
