document.addEventListener("DOMContentLoaded", () => {
    if(getTransactionButtonElement() != null) {
        getTransactionButtonElement().addEventListener("click", startTransactionClick);
    }
    if(getViewProductsButtonElement() != null) {
        getViewProductsButtonElement().addEventListener("click", viewProductsClick);
    }
    if(getCreateEmployeeButtonElement() != null) {
        getCreateEmployeeButtonElement().addEventListener("click", createEmployeeClick);
    }
    if(getSalesReportButtonElement() != null) {
        getSalesReportButtonElement().addEventListener("click", viewSalesReport);
    }
    if(getCashierReportButtonElement() != null) {
        getCashierReportButtonElement().addEventListener("click", viewCashierReport);
    }
    if(getSignOutActionElement() != null) {
        getSignOutActionElement().addEventListener("click", signOutActionClickHandler)
    }
});

//event
function startTransactionClick(event) {
    const startTransactionElement = event.target;
    startTransactionElement.disabled = true;
    const startTransactionUrl = "/api/transaction/";
    const startTransactionRequest = {
        cashierId: getEmployeeIdElement().value,
    };
    ajaxPost(startTransactionUrl, startTransactionRequest, (callbackResponse) => {
        startTransactionElement.disabled = false;
        if(isSuccessResponse(callbackResponse)) {
            location.assign("/productListing");
            window.location.replace(callbackResponse.data.redirectUrl);
        }
    });
    return;
}

function viewProductsClick(event) {
    location.assign("/productListing");
    return;
}

function createEmployeeClick(event) {
    location.assign("/employeeDetail");
    return;
}

function viewSalesReport(event){
    location.assign("/salesReport/");
    return;
}

function viewCashierReport(event){
    location.assign("/cashierReport/");
    return;
}

function tempDisplayError(event) {
    displayError("Functionality has not yet been implemented");
    return;
}

//getters
function getTransactionButtonElement() {
    return document.getElementById("startTransactionButton");
}

function getViewProductsButtonElement() {
    return document.getElementById("viewProductsButton");
}

function getCreateEmployeeButtonElement() {
    return document.getElementById("createEmployeeButton");
}

function getSalesReportButtonElement() {
    return document.getElementById("salesReportButton");
}

function getCashierReportButtonElement() {
    return document.getElementById("cashierReportButton");
}

function getSignOutActionElement() {
    return document.getElementById("signOutImage");
}

function getEmployeeIdElement() {
    return document.getElementById("employeeId");
}