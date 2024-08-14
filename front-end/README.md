# Front-End Web Application

## Overview

This is a dynamic web application built with **Angular 18**. It utilizes **Angular Router** for navigation and **AuthGuard** for securing routes. The application serves as the user interface for interacting with the various microservices in the system.

## Built With

- **Angular 18**
- **Angular Router**
- **AuthGuard**

## Configuration

The application is configured to run on port `4200`.

## Getting Started

### Clone the Project

To get started, clone the project repository:

    git clone https://github.com/eeboulanger/medilabo.git

### Navigate to the FrontEnd Directory

Once the repository is cloned, navigate to the directory:

    cd medilabo/front-end

### Running with Docker

Ensure you have Docker installed on your machine.

To build and run the Docker image, use the following commands:

    docker build -t front-end .
    docker run -p 4200:4200 front-end

### Running Manually

You can also run the application manually using Angular CLI. Navigate to the project directory and execute the following command:

    ng serve
