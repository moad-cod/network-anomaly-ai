# Network Anomaly Detection with AI

This project captures live traffic, detects anomalies using ML, and visualizes it in real-time.

## Stack:
- Python (Agent)
- Spring Boot + ML (Backend)
- Django + PostgreSQL (UI)
- Docker, Prometheus, Grafana

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
        Option A: Scapy → more control (best for learning)
            pip install scapy requests
            ⚠️ On Windows, Scapy uses WinPcap or Npcap (recommended) to capture packets.
            👉 Install Npcap (choose “install in WinPcap compatibility mode”):
            🔗 https://nmap.org/npcap/

        Option B: Pyshark → wrapper for Wireshark, easier but needs tshark installed


    Capture: src IP, dst IP, port, protocol, length, timestamp

    Format output as JSON

    Send POST request to Spring Boot using requests

    Test with Postman or local mock server

    📤 Output: Functional Python agent sending test data

🟠 Day 3: Spring Boot Backend + ML
    🎯 Goal: Receive traffic and detect anomalies
    ✅ Tasks:

    Setup Spring Boot project (Maven/Gradle)

    Create /api/traffic REST endpoint

    Train/load simple ML model (e.g., IsolationForest)

    Classify: normal / anomaly

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