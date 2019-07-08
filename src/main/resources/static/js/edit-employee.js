function editEmployee(id) {
    let editedEmployee = {};

    editedEmployee["id"] = Number(id);
    editedEmployee["firstName"] = $("#firstName").val();
    editedEmployee["lastName"] = $("#lastName").val();
    editedEmployee["position"] = $("#position").val();
    editedEmployee["salary"] = Number($("#salary").val());
    editedEmployee["supervisorId"] = Number($("#supervisorId").val());
    let teams = $("#teams").val().map(x => Number(x));

    $.ajax({
        type: "PUT",
        contentType: "application/json",
        url: "/employees/edit/" + id,
        data: JSON.stringify({"employee": editedEmployee, "teams": teams}),
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {
            swal({
                    title: "Success!",
                    text: "Employee edited",
                    icon: "success"
            }).then(function() {
                window.location = "/employees";
            });
        },
        error: function (e) {
            swal("Error!", "Something went wrong", "error");
        }
    });
}