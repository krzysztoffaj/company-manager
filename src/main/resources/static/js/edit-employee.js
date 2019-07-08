$(document).ready(function() {
//    var editedEmployee;
//
//    $.getJSON('/employees/get-by-id/1', { get_param: 'value' }, function(data) {
//        fillTable(data[0]);
//    });
//
//    $("#employeeSubmit").click(function() {
//        swal(editedEmployee);
//    })
})


function fillTable(element) {
//    $.each(data, function(index, element) {
        var id  = element.id;
        var firstName  = element.firstName;
        var lastName  = element.lastName;

        console.log(firstName);
}

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
            swal("Success!", "Employee edited", "success");
            window.location.replace("/employees");
        },
        error: function (e) {
            swal("Error!", "Something went wrong", "error");
        }
    });
}