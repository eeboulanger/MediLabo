# RiskEvaluator

## Overview
**RiskEvaluator microservice** is a microservice that assesses the risk of Type 2 diabetes for patients based on available patient data and medical records.

## Built With
- **Java 21**
- **Spring Cloud OpenFeign**
- **Eureka**

## Functionality
RiskEvaluator gathers data from two other microservices:
- **PatientService** (patient information)
- **MedicalRecordService** (medical records)

These services are connected via the Eureka discovery server, allowing for communication between microservices.

## API Documentation
To explore the available endpoints, navigate to [Swagger UI](http://localhost:9090/swagger-ui/index.html).

## Getting Started

### Clone the Project
To get started, clone the project repository:

    git clone https://github.com/eeboulanger/medilabo.git

### Navigate to the RiskEvaluator Directory

Once the repository is cloned, navigate to the directory:

    cd medilabo/risk-evaluator-microservice

### Running with Docker Compose
To simplify the setup process, it's recommended to use Docker Compose, which will automatically set up all the necessary services, including Eureka Server, PatientService, MedicalRecordService, and MongoDB.

To run the entire stack:

    docker-compose up
This will bring up all required services and the RiskEvaluator on port 9090.

### Running Manually
If you prefer to run the services manually:

- Start the Eureka Server.
- Start PatientService and MedicalRecordService.
- Ensure MongoDB is set up and populated as described in the MedicalRecordService setup.
- Run the RiskEvaluator application as a Spring Boot app
- The application runs on port `9090`. 
