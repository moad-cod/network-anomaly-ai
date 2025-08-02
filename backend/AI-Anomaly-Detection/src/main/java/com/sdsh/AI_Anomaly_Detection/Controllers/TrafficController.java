package com.sdsh.AI_Anomaly_Detection.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sdsh.AI_Anomaly_Detection.Services.AnomalyDetectionService;
import com.sdsh.AI_Anomaly_Detection.models.NetworkPacket;

@RestController
@RequestMapping("/api")
public class TrafficController {
    private final AnomalyDetectionService anomalyDetectionService;

    @Autowired
    public TrafficController(AnomalyDetectionService anomalyDetectionService) {
        this.anomalyDetectionService = anomalyDetectionService;
    }

    @PostMapping("/traffic")
    public ResponseEntity<NetworkPacket> receiveTraffic(@RequestBody NetworkPacket packet) {
        NetworkPacket savedPacket = anomalyDetectionService.analyzeAndSave(packet);
        return ResponseEntity.ok(savedPacket);
    }
}

