function hideStatus() {
    var statusMessages = document.getElementsByClassName("statusMessage");
    if (statusMessages.length > 0) {
		statusMessages[0].hidden = true;
	}	
}

function showStatus(message) {
    var statusMessages = document.getElementsByClassName("statusMessage");
    if (statusMessages.length > 0) {
		statusMessages[0].innerHTML = message;
        statusMessages[0].hidden = false;	
	}    
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