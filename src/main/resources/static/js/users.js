const uuid = document.getElementById('uuid');

function cancelForm() {
    window.location = "/users";
}

function doDelete() {
    fetch(('/users/' + uuid.innerText), {
        method: 'DELETE'
    })
        .then(() => {
                window.location.reload();
            }
        );
}

function save() {
    const login = document.getElementById('login');

    if (!login.value) {
        alert("Login field can't be empty.");
        return;
    }

    let body = {
        uuid: uuid.value,
        login: login.value
    }

    let url = "/users";
    let putUrl = url + "/" + uuid.value;
    let method = "PUT";

    fetch(putUrl, {
            method,
            headers: {
                'Content-Type': 'application/json;charset=utf-8'
            },
            body: JSON.stringify(body)
        }
    ).then(() => window.location.assign(url));
}