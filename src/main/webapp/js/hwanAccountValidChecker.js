function isEmpty(input) {
	return !input.value;
}

function isLessThan(input, len) {
	return input.value.length < len;
}

function isUnValidName(input) {
	var unValidString = "!#$%^*&@+=:;\'\"\\/><[]{}|"
	for (var i = 0; i < input.value.length; i++) {
		if (unValidString.indexOf(input.value[i]) != -1) {
			return true;
		}
	}
	return false;
}

function isUnValidString(input) {
	var validString = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
	for (var i = 0; i < input.value.length; i++) {
		if (validString.indexOf(input.value[i]) == -1) {
			return true;
		}
	}
	return false;
}

function isNotEquals(input1, input2) {
	return input1.value != input2.value;
}

function isNotIncluded(input, set) {
	for (var i = 0; i < set.length; i++) {
		if (input.value.indexOf(set[i]) != -1) {
			return false;
		}
	}
	return true;
}

function isNotNumber(input) {
	return isNaN(input.value);
}

function isNegativeNumber(input) {
	return input.value <= 0;
}

function isNotType(input, type) {
	return input.value.toLowerCase().indexOf("." + type) == -1
}

function isUnValidBirthday(input) {
	var year = Number(input.value.substr(0, 4));
	var month = Number(input.value.substr(4, 2));
	var day = Number(input.value.substr(6, 2));
	
	if (year >= 3000 || month > 12 || day > 31) {
		return true;
	}
	
	return false;
}