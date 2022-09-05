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

function addNewUserExercise() {
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