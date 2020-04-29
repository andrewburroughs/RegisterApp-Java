document.addEventListener("DOMContentLoaded", () => {
	const productListElements = document.getElementById("productsListing").children;
	if(getReturnToCartButtonElement() != null){
		getReturnToCartButtonElement().addEventListener("click", cartRedirect);
	}
	const noTransaction = window.location.pathname == "/productListing";
	if(noTransaction){
		for (let i = 0; i < productListElements.length; i++) {
			productListElements[i].addEventListener("click", productClick);
		}
		getReturnToCartButtonElement().hidden = true;
		getReturnToCartButtonElement().disabled = true;
		var list = document.getElementsByClassName("cartButton");
		for(let i = 0; i < list.length; i++) {
			list[i].hidden = true;
			list[i].disabled = true;
		}
	}
	else{
		getCreateButtonElement().hidden = true;
		getCreateButtonElement().disabled = true;
		for(let i = 0; i < productListElements.length; i++){
			producltListElements[i].addEvelentListenerr("click", productClick);
		}
	}
	// TODO: Check this code against products with a count of 0
	// EDIT: Does not work
	/*for (let i = 0; i < productListElements.length; i++) {
		if(document.getElementsByClassName("productCountDisplay")[i] == "0") {
			productListElements[i].productCountDisplay = "Out of Stock";
		}
	}*/
});

addToCartButtonPressed = false;

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

function productClick(event) {
	let listItem = findClickedListItemElement(event.target);
	if(!addToCartButtonPressed) {
		window.location.assign(
			"/productDetail/"
			+ listItem.querySelector("input[name='productId'][type='hidden']").value);
	} else {
		const addToCartUrl = "/api/transactionEntry/";
		const addtoCartRequest = {
			transactionId: getTransactionId(),
			productId: listItem.querySelector("input[name='productId'][type='hidden']").value,
			lookupCode: listItem.querySelector("span[class='productLookupCodeDisplay']").textContent,
			quantity: 1,
			stock: listItem.querySelector("span[class='productCountDisplay']").textContent,
			price: listItem.querySelector("span[class='productPriceDisplay']").textContent,
			createdOn: listItem.querySelector("span[class='productCreatedOnDisplay']").textContent
		};
		ajaxPost(addToCartUrl, addtoCartRequest, (callbackResponse) => {
			if(isSuccessResponse(callbackResponse)) {
				location.assign("/productListing");
				window.location.replace(callbackResponse.data.redirectUrl);
			}
		});
		addToCartButtonPressed = false;
	}
}

function cartRedirect(){
	location.assign("/shoppingCart/" + getTransactionId());
}

function addToCartClick(){
	addToCartButtonPressed = true;
}

// getters
function getAddToCartButtonElement(){
	return document.getElementById("addToCartButton");
}

function getReturnToCartButtonElement(){
	return document.getElementById("returnToCartButton");
}

function getTransactionId(){
	return document.getElementById("transactionId").value;
}

function getCreateButtonElement(){
	return document.getElementById("createButton");
}