package com.example.smarthome.service;

import com.example.smarthome.model.AirConditioner;
import com.example.smarthome.model.Appliance;
import com.example.smarthome.model.Fan;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.List;

@Service
public class ApplianceService {

    private Map<String, Appliance> appliances;

    public ApplianceService(List<Appliance> applianceList) {
        // Key by simple class name lowercase, e.g. "light", "fan", "air conditioner"
        this.appliances = applianceList.stream()
                .collect(Collectors.toMap(
                        appliance -> appliance.getClass().getSimpleName().toLowerCase(),
                        appliance -> appliance));
    }

    public void turnOn(String applianceName) {
        Appliance appliance = appliances.get(applianceName.toLowerCase());
        if (appliance != null) {
            appliance.turnOn();
        } else {
            throw new IllegalArgumentException("Appliance not found: " + applianceName);
        }
    }

    public void turnOff(String applianceName) {
        Appliance appliance = appliances.get(applianceName.toLowerCase());
        if (appliance != null) {
            appliance.turnOff();
        } else {
            throw new IllegalArgumentException("Appliance not found: " + applianceName);
        }
    }

    public String getStatus(String applianceName) {
        Appliance appliance = appliances.get(applianceName.toLowerCase());
        if (appliance != null) {
            return appliance.getStatus();
        }
        return "Appliance not found: " + applianceName;
    }

    public void turnOffAll() {
        appliances.values().forEach(Appliance::turnOff);
    }

    // Fan-specific method
    public void setFanSpeed(int speed) {
        Appliance appliance = appliances.get("fan");
        if (appliance != null && appliance instanceof Fan) {
            ((Fan) appliance).setSpeed(speed);
        } else {
            throw new IllegalArgumentException("Fan appliance not found");
        }
    }

    // AC-specific method
    public void setACThermostatMode(String mode) {
        Appliance appliance = appliances.get("airconditioner");
        if (appliance != null && appliance instanceof AirConditioner) {
            ((AirConditioner) appliance).setThermostatMode(mode);
        } else {
            throw new IllegalArgumentException("Air Conditioner appliance not found");
        }
    }

    @Scheduled(cron = "0 0 1 1 1 *") // At 1:00 AM on January 1st every year
    public void scheduledSystemUpdate() {
        System.out.println("Running scheduled system update: turning off all devices.");
        turnOffAll();
    }
}




