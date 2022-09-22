function onStart() {
	console.log("Page loaded.");
	updateVersion();
}

function registerUser() {
	var username = document.forms["registerForm"]["username"].value;
	var password = document.forms["registerForm"]["password"].value;
	var passwordConfirm = document.forms["registerForm"]["passwordConfirm"].value;
	
	username = username.trim();
	password = password.trim();
	passwordConfirm = passwordConfirm.trim();
	
	var url = "/register";
	var object = { username : username,
					password : password,
					passwordConfirm : passwordConfirm };
					
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
			clearRegisterForm();		
		}		
	}
}

function clearRegisterForm() {
	clearFormValue("registerForm", "username");
	clearFormValue("registerForm", "password");
	clearFormValue("registerForm", "passwordConfirm");	
}