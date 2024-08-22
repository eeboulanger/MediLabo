# PatientService

## Overview

**Patient Microservice** is a microservice designed for accessing patient information such as last name, first name,
birthdate, phone number, and address.

## Built With

- **Java 21**
- **Maven**
- **Spring Boot**
- **Spring Data**
- **SQL**

## Database

This version uses an H2 in-memory database that is automatically populated when starting the application. The data is
sourced from the `data.sql` file located in the `resources` folder.

## API Documentation

To access the API documentation and explore the available endpoints, start the application and navigate
to [Swagger UI](http://localhost:8081/swagger-ui/index.html).

## Getting Started

### Clone the Project

To get started, clone the project repository:

    git clone https://github.com/eeboulanger/medilabo.git

### Navigate to the PatientService Directory

Once the repository is cloned, navigate to the Patient microservice directory:

    cd medilabo/patient-microservice

### Running with Docker

Ensure you have Docker installed on your machine.

To build and run the Docker image, use the following commands:

    docker build -t patient-microservice .
    docker run -p 8081:8081 patient-microservice

### Running Manually

You can also run the application as a Spring Boot app using Maven.
The app will use server port 8081. Execute the following command:

    ./mvnw spring-boot:run
