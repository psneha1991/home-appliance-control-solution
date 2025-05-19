package com.example.smarthome.model;

import org.springframework.stereotype.Component;

@Component
public class Fan implements Appliance {

    private int speed = 0; // 0 = off, 1 or 2 = speeds

    @Override
    public void turnOn() {
        if (speed == 0) speed = 1; // default to speed 1 when turned on
    }

    @Override
    public void turnOff() {
        speed = 0;
    }

    public void setSpeed(int speed) {
        if (speed < 0 || speed > 2) {
            throw new IllegalArgumentException("Fan speed must be 0, 1, or 2");
        }
        this.speed = speed;
    }

    @Override
    public String getStatus() {
        return speed == 0 ? "Fan speed is 0 (OFF)" : "Fan speed is " + speed + " (ON)";
    }
}

