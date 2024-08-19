# Gateway Service

## Overview

**Gateway** is a microservice responsible for routing requests to various microservices in the project. It acts as a
single entry point, directing traffic to the appropriate services based on the request.

## Built With

- **Java 21**
- **Spring Cloud Starter Gateway**
- **Spring Security**

## Security

This version of the Gateway Service implements **Basic Authentication** using Spring Security. For testing purposes, an
in-memory user has been configured:

- **Username:** `user`
- **Password:** `user`

This setup allows you to test the authentication mechanism without needing to set up a full user management system.

## Configuration

The Gateway Service is configured to run on port `8080`.

## Running the Gateway Service

### Clone the Project

To get started, clone the project repository:

    git clone https://github.com/eeboulanger/medilabo.git

### Navigate to the Gateway Directory

Once the repository is cloned, navigate to the directory:

    cd medilabo/gateway

### Running with Docker

Ensure you have Docker installed on your machine.

To build and run the Docker image, use the following commands:

    docker build -t gateway .
    docker run -p 8080:8080 gateway

### Running Manually

You can also run the application as a Spring Boot app using Maven.
The app will use server port 8080. Execute the following command:

    ./mvnw spring-boot:run

