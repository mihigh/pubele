
var DEFAULT_DISPLAY_INFO = true;
var DEFAULT_DISPLAY_WARN = true;
var DEFAULT_DISPLAY_DANGER = true;

var selectArray = [10, 25, 50, 75, 100];

var DEFAULT_NO_ELEMENTS = selectArray[1];
var DEFAULT_PAGE = 1;

var displayInfo = DEFAULT_DISPLAY_INFO;
var displayWarn = DEFAULT_DISPLAY_WARN;
var displayDanger = DEFAULT_DISPLAY_DANGER;

var noElements = DEFAULT_NO_ELEMENTS;
var page = DEFAULT_PAGE;

var logs;

var noOfPages = 1;

function createSelect(){
    var select = document.getElementById("logSelect");

    for(var i=0 ; i<selectArray.length ; i++) {
        var option = document.createElement('option');
        option.value = selectArray[i];
        option.text = selectArray[i];
        if(selectArray[i] == DEFAULT_NO_ELEMENTS){
            option.selected = true;
        }
        select.appendChild(option);
    }
}

function constructTable(){
    createPages();

    logsGET (noElements, page);

    var table = $('#logTable');

    document.getElementById("logTable").innerHTML = "";

    var tableHead = '<thead><tr><th width="60px">No</th><th>Message</th><th width="150px">Date</th><th width="100px">Severity</th></tr></thead>';

    table.append(tableHead);

    var counter = (noElements * (page-1)) + 1;

    for(var i = 0; i < logs.length; i++) {
        var obj = logs[i];

        var typeOfLog;
        if(obj.severity == "Info"){
            if(displayInfo == false){
                continue;
            }
            typeOfLog = "success";
        }
        if(obj.severity == "Warning"){
            if(displayWarn == false){
                continue;
            }
            typeOfLog = "warning";
        }
        if(obj.severity == "Error"){
            if(displayDanger == false){
                continue;
            }
            typeOfLog = "danger";
        }

        var tableLine = '<tr class='+typeOfLog+'>' +
                            '<td>'+counter+'</td>' +
                            '<td>'+obj.message+'</td>' +
                            '<td>'+new Date(obj.date).toDateString()+'</td>' +
                            '<td>'+obj.severity+'</td>' +
                        '</tr>';

        table.append(tableLine);
        counter = counter + 1;
    }
}

function noElementsChanged(_noElements){
    console.log("Changed no. elements on page from " + noElements + " to " + _noElements);

    noElements = _noElements;
    page = 1;

    constructTable();
}

function createPages(){
    logDetailsGET();

    var pagination1 = $('#pagination1');
    var pagination2 = $('#pagination2');

    document.getElementById("pagination1").innerHTML = "";
    document.getElementById("pagination2").innerHTML = "";

    if(noOfPages < page){
        page = noOfPages;
    }

    if(page == 1) {
        pagination1.append('<li class="disabled"><a>Prev</a>');
        pagination2.append('<li class="disabled"><a>Prev</a>');
    }else{
        pagination1.append('<li class="enable" onclick="goToPage(page-1)"><a>Prev</a>');
        pagination2.append('<li class="enable" onclick="goToPage(page-1)"><a>Prev</a>');
    }

    if(page == noOfPages) {
        pagination1.append('<li class="disabled"><a>Next</a>');
        pagination2.append('<li class="disabled"><a>Next</a>');
    }else{
        pagination1.append('<li class="enable" onclick="goToPage(page+1)"><a>Next</a>');
        pagination2.append('<li class="enable" onclick="goToPage(page+1)"><a>Next</a>');
    }
}

function goToPage(pageNo){
    console.log("Changing from page " + page + " to page " + pageNo);

    page = pageNo;

    constructTable();
}

function logDetailsGET(){
    var urlRequest = "/logs/details" + '?noElements=' + noElements +
        '&info=' + displayInfo + '&warn=' + displayWarn + '&danger=' + displayDanger;

    $.ajax({
        async: false,
        type: "GET",
        url: urlRequest,
        success: okGetLogsDetails,
        error: failGetLogsDetails,
        contentType: "application/json"
    });

    return false;
}

function okGetLogsDetails(data){
    noOfPages = data.noOfPages;
}

function failGetLogsDetails(){
    alert("An ERROR occurred while retrieving the logs details.");
}

function logsGET(_noElements, _page) {
    noElements = _noElements;
    page = _page;

    var urlRequest = "/logs" + '?noElements=' + noElements + '&page=' + page +
                     '&info=' + displayInfo + '&warn=' + displayWarn + '&danger=' + displayDanger;

    $.ajax({
        async: false,
        type: "GET",
        url: urlRequest,
        success: okGetLogs,
        error: failGetLogs,
        contentType: "application/json"
    });

    return false;
}

function okGetLogs(data) {
    logs = data;
}

function failGetLogs() {
    alert("An ERROR occurred while retrieving the logs.");
}

function reconstruct(){
    constructTable();
}

function infoCheckbox(box){
    displayInfo = box.checked;

    console.log("Info Checkbox = " + displayInfo);
    reconstruct();
}

function warningCheckbox(box){
    displayWarn = box.checked;

    console.log("Warn Checkbox = " + displayInfo);
    reconstruct();
}

function dangerCheckbox(box){
    displayDanger = box.checked;

    console.log("Danger Checkbox = " + displayInfo);
    reconstruct();
}
