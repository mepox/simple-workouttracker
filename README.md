# A simple Workout Tracker

A simple Workout Tracker made with Spring Boot. 

![Alt text](screenshot1.jpg?raw=true "Workout Tracker")
![Alt text](screenshot2.jpg?raw=true "Workout Tracker")

## Features
- Users can register and login to the application.
- Users can create their custom exercises.
- Users can log their workout by adding their own custom exercises to a specific date.

## Installation 
The project is created with Maven, so you just need to import it to your IDE and build the project to resolve the dependencies.

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

# Overview

## Configs
Configurations for the application.

### WebSecurityConfig.java
Configuration for the WebSecurity.

## Models
Represents an object in the application.

### AppUser.java
They are the users in the application.

### UserExercise.java
They are the users' own exercises in the application.

### ExerciseHistory.java
They are the users' exercise history in the application.

### LoginForm.java
Represent the LoginForm. Used when the user tries to log in.

### RegisterForm.java
Represent the RegisterForm. Used when a new user tries to register.

## Controllers
They are responsible to handle the client REST API requests.

### AppUserController.java
Handles the client's REST API requests that are related to the user (AppUser).

### UserExerciseController.java
Handles the client's REST API requests that are related to the user's own exercises (UserExercise).

### ExerciseHistoryController.java
Handles the client's REST API requests that are related to the user's exercise history (ExerciseHistory).

### LoginController.java
Handles the client's REST API requests that are related to logging in the user.

### RegisterController.java
Handles the client's REST API requests that are related to register a new user.

### WebController.java
Maps the client's REST API requests to a html page.

## Services
They are responsible to provide a service to the application.

### AppUserService.java
Provides a service to manipulate AppUsers.

### UserExerciseService.java
Provides a service to manipulate UserExercises.

### ExerciseHistoryService.java
Provides a service to manipulate ExerciseHistory.

### LoginService.java
Provides a service to handle the login process.

### RegisterService.java
Provides a service to handle the registering process.

## Repositories
They are responsible for database operations.

### JdbcAppUserRepository.java 
Responsible for manipulating the AppUsers in the database using JdbcTemplate.

### JdbcUserExerciseRepository.java
Responsible for manipulating the UserExercises in the database using JdbcTemplate.

### JdbcExerciseHistoryRepository.java
Responsible for manipulating the ExerciseHistory in the database using JdbcTemplate.

## Database

<details><summary>schema.sql</summary>
<p>

```
CREATE TABLE appuser (
	id INT NOT NULL AUTO_INCREMENT,
	username VARCHAR(16) NOT NULL,
	password VARCHAR(16) NOT NULL,
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
