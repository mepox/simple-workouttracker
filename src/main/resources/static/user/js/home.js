function onStart() {
	document.getElementById("welcomeMessage").innerHTML = "Welcome";
	hideDivBox("workoutLogBox");
	showDivBox("exercisesBox");
	console.log("page loaded home");
}

function onLogoutButton() {
	console.log("Logout");
	window.location += "logout";	
}

function onWorkoutLogButton() {
	showDivBox("workoutLogBox");
	hideDivBox("exercisesBox");
}

function onMyExercisesButton() {
	hideDivBox("workoutLogBox");
	showDivBox("exercisesBox");	
}