const url = document.URL;
const list = document.getElementById("navbarNavDropdown").getElementsByTagName("li");

for (let index = 0; index < list.length; index++) {
    let item = list[index];
    let link = item.firstElementChild;
    let href = link ? String(link.href) : "-";

    if (href === url) {
        link.classList.add("active");
    } else {
        link.classList.remove("active");
    }
}