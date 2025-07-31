# Network Anomaly Detection with AI

This project captures live traffic, detects anomalies using ML, and visualizes it in real-time.

## Stack:
- Python (Agent)
- Spring Boot + ML (Backend)

        |    AI-Anomaly-Detection/
        ├── src/
        │   ├── main/
        │   │   ├── java/com/sdsh/AI_Anomaly_Detection/
        │   │   │   ├── config/
        │   │   │   │   └── ModelConfig.java
        │   │   │   ├── controllers/
        │   │   │   │   └── TrafficController.java
        │   │   │   ├── dtos/
        │   │   │   │   ├── requests/
        │   │   │   │   │   └── TrafficRequest.java
        │   │   │   │   └── responses/
        │   │   │   │       └── AnomalyResponse.java
        │   │   │   ├── models/
        │   │   │   │   └── NetworkPacket.java
        │   │   │   ├── repositories/
        │   │   │   │   └── PacketRepository.java
        │   │   │   ├── services/
        │   │   │   │   ├── impl/
        │   │   │   │   │   └── AnomalyDetectionServiceImpl.java
        │   │   │   │   └── AnomalyDetectionService.java
        │   │   │   └── AIAnomalyDetectionApplication.java
        │   │   └── resources/
        │   │       ├── data/
        │   │       │   └── network_traffic.csv
        │   │       ├── application.yml
        │   │       └── application-dev.yml
        │   └── test/
        │       ├── java/com/sdsh/AI_Anomaly_Detection/
        │       │   └── services/
        │       │       └── AnomalyDetectionServiceTest.java
        │       └── resources/
        │           └── test-data/
        │               └── test_traffic.csv

- Django + PostgreSQL (UI)
- Docker, Prometheus, Grafana

# >hy we are using java on the backend 
    1. ✅ Stability, Structure, and Scalability
    Spring Boot provides:

    Strong typed APIs

    Clear architecture with layers (controllers, services, repositories)

    Thread-safe, high-concurrency, suitable for heavy request loads

    Ready for large systems and enterprise deployment

    → If you later want to scale this from a 7-day project to a real-time NIDS, Java scales better than a quick Python script.

    2. ✅ Decoupling Concerns
    You split responsibilities like a real-world system:

    Python agent: Lightweight, low-level packet sniffing (Scapy is unbeatable)

    Java backend: Data processing, REST API, anomaly detection, logging, DB, future auth

    → Each language does what it's best at.

    3. ✅ Modern Java Has ML Libraries (Smile, MOA)
    You don’t have to rely on Python for ML anymore.

    Libraries like Smile and MOA let you use ML inside Java apps without needing Python or TensorFlow bridges.

    4. ✅ Spring Boot Ecosystem = Powerful Backend Platform
    Out-of-the-box support for:

    RESTful APIs

    Security (JWT, OAuth)

    Metrics (Micrometer → Prometheus)

    Docker / Cloud deployment

    JPA + PostgreSQL / H2 DB

    → With Python, you’d have to glue together Flask/FastAPI, SQLAlchemy, Celery, etc.

    5. 🛡️ Security + Performance
    Java runs faster on high-load servers (JVM optimizations)

    Less prone to dynamic typing bugs at runtime

    Used in finance, banking, enterprise-grade NIDS/SIEM



## Architecture:
[Insert diagram]

## Roadmap:
🟢 Day 1: Architecture + Repo Setup
    🎯 Goal: Plan your system, set up tools & structure
    ✅ Tasks:

    Create GitHub repo: network-anomaly-ai

    Design architecture diagram (use draw.io or Excalidraw)

    Plan 3 main services:

    Python Agent → Spring Boot API → Django UI

    Create folders:

    /agent/, /backend/, /dashboard/, /docker/

    Setup initial README.md (vision, stack, roadmap)

    Install tools:

    Python, Java 17+, Spring CLI, Docker, PostgreSQL, Prometheus, Grafana

    📤 Output: Project folder created, architecture drawn, tools ready

🟡 Day 2: Python Packet Sniffer Agent
    🎯 Goal: Build and test traffic capture agent
    ✅ Tasks:

    Write script using scapy or pyshark 
        Option A: Scapy 
        → 
        {
            Scapy is a library supported by both Python2 and Python3. It is used for interacting with the packets on the network. It has several functionalities through which we can easily forge and manipulate the packet. Through scapy module we can create different network tools like ARP Spoofer, Network Scanner, packet dumpers etc. This module can be used to create more advanced tools related network security and ethical hacking.
        } 
            pip install scapy requests
            ⚠️ On Windows, Scapy uses WinPcap or Npcap (recommended) to capture packets.
            👉 Install Npcap (choose “install in WinPcap compatibility mode”):
            🔗 https://nmap.org/npcap/

        Option B: Pyshark 
        → 
        {
            yShark is a Python 3 wrapper for TShark. Tshark is a network protocol analyzer that allows you to capture packet data from a live network, or read packets from a previously saved capture file. Tshark itself is the command-line version of Wireshark and PyShark allows Python packet parsing using Wireshark dissectors.
        }


    Capture: src IP, dst IP, port, protocol, length, timestamp

    Format output as JSON

    Send POST request to Spring Boot using requests
        Step 1: Create Spring Boot Project (if not done yet)
            You can use https://start.spring.io to generate it with:

            Dependencies:

                Spring Web 
                ==>
                { 
                    This is a module within the larger Spring Framework that provides core HTTP integration and web-oriented features. It's the foundation for building web applications in Spring. 
                }

                Spring Boot DevTools
                ==>
                {
                    It aims to reduce development time, by intelligently detecting code changes to auto-recompile your code reducing the need to manually restart your application.
                }

                Spring Data JPA
                ==>
                {
                    The java persistence API provides a specification for persisting, reading, and managing data from your java object to your relational tables in the database. JPA specifies the set of rules and guidelines for developing interfaces that follow standards.
                }

                PostgreSQL Driver 

                Lombok
                ==>
                {
                    project Lombok is a java library tool that is used to minimize/remove the boilerplate code and save the precious time of developers during development by just using some annotations.
                }

            -Create Controller to Handle POST named TrafficController 

            -Add an Embedded Database (e.g. H2) for Testing
            ==>
            {

            }
            -add this to your pom.xml:

                <dependency>
                    <groupId>com.h2database</groupId>
                    <artifactId>h2</artifactId>
                </dependency>

            -Then in application.properties:

                spring.datasource.url=jdbc:h2:mem:testdb
                spring.datasource.driverClassName=org.h2.Driver
                spring.datasource.username=sa
                spring.datasource.password=
                spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
                spring.h2.console.enabled=true



    Test with Postman or local mock server

    
    --Advanced Topics {Ignore this rn just try to implement the basic foundation}
    {
        ⚙️ Prerequisites
    On Windows:
        Python 3.x installed

        VMware Workstation or Player installed

        A shared folder path (e.g., C:\Users\SNOW\Desktop\Project\CyberSecurity\network-anomaly-ai\agent\shared)

        On Kali Linux (VM):
        Bettercap installed:

        sudo apt update
        sudo apt install bettercap
        VMware Tools or Open VM Tools installed and working:

        sudo apt install open-vm-tools open-vm-tools-desktop fuse
        🗂️ 1. Create the Shared Folder
        On your Windows host:

        Create a folder:

        C:\Users\SNOW\Desktop\Project\CyberSecurity\network-anomaly-ai\agent\shared
        Open VMware > Settings > Options > Shared Folders:

        Enable Shared Folders → Always enabled

        Click Add → Choose the folder → Give it a name (e.g., shared)

        🖥️ 2. Mount Shared Folder in Kali
        After starting Kali VM, run:

        sudo mkdir -p /mnt/hgfs
        sudo vmhgfs-fuse .host:/ /mnt/hgfs -o allow_other
        Your shared folder will now be accessible in Kali as:

        /mnt/hgfs/shared
        📡 3. Create Bettercap Script (caplet)
        In Kali, create the caplet file:
        nano ~/bettercap-sni.cap

        Paste:

        set net.sniff.output /tmp/sniffed.pcap
        set events.stream.output /tmp/sni.log
        set events.stream.verbose true
        net.sniff on
        events.stream on

        ▶️ 4. Start Bettercap
        Run Bettercap using the caplet:

        sudo bettercap -iface eth0 -caplet ~/bettercap-sni.cap
        Replace eth0 with your actual network interface if needed (ip a to check).

        🔁 5. Auto-Sync sni.log to Windows Shared Folder
        Create a background sync script:

        nano ~/share_sync.sh
        Paste:

        #!/bin/bash
        while true; do
        cp /tmp/sni.log /mnt/hgfs/shared/sni.log 2>> /tmp/sync_errors.log
        sleep 5
        done

        Make it executable and run it:
        chmod +x ~/share_sync.sh
        nohup ~/share_sync.sh &



        GATHER CREDETIALS 
        ==>
        {
            Metric	           |     Tool	        Command Example
            Packet Size/Rate  |	tshark, tcpdump	|tshark -i eth0 -T fields -e frame.len
            Flow Duration  |	argus, nfdump	 | ra -r flows.argus -s dur
            Protocol/Ports |	netstat, tshark	| netstat -tulnp
            DNS Query Rate |	dnstop	        |dnstop -l 5 eth0
            SYN Flood Rate  |	tcpdump	         |tcpdump "tcp[tcpflags] & tcp-syn != 0"
            User-Agent/Method  |	mitmproxy, tshark |	tshark -Y "http" -e http.user_agent


        }
    }

🟠 Day 3: Spring Boot Backend + ML
    🎯 Goal: Receive traffic and detect anomalies
    ✅ Tasks:

    Setup Spring Boot project (Maven/Gradle)

    Create /api/traffic REST endpoint

    Train/load simple ML model (e.g., IsolationForest)

    Classify: normal / anomaly
    CREDENTIALS ==> (Using: BetterCap, TcpDump, Tshark)
    {
        1. General Traffic Features (Baseline)
            Feature	Description	Anomaly Indicator
            packet_size	Size (bytes) of each packet	Unusually large/small packets
            packet_rate	Packets/second per host	Sudden spikes/drops
            flow_duration	How long a connection lasts	Extremely short/long sessions
            protocol	TCP/UDP/ICMP/etc.	Rare protocols (e.g., ICMP floods)
            ports	Source/destination ports	Unusual port activity (e.g., 4444)

        2. Protocol-Specific Features

            A. TLS/HTTPS (Encrypted Traffic)
                Feature	Description	Anomaly Indicator
                SNI (Server Name Indication)	Domain being visited (e.g., google.com)	Connections to known malicious domains
                JA3/JA3S Fingerprints	Client/server TLS fingerprints	Malware C2 traffic
                Certificate Issuer	Who issued the SSL cert	Self-signed/unknown CAs
                Cipher Suites	Encryption algorithms used	Weak/obsolete ciphers

            B. DNS Traffic
                Feature	Description	Anomaly Indicator
                DNS Query Rate	Number of DNS queries per second	DNS tunneling (excessive queries)
                Domain Length	Length of domain name (e.g., long.malicious.xn--xy)	DGA (Domain Generation Algorithm)
                NXDOMAIN Responses	Failed DNS lookups	Probing for C2 servers

            C. HTTP Traffic
                Feature	Description	Anomaly Indicator
                User-Agent	Client browser/device type	Scanners (e.g., sqlmap)
                HTTP Methods	GET/POST/PUT, etc.	Suspicious methods (e.g., PUT /shell.php)
                URL Path	Requested path (e.g., /wp-admin)	Web shell access

        3. Behavioral Anomaly Indicators
            Feature	Description	Anomaly Example
            Geolocation Mismatch	IP location vs. user’s usual location	Account takeover (ATO)
            Time-of-Day Activity	Traffic at unusual hours	Insider threats
            Lateral Movement	Internal host scanning (e.g., SMB/NFS)	Ransomware propagation
            Data Exfiltration	Large outbound transfers (e.g., .zip)	Data theft
        
        4. Threat-Specific Features
            A. Malware/C2 (Command & Control)
                Feature	Description	Example
                Beaconing Interval	Regular C2 check-ins (e.g., every 5 mins)	APT malware
                Random Subdomains	sdh83j.malicious.com	DGA-based botnets
                Non-Standard Ports	HTTP on port 8080 (instead of 80)	Evasion tactics

            B. DDoS Attacks
                Feature	Description	Example
                SYN Flood Rate	Half-open TCP connections	SYN flood attack
                UDP Packet Size	Large UDP packets (e.g., DNS amplification)	Reflection attacks

            C. Insider Threats
                Feature	Description	Example
                Data Access Patterns	Unusual database queries	SQL injection/data theft
                Privilege Escalation	Sudden sudo usage	Compromised account
        
    }

    Log results to console

    📤 Output: Working backend API with ML anomaly detection

🔵 Day 4: Django Dashboard UI
    🎯 Goal: Create admin dashboard to view anomalies
    ✅ Tasks:

    Create Django project + configure PostgreSQL

    Define AnomalyLog model (IP, timestamp, label, severity)

    Create API or view to pull anomaly data

    Build UI table (Tailwind CSS or Bootstrap)

    Optional: Filters (IP, date)

    📤 Output: Functional dashboard showing anomalies

🟣 Day 5: Alert Integration + Docker Setup
    🎯 Goal: Link services and containerize the system
    ✅ Tasks:

    Agent → Spring Boot → Django flow tested

    Decide data flow: DB logging vs API relay

    Create Dockerfiles for:

    Agent, Backend, Frontend

    Build docker-compose.yml for full stack

    📤 Output: Complete stack working in Docker Compose

🟤 Day 6: Monitoring + Metrics
    🎯 Goal: Add observability & real-time monitoring
    ✅ Tasks:

    Setup Prometheus + Grafana

    Expose metrics in Spring Boot using Micrometer

    Monitor:

    of packets
    of anomalies
    Uptime

    Create Grafana dashboard

    📤 Output: Real-time metrics visible in Grafana

🔴 Day 7: Polish + Publish
    🎯 Goal: Finalize, document, and share your work
    ✅ Tasks:

    Write full README.md:

    Overview + Tech Stack

    Architecture diagram

    How to run (manual + Docker)

    Screenshots or video

    Push code to GitHub

    Post on LinkedIn:

🎯 Title: “I built an AI-Powered Network Anomaly Detection System in 7 Days — here’s what happened 🚀”

📤 Output: Project published and visible to the world

🔁 Optional Stretch Tasks (Post-Day 7)
Task	Purpose
Train better ML model (NSL-KDD)	Improve detection accuracy
Add Django login/auth	Multi-user support
Store logs in PostgreSQL/Elastic	Long-term visibility
Deploy to Render/Railway/VPS	Demo link for recruiters