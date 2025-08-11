package com.SystemMonitoring.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class HealthChecker {
    @Autowired
    private SystemStatsService statsService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private RestartService restartService;
    private static final double MEMORY_THRESHOLD = 80.0; // in percent
    private static final String SERVICE_NAME = "your-service-name";

    @Scheduled(fixedRate = 30000) // every 1 minute
    public void checkSystemHealth() {
        double memUsage = statsService.getMemoryUsagePercentage();
        String report = "Timestamp: " + LocalDateTime.now() + "\n" +
                "Memory Usage: " + memUsage + "%";

        if (memUsage > MEMORY_THRESHOLD) {
            emailService.sendEmail("High Memory Usage - Before Restart", report);
            restartService.restartWindowsServiceWithPowerShell(SERVICE_NAME);
            try {
                Thread.sleep(10000); // wait 10 sec
            } catch (InterruptedException ignored) {
            }
            double postRestartMem = statsService.getMemoryUsagePercentage();
            String postReport = report + "\n\nPost-Restart Memory: " + postRestartMem + "%";
            emailService.sendEmail("Memory Usage - After Restart", postReport);
        } else {
            emailService.sendEmail("System Health Check", report);
        }
    }
}
