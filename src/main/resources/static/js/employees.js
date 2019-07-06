$(document).ready(function () {
    $.getJSON('/employees/list-all', { get_param: 'value' }, function(data) {
        $("#employeesTable").append("<tbody>");
        $.each(data, function(index, element) {
            var id  = element.id;
            var firstName  = element.firstName;
            var lastName  = element.lastName;
            var position  = element.position;
            var salary  = element.salary;
            var supervisorId = element.supervisorId;
            var supervisor = data[supervisorId] || null;
            var teams = element.teams;

            $("#employeesTable").append(
                "<tr><td>"+ id +
                "</td><td>"+ firstName +
                "</td><td>"+ lastName +
                "</td><td>"+ position +
                "</td><td>"+ salary +
                "</td><td>"+ getSupervisorInfo(supervisor) +
                "</td><td>"+ teams +
                "</td></tr>"
            );
        });
        $("#employeesTable").append("</tbody>");
    });

    $("#search-form").submit(function (event) {
        event.preventDefault();
        search();
    });
});

function getSupervisorInfo(supervisor) {
    if(supervisor === null) {
        return '';
    } else {
        return supervisor.firstName + ' ' + supervisor.lastName;
    }
}

function search() {
    var query = $("#query").val();

    $.getJSON('/employees/search?query=' + query, { get_param: 'value' }, function(data) {
        $("#employeesTable").find("tbody").empty();
        $.each(data, function(index, element) {
            var id  = element.id;
            var firstName  = element.firstName;
            var lastName  = element.lastName;
            var position  = element.position;
            var salary  = element.salary;
            var supervisor  = getSupervisorInfo(element.supervisorId, data);
            var teams = element.teams;

            $("#employeesTable").append(
                "<tr><td>"+ id +
                "</td><td>"+ firstName +
                "</td><td>"+ lastName +
                "</td><td>"+ position +
                "</td><td>"+ salary +
                "</td><td>"+ supervisor +
                "</td><td>"+ teams +
                "</td></tr>"
            );
        });
    });
}