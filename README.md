UPI Offline Mesh Demo

A Spring Boot project that simulates Offline UPI Payments using Mesh Networking. The project demonstrates how payment packets can travel between nearby devices without internet connectivity and get settled later when a bridge device reconnects to the banking server.

⸻

Tech Stack

* Java 17
* Spring Boot
* Spring Data JPA
* H2 Database
* Maven
* REST APIs
* Postman

⸻

Features

* Account Management
* Direct UPI Transfer
* Transaction History
* Offline Payment Packet Creation
* Mesh Gossip Protocol
* Bridge Device Settlement
* Duplicate Packet Protection
* Device Online/Offline Simulation
* Mesh Network Summary
* Mesh Reset API

⸻

Project Structure

src
├── controller
├── dto
├── model
├── service
└── resources

⸻

REST APIs

Accounts

Get All Accounts

GET /api/accounts

⸻

Transactions

Get Transaction History

GET /api/transactions

⸻

Direct Transfer

Transfer Money

POST /api/transfer

Request Body

{
"senderVpa": "alice@demo",
"receiverVpa": "bob@demo",
"amount": 500
}

⸻

Offline Mesh Payment Flow

Send Payment Packet

POST /api/mesh/send

Request Body

{
"senderVpa": "alice@demo",
"receiverVpa": "bob@demo",
"amount": 300
}

⸻

Run Gossip Protocol

POST /api/mesh/gossip

⸻

Settle Payment

POST /api/mesh/settle

⸻

View Mesh Devices

GET /api/mesh/devices

⸻

View Mesh Summary

GET /api/mesh/summary

⸻

Reset Mesh

POST /api/mesh/reset

⸻

Device Management

Make Device Offline

POST /api/device/offline/phone-b

Make Device Online

POST /api/device/online/phone-b

⸻

Demo Flow

1. Reset Mesh
2. Send Offline Payment Packet
3. Run Gossip Protocol
4. Settle Payment
5. Verify Updated Account Balances
6. View Transaction History
7. Attempt Duplicate Settlement
8. Verify Duplicate Protection

⸻

Sample Output

Before Settlement

Alice : 5000
Bob   : 1000

After Settlement

Alice : 4700
Bob   : 1300

Duplicate Settlement

{
"status": "FAILED",
"reason": "Duplicate packet rejected"
}

⸻

Current Limitations

* Mesh communication is simulated.
* Uses H2 in-memory database.
* No real Bluetooth or Wi-Fi Direct communication.
* No encryption implemented.
* Linear gossip routing.

⸻

Future Improvements

* AES/RSA Encryption
* QR Code Based Offline Payments
* Graph-Based Mesh Routing
* Redis Idempotency
* MySQL/PostgreSQL Support
* Docker Deployment
* Web Dashboard
* Android Client
* Real Bluetooth/Wi-Fi Direct Integration

⸻

Author

Ajay Singh Kaurav

B.Tech Computer Science Engineering

Spring Boot Backend Developer