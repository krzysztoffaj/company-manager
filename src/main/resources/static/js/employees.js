let allEmployees;

$(document).ready(function () {
    $.getJSON('/employees/list-all', { get_param: 'value' }, function(data) {
        $("#employeesTable").append("<tbody>");
        allEmployees = data;
        fillTable(data, allEmployees);
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
        fillTable(data, allEmployees);
    });
}

function fillTable(data, allEmployees) {
    $.each(data, function(index, element) {
        var id  = element.id;
        var firstName  = element.firstName;
        var lastName  = element.lastName;
        var position  = element.position;
        var salary  = element.salary;
        var supervisor = allEmployees[element.supervisorId - 1] || null;
        var teams = element.teams.map(team => "<a href=/teams/edit/" + team.id + ">" + team.name + "</a>").join(', ');

        $("#employeesTable").append(
            "<tr>" +
                "<td>" + id + "</td>" +
                "<td>" + firstName + "</td>" +
                "<td>" + lastName + "</td>" +
                "<td>" + position + "</td>" +
                "<td>" + salary + "</td>" +
                "<td>" + getSupervisorInfo(supervisor) + "</td>" +
                "<td>" + teams + "</td>" +
                "<td>" +
                    "<a href='/employees/edit/" + id + "'>" +
                        "<input id='editEmployee' type='button' value='Edit'/>" +
                    "</a>" +
                "</td>" +
            "</tr>");
    });
}