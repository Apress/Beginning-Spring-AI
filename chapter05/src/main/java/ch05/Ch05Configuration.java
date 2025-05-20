package ch05;

import ch03.model.Light;
import ch03.service.LightService;
import ch03.service.RequestLightStatusFunction;
import ch03.service.UpdateChatService;
import ch03.service.UpdateLightStatusFunction;
import org.springframework.ai.chat.client.ChatClient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;

@SpringBootApplication
@Configuration
public class Ch05Configuration {

    @Bean
    Light getYellowLight() {
        return new Light("yellow", false);
    }

    @Bean
    Light getRedLight() {
        return new Light("red", true);
    }

    @Bean
    Light getGreenLight() {
        return new Light("green", false);
    }

    @Bean
    Light getBlueLight() {
        return new Light("blue", true);
    }

    @Bean
    LightService getLightService(ApplicationContext context) {
        return new LightService(context);
    }

    @Bean("RequestLightStatusService")
    @Description("Get light status")
    RequestLightStatusFunction getRequestLightStatusFunction(LightService lightService) {
        return new RequestLightStatusFunction(lightService);
    }

    @Bean("ChangeLightStatusService")
    @Description("Change a light's state")
    UpdateLightStatusFunction getChangeLightStatusFunction(LightService lightService) {
        return new UpdateLightStatusFunction(lightService);
    }

    @Bean
    UpdateChatService getLightUpdateChatService(ChatClient.Builder builder) {
        return new UpdateChatService(builder);
    }

    public static void main(String[] args) {
        SpringApplication.run(Ch05Configuration.class, args);
    }
}
