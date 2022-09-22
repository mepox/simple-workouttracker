function onStart() {
	showWelcomeMessage();
	showStatus("Ready.");
	resetCalendar();
	showWorkoutLog();
	updateVersion();	
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

function logout() {	
	window.location += "logout";	
}