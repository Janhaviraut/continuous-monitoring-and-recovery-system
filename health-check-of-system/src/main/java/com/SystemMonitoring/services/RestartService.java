package com.SystemMonitoring.services;

import org.springframework.stereotype.Service;

@Service
public class RestartService{

   @SuppressWarnings({"UseSpecificCatch", "CallToPrintStackTrace"})
   public void restartWindowsServiceWithPowerShell(String serviceName) {
    try {
        String command = "powershell.exe Restart-Service -Name \"" + serviceName + "\" -Force";
        @SuppressWarnings("deprecation")
        Process process = Runtime.getRuntime().exec(command);
        process.waitFor();
    } catch (Exception e) {
        e.printStackTrace();
    }
}


}