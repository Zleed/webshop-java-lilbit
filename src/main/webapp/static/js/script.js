

function main() {
    window.onload = function () {
        $(".addToCart").bind("click", function () {
            $.ajax({
                type: "post",
                url: "/data-post-handler",
                data: JSON.parse(`{"process": "addToCart", "productID": ${$(this).val()}}`),
                dataType: "json"
            });
        });
    }
}


main();
