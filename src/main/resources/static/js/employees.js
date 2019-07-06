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

//    $.ajax({
//        type    : "GET",
//        url     : "/employees/list-all",
//        dataType: "json",
//        data: {
//            get_param: 'value'
////            action:"search",
////            type: 'employees',
////            parameter: parameter,
////            parameterContent: parameterContent,
//        },
//        success:function(data) {
//            alert(data);
//            $('#employeesTable').show();
//
//            var len = data.length;
//            for (var i = 0; i< len; i++) {
//                var id  = data[i].id;
//                var firstName  = data[i].firstName;
//                var lastName  = data[i].lastName;
//                var position  = data[i].position;
//                var salary  = data[i].salary;
//                var supervisorId  = data[i].supervisorId;
//                var teams = data[i].teams;
//
//                $("#employeesTable").append(
//                    "<tr><td>"+ id +
//                    "</td><td>"+ firstName +
//                    "</td><td>"+ lastName +
//                    "</td><td>"+ position +
//                    "</td><td>"+ salary +
//                    "</td><td>"+ supervisorId +
//                    "</td><td>"+ teams
//                );
//            }
//        }
//    });

    $.getJSON('/employees/list-all', { get_param: 'value' }, function(data) {
        $.each(data, function(index, element) {
            var id  = element.id;
            var firstName  = element.firstName;
            var lastName  = element.lastName;
            var position  = element.position;
            var salary  = element.salary;
            var supervisorId  = element.supervisorId;
            var teams = element.teams;

            $("#employeesTable").append(
                "<tr><td>"+ id +
                "</td><td>"+ firstName +
                "</td><td>"+ lastName +
                "</td><td>"+ position +
                "</td><td>"+ salary +
                "</td><td>"+ supervisorId +
                "</td><td>"+ teams +
                "</td>"
            );
        });
    });
});