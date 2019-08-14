
sideNav();

/* Set the width of the side navigation to 250px and the left margin of the page content to 250px and add a black background color to body */
function openNav() {

    let openNav = document.getElementById("openNav");
    openNav.addEventListener("click", () => {
        document.getElementById("mySidenav").style.width = "250px";
    })

}

/* Set the width of the side navigation to 0 and the left margin of the page content to 0, and the background color of body to white */
function closeNav() {
    let closeNav = document.getElementById("closeNav");
    closeNav.addEventListener("click", () => {
        document.getElementById("mySidenav").style.width = "0";
    });
}

function sideNav() {
    openNav();
    closeNav();
}

