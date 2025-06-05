const txtId = document.getElementById("txtId");
const txtPetName = document.getElementById("txtPetName");
const txtOwnerName = document.getElementById("txtOwnerName");
const txtService = document.getElementById("txtService");
const txtDuration = document.getElementById("txtDuration");
const txtPrice = document.getElementById("txtPrice");
const statusMsg = document.getElementById("statusMsg");
txtId.focus();

function loadBath(data) {
    txtId.value = data.id;
    txtPetName.value = data.petName;
    txtOwnerName.value = data.ownerName;
    txtService.value = data.service;
    txtDuration.value = data.duration;
    txtPrice.value = data.price;
}

async function getBath() {
    if (!txtId.value || !txtId.value.trim().length) {
        statusMsg.innerHTML = "Informe o ID do servi&ccedil;o para realizar a consulta.";
    } else {
        fetch("api/bath/" + txtId.value.trim(), {
            method: "GET",
            headers: {
                "Content-Type": "application/json; charset=utf-8"
            }
        })
        .then(response => {
            if (!response.ok) {
                throw new Error(response.status);
            }
            return response.json();
        })
        .then(data => {
            loadBath(data);
            statusMsg.innerHTML = "Dados do servi&ccedil;o foram carregados.";
        })
        .catch(error => statusMsg.innerHTML = "<strong>Erro!</strong> Servi&ccedil;o com ID " + txtId.value + " n&atilde;o encontrado.<br/>(" + error.message + ")");
    }
    
    txtId.focus();
}

async function addBath() {
    if (!txtId.value || !txtId.value.trim().length) {
        statusMsg.innerHTML = "Informe o ID do servi&ccedil;o para inserir um novo servi&ccedil;o.";
    } else {
        fetch("api/bath", {
            method: "POST",
            body: JSON.stringify({
                id: txtId.value,
                petName: txtPetName.value,
                ownerName: txtOwnerName.value,
                service: txtService.value,
                duration: parseInt(txtDuration.value),
                price: parseFloat(txtPrice.value)
            }),
            headers: {
                "Content-Type": "application/json; charset=utf-8"
            }
        })
        .then(response => {
            if (!response.ok) {
                throw new Error(response.status);
            }
            return response.text();
        })
        .then(data => statusMsg.innerHTML = "Servi&ccedil;o para '" + txtPetName.value + "' (ID " + txtId.value + ") inserido.")
        .catch(error => statusMsg.innerHTML = "<strong>Erro!</strong> N&atilde;o foi poss&iacute;vel inserir o servi&ccedil;o com ID " + txtId.value + ".<br/>(" + error.message + ")");
    }

    txtId.focus();
}

async function setBath() {
    if (!txtId.value || !txtId.value.trim().length) {
        statusMsg.innerHTML = "Informe o ID do servi&ccedil;o para atualizar as informa&ccedil;&otilde;es.";
    } else {
        fetch("api/bath", {
            method: "PUT",
            body: JSON.stringify({
                id: parseInt(txtId.value),
                petName: txtPetName.value,
                ownerName: txtOwnerName.value,
                service: txtService.value,
                duration: parseInt(txtDuration.value),
                price: parseFloat(txtPrice.value)
            }),
            headers: {
                "Content-Type": "application/json; charset=utf-8"
            }
        })
        .then(response => {
            if (!response.ok) {
                throw new Error(response.status);
            }
            return response.text();
        })
        .then(data => statusMsg.innerHTML = "Servi&ccedil;o para '" + txtPetName.value + "' (ID " + txtId.value + ") atualizado.")
        .catch(error => statusMsg.innerHTML = "<strong>Erro!</strong> N&atilde;o foi poss&iacute;vel atualizar o servi&ccedil;o com ID " + txtId.value + ".<br/>(" + error.message + ")");
    }

    txtId.focus();
}

async function delBath() {
    if (!txtId.value || !txtId.value.trim().length) {
        statusMsg.innerHTML = "Informe o ID do servi&ccedil;o para remover o servi&ccedil;o.";
    } else {
        fetch("api/bath/" + txtId.value.trim(), {
            method: "DELETE",
            headers: {
                "Content-Type": "application/json; charset=utf-8"
            }
        })
        .then(response => {
            if (!response.ok) {
                throw new Error(response.status);
            }
            return response.text();
        })
        .then(data => {
            const id = txtId.value;
            const petName = txtPetName.value;
            clearFields();
            statusMsg.innerHTML = "Servi&ccedil;o com ID '" + id + "' (" + petName  + ") removido.";
        })
        .catch(error => statusMsg.innerHTML = "<strong>Erro!</strong> N&atilde;o foi poss&iacute;vel remover o servi&ccedil;o com ID " + txtId.value + ".<br/>(" + error.message + ")");
    }

    txtId.focus();
}

async function clearFields() {
    txtId.value = "";
    txtPetName.value = "";
    txtOwnerName.value = "";
    txtService.value = "";
    txtDuration.value = "";
    txtPrice.value = "";
    statusMsg.innerHTML = "";
    txtId.focus();
}

