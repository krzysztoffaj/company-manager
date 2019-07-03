$(document).ready(function () {
    $("#search-form").submit(function (event) {
        event.preventDefault();
        search();
    });
});

function search() {
    var search = {}
    search["input"] = $("#input").val();

    $("#btn-search").prop("disabled", true);

    $.ajax({
        type: "POST",
        url: "/browseemployees",
        data: search,
        success: function(result){
            $("#search-results").html(result);
//            $("#search-form")[0].reset();
    }});
}