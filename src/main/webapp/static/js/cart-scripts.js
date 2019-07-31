

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


function main() {

    window.onload = function () {

        $("input[type='number']").inputSpinner();

        ajaxPost("addToCart");

        ajaxPost("removeFromCart");

        ajaxPost("removeAllFromCart")
    }
}


main();