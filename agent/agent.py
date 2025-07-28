# agent/agent.py
from scapy.all import sniff, IP, TCP, UDP
import json
import requests
from datetime import datetime

API_URL = "http://localhost:8080/api/traffic"  # Replace with your Spring Boot URL

def packet_callback(packet):
    if IP in packet:
        proto = "TCP" if TCP in packet else "UDP" if UDP in packet else "Other"
        port = None
        if TCP in packet:
            port = packet[TCP].dport
        elif UDP in packet:
            port = packet[UDP].dport

        data = {
            "src_ip": packet[IP].src,
            "dst_ip": packet[IP].dst,
            "protocol": proto,
            "length": len(packet),
            "timestamp": datetime.now().isoformat(),
            "port": port
        }

        print("Packet:", data)
        try:
            response = requests.post(API_URL, json=data)
            print("✅ Sent → Status:", response.status_code)
        except Exception as e:
            print("❌ Failed to send:", e)

# Use interface index or name on Windows. Example: "Ethernet", "Wi-Fi"
print("🟡 Sniffing started...")
sniff(filter="ip", prn=packet_callback, store=0)
