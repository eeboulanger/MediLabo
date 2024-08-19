# MedicalRecordService

## Overview

**MedicalRecord Microservice** is a microservice designed for accessing medical records associated with patients. The records
are stored as documents using a NoSQL database.

## Built With

- **Java 21**
- **Spring Boot**
- **Spring Data**
- **NoSQL (MongoDB)**

## Database

This microservice utilizes MongoDB as its database. To test the application, you'll need to create a MongoDB database
named `medical_records` and populate it with the necessary data.

Test data is provided in the `data.sql` file located in the `resources` folder.

## API Documentation

To access the API documentation and explore the available endpoints, start the application and navigate
to [Swagger UI](http://localhost:7070/swagger-ui/index.html).

## Getting Started

### Clone the Project

To get started, clone the project repository:

    git clone https://github.com/eeboulanger/medilabo.git

### Navigate to the Medical record microservice Directory

Once the repository is cloned, navigate to the directory:

    cd medilabo/medical-record-microservice

### Running with Docker

Ensure you have Docker installed on your machine.

To build and run the Docker image, use the following commands:

    docker build -t medicalrecordservice .
    docker run -p 7070:7070 medicalrecordservice
