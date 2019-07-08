$(document).ready(function(){
    $('#teamSubmit').attr('disabled', true);
    $('#teamName').keyup(function(){
        if($(this).val().length !=0)
            $('#teamSubmit').attr('disabled', false);
        else
            $('#teamSubmit').attr('disabled',true);
    })
});

function addTeam() {
    let team = {};

    team["name"] = $("#teamName").val();
    team["pmId"] = $("#pmId").val();
    team["poId"] = $("#poId").val();
    team["scrummasterId"] = Number($("#scrummasterId").val());

//    if(isEmptyOrSpaces(employee["name"])) {
//        swal("Invalid input!", "Please provide valid name", "warning");
//        return;
//    }

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/teams/add",
        data: JSON.stringify(team),
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {
            swal({
                    title: "Success!",
                    text: "Team added",
                    icon: "success"
            }).then(function() {
                window.location = "/teams";
            });
        },
        error: function (e) {
            swal("Error!", "Something went wrong", "error");
        }
    });
}

//function isEmptyOrSpaces(str){
//    return str === null || str.match(/^ *$/) !== null;
//}