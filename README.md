# ApicaAssignment - Spring Boot Microservices with Kafka

This project consists of two Spring Boot microservices: **User Service** and **Journal Service**. The services communicate via **Kafka** and follow a **role-based authentication** system using **Spring Security**.

## Architecture Overview
- **User Service**: Responsible for managing user details, user registration, and roles. It also publishes events to the Kafka topic `user-events` when any user-related data is modified.
- **Journal Service**: Consumes the `user-events` Kafka topic, logs the received user events, and persists them to a database (H2). It also has role-based access control to retrieve journaled user events.

---

## API Endpoints

### User Service API

#### **1. POST /api/users/register**  
Registers a new user.

- **Request Body**:
  ```json
  {
    "username": "user1",
    "password": "password123",
    "role": "USER"
  }
Response:

Status: 201 Created

Response Body:

json
Copy
Edit
{
  "message": "User registered successfully"
}
2. GET /api/users/{id}
Fetches user details by ID.

Request Parameters: id (User ID)

Response:

Status: 200 OK

Response Body:

json
Copy
Edit
{
  "id": 1,
  "username": "user1",
  "role": "USER"
}
3. PUT /api/users/{id}
Updates user details by ID.

Request Parameters: id (User ID)

Request Body:

json
Copy
Edit
{
  "username": "user1_updated",
  "role": "ADMIN"
}
Response:

Status: 200 OK

Response Body:

json
Copy
Edit
{
  "message": "User details updated"
}
4. DELETE /api/users/{id}
Deletes a user by ID.

Request Parameters: id (User ID)

Response:

Status: 200 OK

Response Body:

json
Copy
Edit
{
  "message": "User deleted successfully"
}
Journal Service API
1. GET /api/journal/events
Fetches all journaled user events.

Response:

Status: 200 OK

Response Body:

json
Copy
Edit
[
  {
    "event": "USER_CREATED",
    "userId": 1,
    "username": "user1",
    "role": "USER",
    "timestamp": "2025-04-05T12:34:56"
  }
]
Technologies Used
Spring Boot for building the microservices

Spring Security for role-based authentication and authorization

Apache Kafka for event-driven communication between services

H2 Database for data storage

Docker for containerization of both services

How to Run the Application Locally
Prerequisites:
Java 17 or above

Maven

Docker (for running Kafka and H2 in containers)

Steps:
Clone the Repository:

bash
Copy
Edit
git clone https://github.com/bobbyNithish/ApicaAssignment.git
cd ApicaAssignment
Run Kafka and H2 Database using Docker:

The docker-compose.yml file is already configured to run both Kafka and H2.

Run the following command to start the containers:

bash
Copy
Edit
docker-compose up
Run User Service:

Navigate to the user-service directory and run:

bash
Copy
Edit
mvn spring-boot:run
Run Journal Service:

Navigate to the journal-service directory and run:

bash
Copy
Edit
mvn spring-boot:run
Test the API Endpoints:

Once both services are running, you can test the APIs using tools like Postman or curl.

Testing the Application
Start both services using the steps above.

Test User Service: You can use Postman or curl to test the user-related endpoints:

Register a new user (POST /api/users/register)

Get user details (GET /api/users/{id})

Update user details (PUT /api/users/{id})

Delete a user (DELETE /api/users/{id})

Test Journal Service: Similarly, you can test the journal-related endpoint:

Get all journaled events (GET /api/journal/events)

Assumptions
Role-based authentication is handled using in-memory authentication for simplicity.

Kafka is set up locally, and both services publish/consume messages correctly.

H2 Database is used for persistence in the Journal Service.

You are running both services locally, and Kafka is properly configured.
