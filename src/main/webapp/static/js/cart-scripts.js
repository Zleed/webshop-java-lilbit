let oldQuantity;

function ajaxPost(button) {
    $(`.${button}`).bind("click", function () {
        let id = $(this).closest("tr").find(".hidden-id").val();
        $.ajax({
            type: "post",
            url: "/api/cart",
            data: JSON.parse(`{"process": "${button}", "productID": ${id}}`),
            dataType: "json"
        });
    });
}


function setQuantity() {
    let inputTag = $(".quantity");
    inputTag.bind("focus", function () {
        oldQuantity = $(this).val();
    });

    inputTag.bind("blur", function () {
        let id = $(this).closest("tr").find(".hidden-id").val();
        let newQuantity = $(this).val();
        let quantity = newQuantity-oldQuantity;
        $.ajax({
            type: "post",
            url: "/api/cart",
            data: JSON.parse(`{"process": "setQuantity", "productID": ${id}, "quantity": ${quantity}}`),
            dataType: "json"
        });
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