package com.sdsh.AI_Anomaly_Detection.models;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class NetworkPacket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String srcIp;
    private String dstIp;
    private String protocol;
    private int port;
    private int length;
    private LocalDateTime timestamp;

    private double avgPacketSize;
    private double packetRate;
    private double dnsRate;
    private double synRate;

    @ElementCollection
    private List<String> top5Flows;

    @ElementCollection
    private List<String> userAgents;

    @ElementCollection
    private List<String> tlsSni;

    private boolean isAnomaly = false;
}
