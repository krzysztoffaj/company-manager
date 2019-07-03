$(document).ready(function() {
    $("#employeesubmit").click(function() {
        var firstName = $('#firstName').val();
        var lastName = $('#lastName').val();
        var position = $('#position').val();
        var salary = $('#salary').val();
        var supervisorId = $('#supervisorId').val();
        var teamIds = $('#teamIds').val();

//        $.ajax({
//            url: "/addnewemployeesubmit?firstName=" + firstName + "&lastName=" + lastName + "&position=" + position +
//                                       "&salary=" + salary + "&supervisorId=" + supervisorId + "&teamIds=" + teamIds,
//            method: 'GET',
//            success: function(result){
//                alert("Success!");
//                window.location.replace("/search");
//        }});
        $.ajax({
            url: "/editemployeesubmit?position=" + position + "&salary=" + salary + "&supervisorId=" + supervisorId + "&teamIds=" + teamIds,
            method: 'GET',
            success: function(result){
                alert("Success!");
                window.location.replace("/search");
        }});
    });
});