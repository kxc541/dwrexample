window.onload = function() {

  // Update Cart state from session
	MyDate.currentDate(displayDate);
}

function displayDate(bean) {
	msg.innerHTML = bean.date;
}