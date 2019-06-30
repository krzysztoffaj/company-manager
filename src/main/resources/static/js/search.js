$(document).ready(function () {
    $("#search-form").submit(function (event) {
        event.preventDefault();
//        alert("Tak");
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
        $("#search-results").html(result);
      }});


//    $.ajax({
//        type: "POST",
//        contentType: "application/json",
//        url: "/api/search",
//        data: JSON.stringify(search),
//        dataType: 'json',
//        cache: false,
//        timeout: 600000,
//        success: function (data) {
//
//            var json = "<h4>Ajax Response</h4><pre>"
//                + JSON.stringify(data, null, 4) + "</pre>";
//            $('#feedback').html(json);
//
//            console.log("SUCCESS : ", data);
//            $("#btn-search").prop("disabled", false);
//
//        },
//        error: function (e) {
//
//            var json = "<h4>Ajax Response</h4><pre>"
//                + e.responseText + "</pre>";
//            $('#feedback').html(json);
//
//            console.log("ERROR : ", e);
//            $("#btn-search").prop("disabled", false);
//
//        }
//    });

//    $.post("/search", search);


//    var search = {}
//    search["input"] = $("#input").val();



//    $.ajax({
//            type: "POST",
//            url: "/search",
//            dataType: 'json',
//            cache: false,
//            timeout: 600000,
//            success: function (data) {
//
//                var json = "<h4>Ajax Response</h4><pre>"
//                    + JSON.stringify(data, null, 4) + "</pre>";
//                $('#feedback').html(json);
//
//                console.log("SUCCESS : ", data);
//                $("#btn-search").prop("disabled", false);
//
//            },
//            error: function (e) {
//
//                var json = "<h4>Ajax Response</h4><pre>"
//                    + e.responseText + "</pre>";
//                $('#feedback').html(json);
//
//                console.log("ERROR : ", e);
//                $("#btn-search").prop("disabled", false);
//
//            }
//        });

}