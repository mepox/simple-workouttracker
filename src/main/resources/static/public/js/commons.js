function hideStatus() {
    var statusMessage = document.getElementById("statusMessage");
    statusMessage.hidden = true;    	
}

function showStatus(message) {
    var statusMessage = document.getElementById("statusMessage");
    statusMessage.hidden = false;
    statusMessage.innerHTML = "Status: " + message;    
}

function clearFormValue(formId, valueName) {
	document.forms[formId][valueName].value = "";	
}


function hideDivBox(id) {
	document.getElementById(id).style = "display:none";	
}

function showDivBox(id) {
	document.getElementById(id).style = "display:block";
}

function updateVersion() {
	var url = "/version";
    var xhr = new XMLHttpRequest();

	xhr.open("GET", url);
    xhr.send();	
    
    xhr.onreadystatechange = function() {
		if (this.readyState == XMLHttpRequest.DONE) {
			if (this.status == 200) {
				// SUCCESS
				addVersion(this.responseText);
			} else {
				// ERROR
				console.log("Couldn't get the app version.");				
			}
		}
	}
}

function addVersion(text) {
	var footer = document.getElementsByTagName("footer")[0];
	if (footer == null) {
		console.log("We dont have a footer in the HTML!");
		return;
	}
	footer.innerHTML += "<footer class='center'><br>" + text + "</footer>";
}