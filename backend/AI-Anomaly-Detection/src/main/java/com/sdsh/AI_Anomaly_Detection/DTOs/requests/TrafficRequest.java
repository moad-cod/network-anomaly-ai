package com.sdsh.AI_Anomaly_Detection.DTOs.requests;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;



@Data
public class TrafficRequest {

     private String srcIp;
    private String dstIp;
    private String protocol;
    private int port;
    private int length;
    private LocalDateTime timestamp;

    // New indicators for anomaly detection
    private double avgPacketSize;
    private double packetRate;
    private double dnsRate;
    private double synRate;

    private List<String> top5Flows;
    private List<String> userAgents;
    private List<String> tlsSni;
}
