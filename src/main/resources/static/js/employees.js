$(document).ready(function () {
//	 var table = $('#employeesTable').DataTable({
//			"sAjaxSource": "/employees/list-all",
//			"sAjaxDataProp": "",
//			"order": [[ 0, "asc" ]],
//			"aoColumns": [
//                  { "mData": "id"},
//                  { "mData": "firstName" },
//                  { "mData": "lastName" },
//                  { "mData": "position" },
//                  { "mData": "salary" },
//                  { "mData": "supervisorId" },
//                  { "mData": "teams" }
//			]
//	 }

    $.getJSON('/employees/list-all', { get_param: 'value' }, function(data) {
        $("#employeesTable").append("<tbody>");
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
        $("#employeesTable").append("</tbody>");
    });

    $("#search-form").submit(function (event) {
        event.preventDefault();
        search();
    });
});

function getSupervisorInfo(supervisorId, data) {
    $.each(data, function(index, element) {
        if (index === supervisorId) {
            return true;
        }
    });
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