

function ajaxPost(button) {
    $(`.${button}`).bind("click", function () {
            let id = $(this).closest("tr").find(".hidden-id").val();
            $.ajax({
                type: "post",
                url: "/data-post-handler",
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