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
    for(let i = 0; i<quantityList.length; i++){
        var newNum = Number(quantityList[i].innerHTML) * Number(priceList[i].innerTHML);
        var test = Number(quantityList[i].innerHTML) + Number(priceList[i].innerHTML);
        var test2 = Number(quantityList[i].innerHTML);
        var test3 = Number(priceList[i].innerHTML);
        var test4 = 42.0 * 1;
        console.log(test);
        console.log(test2);
        console.log(test3);
        console.log(test4);
        console.log(newNum);
        num = num + newNum;
        console.log(num);
        console.log(newNum);
        console.log(newNum + " " + num + " " + Number(quantityList[i].innerHTML) + " " + Number(priceList[i].innerHTML));
    }
    total = num;
    getTotalDisplayElement().innerHTML = ("Total: " + total);
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