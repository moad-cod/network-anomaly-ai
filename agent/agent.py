import json
import requests

# Path to your JSON file (adjust if needed)
json_file_path = 'C:\\Users\\SNOW\\Desktop\\Project\\CyberSecurity\\network-anomaly-ai\\agent\\shared\\network_metrics_trafic.json'


# Your Spring Boot backend URL (adjust port and path as needed)
backend_url = 'http://localhost:8080/api/traffic'

def send_packet_data():
    # Read JSON data from file
    with open(json_file_path, 'r') as file:
        data = json.load(file)
    
    # Send POST request
    response = requests.post(backend_url, json=data)

    print(f'Status code: {response.status_code}')
    if response.status_code == 200:
        print('Data sent successfully!')
    else:
        print('Failed to send data:')
        print(response.text)

if __name__ == '__main__':
    send_packet_data()
