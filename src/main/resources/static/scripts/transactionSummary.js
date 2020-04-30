document.addEventListener("DOMContentLoaded", () => {
	
	if (getOkayButtonElement() != null) {
		getOkayButtonElement().addEventListener("click", okayButtonClickHandler);
	}
});

var list = document.getElementsByClassName("productPriceDisplay");
	for(let i = 0; i<list.length; i++){
		var x = list[i].innerHTML;
		var len = x.length;
		var pos = x.indexOf(".");
		if(pos == -1){
			x += ".";
			pos = x.indexOf(".");
		}
		var diff = len - pos;
		var newText = "$" + x;
		for(let j = diff; j<3; j++){
			newText += "0"
		}
		list[i].innerHTML = newText;
	}

function okayButtonClickHandler() {

	location.assign("/mainMenu");
	return;
	} 

//getters
function getOkayButtonElement() {
    return document.getElementById("okayButton");
}