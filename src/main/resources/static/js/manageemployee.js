function submitemployee(employeeId) {
    var firstName = $('#firstName').val();
    var lastName = $('#lastName').val();
    var position = $('#position').val();
    var salary = $('#salary').val();
    var supervisorId = $('#supervisorId').val();
    var teamIds = $('#teamIds').val();

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