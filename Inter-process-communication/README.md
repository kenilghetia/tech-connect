# Inter-Process Communication (IPC)

Inter-process communication (IPC) is a mechanism that allows processes to communicate with each other and synchronize their actions. Below are two examples demonstrating different IPC methods: Sockets and Remote Procedure Call (RPC).

## 1st Example: Communication Using Sockets

This example demonstrates how to use sockets for IPC.

### Server
- Listens for incoming connections from clients.
- Receives messages from clients.
- Broadcasts received messages to all connected clients.

### Client
- Connects to the server.
- Sends messages to the server.
- Receives messages broadcasted by the server.

## 2nd Example: Communication Using Remote Procedure Call (RPC)

Remote Procedure Call (RPC) is a powerful paradigm for implementing IPC in a distributed system. RPC allows a program to cause a procedure to execute on a different address space (commonly on another physical machine). This mechanism is useful in scenarios where you need to perform operations on a remote server as if they were local function calls.

In a microservices architecture, different services handle distinct functionalities of an application. These services need to communicate with each other to fulfill complex operations. RPC can facilitate this communication.

### Scenario: Simple RPC-based Microservice for a Fintech Application

In this example, one service handles user account operations, and another handles transaction operations.

### User Service
- Manages user creation and retrieval.
- Listens on port `8001`.

### Transaction Service
- Handles transactions by calling the User Service to get and update user information.
- Listens on port `8002`.

### Client
- Interacts with both services to create a user, perform a transaction, and check the user's balance.
