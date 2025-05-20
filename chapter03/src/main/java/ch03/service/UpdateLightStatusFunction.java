package ch03.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service("ChangeLightStatusService")
@Description("Change a light's state")
public class UpdateLightStatusFunction
        implements Function<UpdateLightStatusFunction.Request, UpdateLightStatusFunction.Response> {
    public final Logger logger = LoggerFactory.getLogger(this.getClass());
    LightService lightService;

    public UpdateLightStatusFunction(LightService lightService) {
        this.lightService = lightService;
    }

    public record Request(String color, boolean on) {
    }

    public record Response(String color, boolean on) {
    }

    public Response apply(Request request) {
        logger.info("Changing status for light: {}", request);
        var light=lightService.setLight(request.color, request.on);
        return light
                .map(value -> new Response(request.color, value.isOn()))
                .orElse(null);
    }
}
