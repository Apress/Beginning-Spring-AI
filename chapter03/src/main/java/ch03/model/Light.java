package ch03.model;

import java.util.StringJoiner;

public class Light {
    private String color;
    private Boolean on;

    public Light(String color, boolean on) {
        this.color = color;
        this.on = on;
    }

    // included for serialization later
    public Light() {
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isOn() {
        return on;
    }

    public void setOn(boolean on) {
        this.on = on;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ",
                Light.class.getSimpleName() + "[", "]")
                .add("color='" + color + "'")
                .add("on=" + on)
                .toString();
    }
}
