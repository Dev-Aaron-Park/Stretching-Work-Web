function goAccountDelete() {
	var really = prompt("To cancel your membership, enter \'Agree to delete member\'");
	if(really == "Agree to delete member") {
		location.href = "AccountDeleteController";
	}
}

function goWorkingEdit(no, id, todo, memo) {
	location.href = "WorkingEditController?no=" + no + "&id=" + id + "&todo=" + todo + "&memo="  + memo;
}