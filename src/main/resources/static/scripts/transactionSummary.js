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
		var comma = newText.substring(newText.length-3, newText.length);
		var count = 0;
		for(let k = newText.length-4; k>-1; k--){
			if(count%3==0 && count != 0){
				if(newText.charAt(k) == '$'){
					comma = newText.charAt(k) + comma;
				}
				else{
					comma = newText.charAt(k) + "," + comma;
				}
			}
			else{
				comma = newText.charAt(k) + comma;
			}
			count++;
		}
		list[i].innerHTML = comma;
	}

function okayButtonClickHandler() {

	location.assign("/mainMenu");
	return;
	} 

//getters
function getOkayButtonElement() {
    return document.getElementById("okayButton");
}