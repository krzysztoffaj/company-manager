function submitemployee(employeeId) {
    var firstName = $('#firstName').val();
    var lastName = $('#lastName').val();
    var position = $('#position').val();
    var salary = $('#salary').val();
    var supervisorId = $('#supervisorId').val();
    var teamIds = $('#teamIds').val();

    if(isEmptyOrSpaces(firstName)) {
        alert("Please provide first name.");
        return;
    }
    if(isEmptyOrSpaces(lastName)) {
        alert("Please provide last name.");
        return;
    }
    if(!isOnlyNumbersAndOptionallyDot(salary)) {
        alert("Please provide valid salary.");
        return;
    }


    if (employeeId === "null") {
        $.ajax({
            url: "/manageemployee/add/submit?firstName=" + firstName + "&lastName=" + lastName + "&position=" + position +
                                       "&salary=" + salary + "&supervisorId=" + supervisorId + "&teamIds=" + teamIds,
            method: 'GET',
            success: function(result){
                alert("Success!");
                window.location.replace("/browseemployees");
        }});
    } else {
        $.ajax({
            url: "/manageemployee/edit/submit?employeeId=" + employeeId + "&position=" + position + "&salary=" + salary +
                                    "&supervisorId=" + supervisorId + "&teamIds=" + teamIds,
            method: 'GET',
            success: function(result){
                alert("Success!");
                window.location.replace("/browseemployees");
        }});
    }
}

function isEmptyOrSpaces(str){
    return str === null || str.match(/^ *$/) !== null;
}

function isOnlyNumbersAndOptionallyDot(str){
    return str.match(/(?<=^| )\d+(\.\d+)?(?=$| )/);
}