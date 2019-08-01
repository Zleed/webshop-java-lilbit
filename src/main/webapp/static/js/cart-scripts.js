

function ajaxPost(button) {
    $(`.${button}`).bind("click", function () {
        let closestRow = $(this).closest("tr");
        let id = closestRow.find(".hidden-id").val();
        $.ajax({
            type: "post",
            url: "/api/cart",
            data: JSON.parse(`{"process": "${button}", "productID": ${id}}`),
            dataType: "json"
        });

        let newQuantity = (button == "removeAllFromCart") ? 0 :  closestRow.find(".quantity").val();
        if (newQuantity == 0) {
            $(this).closest("tr").remove();
        }
    });
}


function setQuantity() {
    let oldQuantity;
    let newQuantity;
    let inputTag = $(".quantity");

    inputTag.bind("focus", function () {
        oldQuantity = $(this).val();
    });

    inputTag.bind("blur", function () {
        let id = $(this).closest("tr").find(".hidden-id").val();
        newQuantity = $(this).val();
        let quantity = newQuantity-oldQuantity;
        $.ajax({
            type: "post",
            url: "/api/cart",
            data: JSON.parse(`{"process": "setQuantity", "productID": ${id}, "quantity": ${quantity}}`),
            dataType: "json"
        });

        if (newQuantity == 0) {
            $(this).closest("tr").remove();
        }
    });

}


function main() {

    window.onload = function () {
        $("input[type='number']").inputSpinner();

        setQuantity();

        ajaxPost("addToCart");

        ajaxPost("removeFromCart");

        ajaxPost("removeAllFromCart")
    }
}


main();