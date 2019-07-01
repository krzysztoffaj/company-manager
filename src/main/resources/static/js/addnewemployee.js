$(document).ready(function() {
    $("#newemployeesubmit").click(function() {
        var firstName = $('#firstName').val();
        var lastName = $('#lastName').val();
        var position = $('#position').val();
        var salary = $('#salary').val();
        var supervisorId = $('#supervisorId').val();
        var teams = $('#teams').val();

        $.ajax({
            url: "/addnewemployeesubmit?firstName=" + firstName + "&lastName=" + lastName + "&position=" + position +
                                       "&salary=" + salary + "&supervisorId=" + supervisorId + "&teams=" + teams,
            method: 'GET',
            success: function(result){
                alert("Success!");
                window.location.replace("/search");
        }});
    });
});