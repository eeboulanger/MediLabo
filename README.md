# MediLabo Diabetes Type 2 Risk Evaluator App

## About
The MediLabo app fetches patient data and evaluates the patient's risk for Type 2 diabetes based on factors such as medical records, known triggers, gender, and age. 
This tool is designed to help healthcare professionals and researchers assess diabetes risk with the provided test data.

## Built With
### **Front End**
- **Angular 18**: Framework for building standalone, dynamic app.

### **Back end**
- **Java 21**: High-performance language for backend development.
- **Spring Boot**: Framework for building microservices.
- **Spring Cloud Gateway**: Used for routing and filtering requests to microservices.
- **Spring Security**: Provides authentication and authorization.
- **Spring Data**: Simplifies data access.

### **Database**
- **MongoDB**: NoSQL database used to store patient medical records.
- **H2**: In-memory SQL database used for testing with patient data (will be replaced with MySQL in production).

## Architecture Overview

The MediLabo Diabetes Type 2 Risk Evaluator App is built using a microservice architecture, which offers flexibility and scalability. Here’s a brief overview of the key components:

- **Microservices:** The backend is composed of multiple Spring Boot microservices, each responsible for a specific part of the application:
  - **Eureka-microservice:** Acts as a service registry, allowing microservices to discover each other.
  - **Gateway:** A Spring Cloud Gateway that handles routing of requests to the appropriate microservices and manages authorization using Spring Security.
  - **Patient microservice:** Manages patient data, using an H2 database for testing and a SQL database in production.
  - **Medical-record microservice:** Manages medical records, using MongoDB for data storage.
  - **Risk-evaluator microservice:** Analyzes patient data to evaluate their risk for Type 2 diabetes.

- **Frontend:** The user interface is built with Angular 18, providing a dynamic and responsive user interface.

This architecture allows for separation of concerns.

## Getting Started
There are two primary ways to get started with the MediLabo app: using Docker or installing it manually.

### Run App with Docker
Running the app in Docker is the easiest way to get started, as it includes all necessary services and test data.

1. **Clone the Project:**
   ```bash
   git clone https://github.com/eeboulanger/medilabo.git

2. **Run docker compose**
  - Ensure Docker is installed on your computer.
  - Run the following command to start the application with all necessary services:
    ```bash
    docker-compose up --build
  - The Docker setup includes pre-populated test data, allowing you to explore the app fully without additional setup.


### Manually install app
If you prefer to install and run the app manually, follow these steps:

1. **Clone the Project:**
   ```bash
   git clone https://github.com/eeboulanger/medilabo.git

2. **Install NPM Packages:**
Navigate to the Front-end directory and install the necessary packages:
   ```bash
    npm install
  
3. **Set Up the Backend:**
- Make sure Java 21 is installed on your machine.
- Run the microservices as Spring Boot applications:
   - **Eureka-microservice:** Service registry for microservices.
   - **Gateway:** Handles routing and authorization.
   - **Patient microservice:** Manages patient-related data.
   - **Medical-record microservice:** Manages medical records.
   - **Risk-evaluator microservice:** Evaluates patient risk for diabetes.

The microservices will automatically register with the Eureka microservice. 
The Gateway will handle authorization, with an in-memory user provided for testing purposes.


4. **Set up the databases:**
- The 'Patient microservice' uses an H2 database for testing purposes and will automatically generate test patient data.
- Set Up MongoDB:
  - Install and run a MongoDB server locally.
  - Populate the MongoDB database with test data by using the file located at ./medical-record-microservice/main/resources/data.json


## Usage
Once the application is up and running, navigate to [http://localhost:4200](http://localhost:4200) to start using the app.

The default credentials to log in to the app are:

- **Username:** `user`
- **Password:** `user`

The user interface allows you to:

- View a list of patients and their risk for Type 2 diabetes based on the provided patient data.
- Edit a patient’s data or add a new patient.
- Add a new note (medical record) for a patient.


