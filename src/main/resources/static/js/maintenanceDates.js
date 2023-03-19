function doDelete(hardwareUuid, dateUuid) {
    let url = '/hardware/maintenanceDates/' + hardwareUuid + '/' + dateUuid;

    fetch(url, {
        method: 'DELETE'
    })
        .then(() => {
                window.location.reload();
            }
        );
}

let date = document.getElementById('date');
let description = document.getElementById('description');

function save(hardwareUuid, dateUuid) {
    let body = {
        uuid: dateUuid.value,
        date: date.value,
        description: description.value
    }
    let url = '/hardware/maintenanceDates/';
    let method;

    if (window.location.href.includes("/add")) {
        method = 'POST';
        url += hardwareUuid;
    } else {
        method = 'PUT';
        url += dateUuid.value;
    }

    fetch(url, {
        method: method,
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        },
        body: JSON.stringify(body)
    })
        .then(() => {
            if (method === 'POST') {
                window.location.assign(url);
            } else {
                window.location.reload();
            }
        });
}