var permissions;

function createPermissionsTable(){

    getPermissions();

    var table = $('#permissionsTable');

    var tableHead = '<thead>' +
                        '<tr>' +
                            '<th><acronym title="The number in the table.">No</acronym></th>' +
                            '<th><acronym title="The name of the permission.">Name</th>' +
                            '<th><acronym title="The user that created this permission.">Creator</th>' +
                            '<th><acronym title="The date when the permission was created.">Creation Date</th>' +
                            '<th><acronym title="The permission to read details about assets.">Fences R</th>' +
                            '<th><acronym title="The permission to read and edit details about assets.">Fences R+W</th>' +
                            '<th><acronym title="The permission to read details about objectives.">Objectives R</th>' +
                            '<th><acronym title="The permission to read and edit details about objectives.">Objectives R+W</th>' +
                            '<th><acronym title="The permission to read details about users.">Users R</th>' +
                            '<th><acronym title="The permission to read and edit details about users.">Users R+E</th>' +
                            '<th><acronym title="The permission to read and edit details about assets and create users.">Users R+E+W</th>' +
                            '<th><acronym title="The permission to read details about Alarms.">Alerts R</th>' +
                            '<th><acronym title="The permission to read and edit details about assets.">Alerts R+W</th>' +
                            '<th><acronym title="The permission to read details about logs.">Logs R</th>' +
                            '<th><acronym title="The permission to read and edit details about assets.">Logs R+W</th>' +
                            '<th><acronym title="The permission to read the statistics.">Statistics R</th>' +
                        '</tr>' +
                    '</thead>';

    table.append(tableHead);

    for(var i = 0; i < permissions.length; i++) {
        var obj = permissions[i];

        var bgColor = '#eee';

        if(i % 2 == 0 ){
            bgColor = '#FFFFFF';
        }

        var tableEntry = '<tr style="background-color: '+bgColor+'">' +
                            '<td>'+(i+1)+'</td>' +
                            '<td>'+obj.name+'</td>' +
                            '<td>'+obj.owner+'</td>' +
                            '<td>'+new Date(obj.createdDate).toDateString()+'</td>' +
                            '<td>'+obj.fencesRead+'</td>' +
                            '<td>'+obj.fencesReadWrite+'</td>' +
                            '<td>'+obj.objectivesRead+'</td>' +
                            '<td>'+obj.objectivesReadWrite+'</td>' +
                            '<td>'+obj.usersRead+'</td>' +
                            '<td>'+obj.usersReadUpdate+'</td>' +
                            '<td>'+obj.usersReadCreate+'</td>' +
                            '<td>'+obj.alertRead+'</td>' +
                            '<td>'+obj.alertReadWrite+'</td>' +
                            '<td>'+obj.logsRead+'</td>' +
                            '<td>'+obj.logsReadWrite+'</td>' +
                            '<td>'+obj.statisticsRead+'</td>' +

                         '</tr>';

        table.append(tableEntry);
    }

}

function getPermissions(){
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

function okGetPermissions(data){
    permissions = data;
}

function failGetPermissions(){
    alert("An ERROR occurred while retrieving the permissions details.");
}

function popUpPermissionCreation(){
    $("#myModal").modal('show');
}

function createPermission(form){
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

    console.log("permission: " + JSON.stringify(permission));

    $.ajax({
        type: "POST",
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
