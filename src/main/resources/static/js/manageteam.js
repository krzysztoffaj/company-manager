function submitteam(teamId) {
    var teamName = $('#teamName').val();
    var pmId = $('#pmId').val();
    var poId = $('#poId').val();
    var scrummasterId = $('#scrummasterId').val();

    if (teamId === "null") {
        $.ajax({
            url: "/manageteam/add/submit?teamName=" + teamName + "&pmId=" + pmId + "&poId=" + poId + "&scrummasterId=" + scrummasterId,
            method: 'GET',
            success: function(result){
                alert("Success!");
                window.location.replace("/");
        }});
    } else {
        $.ajax({
            url: "/manageteam/edit/submit?teamId=" + teamId + "&pmId=" + pmId + "&poId=" + poId + "&scrummasterId=" + scrummasterId,
            method: 'GET',
            success: function(result){
                alert("Success!");
                window.location.replace("/");
        }});
    }
}