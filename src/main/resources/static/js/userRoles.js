function doDelete(roleUuid) {
    fetch((document.URL + `/${roleUuid}`), {
        method: 'DELETE'
    }).then(() => location.reload());
}

function addRole() {
    let select = document.getElementById('roleSelect');
    let value = select.value;

    fetch((document.URL + `/${value}`), {
        method: 'PUT',
    }).then(() => location.reload());
}