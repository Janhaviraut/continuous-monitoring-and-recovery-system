package com.SystemMonitoring.services;

import org.springframework.stereotype.Component;

import oshi.SystemInfo;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HardwareAbstractionLayer;

@Component
public class SystemStatsService {
    private final SystemInfo systemInfo = new SystemInfo();
    private final HardwareAbstractionLayer hal = systemInfo.getHardware();

    public double getMemoryUsagePercentage() {
        GlobalMemory memory = hal.getMemory();
        long total = memory.getTotal();
        long available = memory.getAvailable();
        return ((double)(total - available) / total) * 100;
    }
}
