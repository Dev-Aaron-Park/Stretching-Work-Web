function loginCheck() {
	var loginID = document.loginForm.loginID;
	var loginPW = document.loginForm.loginPW;

	if (isEmpty(loginID) || isEmpty(loginPW)) {
		alert("?");
		loginID.value = "";
		loginPW.value = "";
		loginID.focus();
		return false;
	}
	return true;
}

function signupCheck() {
	var signupID = document.signupForm.signupID;
	var signupPW = document.signupForm.signupPW;
	var signupPWC = document.signupForm.signupPWC;
	var signupName = document.signupForm.signupName;
	var signupAddr1 = document.signupForm.signupAddr1;
	var signupAddr2 = document.signupForm.signupAddr2;
	var signupAddr3 = document.signupForm.signupAddr3;
	var signupPhoto = document.signupForm.signupPhoto;
	
	if (isEmpty(signupID)
		|| isLessThan(signupID, 4)
		|| isUnValidString(signupID)) {
		
		alert("Please Check ID");
		signupID.value = "";
		signupID.focus();
		return false;
	}
	
	if (isEmpty(signupPW)
		|| isLessThan(signupPW, 8)
		|| isNotEquals(signupPW, signupPWC)
		|| isNotIncluded(signupPW, "!@#$%^&*()")
		|| isNotIncluded(signupPW, "0123456789")) {
			
		alert("Please Check Password");
		signupPW.value = "";
		signupPWC.value = "";
		signupPW.focus();
		return false;
	}
	
	if (isEmpty(signupName)
		|| isUnValidName(signupName)) {
		
		alert("Please Check Name");
		signupName.value = "";
		signupName.focus();
		return false;
	}
	
	if (isEmpty(signupAddr1)
		|| isEmpty(signupAddr2)
		|| isEmpty(signupAddr3)) {
		
		alert("Please Check Address");
		signupAddr.value = "";
		signupAddr.focus();
		return false;
	}
	
	if (isEmpty(signupPhoto) ||
		(isNotType(signupPhoto, "png")
		&& isNotType(signupPhoto, "gif")
		&& isNotType(signupPhoto, "jpg"))) {
		
		alert("Please Check Profile Photo");
		signupPhoto.value = "";
		signupPhoto.focus();
		return false;
	}
	
	return true;
}

function editCheck() {
	var changePW = document.editForm.changePW;
	var changePWC = document.editForm.changePWC;
	var name = document.editForm.editName;
	var addr1 = document.editForm.editAddr1;
	var addr2 = document.editForm.editAddr2;
	var addr3 = document.editForm.editAddr3;
	var photo = document.editForm.editPhoto;
	
	if (isEmpty(changePW)
		|| isLessThan(changePW, 8)
		|| isNotEquals(changePW, changePWC)
		|| isNotIncluded(changePW, "!@#$%^&*()")
		|| isNotIncluded(changePW, "0123456789")) {
		
		alert("Please Check Password");
		changePW.value = "";
		changePW.focus();
		return false;
	}
	
	if (isEmpty(name)
		|| isUnValidName(name)) {
		
		alert("Please Check Name");
		originalPW.value = "";
		originalPW.focus();
		return false;
	}
	
	if (isEmpty(addr1)
		|| isEmpty(addr2)
		|| isEmpty(addr3)) {
		
		alert("Please Check Address");
		originalPW.value = "";
		originalPW.focus();
		return false;
	}
	
	if (isEmpty(photo)) {
		return true;
	}
	
	if (isNotType(photo, "png")
		&& isNotType(photo, "gif")
		&& isNotType(photo, "jpg")) {
		
		alert("Please Check Profile Photo");
		originalPW.value = "";
		originalPW.focus();
		return false;
	}
	
	return true;
}

function workingCheck() {
	var todoField = document.addTodoForm.todo;
	var memoField = document.addTodoForm.memo;
	
	if (isEmpty(todoField)
		|| isEmpty(memoField)) {
		
		alert("Please Check Empty Block");
		return false;
	}
	return true;
}