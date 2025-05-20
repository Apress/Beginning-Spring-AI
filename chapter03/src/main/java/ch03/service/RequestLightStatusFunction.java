package ch03.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service("RequestLightStatusService")
@Description("Get light status")
public class RequestLightStatusFunction
        implements Function<RequestLightStatusFunction.Request, RequestLightStatusFunction.Response> {
    public final Logger logger = LoggerFactory.getLogger(this.getClass());
    LightService lightService;

    public RequestLightStatusFunction(LightService lightService) {
        this.lightService = lightService;
    }

    public record Request(String color) {
    }

    public record Response(String color, boolean on) {
    }

    public Response apply(Request request) {
        logger.info("Requesting status for light: {}", request);
        var light = lightService.getLight(request.color);
        return light
                .map(value -> new Response(request.color, value.isOn()))
                .orElse(null);
    }
}
