# Automatic Message Sending System

## Project Description
This project is aimed at developing an automatic message sending system using Spring Boot, Java, Redis, and Docker. The system retrieves messages from a database, sends them to recipients, and caches information about sent messages in Redis.

## Installation and Configuration
1. Clone the repository:
   ```bash
   git clone https://github.com/mehmetbebek/insider-auto-message-sender.git
   or via ssh
   git clone git@github.com:mehmetbebek/insider-auto-message-sender.git

2. Navigate to the project directory:
    ```
    cd insider-auto-message-sender
3. Run Docker Compose to build and start the application:
    ```
   docker-compose up -d
    or(if you have newer docker)
    docker compose up -d
    if you want to rebuild application service:
   docker-compose up -d --no-deps --build insider_auto_message_sender_app
    or(if you have newer docker)
    docker compose up -d --no-deps --build insider_auto_message_sender_app

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
- Postgres Database(for application)
- H2 Database(for application testing)
- Google Phone Number Check(libphonenumber)

## Api Usages
### Postman Collection
Postman collection is attached to test/resources folder. It can be imported to postman and start testing immediately.

<img width="1836" alt="postman collection" src="https://github.com/mehmetbebek/insider-auto-message-sender/assets/663996/40d086a1-a2c6-46ce-b925-40ee142b8532">

### Swagger
Swagger is integrated and it can be used right after project running.

<img width="1822" alt="swagger" src="https://github.com/mehmetbebek/insider-auto-message-sender/assets/663996/ddbf340c-1689-4d81-840c-71ebf28060e0">


### Apis

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

## Use Case Diagram

![use case diagram](https://github.com/mehmetbebek/insider-auto-message-sender/assets/663996/b24b58a0-e351-443d-80a4-03088a0ca9c2)

## Database Diagram

![database diagram](https://github.com/mehmetbebek/insider-auto-message-sender/assets/663996/e6932bb7-bfb8-4ce0-93e3-274788b17994)

## Tests
Added integration tests for controllers, and unit tests for service classes.

<img width="733" alt="test coverage" src="https://github.com/mehmetbebek/insider-auto-message-sender/assets/663996/0576465a-16b7-45e9-90d1-f92a4f67e02c">


## Support
For any issues or inquiries, please contact [Mehmet BEBEK] at [bmbebek@gmail.com].
