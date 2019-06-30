$(document).ready(function () {
    $("#search-form").submit(function (event) {
        event.preventDefault();
        fire_ajax_submit();
    });
});

function fire_ajax_submit() {

    var search = {}
    search["input"] = $("#input").val();

    $("#btn-search").prop("disabled", true);

    $.ajax({
        type: "POST",
        url: "/search",
        data: search,
        success: function(result){
//            $("#search-results").html(result);
            $("#search-form")[0].reset();
    }});
}