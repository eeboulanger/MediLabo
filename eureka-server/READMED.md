# Eureka Server

## Overview

**Eureka Server** is a service discovery server that acts as a registry for microservices in the project. It enables
communication between microservices by allowing them to discover each other dynamically.

## Built With

- **Java 21**
- **Spring Cloud Starter Netflix Eureka Server**

## Functionality

The Eureka Server allows microservices like `PatientService`, `MedicalRecordService`, and `RiskEvaluator` to register
themselves and discover other services. This enables interaction between microservices without hard-coded service URLs.

## Configuration

The Eureka Server is configured to run on port `9102`.

## Getting Started

### Clone the Project

To get started, clone the project repository:

    git clone https://github.com/eeboulanger/medilabo.git

### Navigate to the EurekaServer Directory

Once the repository is cloned, navigate to the directory:

    cd medilabo/eureka-server

### Running with Docker

Ensure you have Docker installed on your machine.

To build and run the Docker image, use the following commands:

    docker build -t eureka-server .
    docker run -p 9102:9102 eureka-server

### Running Manually

You can also run the Eureka Server as a Spring Boot app using Maven. Execute the following command:

    ./mvnw spring-boot:run
