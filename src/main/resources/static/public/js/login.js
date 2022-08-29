function onStart() {
	clearLoginForm();
}

function loginUser() {
	var username = document.forms["loginForm"]["username"].value;
	var password = document.forms["loginForm"]["password"].value;
	
	username = username.trim();
	password = password.trim();
	
	var url = window.location + "/perform_login";	
	console.log("url: " + url);
	var object = { username : username,
					password : password };
					
	var xhr = new XMLHttpRequest();
	xhr.open("POST", url);
	xhr.setRequestHeader("Content-Type", "application/json; charset=UTF-8");			
	xhr.send(JSON.stringify(object));
	
	xhr.onreadystatechange = function() {
		if (this.readyState == XMLHttpRequest.DONE) {
			if (this.status == 200) {
				// SUCCESS
				console.log("OK: " + this.responseText);
				showStatus(this.responseText);
				window.open("/", "_top");
			} else {
				// ERROR
				console.log("ERROR: " + this.responseText);
				showStatus(this.responseText);
				clearLoginForm();
			}
		}		
	}
}

function clearLoginForm() {
	clearFormValue("loginForm", "username");
	clearFormValue("loginForm", "password");
}