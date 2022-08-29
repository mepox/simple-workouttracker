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