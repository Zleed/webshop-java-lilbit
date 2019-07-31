

function ajaxPost(button) {
    $(`.${button}`).bind("click", function () {
            let id = $(this).closest("tr").find(".hidden-id").val();
            $.ajax({
                type: "post",
                url: "/",
                data: JSON.parse(`{"buttonType": "${button}", "productID": ${id}}`),
                dataType: "json"
            });
        });
}


function main() {

    window.onload = function () {

        $("input[type='number']").inputSpinner();

        ajaxPost("addButton");

        ajaxPost("removeButton");

        ajaxPost("removeAllButton")
    }
}


main();