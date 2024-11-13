# Ship Reservation System

This is a Java-based Ship Reservation System application. It is built using Spring Boot and Maven and is packaged as a JAR file. The application is deployed using Docker and GitHub Actions, with the deployment hosted on Render.

## Table of Contents
- [Overview](#overview)
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Project Structure](#project-structure)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
  - [Running the Application](#running-the-application)
- [Docker Setup](#docker-setup)
- [GitHub Actions CI/CD](#github-actions-cicd)
- [Deployment](#deployment)
- [Troubleshooting](#troubleshooting)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)
- [Contact](#contact)

## Overview
The Ship Reservation System is a web application built using Java, Spring Boot, and Maven. It allows users to search for ships, view available seats, and book journeys. The application features a responsive design and secure booking system.

## Features
- **Search Ships**: Users can search for ships based on source and destination.
- **View Ship Details**: Users can view details such as ship name, model, date of journey, seat availability, and price.
- **Book Ships**: Users can book available seats on ships.
- **Responsive Design**: The application is optimized for both desktop and mobile devices.
- **Secure Transactions**: Ensures safe booking and secure transactions.
- **User Authentication**: Users can register and log in to manage their bookings.

## Technologies Used
- **Java**
- **Spring Boot**
- **Maven**
- **Thymeleaf**
- **Bootstrap**
- **jQuery**

## Project Structure
```
src/
├── main/
│   ├── java/
│   │   └── com/
│   │       └── example/
│   │           └── shipreservationsystem/
│   │               ├── controller/
│   │               ├── model/
│   │               ├── repository/
│   │               └── service/
│   ├── resources/
│   │   ├── static/
│   │   ├── templates/
│   │   └── application.properties
│   └── webapp/
└── test/
```

## Getting Started

### Prerequisites
Ensure you have the following installed on your machine:
- Java 19 or higher
- Maven
- MySQL
- Docker
- Git

### Installation
1. Clone the repository:
   ```sh
   git clone https://github.com/your-username/ShipReservationSystem.git
   ```
2. Navigate to the project directory:
   ```sh
   cd ShipReservationSystem
   ```
3. Build the project using Maven:
   ```sh
   mvn clean install
   ```

### Running the Application
1. Run the Spring Boot application:
   ```sh
   mvn spring-boot:run
   ```
2. Open your browser and navigate to `http://localhost:9095`.

## Docker Setup
1. Build the Docker image:
   ```sh
   docker build -t ship-reservation-system .
   ```
2. Run the Docker container:
   ```sh
   docker run -p 8080:8080 ship-reservation-system
   ```

## GitHub Actions CI/CD
The project uses GitHub Actions for continuous integration and deployment. The workflow file is located in `.github/workflows/deploy.yml`.

## Deployment
The application is deployed on Render. You can access it live at [https://shipreservationsystem.onrender.com](https://shipreservationsystem.onrender.com).

## Troubleshooting
For any issues, please refer to the [GitHub Issues](https://github.com/mohit-on-github/ShipReservationSystem/issues) page.

## Usage
- **Home Page**: Provides an overview and navigation to login and registration pages.
- **Dashboard**: Allows users to search for ships between destinations.
- **Search Results**: Displays the list of ships based on the search criteria with options to book available seats.

## Contributing
Contributions are welcome! Please fork the repository and create a pull request with your changes.

## License
This project is licensed under the MIT License. See the `LICENSE` file for details.

## Contact
For any inquiries or issues, please contact [official.mohitkr19@gmail.com].
