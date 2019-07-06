$(document).ready( function () {
	 var table = $('#employeesTable').DataTable({
			"sAjaxSource": "/employees/list-all",
			"sAjaxDataProp": "",
			"order": [[ 0, "asc" ]],
			"aoColumns": [
                  { "mData": "id"},
                  { "mData": "firstName" },
                  { "mData": "lastName" },
                  { "mData": "position" },
                  { "mData": "salary" },
                  { "mData": "supervisorId" },
                  { "mData": "teams" }
			]
	 })
});