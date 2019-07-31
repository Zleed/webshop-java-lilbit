

function main() {
    window.onload = function () {
        $(".addButton").bind("click", function () {
            $.ajax({
                type: "post",
                url: "/",
                data: JSON.parse(`{"buttonType": "addButton", "productID": ${$(this).val()}}`),
                dataType: "json"
            });
        });
    }
}


main();
