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

function createPermission(){
    var createPermissionForm = $('#createPermissionForm');

    console.log(createPermissionForm);
}

function permissionFormChanged(element){
    console.log(element);

    if(element.id == "pAssetsR"){
        console.log("bang!");

        console.log(element.checked);

        if(element.checked == false){
            console.log("element.checked: " + element.checked);
            var pAssetsRW = $('#pAssetsRW');
            pAssetsRW.checked = "false";
            document.getElementById("pAssetsRw").checked = false;
        }
    }
}