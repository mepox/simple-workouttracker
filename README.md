# A simple Workout Tracker

A simple Workout Tracker made with Spring Boot. It uses an in-memory (H2) database to store the data.

![Alt text](screenshot.jpg?raw=true "URL Shortener")

## Installation 
The project is created with Maven, so you just need to import it to your IDE and build the project to resolve the dependencies.

## Live Preview
The app is hosted on Heroku: 

## To view your H2 in-memory database
To view and query the database you can browse to */console*, eg.: http://localhost:8080/console

Login details:
```
spring.datasource.url=jdbc:h2:mem:db
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=admin
spring.datasource.password=admin
```

# How it works

## Controllers
They are responsible to handle the client REST calls.

### AppUserController.java
### ExerciseHistoryController.java
### LoginController.java
### RegisterController.java
### UserExerciseController.java
### WebController.java

## Services
They are responsible to provide a service to the application.

### AppUserService.java
### ExerciseHistoryService.java
### LoginService.java
### RegisterService.java
### UserExerciseService.java

## Repositories
They are responsible for database operations.

### JdbcAppUserRepository.java
### JdbcExerciseHistoryRepository.java
### JdbcUserExerciseRepository.java

## Models
Represent an object in the application.

### AppUser.java
### ExerciseHistory.java
### LoginForm.java
### RegisterForm.java
### UserExercise.java

## Config
Configuration for the application.

### WebSecurityConfig.java
