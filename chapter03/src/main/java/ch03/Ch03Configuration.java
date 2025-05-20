package ch03;

import ch03.model.Light;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Ch03Configuration {
    @Bean
    Light getYellowLight() {
        return new Light("yellow", false);
    }

    @Bean
    Light getRedLight() {
        return new Light("red", false);
    }

    @Bean
    Light getGreenLight() {
        return new Light("green", false);
    }

    @Bean
    Light getBlueLight() {
        return new Light("blue", false);
    }
}
