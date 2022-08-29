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