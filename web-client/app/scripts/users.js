var permissions;

var selectArray = [10, 25, 50, 75, 100];

var DEFAULT_NO_ELEMENTS = selectArray[1];
var DEFAULT_PAGE = 1;

var users;
var page = DEFAULT_PAGE;
var noOfUsersPages = 1;
var noElementsOnPage = DEFAULT_NO_ELEMENTS;

function createPermissionsTable() {

    getPermissions();

    var table = $('#permissionsTable');

    var tableHead = '<thead>' +
                    '<tr>' +
                    '<th><acronym title="The number in the table.">No</acronym></th>' +
                    '<th style="display: none;">id</th>' +
                    '<th><acronym title="The name of the permission.">Name</th>' +
                    '<th><acronym title="The user that created this permission.">Creator</th>' +
                    '<th><acronym title="The date when the permission was created.">Creation Date</th>' +
                    '<th><acronym title="The permission to read details about assets.">Fences R</th>' +
                    '<th><acronym title="The permission to read and edit details about assets.">Fences R+W</th>' +
                    '<th><acronym title="The permission to read details about objectives.">Objectives R</th>' +
                    '<th><acronym title="The permission to read and edit details about objectives.">Objectives R+W</th>'
                        +
                    '<th><acronym title="The permission to read details about users.">Users R</th>' +
                    '<th><acronym title="The permission to read and edit details about users.">Users R+E</th>' +
                    '<th><acronym title="The permission to read and edit details about assets and create users.">Users R+E+W</th>'
                        +
                    '<th><acronym title="The permission to read details about Alarms.">Alerts R</th>' +
                    '<th><acronym title="The permission to read and edit details about assets.">Alerts R+W</th>' +
                    '<th><acronym title="The permission to read details about logs.">Logs R</th>' +
                    '<th><acronym title="The permission to read and edit details about assets.">Logs R+W</th>' +
                    '<th><acronym title="The permission to read the statistics.">Statistics R</th>' +
                    '</tr>' +
                    '</thead>';

    table.append(tableHead);

    for (var i = 0; i < permissions.length; i++) {
        var obj = permissions[i];

        var bgColor = '#eee';

        if (i % 2 == 0) {
            bgColor = '#FFFFFF';
        }

        var tableEntry = '<tr style="background-color: ' + bgColor + '" onclick="selectPermissionRow(this)">' +
                         '<td>' + (i + 1) + '</td>' +
                         '<td style="display: none">' + obj.id + '</td>' +
                         '<td class="permissionName">' + obj.name + '</td>' +
                         '<td>' + obj.owner + '</td>' +
                         '<td>' + new Date(obj.createdDate).toDateString() + '</td>' +
                         '<td>' + obj.fencesRead + '</td>' +
                         '<td>' + obj.fencesReadWrite + '</td>' +
                         '<td>' + obj.objectivesRead + '</td>' +
                         '<td>' + obj.objectivesReadWrite + '</td>' +
                         '<td>' + obj.usersRead + '</td>' +
                         '<td>' + obj.usersReadUpdate + '</td>' +
                         '<td>' + obj.usersReadCreate + '</td>' +
                         '<td>' + obj.alertRead + '</td>' +
                         '<td>' + obj.alertReadWrite + '</td>' +
                         '<td>' + obj.logsRead + '</td>' +
                         '<td>' + obj.logsReadWrite + '</td>' +
                         '<td>' + obj.statisticsRead + '</td>' +

                         '</tr>';

        table.append(tableEntry);
    }

}

var lastPermissionRowSelected;

function selectPermissionRow(row) {
    //remove the old row selected
    if (lastPermissionRowSelected != undefined) {
        $(lastPermissionRowSelected).find(".selected").each(function (index, val) {
            $(val).removeClass("selected");
        });
    }

    if (lastPermissionRowSelected != row) {
        //select the new row
        $(row).find("td").each(function (index, val) {
            $(val).addClass("selected");
        });
        lastPermissionRowSelected = row;
    } else {
        lastPermissionRowSelected = undefined;
    }

    updateMenuButtons();

}

function updateMenuButtons() {
    var addClass = "enabled";
    var removeClass = "disabled";

    if (lastPermissionRowSelected == undefined) {
        addClass = "disabled";
        removeClass = "enabled";
    }

    $("#editPermission").addClass(addClass + "-edit");
    $("#editPermission").removeClass(removeClass + "-edit");
    $("#deletePermission").addClass(addClass + "-delete");
    $("#deletePermission").removeClass(removeClass + "-delete");
}

function editPermission() {
    if (lastPermissionRowSelected == undefined) {
        return;
    }

    var permissionName = $(lastPermissionRowSelected).find(".permissionName").html();

    var permissionOptions;
    $.each(permissions, function (index, value) {
        if (value.name == permissionName) {
            permissionOptions = value;
        }
    });

    var form = $("#createPermissionForm")[0];

    form.permissionId.value = permissionOptions.id;
    form.pName.value = permissionOptions.name;
    form.pAssetsR.checked = permissionOptions.fencesRead;
    form.pAssetsRW.checked = permissionOptions.fencesReadWrite;
    form.pObjectivesR.checked = permissionOptions.objectivesRead;
    form.pObjectivesRW.checked = permissionOptions.objectivesReadWrite;
    form.pUsersR.checked = permissionOptions.usersRead;
    form.pUsersRE.checked = permissionOptions.usersReadUpdate;
    form.pUsersREW.checked = permissionOptions.usersReadCreate;
    form.pAlertsR.checked = permissionOptions.alertRead;
    form.pAlertsRW.checked = permissionOptions.alertReadWrite;
    form.pLogsR.checked = permissionOptions.logsRead;
    form.pLogsRW.checked = permissionOptions.logsReadWrite;
    form.pStatisticsR.checked = permissionOptions.statisticsRead;

    $("#myModal").modal('show');

}

function deletePermission() {
    if (lastPermissionRowSelected == undefined) {
        return;
    }

    var permisionName = $(lastPermissionRowSelected).find(".permissionName").html();

    $.ajax({
               async: false,
               type: "DELETE",
               url: "/users/permissions",
               success: okDeletePermissions,
               error: failDeletePermissions,
               contentType: "application/json"
           });

    function okDeletePermissions(data) {
        $(lastPermissionRowSelected).remove();
        lastPermissionRowSelected = undefined;
        updateMenuButtons();
    }

    function failDeletePermissions(data) {
        alert("An error has occurred");
    }
}

function getPermissions() {
    var urlRequest = "/users/permissions";

    $.ajax({
               async: false,
               type: "GET",
               url: urlRequest,
               success: okGetPermissions,
               error: failGetPermissions,
               contentType: "application/json"
           });

    return false;
}

function okGetPermissions(data) {
    permissions = data;
}

function failGetPermissions() {
    alert("An ERROR occurred while retrieving the permissions details.");
}

function popUpPermissionCreation() {

    var form = $("#createPermissionForm")[0];
    form.permissionId.value = "";
    form.pName.value = "";
    form.pAssetsR.checked = false;
    form.pAssetsRW.checked = false;
    form.pObjectivesR.checked = false;
    form.pObjectivesRW.checked = false;
    form.pUsersR.checked = false;
    form.pUsersRE.checked = false;
    form.pUsersREW.checked = false;
    form.pAlertsR.checked = false;
    form.pAlertsRW.checked = false;
    form.pLogsR.checked = false;
    form.pLogsRW.checked = false;
    form.pStatisticsR.checked = false;

    $("#myModal").modal('show');
}

function popUpUserCreation(){
    $("#myModal2").modal('show');
}

function createPermission(form) {
    console.log(form);

    var permission = {
        "name": form.pName.value,
        "fencesRead": form.pAssetsR.checked,
        "fencesReadWrite": form.pAssetsRW.checked,
        "objectivesRead": form.pObjectivesR.checked,
        "objectivesReadWrite": form.pObjectivesRW.checked,
        "usersRead": form.pUsersR.checked,
        "usersReadUpdate": form.pUsersRE.checked,
        "usersReadCreate": form.pUsersREW.checked,
        "alertRead": form.pAlertsR.checked,
        "alertReadWrite": form.pAlertsRW.checked,
        "logsRead": form.pLogsR.checked,
        "logsReadWrite": form.pLogsRW.checked,
        "statisticsRead": form.pStatisticsR.checked
    };

    var type = "POST";
    if ( form.permissionId.value != ""){
        type = "PUT";
        permission.id = form.permissionId.value;
    }

    $.ajax({
               type: type,
               url: "/users/permissions",
               data: JSON.stringify(permission),
               success: okCreatePermission,
               error: errorCreatePermission,
               contentType: "application/json"
           });

    $("#myModal").modal('hide');
    return false;
}

function okCreatePermission(data){
    console.log("Creating the Permission ended SUCCESSFULLY.");
}

function errorCreatePermission(data){
    console.log("Creating the Permission ended BAD.");
}

function permissionFormChanged(element){
    if(element.id == "pAssetsR"){
        if(element.checked == false){
            document.getElementById("pAssetsRW").checked = false;
        }
    }
    else if(element.id == "pAssetsRW"){
        if(element.checked == true){
            document.getElementById("pAssetsR").checked = true;
        }
    }


    else if(element.id == "pObjectivesR"){
        if(element.checked == false){
            document.getElementById("pObjectivesRW").checked = false;
        }
    }
    else if(element.id == "pObjectivesRW"){
        if(element.checked == true){
            document.getElementById("pObjectivesR").checked = true;
        }
    }


    else if(element.id == "pUsersR"){
        if(element.checked == false){
            document.getElementById("pUsersRE").checked = false;
            document.getElementById("pUsersREW").checked = false;
        }
    }
    else if(element.id == "pUsersRE"){
        if(element.checked == true){
            document.getElementById("pUsersR").checked = true;
        }
    }
    else if(element.id == "pUsersRE"){
        if(element.checked == false){
            document.getElementById("pUsersREW").checked = false;
        }
    }
    else if(element.id == "pUsersREW"){
        if(element.checked == true){
            document.getElementById("pUsersR").checked = true;
            document.getElementById("pUsersRE").checked = true;
        }
    }


    else if(element.id == "pAlertsR"){
        if(element.checked == false){
            document.getElementById("pAlertsRW").checked = false;
        }
    }
    else if(element.id == "pAlertsRW"){
        if(element.checked == true){
            document.getElementById("pAlertsR").checked = true;
        }
    }

    else if(element.id == "pLogsR"){
        if(element.checked == false){
            document.getElementById("pLogsRW").checked = false;
        }
    }
    else if(element.id == "pLogsRW"){
        if(element.checked == true){
            document.getElementById("pLogsR").checked = true;
        }
    }
}

function getUsers(){
    var urlRequest = "/users" + '?noElements=' + noElementsOnPage + '&page=' + page;

    $.ajax({
        async: false,
        type: "GET",
        url: urlRequest,
        success: okGetUsers,
        error: failGetUsers,
        contentType: "application/json"
    });

    return false;
}

function okGetUsers(data){
    console.log("Creating the Permission ended SUCCESSFULLY.");
    users = data;
    console.log("Users: " + users);
}

function failGetUsers(data){
    console.log("Creating the Permission ended BAD.");
}

function createUsersTable(){
    getUsers();

    var table = $('#usersTable');

    document.getElementById("usersTable").innerHTML = "";

    var tableHead = '<thead>' +
        '<tr>' +
        '<th><acronym title="The number in the table.">No</acronym></th>' +
        '<th><acronym title="The First Name of the user.">First Name</th>' +
        '<th><acronym title="The Last Name of the user.">Last Name</th>' +
        '<th><acronym title="The Username of the user.">Username</th>' +
        '<th><acronym title="The phone number of the user.">Phone</th>' +
        '<th><acronym title="The email address of the user.">E-Mail</th>' +
        '<th><acronym title="The employee id of the user.">Employee ID</th>' +
        '<th><acronym title="The permission level of the user.">Permission</th>' +
        '<th><acronym title="The date when the user was created.">Created Date</th>' +
        '</tr>' +
        '</thead>';

    table.append(tableHead);

    var counter = (noElementsOnPage * (page-1)) + 1;

    for(var i = 0; i < users.length; i++) {
        var obj = users[i];

        var bgColor = '#eee';

        if(i % 2 == 0 ){
            bgColor = '#FFFFFF';
        }

        var tableEntry = '<tr style="background-color: '+bgColor+'">' +
            '<td>'+(counter)+'</td>' +
            '<td>'+obj.firstName+'</td>' +
            '<td>'+obj.lastName+'</td>' +
            '<td>'+obj.username+'</td>' +
            '<td>'+obj.phoneNumber+'</td>' +
            '<td>'+obj.email+'</td>' +
            '<td>'+obj.employeeId+'</td>' +
            '<td>'+obj.permission.name+'</td>' +
            '<td>'+new Date(obj.createdDate).toDateString()+'</td>' +

            '</tr>';

        table.append(tableEntry);

        counter = counter + 1;
    }
}

function createSelect(){
    var select = document.getElementById("usersSelect");

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

function createPages(){
    var pagination1 = $('#pagination1');
    var pagination2 = $('#pagination2');

    document.getElementById("pagination1").innerHTML = "";
    document.getElementById("pagination2").innerHTML = "";

    if(noOfUsersPages < page){
        page = noOfUsersPages;
    }

    if(page == 1) {
        pagination1.append('<li class="disabled"><a>Prev</a>');
        pagination2.append('<li class="disabled"><a>Prev</a>');
    }else{
        pagination1.append('<li class="enable" onclick="goToPage(page-1)"><a>Prev</a>');
        pagination2.append('<li class="enable" onclick="goToPage(page-1)"><a>Prev</a>');
    }

    if(page == noOfUsersPages) {
        pagination1.append('<li class="disabled"><a>Next</a>');
        pagination2.append('<li class="disabled"><a>Next</a>');
    }else{
        pagination1.append('<li class="enable" onclick="goToPage(page+1)"><a>Next</a>');
        pagination2.append('<li class="enable" onclick="goToPage(page+1)"><a>Next</a>');
    }
}

function noElementsChanged(_noElements){
    console.log("Changed no. elements on page from " + noElementsOnPage + " to " + _noElements);

    noElementsOnPage = _noElements;
    page = 1;

    createUsersTable();
}

function goToPage(pageNo){
    console.log("Changing from page " + page + " to page " + pageNo);

    page = pageNo;

    createUsersTable();
}

function createUsersPermissionSelect(){
    getPermissions();

    var select = document.getElementById("usersPermissionSelect");

    var option = document.createElement('option');

    option.value = "";
    option.text = "None";
    option.disabled = true;

    select.appendChild(option);

    for(var i=0 ; i<permissions.length ; i++) {
        var obj = permissions[i];
        option = document.createElement('option');

        option.value = obj.name;
        option.text = obj.name;

        select.appendChild(option);
    }
}

function createUser(form){
    console.log(form);

    var user = {
        "firstName": form.firstName.value,
        "lastName": form.lastName.value,
        "username": form.username.value,
        "phoneNumber": form.phone.value,
        "email": form.email.value,
        "employeeId": form.employeeId.value,
        "permissionName": form.permission.value,
        "password": form.userPassword.value
    };

    console.log("user: " + JSON.stringify(user));

    $.ajax({
        type: "POST",
        url: "/users",
        data: JSON.stringify(user),
        success: okCreateUser,
        error: errorCreateUser,
        contentType: "application/json"
    });

    $("#myModal2").modal('hide');

    return false;
}

function okCreateUser(data){
    console.log("Creating the User ended SUCCESSFULLY.");
}

function errorCreateUser(data){
    console.log("Creating the User ended BAD.");
}