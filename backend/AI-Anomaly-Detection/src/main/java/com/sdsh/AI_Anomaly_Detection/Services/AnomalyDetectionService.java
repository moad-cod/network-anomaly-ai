package com.sdsh.AI_Anomaly_Detection.Services;

import org.springframework.stereotype.Service;

import com.sdsh.AI_Anomaly_Detection.models.NetworkPacket;



@Service
public interface AnomalyDetectionService {
    NetworkPacket analyzeAndSave(NetworkPacket packet);
}