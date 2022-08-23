function success({ coords }) {
	const latitude = coords.latitude;
	const longitude = coords.longitude;
	
	location.href = "WorkingController?lan=" + latitude + "&lon=" + longitude;
}


function getUserLocation() {
	location.href = "WorkingController";
/*	if (!navigator.geolocation) {
		throw "Location information is not supported.";
	}
	navigator.geolocation.watchPosition(success);*/
}
