document.addEventListener("DOMContentLoaded", () => {
    if(getSelectButtonElement() != null){
        getSelectButtonElement().addEventListener("click", optionRedirect);
    }
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
});


function optionRedirect(event){
    var sort = getSortElement().selectedIndex == 1; //If true, ascending is selected
    var column = getColumnElement().selectedIndex == 1; //If true, productSales is selected
    if(sort && column){
        location.replace("3");
    }
    else if(!sort && column){
        location.replace("2");
    }
    else if(sort && !column){
        location.replace("1");
    }
    else{
        location.replace("0");
    }
}

//Getters
function getSortElement(){
    return document.getElementById("sort");
}

function getColumnElement(){
    return document.getElementById("column");
}

function getSelectButtonElement(){
    return document.getElementById("selectButton");
}