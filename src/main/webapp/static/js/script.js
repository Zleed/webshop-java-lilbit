window.onload = function () {
    $(".addToCart").bind("click", function () {
        $.ajax({
            type: "post",
            url: "/api/cart",
            data: JSON.parse(`{"process": "addToCart", "productID": ${$(this).val()}}`),
            dataType: "json"
        });
    });

    supplierPost();
};


function supplierPost() {

    let supplierButtons = document.querySelectorAll(".supplierButton");
    for (let supplierButton of supplierButtons) {
        supplierButton.addEventListener("click", evt => {
            let supplierID = supplierButton.value;
            api_post("/api/supplier", `{"process": "addToCart"}`);
            // api_post("/api/supplier", {"process": "test"});
        })
    }
}

function api_post(url, data) {
    $.ajax({
        type: "post",
        url: url,
        data: JSON.parse(data),
        dataType: "json",
        success: function (response) {
            console.log("TEST FOR RESPONSE");
            console.log(response    );
        }
    });
}

// function api_post(url, data, callback) {
//     fetch(url, {
//         method: "POST",
//         mode: "cors",
//         cache: "no-cache",
//         credentials: "same-origin",
//         headers: {
//             "Content-Type": "application/json",
//         },
//         redirect: "follow",
//         referrer: "no-referrer",
//         body: JSON.stringify(data),
//     })
// }
