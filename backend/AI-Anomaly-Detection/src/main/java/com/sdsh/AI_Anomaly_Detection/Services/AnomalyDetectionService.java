package com.sdsh.AI_Anomaly_Detection.Services;

import org.springframework.stereotype.Service;

import com.sdsh.AI_Anomaly_Detection.DTOs.requests.TrafficRequest;



@Service
public class AnomalyDetectionService {
    public boolean isAnomalous(TrafficRequest packet) {
        // Basic Rule-Based Detection (for now)
        if (packet.getLength() > 10000 || "UDP".equalsIgnoreCase(packet.getProtocol())) {
            return true;
        }
        // TODO: Later: Add ML detection logic here
        return false;
    }

    public void analyze(TrafficRequest packet) {
        if (isAnomalous(packet)) {
            System.out.println("⚠️ [ANOMALY] Suspicious traffic detected!");
            // You could log, alert, or store it in DB
        } else {
            System.out.println("✅ Normal traffic.");
        }
    }
}