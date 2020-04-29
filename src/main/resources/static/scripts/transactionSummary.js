document.addEventListener("DOMContentLoaded", () => {
	
	if (getOkayButtonElement() != null) {
		getOkayButtonElement().addEventListener("click", okayButtonClickHandler);
	}
});

var quantity;
		
function incrementValue(){

    quantity = parseInt(document.getElementById('number').value, 10);
   	quantity = isNaN(quantity) ? 0 : quantity;
	quantity++;
	document.getElementById('number').value = quantity;
			
}

function decrementValue(){

	quantity = parseInt(document.getElementById('number').value, 10);
	quantity = isNaN(quantity) ? 0 : quantity;
	if(quantity > 0)
		quantity--;
	else
		quantity = 0;
	document.getElementById('number').value = quantity;
} 

function okayButtonClickHandler() {

	location.assign("/mainMenu");
	return;
	} 

//getters
function getOkayButtonElement() {
    return document.getElementById("okayButton");
}