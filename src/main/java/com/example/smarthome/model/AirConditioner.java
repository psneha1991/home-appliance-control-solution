package com.example.smarthome.model;

import org.springframework.stereotype.Component;

@Component
public class AirConditioner implements Appliance {

    private String thermostatMode = "off"; // off, cool etc.

    @Override
    public void turnOn() {
        if ("off".equalsIgnoreCase(thermostatMode)) {
            thermostatMode = "cool"; // default mode when turned on
        }
    }

    @Override
    public void turnOff() {
        thermostatMode = "off";
    }

    public void setThermostatMode(String mode) {
        if (mode == null || mode.isBlank()) {
            throw new IllegalArgumentException("Thermostat mode cannot be empty");
        }
        thermostatMode = mode.toLowerCase();
    }

    @Override
    public String getStatus() {
        if ("off".equalsIgnoreCase(thermostatMode)) {
            return "Air Conditioner thermostat is OFF";
        }
        return "Air Conditioner thermostat is " + thermostatMode.toUpperCase() + " (ON)";
    }
}

