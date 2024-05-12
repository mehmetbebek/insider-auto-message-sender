# Automatic Message Sending System

## Project Description
This project is aimed at developing an automatic message sending system using Spring Boot, Java, Redis, and Docker. The system retrieves messages from a database, sends them to recipients, and caches information about sent messages in Redis.

## Installation and Configuration
1. Clone the repository:
   ```bash
   git clone <repository_url>

2. Navigate to the project directory:
    ```
    cd <project_directory>
3. Run Docker Compose to build and start the application:
    ```
   docker-compose up -d

## Usage
- Once the application is deployed and running, the automatic message sending system will start processing unsent records from the database.
- Access the Swagger UI to interact with the API endpoints:
- Start/Stop automatic message sending: `http://localhost:7777/insider/swagger-ui/index.html#/message-scheduler-controller/changeMessageSendStatus`
- Retrieve a list of sent messages: `http://localhost:7777/insider/swagger-ui/index.html#/message-controller/getMessages`
- Add a message to system `http://localhost:7777/insider/swagger-ui/index.html#/message-controller/saveMessage`

## Technologies Used
- Spring Boot
- Java
- Redis
- Docker
- Postgres Database
- Google Phone Number Check(libphonenumber)

## Api Usages
### Base URL

All API endpoints mentioned in this documentation are relative to the following base URL:

API Endpoints
1. Save Message
   ```
   Endpoint: POST http://localhost:7777/insider/api/v1/messages
   Description: Saves a new message to the database.
   Request Body:
   jsonCopy code{
     "content": "Hello, world!",
     "recipientPhoneNumber": "+905453713454"
   }

Response:

Status: 201 Created


2. Get Messages

   ```
   Endpoint: GET http://localhost:7777/insider/api/v1/messages
   Description: Retrieves a list of messages from the database.
   Query Parameters:
   sent (optional): If provided with a value of true, returns only sent messages. If false or not provided, returns all messages.
   
Response:
[
  {
    "content": "Hello, world!"
  },
  {
    "content": "Hello, again!"
  }
]


3. Change Message Sending Status

   ```
   Endpoint: PUT http://localhost:7777/insider/api/v1/scheduler
   Description: Starts or stops the automatic message sending scheduler.
   Query Parameters:
   enabled (required): If set to true, starts the scheduler. If set to false, stops the scheduler.


Response:

Status: 200 OK
Body: "Message scheduler enabled: true" or "Message scheduler enabled: false"

## Support
For any issues or inquiries, please contact [Mehmet BEBEK] at [bmbebek@gmail.com].