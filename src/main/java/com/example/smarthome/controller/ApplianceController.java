package com.example.smarthome.controller;

import com.example.smarthome.service.ApplianceService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/appliances")
public class ApplianceController {

    private final ApplianceService applianceService;

    public ApplianceController(ApplianceService applianceService) {
        this.applianceService = applianceService;
    }

    // Generic method turn on any appliance
    @PostMapping("/{appliance}/on")
    public ResponseEntity<String> turnOn(@PathVariable String appliance) {
        applianceService.turnOn(appliance);
        return ResponseEntity.ok(applianceService.getStatus(appliance));
    }

    // Generic Method turn off any appliance
    @PostMapping("/{appliance}/off")
    public ResponseEntity<String> turnOff(@PathVariable String appliance) {
        applianceService.turnOff(appliance);
        return ResponseEntity.ok(applianceService.getStatus(appliance));
    }

    // Fetch the status of any appliance
    @GetMapping("/{appliance}/status")
    public ResponseEntity<String> getStatus(@PathVariable String appliance) {
        return ResponseEntity.ok(applianceService.getStatus(appliance));
    }

    // Fan speed adjustment
    @PostMapping("/fan/speed/{speed}")
    public ResponseEntity<String> setFanSpeed(@PathVariable int speed) {
        if (speed < 0 || speed > 2) {
            return ResponseEntity.badRequest().body("Invalid speed. Allowed values: 0, 1, 2");
        }
        applianceService.setFanSpeed(speed);
        return ResponseEntity.ok(applianceService.getStatus("fan"));
    }

    // Switch the AC mode
    @PostMapping("/ac/mode")
    public ResponseEntity<String> setACThermostatMode(@RequestParam String mode) {
        if (mode == null || mode.isBlank()) {
            return ResponseEntity.badRequest().body("Thermostat mode cannot be empty");
        }
        applianceService.setACThermostatMode(mode);
        return ResponseEntity.ok(applianceService.getStatus("airconditioner"));
    }

    // Generic Method to turn off all the appliances.
    @PostMapping("/shutdown")
    public ResponseEntity<String> shutdownAll() {
        applianceService.turnOffAll();
        return ResponseEntity.ok("All appliances turned off for update");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgument(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}


