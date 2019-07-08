function addEmployee() {
    let employee = {};

    employee["firstName"] = $("#firstName").val();
    employee["lastName"] = $("#lastName").val();
    employee["position"] = $("#position").val();
    employee["salary"] = Number($("#salary").val());
    employee["supervisorId"] = Number($("#supervisorId").val());
    let teams = $("#teams").val().map(x => Number(x));

//    if(isEmptyOrSpaces(employee["firstName"])) {
//        swal("Invalid input!", "Please provide valid first name", "warning");
//        return;
//    }
//    if(isEmptyOrSpaces(employee["lastName"])) {
//        swal("Invalid input!", "Please provide valid last name", "warning");
//        return;
//    }
//    if(!isOnlyNumbersAndOptionallyDot(employee["salary"])) {
//        swal("Invalid input!", "Please provide valid salary", "warning");
//        return;
//    }

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/employees/add",
        data: JSON.stringify({"employee": employee, "teams": teams}),
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {
            swal({
                    title: "Success!",
                    text: "Employee added",
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

//function isEmptyOrSpaces(str){
//    return str === null || str.match(/^ *$/) !== null;
//}
//
//function isOnlyNumbersAndOptionallyDot(str){
//    return str.match(/(?<=^| )\d+(\.\d+)?(?=$| )/);
//}