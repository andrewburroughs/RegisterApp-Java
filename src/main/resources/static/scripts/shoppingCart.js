var total = 0;
document.addEventListener("DOMContentLoaded", () => {
    var addToCartButtons = document.getElementsByClassName("updateButton");
    for(let i = 0; i < addToCartButtons.length; i++){
        addToCartButtons[i].addEventListener("click", updateButtonClick);
    }
    if(getCheckoutButtonElement() != null) {
        getCheckoutButtonElement().addEventListener("click", checkout);
    }
    if(getClearCartButtonElement() != null) {
        getClearCartButtonElement().addEventListener("click",clearCart);
    }
    if(getContinueShoppingButtonElement() != null) {
        getContinueShoppingButtonElement().addEventListener("click", continueShopping);
    }
    if(getCancelTransactionButtonElement() != null) {
        getCancelTransactionButtonElement().addEventListener("click", cancelTransaction);
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
    var checkoutUrl = "/api/transaction/" + getTransactionId();
    ajaxPut(checkoutUrl, null, (callbackResponse) => {
        if (isSuccessResponse(callbackResponse)) {
            window.location.assign("/transactionSummary/" + getTransactionId());
        }
    });
}

function clearCart() {
    var clearCartUrl = "/api/transactionEntry//" + getTransactionId();
    ajaxDelete(clearCartUrl, (callbackResponse) => {
        if (isSuccessResponse(callbackResponse)) {
            window.location.replace("/shoppingCart/" + getTransactionId());
        }
    });
}

function getNumUnits() {
// TODO add details when shopping cart storage is worked out.
}

function removeItem(listItem) {
    var removeItemUrl = "/api/transactionEntry/" + listItem.querySelector("input[name='transactionEntryId']").value;

    ajaxDelete(removeItemUrl, (callbackResponse) => {
        if (isSuccessResponse(callbackResponse)) {
            window.location.replace("/shoppingCart/" + getTransactionId());
        }
    });
}

function findClickedListItemElement(clickedTarget) {
	if (clickedTarget.tagName.toLowerCase() === "li") {
		return clickedTarget;
	} else {
		let ancestorIsListItem = false;
		let ancestorElement = clickedTarget.parentElement;

		while (!ancestorIsListItem && (ancestorElement != null)) {
			ancestorIsListItem = (ancestorElement.tagName.toLowerCase() === "li");

			if (!ancestorIsListItem) {
				ancestorElement = ancestorElement.parentElement;
			}
		}

		return (ancestorIsListItem ? ancestorElement : null);
	}
}

function continueShopping() {
    location.assign("/productListing/" + getTransactionId());
    return;
}

function cancelTransaction(){
    location.assign("/mainMenu");
    return;
}

function calculateTotal(){
    var quantityList = document.getElementsByClassName("quantitySelect");
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
        var num1 = Number(quantityList[i].value);
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

function getCancelTransactionButtonElement() {
    return document.getElementById("cancelTransaction");
}

function getTransactionId(){
	return document.getElementById("transactionId").value;
}

function updateButtonClick(event) {
    let listItem = findClickedListItemElement(event.target);
    const updateQuantityUrl = "/api/transactionEntry/" + listItem.querySelector("input[name='transactionEntryId']").value;
    var str = listItem.querySelector("input[name='quantitySelect']").value;
    var num = parseInt(str);
    console.log(updateQuantityUrl);
    console.log(num);
    const updateCartRequest = {
        quantity: num
    };
    ajaxPut(updateQuantityUrl, updateCartRequest, (callbackResponse) => {
        if (isSuccessResponse(callbackResponse)) {
            window.location.replace("/shoppingCart/" + getTransactionId());
        }
    });
    calculateTotal();
}

function getTotalDisplayElement(){
    return document.getElementById("totalDisplay");
}