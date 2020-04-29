var productListElements;
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
    for(let i = 0; i<productListElements.length; i++){
        console.log(productListElements[i]);
    }
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