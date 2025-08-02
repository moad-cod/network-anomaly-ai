package com.sdsh.AI_Anomaly_Detection.Services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sdsh.AI_Anomaly_Detection.Services.AnomalyDetectionService;
import com.sdsh.AI_Anomaly_Detection.models.NetworkPacket;
import com.sdsh.AI_Anomaly_Detection.repositories.PacketRepository;

@Service
public class AnomalyDetectionServiceImpl implements AnomalyDetectionService{
    
    private final PacketRepository packetRepository;

    @Autowired
    public AnomalyDetectionServiceImpl(PacketRepository packetRepository) {
        this.packetRepository = packetRepository;
    }

    @Override
    public NetworkPacket analyzeAndSave(NetworkPacket packet) {
        boolean isAnomaly = false;
        if (packet.getPacketRate() > 100) {
            isAnomaly = true;
        }

        if (packet.getAvgPacketSize() > 1500) {
            isAnomaly = true;
        }

        if (packet.getSynRate() > 0.5) {
            isAnomaly = true;
        }

        // Example: Check if SNI list is empty (could be suspicious)
        if (packet.getTlsSni() == null || packet.getTlsSni().isEmpty()) {
            isAnomaly = true;
        }

        // Add more rules as needed...

        packet.setAnomaly(isAnomaly);

        return packetRepository.save(packet);
    }

}
