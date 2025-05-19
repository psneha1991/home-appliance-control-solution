package com.example.smarthome.model;

import org.springframework.stereotype.Component;

@Component
public class Light implements Appliance {

    private boolean isOn = false;

    @Override
    public void turnOn() {
        isOn = true;
    }

    @Override
    public void turnOff() {
        isOn = false;
    }

    @Override
    public String getStatus() {
        return isOn ? "Light is ON" : "Light is OFF";
    }
}
