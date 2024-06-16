# TicketEase - Ticket Booking Platform

### Tech Stack:
- Java
- Tomcat
- Servlet
- EmberJS
- Ember-data
- MySQL
- Hibernate
- Tomcat Authentication
- OAuth

## Project Overview

TicketEase is a comprehensive ticket booking platform designed to streamline the process of purchasing and managing event tickets. The project leverages a robust tech stack to deliver a seamless user experience and secure authentication.

## Features

- **Comprehensive Ticket Booking System:**
  - Developed using Servlet and Tomcat for backend operations.
  - Utilized EmberJS and Ember-data for a dynamic and responsive frontend interface.
  - Implemented Hibernate for efficient ORM and database interactions.

- **Custom Authentication Realm:**
  - Designed a custom Tomcat authentication realm to support various authentication methods including password, email-based OTP, and OAuth.
  - Integrated dynamic user role-based security filters.
  - Implemented Role-Based Access Control (RBAC) for precise authorization and tailored security policies.

## Getting Started

### Prerequisites

- Java Development Kit (JDK)
- Apache Tomcat Server
- MySQL Database
- Node.js and npm (for EmberJS)

### Installation

1. **Clone the repository:**
   ```bash
   git clone https://github.com/yourusername/ticketease.git
   cd ticketease

# Project Setup Guide

## Backend Setup

1. Import the project into your preferred IDE.
2. Configure the database connection in `hibernate.cfg.xml`.
3. Deploy the WAR file to your Tomcat server.

## Frontend Setup [(Frontend Repository)](https://github.com/GokulaKrishnanRGK/RailwayReservation)

1. Navigate to the frontend directory:
    ```bash
    cd <frontend_ dir>
    ```
2. Install the dependencies:
    ```bash
    npm install
    ```
3. Build the EmberJS project:
    ```bash
    ember build --environment=production
    ```

## Database Setup

1. Create a new MySQL database.
2. Run the provided SQL scripts to set up the initial schema and data.

## Running the Application

1. Start the Tomcat server.
2. Access the application through your web browser at [http://localhost:8080](http://localhost:8080).

## Usage

### User Registration and Login

- Register a new account or log in using existing credentials.
- Choose between password authentication, email-based OTP, or OAuth for logging in.

### Ticket Booking

- Browse available events and select tickets to purchase.
- Complete the booking process and receive a confirmation.

### Admin Panel

- Access the admin panel to manage events, users, and bookings.
- Utilize the RBAC features to assign roles and permissions.

### Additional Details
- [Frontend Repository](https://github.com/GokulaKrishnanRGK/RailwayReservation)
