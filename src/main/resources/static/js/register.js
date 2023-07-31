function save() {
    const login = document.getElementById("login").value;
    const password = document.getElementById("password").value;

    if (login === "") {
        alert("Login field must have a value.");
        return;
    } else if (password === "") {
        alert("Password field must have a value.");
        return;
    }

    let url = '/register';
    let method = 'POST';
    let body = {
        login: login,
        password: password
    }

    fetch(url, {
        method: method,
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        },
        body: JSON.stringify(body)
    }).then(r => {
        if (!r.ok) {
            alert("Oops! Something went wrong.");
        } else {
            window.location.replace("/");
        }
    });
}