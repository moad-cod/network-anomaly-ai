package com.sdsh.AI_Anomaly_Detection.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sdsh.AI_Anomaly_Detection.models.NetworkPacket;

public interface PacketRepository extends JpaRepository<NetworkPacket, Long>{
        
}
