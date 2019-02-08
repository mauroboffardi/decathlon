function validateCentimeters(input)
{
 // var cmREGEX = /^\d+\.?\d{1-3}?$/;
	var e = event || window.event;  // get event object
    var key = e.keyCode || e.which; // get the key
	var cmREGEX = /^\d+$/;
	check = input.value + key;
	alert (check);
	var result = cmREGEX.test(check);
	return result;
}