$(document).ready(function() {
    $("#teamsubmit").click(function() {
        var teamName = $('#teamName').val();
        var pmId = $('#pmId').val();
        var poId = $('#poId').val();
        var scrummasterId = $('#scrummasterId').val();

        $.ajax({
            url: "/addnewteamsubmit?teamName=" + teamName + "&pmId=" + pmId + "&poId=" + poId + "&scrummasterId=" + scrummasterId,
            method: 'GET',
            success: function(result){
                alert("Success!");
                window.location.replace("/search");
        }});
    });
});