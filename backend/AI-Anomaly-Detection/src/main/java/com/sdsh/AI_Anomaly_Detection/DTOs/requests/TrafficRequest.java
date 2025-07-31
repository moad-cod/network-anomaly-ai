package com.sdsh.AI_Anomaly_Detection.DTOs.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TrafficRequest {

    @JsonProperty("src_ip")
    private String srcIp;

    @JsonProperty("dst_ip")
    private String dstIp;

    @JsonProperty("protocol")
    private String protocol;

    @JsonProperty("length")
    private int length;

    @JsonProperty("timestamp")
    private String timestamp;

    @JsonProperty("port")
    private int port;

    // Getters and Setters
    public String getSrcIp() { return srcIp; }
    public void setSrcIp(String srcIp) { this.srcIp = srcIp; }

    public String getDstIp() { return dstIp; }
    public void setDstIp(String dstIp) { this.dstIp = dstIp; }

    public String getProtocol() { return protocol; }
    public void setProtocol(String protocol) { this.protocol = protocol; }

    public int getLength() { return length; }
    public void setLength(int length) { this.length = length; }

    public String getTimestamp() { return timestamp; }
    public void setTimestamp(String timestamp) { this.timestamp = timestamp; }

    public int getPort() { return port; }
    public void setPort(int port) { this.port = port; }
}
