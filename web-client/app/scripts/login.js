function login(form) {
    var user = {
        "name": form.username.value,
        "password": form.password.value
    };

    $.ajax({
               type: "POST",
               url: "/api/v1/login",
               data: JSON.stringify(user),
               success: okLogin,
               error: errorLogin,
               contentType: "application/json"
           });
    return false;
}

function okLogin(data) {
    window.location.href = '/Home.html';
}

function errorLogin(data) {
    alert("Username or password are invalid");
}