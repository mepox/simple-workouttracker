# Workout Tracker app

Workout Tracker app made with Spring Boot.

![Alt text](screenshot1.jpg?raw=true "Workout Tracker")
![Alt text](screenshot2.jpg?raw=true "Workout Tracker")

## What's inside
The project uses the following technologies:
- Java 11
- Spring Boot
- Spring Security
- HTML, CSS and JavaScript
- SQL
- Maven

## Features
- Users can register and login to the application.
- Users can create their custom exercises.
- Users can log their workout by adding their own custom exercises to a specific date.

## Overview
The service uses Controller - Service - Repository pattern:

- Controllers: Handle the clients' REST API requests and pass them to the Services.
- Services: Provide a service to the application. Receive input from Controllers, perform validation and business logic, and calling Repositories for data manipulation.
- Repositories: Responsible to database operations.

The main components of the service are:

- AppUserService: Provides a service to manipulate AppUsers.
- UserExerciseService: Provides a service to manipulate UserExercises.
- ExerciseHistoryService: Provides a service to manipulate ExerciseHistory.
- LoginService: Provides a service to handle the login process.
- RegisterService: Provides a service to handle the registering process.

## Installation 
The project is created with Maven, so you just need to import it to your IDE and build the project to resolve the dependencies.

Alternatively, you can start the project with: `mvnw spring-boot:run`

## Running the application

A default user with some preloaded data is added at the start of the application.

```
username: user
password: user
```

Each newly registered user comes with some default exercises which are read from the file: ```default_exercises.txt```

## Live Preview
The app is hosted on Heroku: https://spring-workouttracker.herokuapp.com

## Data Storage
It uses an in-memory (H2) database to store the data.

To view and query the database you can browse to */console*, eg.: http://localhost:8080/console

Login details:
```
spring.datasource.url=jdbc:h2:mem:db
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=admin
spring.datasource.password=admin
```

<details><summary>schema.sql</summary>
<p>

```
CREATE TABLE appuser (
	id INT NOT NULL AUTO_INCREMENT,
	username VARCHAR(16) NOT NULL,
	password VARCHAR(72) NOT NULL,
	rolename VARCHAR(16) NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE user_exercise (
	id INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(32) NOT NULL,
	userId INT NOT NULL,	
	PRIMARY KEY (id),
	FOREIGN KEY (userId) REFERENCES appuser(id)
);

CREATE TABLE exercise_history (
	id INT NOT NULL AUTO_INCREMENT,
	userId INT NOT NULL,
	userExerciseId INT NOT NULL,
	weight INT,
	reps INT NOT NULL,
	exercise_date VARCHAR(16) NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (userId) REFERENCES appuser(id),
	FOREIGN KEY (userExerciseId) REFERENCES user_exercise(id)
);
```
</p>
</details>

## Endpoints
AppUserController:
- GET requests to ```/user/username``` to retrieve the current user's name.

ExerciseHistoryController:
- GET requests to ```/user/history/{date}``` to retrieve all the ExerciseHistory from a specific date.
- POST requests to ```/user/history/add``` to add a new ExerciseHistory.
- DELETE requests to ```/user/history/delete/{id}``` to delete an ExerciseHistory.

LoginController:
- POST requests to ```/login/perform_login``` to handle the login using the LoginForm model.

RegisterController:
- POST requests to ```/register``` to handle the registering using the RegisterForm model.

UserExerciseController:
- GET requests to ```/user/exercises/all``` to retrieve all the UserExercise.
- POST requests to ```/user/exercises/new``` to add a new exercise.

WebController:
- Maps the client's GET requests to a html page.
