function logsGET(noElements, page) {
    $.ajax({
        type: "GET",
        url: "/logs" + '?noElements=' + noElements + '&page=' + page,
        success: okGetLogs,
        error: errorGetLogs,
        contentType: "application/json"
    });
    return false;
}

function okGetLogs(data) {
    console.log(data);
}

function errorGetLogs(data) {
    alert("An ERROR occurred while retrieving the logs.");
}

function infoCheckbox(box){

}

function warningCheckbox(box){

}

function dangerCheckbox(box){

}