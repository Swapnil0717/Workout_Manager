# Workout Manager

## Project Description

**Workout Manager** is a Spring Boot application designed to help users manage and track their fitness exercises. It supports user authentication (manual login/signup and OAuth2) and provides a structured way to organize exercises based on difficulty levels: **Beginner, Intermediate, and Advanced**.

The application allows **admin users** to manage exercises and exercise details, while regular users can:  

- View exercises filtered by their chosen **level**  
- Search exercises by name or muscle group  
- Mark exercises as **COMPLETED** or **PENDING**  
- Track when an exercise was completed, with **timestamps** stored in the database  

The project uses **Spring Boot, Spring Security, Spring Data JPA, JWT, MySQL**, and **MapStruct** for DTO mapping.

---

## Features

### Admin Features
- Add new exercises with detailed information  
- Set difficulty levels for each exercise: Beginner, Intermediate, Advanced  
- Update or delete existing exercises  

### User Features
- View exercises filtered by difficulty level  
- Search exercises by name or muscle group  
- Mark exercises as **COMPLETED** or **PENDING**  
- Track **completion date and time** for each exercise  

---

## Tech Stack

- **Backend:** Spring Boot, Spring Security, Spring Data JPA  
- **Database:** MySQL  
- **Authentication:** JWT, OAuth2 (Google login optional)  
- **DTO Mapping:** MapStruct  
- **Build Tool:** Maven  
- **Java Version:** 17  

---

## Project Structure

com.example.Workout.Manager
│
├── controller # REST API controllers
├── dto # Data Transfer Objects
├── entity # JPA Entities
├── mapper # MapStruct mappers
├── repository # Spring Data Repositories
├── service # Business logic / services
└── WorkoutManagerApplication.java # Main Spring Boot application


---

## Getting Started

### Prerequisites
- Java 17 or above  
- Maven 3.x  
- MySQL database  

### Setup
1. Clone the repository:
```bash
git clone <>

2. Configure your MySQL database in application.yml:

spring.datasource.url: jdbc:mysql://localhost:3306/workoutdb
spring.datasource.username: your_db_username
spring.datasource.password: your_db_password
spring.jpa.hibernate.ddl-auto: update

3. Build and run the project:

mvn clean install
mvn spring-boot:run

4.The application will run on http://localhost:8080.

Postman Example Usage
1. User Signup

Request:

POST /api/auth/signup
Content-Type: application/json
Body:
{
  "username": "john",
  "email": "john@example.com",
  "password": "password123"
}


Response:

{
  "message": "User registered successfully"
}

2. User Login

Request:

POST /api/auth/login
Content-Type: application/json
Body:
{
  "email": "john@example.com",
  "password": "password123"
}


Response:

{
  "token": "<JWT_TOKEN>",
  "refreshToken": "<REFRESH_TOKEN>"
}


Use the JWT_TOKEN in Authorization headers for all protected endpoints:
Authorization: Bearer <JWT_TOKEN>

3. Get Exercises by Level

Request:

GET /api/user/exercises?level=BEGINNER
Headers:
Authorization: Bearer <JWT_TOKEN>


Response:

[
  {
    "id": 1,
    "name": "Push Up",
    "description": "Basic push up",
    "muscleGroup": "Chest",
    "levels": [...],
    "status": "PENDING",
    "completedAt": null
  }
]

4. Search Exercises

Request:

GET /api/user/exercises/search?search=push&level=BEGINNER
Headers:
Authorization: Bearer <JWT_TOKEN>

5. Mark Exercise as Completed

Request:

POST /api/user/exercises/1/status?status=COMPLETED
Headers:
Authorization: Bearer <JWT_TOKEN>


Response:

"Status updated"

