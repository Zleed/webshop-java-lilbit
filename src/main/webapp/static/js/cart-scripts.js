function main() {

    window.onload = function () {

        $("input[type='number']").inputSpinner();
        setQuantityEventHandler();

        setButtonEventHandler("addToCart");
        setButtonEventHandler("removeFromCart");
        setButtonEventHandler("removeAllFromCart")
    }
}


function setButtonEventHandler(button) {

    $(`.${button}`).bind("click", function () {
        let closestRow = $(this).closest("tr");
        let id = closestRow.find(".hidden-id").val();

        api_post("/api/cart", `{"process": "${button}", "productID": ${id}}`, changeSumOfPriceValue);

        let newQuantity = (button == "removeAllFromCart") ? 0 : closestRow.find(".quantity").val();
        if (newQuantity == 0) {
            closestRow.remove();
        }
    });
}


function setQuantityEventHandler() {
    let oldQuantity;
    let newQuantity;
    let inputTag = $(".quantity");

    inputTag.bind("focus", function () {
        oldQuantity = $(this).val();
    });

    inputTag.bind("blur", function () {
        let id = $(this).closest("tr").find(".hidden-id").val();
        newQuantity = $(this).val();
        let quantity = newQuantity - oldQuantity;

        api_post("/api/cart", `{"process": "setQuantity", "productID": ${id}, "quantity": ${quantity}}`, changeSumOfPriceValue);

        if (newQuantity == 0) {
            $(this).closest("tr").remove();
        }
    });
}


function changeSumOfPriceValue(sumOfPrice) {
    $(".sumOfPrice").text(sumOfPrice.toFixed(1) + " USD");
}


function api_post(url, data, callback) {
    $.ajax({
        type: "post",
        url: url,
        data: JSON.parse(data),
        dataType: "json",
        success: callback
    });
}


main();