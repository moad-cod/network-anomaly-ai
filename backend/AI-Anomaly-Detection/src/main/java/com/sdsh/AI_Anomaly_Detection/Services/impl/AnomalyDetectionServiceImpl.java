package com.sdsh.AI_Anomaly_Detection.Services.impl;

import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sdsh.AI_Anomaly_Detection.Services.AnomalyDetectionService;
import com.sdsh.AI_Anomaly_Detection.models.NetworkPacket;
import com.sdsh.AI_Anomaly_Detection.repositories.PacketRepository;

@Service
public class AnomalyDetectionServiceImpl implements AnomalyDetectionService {
    
    private static final double HIGH_SYN_RATE_THRESHOLD = 0.5;
    private static final double HIGH_DNS_RATE_THRESHOLD = 0.3;
    private static final int HIGH_PACKET_RATE_THRESHOLD = 100;
    private static final int HIGH_AVG_PACKET_SIZE_THRESHOLD = 1500;
    private static final int UNCOMMON_PORT_THRESHOLD = 49152; // Start of dynamic/private ports
    private static final Pattern IP_PATTERN = Pattern.compile("^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$");

    private final PacketRepository packetRepository;

    @Autowired
    public AnomalyDetectionServiceImpl(PacketRepository packetRepository) {
        this.packetRepository = packetRepository;
    }

    @Override
    public NetworkPacket analyzeAndSave(NetworkPacket packet) {
        boolean isAnomaly = detectAnomalies(packet);
        packet.setAnomaly(isAnomaly);
        return packetRepository.save(packet);
    }

    private boolean detectAnomalies(NetworkPacket packet) {
        // Rate-based anomalies
        if (packet.getPacketRate() > HIGH_PACKET_RATE_THRESHOLD) {
            return true; // High packet rate could indicate DDoS
        }

        if (packet.getAvgPacketSize() > HIGH_AVG_PACKET_SIZE_THRESHOLD) {
            return true; // Unusually large packets
        }

        if (packet.getSynRate() > HIGH_SYN_RATE_THRESHOLD) {
            return true; // Potential SYN flood attack
        }

        if (packet.getDnsRate() > HIGH_DNS_RATE_THRESHOLD) {
            return true; // Potential DNS amplification attack
        }

        // Protocol and port anomalies
        if (isUncommonPortUsage(packet.getPort(), packet.getProtocol())) {
            return true;
        }

        // IP address anomalies
        if (!isValidIp(packet.getSrcIp()) || !isValidIp(packet.getDstIp())) {
            return true; // Malformed IP addresses
        }

        // TLS/SNI anomalies
        if (packet.getTlsSni() == null || packet.getTlsSni().isEmpty()) {
            if ("TCP".equalsIgnoreCase(packet.getProtocol()) && packet.getPort() == 443) {
                return true; // HTTPS traffic without SNI
            }
        } else {
            if (containsSuspiciousDomain(packet.getTlsSni())) {
                return true;
            }
        }

        // User-Agent anomalies
        if (packet.getUserAgents() != null && containsSuspiciousUserAgent(packet.getUserAgents())) {
            return true;
        }

        // Flow anomalies
        if (packet.getTop5Flows() != null && packet.getTop5Flows().size() >= 5) {
            return true; // Many distinct flows could indicate scanning
        }

        // Packet length anomalies
        if (packet.getLength() <= 0 || packet.getLength() > 65535) {
            return true; // Invalid packet length
        }

        return false;
    }

    private boolean isUncommonPortUsage(int port, String protocol) {
        // Check for uncommon port usage for specific protocols
        if ("HTTP".equalsIgnoreCase(protocol) && port != 80 && port != 8080 && port != 443) {
            return true;
        }
        if ("HTTPS".equalsIgnoreCase(protocol) && port != 443) {
            return true;
        }
        if ("DNS".equalsIgnoreCase(protocol) && port != 53) {
            return true;
        }
        if ("FTP".equalsIgnoreCase(protocol) && port != 21 && port != 20) {
            return true;
        }
        
        // Check for dynamic/private ports with uncommon protocols
        return port > UNCOMMON_PORT_THRESHOLD && !"TCP".equalsIgnoreCase(protocol) 
               && !"UDP".equalsIgnoreCase(protocol);
    }

    private boolean isValidIp(String ip) {
        return ip != null && IP_PATTERN.matcher(ip).matches();
    }

    private boolean containsSuspiciousDomain(List<String> sniList) {
        if (sniList == null) return false;
        
        // List of suspicious domains or patterns
        List<String> suspiciousPatterns = List.of(
            "xn--", // Internationalized domain names (could be used for homograph attacks)
            ".tk", ".gq", ".ml", ".ga", ".cf", // Free domains often used maliciously
            "ddns.net", "no-ip.com", // Dynamic DNS services
            "torproject.org", // Tor related
            "bitcoin", "cryptocurrency" // Crypto-related
        );
        
        return sniList.stream()
            .anyMatch(sni -> suspiciousPatterns.stream()
                .anyMatch(pattern -> sni.toLowerCase().contains(pattern)));
    }

    private boolean containsSuspiciousUserAgent(List<String> userAgents) {
        if (userAgents == null) return false;
        
        // List of suspicious user agent patterns
        List<String> suspiciousPatterns = List.of(
            "sqlmap", "nmap", "metasploit", "nikto", // Security tools
            "wget", "curl", "python-requests", // Scripted requests
            "libwww-perl", "java/", "go-http-client", // Scripted requests
            "", " ", null, // Empty user agents
            "mozilla/4.0", "mozilla/5.0" // Very generic user agents
        );
        
        return userAgents.stream()
            .anyMatch(ua -> ua == null || suspiciousPatterns.stream()
                .anyMatch(pattern -> ua.toLowerCase().contains(pattern.toLowerCase())));
    }
}