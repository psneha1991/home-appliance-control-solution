package com.example.smarthome.scheduler;

import com.example.smarthome.service.ApplianceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class SystemUpdateScheduler {

    @Autowired
    private ApplianceService applianceService;

    @Scheduled(cron = "0 0 1 1 1 *") // At 1:00 AM on January 1st
    public void annualUpdateShutdown() {
        System.out.println("Annual Update - Shutting down all appliances...");
        applianceService.turnOffAll();
    }
}