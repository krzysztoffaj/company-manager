function editTeam(id) {
    let editedTeam = {};

    editedTeam["id"] = Number(id);
    editedTeam["name"] = $("#teamName").val();
    editedTeam["pmId"] = $("#pmId").val();
    editedTeam["poId"] = $("#poId").val();
    editedTeam["scrummasterId"] = Number($("#scrummasterId").val());

    $.ajax({
        type: "PUT",
        contentType: "application/json",
        url: "/teams/edit/" + id,
        data: JSON.stringify(editedTeam),
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