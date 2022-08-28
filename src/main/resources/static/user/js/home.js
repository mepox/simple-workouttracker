function onStart() {
	showWelcomeMessage();
	showMyExercises();	
}

function logout() {	
	window.location += "logout";	
}

function showWorkoutLog() {
	showDivBox("workoutLogBox");
	hideDivBox("exercisesBox");
	
	// Refresh the options list	
	var url = "/user/exercises/all";
    var xhr = new XMLHttpRequest();

	xhr.open("GET", url);
    xhr.send();
    
    xhr.onreadystatechange = function() {
        if (this.readyState == XMLHttpRequest.DONE) {
			if (this.status == 200) {
				// SUCCESS			
				console.log("OK - list updated");	
				var data = JSON.parse(this.responseText);
				
				var exerciseList = document.getElementById("exerciseList");
				
				// Clear the options
				while (exerciseList.firstChild) {
					exerciseList.removeChild(exerciseList.firstChild);
				}
				
				var toAdd = "";
				
				for (var i = 0; i < data.length; i++) {
					toAdd += "<option>" + data[i].name + "</option>";
				}
				
				exerciseList.innerHTML = toAdd;				
				
			} else {
				// ERROR
				showStatus(this.responseText);
				console.log("ERROR: " + this.responseText);
			}
		}        
    };
    // Set calendar for today
    // Refresh the table for the selected date
}

function addExercise() {
	console.log("adding exercise to the log")
	var exerciseList = document.getElementById("exerciseList");
	var exerciseName = exerciseList.options[exerciseList.selectedIndex].text;
	var weight = document.forms["addExerciseForm"]["weight"].value;
	var reps = document.forms["addExerciseForm"]["reps"].value;
	var selectedDate = document.getElementById("selectedDate").value;
	
	console.log("name: " + exerciseName);
	console.log("weight: " + weight);
	console.log("reps: " + reps);	
	console.log("date: " + selectedDate);
}

function showMyExercises() {
	hideDivBox("workoutLogBox");
	showDivBox("exercisesBox");	
	
	var url = "/user/exercises/all";
    var xhr = new XMLHttpRequest();

	xhr.open("GET", url);
    xhr.send();
    
    xhr.onreadystatechange = function() {
        if (this.readyState == XMLHttpRequest.DONE) {
			if (this.status == 200) {
				// SUCCESS				
				console.log("OK - showMyExercises");
				var data = JSON.parse(this.responseText);
				
				var tbody = document.getElementById("exercisesTable").getElementsByTagName("tbody")[0];
				
				// Clear the table
        		while (tbody.firstChild) {
	        		tbody.removeChild(tbody.firstChild);
        		}
        		
        		var toAdd = "";
        		
        		for (var i = 0; i < data.length; i++) {
					toAdd += "<tr><td>" + data[i].name + "</td>" +
						"<td><input type='button' class='deleteButton' value='Delete' onclick=deleteUserExercise(" + data[i].id + ")></td></tr>";
				}
				
				tbody.innerHTML = toAdd;
			} else {
				// ERROR
				showStatus(this.responseText);
				console.log("ERROR: " + this.responseText);
			}
		}        
    };	
}

function addNewExercise() {
	var newExercise = document.forms["newExerciseForm"]["newExercise"].value;
	
	newExercise = newExercise.trim();
	
	var url = "/user/exercises/new";
	var xhr = new XMLHttpRequest();
	xhr.open("POST", url);	
	xhr.send(newExercise);
	
	xhr.onreadystatechange = function() {
		if (this.readyState == XMLHttpRequest.DONE) {
			if (this.status == 200) {
				// SUCCESS				
				console.log("OK: " + this.responseText);				
			} else {
				// ERROR
				console.log("ERROR: " + this.responseText);
			}
			showStatus(this.responseText);
			showMyExercises();
		}		
	}
			
	document.forms["newExerciseForm"]["newExercise"].value = "";
}

function deleteUserExercise(exerciseId) {
	var url = "/user/exercises/delete/" + exerciseId;
    var xhr = new XMLHttpRequest();
	xhr.open("DELETE", url);
    xhr.send();
    
    xhr.onreadystatechange = function() {
		if (this.readyState == XMLHttpRequest.DONE) {
			if (this.status == 200) {
				// SUCCESS				
				console.log("OK: " + this.responseText);				
			} else {
				// ERROR
				console.log("ERROR: " + this.responseText);
			}
			showStatus(this.responseText);
			showMyExercises();
		}		
	}
}

function showWelcomeMessage() {
	var url = "/user/username";
    var xhr = new XMLHttpRequest();
	xhr.open("GET", url);
    xhr.send();
    
    xhr.onreadystatechange = function() {
		if (this.readyState == XMLHttpRequest.DONE) {
			if (this.status == 200) {
				// SUCCESS				
				console.log("OK: " + this.responseText);
				document.getElementById("welcomeMessage").innerText = "Welcome " + this.responseText;
			} else {
				// ERROR
				console.log("ERROR: " + this.responseText);
				showStatus(this.responseText);
			}
		}		
	}
}