package ch03.service;

import ch03.model.Light;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LightService {
    public final Logger logger = LoggerFactory.getLogger(this.getClass());

    ApplicationContext context;

    public LightService(ApplicationContext context) {
        this.context = context;
    }

    public List<Light> getLights() {
        return context
                .getBeansOfType(Light.class)
                .values()
                .stream()
                .toList();
    }

    public Optional<Light> getLight(String color) {
        return getLights()
                .stream()
                .filter(light -> light.getColor().equalsIgnoreCase(color))
                .findFirst();
    }

    public Optional<Light> setLight(String color, boolean status) {
        var light = getLight(color);
        light.ifPresent(it -> {
            it.setOn(status);
        });
        return light;
    }
}
