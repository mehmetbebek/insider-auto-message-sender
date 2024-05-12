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
- Start/Stop automatic message sending: `http://localhost:8080/swagger-ui.html#/`
- Retrieve a list of sent messages: `http://localhost:8080/swagger-ui.html#/`

## Technologies Used
- Spring Boot
- Java
- Redis
- Docker
- Postgres Database
- Google Phone Number Check(libphonenumber)

## Support
For any issues or inquiries, please contact [Mehmet BEBEK] at [bmbebek@gmail.com].