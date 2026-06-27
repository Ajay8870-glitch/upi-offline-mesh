UPI Offline Mesh Demo

A Spring Boot backend that simulates offline UPI-style payments using mesh networking.
The project demonstrates how payment packets can move between nearby devices without internet and later settle when a bridge device reconnects to the server.

Tech Stack

* Java 17
* Spring Boot
* Spring Data JPA
* H2 Database
* Maven
* REST APIs
* Postman

Features

* Account balance management
* Direct money transfer API
* Transaction history
* Offline payment packet creation
* Mesh gossip protocol
* Bridge-device settlement
* Duplicate packet protection
* Device online/offline simulation
* Mesh summary API
* Mesh reset API

Project Structure

src
├── main
│   ├── java
│   │   └── com.demo.upimesh
│   │       ├── controller
│   │       ├── dto
│   │       ├── model
│   │       └── service
│   └── resources
└── test

API Endpoints

Accounts

GET /api/accounts

Returns all demo accounts and balances.

Transactions

GET /api/transactions

Returns recent transaction history.

Direct Transfer

POST /api/transfer

Request body:

{
"senderVpa": "alice@demo",
"receiverVpa": "bob@demo",
"amount": 500
}

Send Offline Mesh Packet

POST /api/mesh/send

Request body:

{
"senderVpa": "alice@demo",
"receiverVpa": "bob@demo",
"amount": 300
}

Run Gossip Protocol

POST /api/mesh/gossip

Moves packets from one virtual device to the next.

Settle Mesh Packet

POST /api/mesh/settle

Settles the packet available at the bridge device.

View Mesh Devices

GET /api/mesh/devices

Shows all virtual devices, online/offline status, and packet inboxes.

Mesh Summary

GET /api/mesh/summary

Returns total devices, online devices, offline devices, packets in mesh, and settled packet count.

Reset Mesh

POST /api/mesh/reset

Clears all mesh packets and settled packet IDs.

Device Status

POST /api/device/offline/phone-b

Marks phone-b as offline.

POST /api/device/online/phone-b

Marks phone-b as online.

Demo Flow

1. Reset the mesh.
2. Send an offline payment packet.
3. Run the gossip protocol.
4. Settle the packet from the bridge device.
5. Verify updated account balances.
6. View transaction history.
7. Attempt duplicate settlement.
8. Confirm duplicate packet rejection.

Sample Output

Initial balances:

Alice: 5000
Bob:   1000

After settling a ₹300 mesh payment:

Alice: 4700
Bob:   1300

Duplicate settlement response:

{
"status": "FAILED",
"reason": "Duplicate packet rejected"
}

Current Limitations

* Mesh communication is simulated.
* H2 is used as an in-memory database.
* No real Bluetooth or Wi-Fi Direct integration.
* No encryption is implemented yet.
* Gossip routing is linear, not graph-based.

Future Improvements

* AES/RSA encryption
* QR-based offline payment packet generation
* Graph-based mesh routing
* Redis-based idempotency
* MySQL/PostgreSQL persistence
* Docker deployment
* Web dashboard
* Android client
* Real Bluetooth or Wi-Fi Direct integration

Author

Ajay Singh Kaurav
B.Tech Computer Science Engineering
Spring Boot Backend Developer