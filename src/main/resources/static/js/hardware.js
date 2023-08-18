const serial = document.getElementById('serial');
const manufacturer = document.getElementById('manufacturer');
const name = document.getElementById('name');
const description = document.getElementById('description');
const manufacturingDate = document.getElementById('manufacturingDate');
const installationDate = document.getElementById('installationDate');
const installationAddress = document.getElementById('installationAddress');
const ownerPhoneNumber = document.getElementById('ownerPhoneNumber');
const ownerEmail = document.getElementById('ownerEmail');

function cancelForm() {
    window.location = "/hardware";
}
function doDelete(uuid) {
    fetch((`/hardware/${uuid}`), {
        method: 'DELETE'
    })
        .then(() => {
                window.location.reload();
            }
        );
}

function save() {
    const uuid = document.getElementById('uuid');

    if (manufacturer.value === "") {
        alert("Field 'Manufacturer' must has text.");
        return;
    } else if (name.value === "") {
        alert("Field 'Name' must has text.");
        return;
    } else if (serial.value === "") {
        alert("Field 'S/N' must has text.");
        return;
    } else if (manufacturingDate.value === "") {
        alert("Field 'Production Date' must has text.");
        return;
    }

    if (ownerPhoneNumber.value !== "") {
        const phoneMatcher = new RegExp("^[\\+]?[(]?[0-9]{3}[)]?[-\\s\\.]?[0-9]{3}[-\\s\\.]?[0-9]{4,6}$");

        if (!phoneMatcher.test(ownerPhoneNumber.value)) {
            alert("Incorrect phone number format.");
            return;
        }
    }

    if (ownerEmail.value !== "") {
        const emailMatcher = new RegExp("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$");

        if (!emailMatcher.test(ownerEmail.value)) {
            alert("Incorrect email address format.");
            return;
        }
    }

    let body = {
        serialNumber: serial.value,
        manufacturer: manufacturer.value,
        name: name.value,
        description: description.value,
        manufacturingDate: manufacturingDate.value,
        installationDate: installationDate.value,
        installationAddress: installationAddress.value,
        ownerPhoneNumber: ownerPhoneNumber.value,
        ownerEmail: ownerEmail.value
    }
    let url = '/hardware';
    let method;

    if (window.location.href.includes("/add")) {
        method = 'POST';
    } else {
        method = 'PUT';
        url += '/' + uuid.value;
    }

    fetch(url, {
        method: method,
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        },
        body: JSON.stringify(body)
    }).then(() => {
        if (method === 'POST') {
            window.location.assign(url);
        } else {
            window.location.reload();
        }
    });
}

function dynamicObjects() {
    if (url.endsWith("/add")) {
        document.getElementById("datesButton").style.display = 'none';
        document.getElementById("header").childNodes[0].textContent = "New Hardware";
    } else if (url.endsWith("/edit")) {
        document.getElementById("header").childNodes[0].textContent = "Editing Hardware";
    }
}