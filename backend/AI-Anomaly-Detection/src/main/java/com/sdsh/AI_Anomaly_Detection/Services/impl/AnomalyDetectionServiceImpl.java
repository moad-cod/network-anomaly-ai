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
        return packetRepository.save(packet);
    }

}
