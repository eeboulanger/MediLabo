# Eureka Server

## Overview

**Eureka Microservice** is a discovery server that acts as a registry for microservices in the project. It enables
communication between microservices by allowing them to discover each other dynamically.

## Built With

- **Java 21**
- **Spring Cloud Starter Netflix Eureka Server**

## Functionality

The Eureka Server allows microservices like `Patient-microservice`, `MedicalRecord-microservice`, and `RiskEvaluator-microservice` to register
themselves and discover other services. This enables interaction between microservices without hard-coded service URLs.

## Configuration

The Eureka Server is configured to run on port `9102`.

## Getting Started

### Clone the Project

To get started, clone the project repository:

    git clone https://github.com/eeboulanger/medilabo.git

### Navigate to the EurekaServer Directory

Once the repository is cloned, navigate to the directory:

    cd medilabo/eureka-microservice

### Running with Docker

Ensure you have Docker installed on your machine.

To build and run the Docker image, use the following commands:

    docker build -t eureka-microservice .
    docker run -p 9102:9102 eureka-microservice

### Running Manually

You can also run the Eureka Server as a Spring Boot app using Maven. Execute the following command:

    ./mvnw spring-boot:run
