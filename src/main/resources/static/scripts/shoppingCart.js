var productListElements;
var total = 0;
document.addEventListener("DOMContentLoaded", () => {
    productListElements = document.getElementById("productsListing").children;
    if(getCheckoutButtonElement() != null) {
        getCheckoutButtonElement().addEventListener("click", checkout);
    }
    if(getContinueShoppingButtonElement() != null) {
        getContinueShoppingButtonElement().addEventListener("click", continueShopping);
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
		list[i].innerHTML = newText;
	}
    calculateTotal();
});

function checkout() {
    location.assign("/transactionSummary" + getTransactionId());
    return;
}

function clearCart() {
// TODO add details when shopping cart storage is worked out.
}

function getNumUnits() {
// TODO add details when shopping cart storage is worked out.
}

function removeItem(itemID) {

}

function continueShopping() {
    location.assign("/productListing/" + getTransactionId());
    return;
}

function calculateTotal(){
    var quantityList = document.getElementsByClassName("productCountDisplay");
    var priceList = document.getElementsByClassName("productPriceDisplay");
    var num = 0;
    for(let i = 0; i<priceList.length; i++){
        var str = priceList[i].innerHTML;
        var newStr = "";
        for(let i = 0; i < str.length; i++){
			if(str[i] != '$' && str[i] != ','){
				newStr += str[i];
			}
        }
        var num1 = Number(quantityList[i].innerHTML);
        var num2 = Number(newStr);
        var newNum = num1 * num2;
        num += newNum;
    }
    total = num;
    num += "";
    var len = num.length;
    var pos = num.indexOf(".");
    if(pos == -1){
        num += ".";
        pos = num.indexOf(".");
        len = num.length;
    }
    var diff = len - pos;
    console.log(len);
    console.log(pos);
    console.log(diff);
    var newText = "$" + num;
    for(let j = diff; j<3; j++){
        newText += "0"
    }
    num = newText;
    getTotalDisplayElement().innerHTML = ("Total: " + num);
}

// Getters

function getCheckoutButtonElement() {
    return document.getElementById("checkoutButton");
}

function getClearCartButtonElement() {
    return document.getElementById("clearCartButton");
}

function getContinueShoppingButtonElement() {
    return document.getElementById("continueShoppingButton");
}

function getTransactionId(){
	return document.getElementById("transactionId").value;
}

function getTotalDisplayElement(){
    return document.getElementById("totalDisplay");
}