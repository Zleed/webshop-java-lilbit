window.onload = function () {

    addToCartEventHandler();

    supplierEventHandler();
};

function addToCartEventHandler() {
    $(".addToCart").bind("click", function () {
        $.ajax({
            type: "post",
            url: "/api/cart",
            data: JSON.parse(`{"process": "addToCart", "productID": ${$(this).val()}}`),
            dataType: "json"
        });
    });
}


function supplierEventHandler() {

    let supplierButtons = document.querySelectorAll(".supplierButton");
    for (let supplierButton of supplierButtons) {
        supplierButton.addEventListener("click", (event) => buttonOnclickHandler(event,supplierButton))
    }
}

function buttonOnclickHandler(event, supplierButton) {
    let supplierID = supplierButton.value;
    let productCategoryID = supplierButton.dataset.productCategoryId;

    api_post("/products/*",
        `{"supplierID" : "${supplierID}", "productCategoryID" : "${productCategoryID}" }`,
        renderProducts);

    // api_post("/api/supplier", {"process": "test"});
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

function renderProducts(products) {
    console.log(products);

    let productContainer = document.querySelector(".product-container");
    while (productContainer.firstChild) {
        productContainer.removeChild(productContainer.firstChild)
    }

    for (let product of products) {

        let productHTML =
            `<div class="col col-sm-12 col-md-6 col-lg-4">
                <div class="card">
                <img class="" src="/static/img/product_${product.id}.jpg" alt=""/>
                    <div class="card-header">
                    <h4 class="card-title">${product.name}</h4>
                    <p class="card-text">${product.description}</p>
                </div>
                <div class="card-body">
                    <div class="card-text">
                        <p class="lead">${ product.defaultPrice.toFixed(1) } USD</p>
                    </div>
                <div class="card-text">
                <button class="btn btn-success addToCart" value="${product.id}">
                    Add to cart
                </button>
                </div>
            </div>`;

        productContainer.insertAdjacentHTML("afterbegin",productHTML);
    }

    addToCartEventHandler();
}

//TODO Get to work with Java ES6

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
