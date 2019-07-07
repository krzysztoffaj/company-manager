$(document).ready(function () {
    $.getJSON('/teams/list-all', { get_param: 'value' }, function(data) {
        $("#teamsTable").append("<tbody>");
        fillTable(data);
        $("#teamsTable").append("</tbody>");
    });
});

function getManagingEmployeeInfoHyperlinked(managingEmployee) {
    if(managingEmployee === null) {
        return '';
    } else {
        return "<a href=/employees/edit/" + managingEmployee.id + ">" + managingEmployee.firstName + ' ' + managingEmployee.lastName + "</a>";
    }
}

function fillTable(data) {
    $.each(data, function(index, element) {
        var id  = element.id;
        var name  = element.name;
        var pm = element.members.find(member => member.id === element.pmId) || null;
        var po = element.members.find(member => member.id === element.poId) || null;
        var scrummaster = element.members.find(member => member.id === element.scrummasterId) || null;
        var members = element.members.map(member => "<a href=/employees/edit/" + member.id + ">"
                                        + member.firstName + ' ' + member.lastName + "</a>").join(', ');

        $("#teamsTable").append(
            "<tr>" +
                "<td>" + id + "</td>" +
                "<td>" + name + "</td>" +
                "<td>" + getManagingEmployeeInfoHyperlinked(pm) + "</td>" +
                "<td>" + getManagingEmployeeInfoHyperlinked(po) + "</td>" +
                "<td>" + getManagingEmployeeInfoHyperlinked(scrummaster) + "</td>" +
                "<td>" + members + "</td>" +
                "<td>" +
                    "<a href='/teams/edit/" + id + "'>" +
                        "<input id='editTeam' type='button' value='Edit'/>" +
                    "</a>" +
                "</td>" +
            "</tr>");
    });
}