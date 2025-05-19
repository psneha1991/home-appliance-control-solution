package com.example.smarthome;

import static org.junit.jupiter.api.Assertions.*;

import com.example.smarthome.model.AirConditioner;
import com.example.smarthome.model.Fan;
import com.example.smarthome.model.Light;
import com.example.smarthome.service.ApplianceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ApplianceServiceTest {

    private ApplianceService applianceService;

    @BeforeEach
    public void setUp() {
        // Manually create appliance instances (since no Spring context here)
        Light light = new Light();
        Fan fan = new Fan();
        AirConditioner ac = new AirConditioner();

        // Create service with appliances
        applianceService = new ApplianceService(java.util.List.of(light, fan, ac));
    }

    @Test
    public void testLightOnOffStatus() {
        applianceService.turnOn("light");
        assertEquals("Light is ON", applianceService.getStatus("light"));

        applianceService.turnOff("light");
        assertEquals("Light is OFF", applianceService.getStatus("light"));
    }

    @Test
    public void testFanOnOffAndSpeed() {
        // Initially off
        assertEquals("Fan speed is 0 (OFF)", applianceService.getStatus("fan"));

        // Turn on sets speed 1
        applianceService.turnOn("fan");
        assertEquals("Fan speed is 1 (ON)", applianceService.getStatus("fan"));

        // Set speed 2
        applianceService.setFanSpeed(2);
        assertEquals("Fan speed is 2 (ON)", applianceService.getStatus("fan"));

        // Turn off sets speed 0
        applianceService.turnOff("fan");
        assertEquals("Fan speed is 0 (OFF)", applianceService.getStatus("fan"));
    }

    @Test
    public void testInvalidFanSpeed() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            applianceService.setFanSpeed(3);
        });
        assertEquals("Fan speed must be 0, 1, or 2", ex.getMessage());
    }

    @Test
    public void testACOnOffAndMode() {
        // Initially off
        assertEquals("Air Conditioner thermostat is OFF", applianceService.getStatus("airconditioner"));

        // Turn on sets mode to cool
        applianceService.turnOn("airconditioner");
        assertEquals("Air Conditioner thermostat is COOL (ON)", applianceService.getStatus("airconditioner"));

        // Set mode to heat
        applianceService.setACThermostatMode("heat");
        assertEquals("Air Conditioner thermostat is HEAT (ON)", applianceService.getStatus("airconditioner"));

        // Turn off sets mode to off
        applianceService.turnOff("airconditioner");
        assertEquals("Air Conditioner thermostat is OFF", applianceService.getStatus("airconditioner"));
    }

    @Test
    public void testInvalidACMode() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            applianceService.setACThermostatMode("");
        });
        assertEquals("Thermostat mode cannot be empty", ex.getMessage());
    }

    @Test
    public void testTurnOffAll() {
        applianceService.turnOn("light");
        applianceService.turnOn("fan");
        applianceService.turnOn("airconditioner");

        applianceService.turnOffAll();

        assertEquals("Light is OFF", applianceService.getStatus("light"));
        assertEquals("Fan speed is 0 (OFF)", applianceService.getStatus("fan"));
        assertEquals("Air Conditioner thermostat is OFF", applianceService.getStatus("airconditioner"));
    }

    @Test
    public void testApplianceNotFound() {
        Exception ex1 = assertThrows(IllegalArgumentException.class, () -> {
            applianceService.turnOn("nonexistent");
        });
        assertEquals("Appliance not found: nonexistent", ex1.getMessage());

        Exception ex2 = assertThrows(IllegalArgumentException.class, () -> {
            applianceService.turnOff("nonexistent");
        });
        assertEquals("Appliance not found: nonexistent", ex2.getMessage());
    }
}