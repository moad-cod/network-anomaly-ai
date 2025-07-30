package com.sdsh.AI_Anomaly_Detection.Controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sdsh.AI_Anomaly_Detection.DTOs.TrafficPacketDTO;
import com.sdsh.AI_Anomaly_Detection.Services.AnomalyDetectionService;

@RestController
@RequestMapping("/api/traffic")
public class TrafficController {

    private final AnomalyDetectionService anomalyService;

    public TrafficController(AnomalyDetectionService anomalyService) {
        this.anomalyService = anomalyService;
    }

    @PostMapping
    public void receiveTraffic(@RequestBody TrafficPacketDTO packet) {
        System.out.println("📥 Received Packet:");
        System.out.println("From: " + packet.getSrcIp() + " → " + packet.getDstIp());
        System.out.println("Protocol: " + packet.getProtocol() + " | Port: " + packet.getPort());
        System.out.println("Length: " + packet.getLength() + " | Timestamp: " + packet.getTimestamp());

        anomalyService.analyze(packet);
    }
}

