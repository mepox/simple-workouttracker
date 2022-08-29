function onStart() {
	showWelcomeMessage();
	showStatus("Ready.");
	resetCalendar();
	showWorkoutLog();	
}

function resetCalendar() {
	// Set calendar for the current date    
    var today = new Date();
	var dd = String(today.getDate()).padStart(2, '0');
	var mm = String(today.getMonth() + 1).padStart(2, '0'); // January is 0!
	var yyyy = today.getFullYear();
	today = yyyy + "-" + mm + "-" + dd;
	
    document.getElementById("calendar").value = today;
}

function onCalendarChange() {
	showWorkoutLog();
}

function stepUpCalendar() {
	document.getElementById("calendar").stepUp();
	onCalendarChange();
}

function stepDownCalendar() {
	document.getElementById("calendar").stepDown();
	onCalendarChange();
}

function logout() {	
	window.location += "logout";	
}

function showWorkoutLog() {
	showDivBox("workoutLogBox");
	hideDivBox("exercisesBox");
	
	document.getElementById("workoutLogButton").className = "menuButtonSelected";
	document.getElementById("myexercisesButton").className = "menuButton";
	
	// Refresh the options list	first
	// This will also refresh the history when it's done
	refreshOptionsList();
}

function refreshOptionsList() {
	var url = "/user/exercises/all";
    var xhr = new XMLHttpRequest();

	xhr.open("GET", url);
    xhr.send();	
    
    xhr.onreadystatechange = function() {
        if (this.readyState == XMLHttpRequest.DONE) {
			if (this.status == 200) {
				// SUCCESS
				var data = JSON.parse(this.responseText);
				// Save the user exercises for later
				var userExercises = data;
				
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
				
				// Refresh the history too
				refreshHistory(userExercises);
			} else {
				// ERROR
				showStatus(this.responseText);
				console.log("ERROR: " + this.responseText);
			}
		}
	};
}

function refreshHistory(userExercises) {	
	// Refresh the exercise history				

	calendarValue = document.getElementById("calendar").value;
        
    // Refresh the table for the selected date
    var url = "/user/history/" + calendarValue;
    var xhr = new XMLHttpRequest();

	xhr.open("GET", url);
    xhr.send();	
    
    xhr.onreadystatechange = function() {
		if (this.readyState == XMLHttpRequest.DONE) {
			if (this.status == 200) {
				// SUCCESS
				var data = JSON.parse(this.responseText);				
		
				var exerciseHistoryTable = document.getElementById("exerciseHistoryTable").getElementsByTagName("tbody")[0];
		
				// Clear the table
				while (exerciseHistoryTable.firstChild) {
					exerciseHistoryTable.removeChild(exerciseHistoryTable.firstChild);
				}
		
				var toAdd = "";
							
				for (var i = 0; i < data.length; i++) {					
					toAdd += "<tr><td>";
					
					// We only got the userExerciseId for each exercise in the data
					// We need to find the names in the userExercises by the ID
					for (var j = 0; j < userExercises.length; j++) {
						if (userExercises[j].id == data[i].userExerciseId) {
							toAdd += userExercises[j].name + "</td>";
						}
					}					
					
					// Add the weight and reps					
					toAdd += "<td>" + data[i].weight + "</td><td>" + data[i].reps + "</td>";		
					
					// Add actions
					toAdd += "<td><input type='button' class='redButton' value='Delete' onclick=deleteExerciseHistory(" + 
						data[i].id + ")></td></tr>";
				}
					
				exerciseHistoryTable.innerHTML = toAdd;				
				
			} else {
				// ERROR
				showStatus(this.responseText);
				console.log("ERROR: " + this.responseText);
			}
		}        
    };
}

function deleteExerciseHistory(id) {
	var url = "/user/history/delete/" + id;
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
			showWorkoutLog();
		}		
	};	
}

function addExercise() {
	// First get the user exercises so we can get the IDs
	var url = "/user/exercises/all";
    var xhr = new XMLHttpRequest();

	xhr.open("GET", url);
    xhr.send();
    
    xhr.onreadystatechange = function() {
        if (this.readyState == XMLHttpRequest.DONE) {
			if (this.status == 200) {
				// SUCCESS
				var userExercises = JSON.parse(this.responseText);				
				addExerciseWithUserExercises(userExercises);
			} else {
				// ERROR
				showStatus(this.responseText);
				console.log("ERROR: " + this.responseText);
			}
		}        
    };
}

function addExerciseWithUserExercises(userExercises) {
	var exerciseList = document.getElementById("exerciseList");
	
	var exerciseName = exerciseList.options[exerciseList.selectedIndex].text;
	var weight = document.forms["addExerciseForm"]["weight"].value;
	var reps = document.forms["addExerciseForm"]["reps"].value;
	var selectedDate = document.getElementById("calendar").value;
	
	// We need to find the exercise ID by name
	var exerciseId;
	for (var i = 0; i < userExercises.length; i++) {
		
		if (userExercises[i].name == exerciseName) {
			exerciseId = userExercises[i].id;
			break;
		}
	}
	
	var object = { userExerciseId : exerciseId,
					weight : weight,
					reps : reps,
					date : selectedDate };
					
	var url = "/user/history/add";
	var xhr = new XMLHttpRequest();
	
	xhr.open("POST", url);
	xhr.setRequestHeader("Content-Type", "application/json; charset=UTF-8");			
	xhr.send(JSON.stringify(object));
	
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
			showWorkoutLog();
		}		
	}
}

function showMyExercises() {
	hideDivBox("workoutLogBox");
	showDivBox("exercisesBox");	
	
	document.getElementById("workoutLogButton").className = "menuButton";
	document.getElementById("myexercisesButton").className = "menuButtonSelected";
	
	var url = "/user/exercises/all";
    var xhr = new XMLHttpRequest();

	xhr.open("GET", url);
    xhr.send();
    
    xhr.onreadystatechange = function() {
        if (this.readyState == XMLHttpRequest.DONE) {
			if (this.status == 200) {
				// SUCCESS
				var data = JSON.parse(this.responseText);
				
				var tbody = document.getElementById("exercisesTable").getElementsByTagName("tbody")[0];
				
				// Clear the table
        		while (tbody.firstChild) {
	        		tbody.removeChild(tbody.firstChild);
        		}
        		
        		var toAdd = "";
        		
        		for (var i = 0; i < data.length; i++) {
					toAdd += "<tr><td>" + data[i].name + "</td>" +
						"<td><input type='button' class='redButton' value='Delete' onclick=deleteUserExercise(" + data[i].id + ")></td></tr>";
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
				console.log("Logged in as: " + this.responseText);
				document.getElementById("welcomeMessage").innerText = "Welcome " + this.responseText;
			} else {
				// ERROR
				console.log("ERROR: " + this.responseText);
				showStatus(this.responseText);
			}
		}		
	}
}