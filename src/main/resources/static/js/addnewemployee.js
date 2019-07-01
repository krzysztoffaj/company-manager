$(document).ready(function() {
    $("#newemployeesubmit").click(function() {
        var firstName = $('#firstName').val();
        var lastName = $('#lastName').val();
        var position = $('#position').val();
        var salary = $('#salary').val();
        var supervisor = $('#supervisor').val();
        var teams = $('#teams').val();

        alert(supervisor);
        $.ajax({
            url: "/addnewemployeesubmit?firstName=" + firstName + "&lastName=" + lastName,
            method: 'GET',
            success: function(result){
                alert("Success!");
                window.location("/search");
        }});
    });
});