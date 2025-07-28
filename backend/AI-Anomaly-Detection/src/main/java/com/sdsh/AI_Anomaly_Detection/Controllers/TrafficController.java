package com.sdsh.AI_Anomaly_Detection.Controllers;

import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TrafficController {
    @PostMapping("/traffic")
    public String receiveTraffic(@RequestBody Map<String, Object> data) {
        System.out.println("🔥 Received packet: " + data);
        return "OK";
    }
}
