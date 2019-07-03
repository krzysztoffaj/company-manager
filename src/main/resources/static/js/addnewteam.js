function submitteam(teamId) {
    var teamName = $('#teamName').val();
    var pmId = $('#pmId').val();
    var poId = $('#poId').val();
    var scrummasterId = $('#scrummasterId').val();

    if (teamId === "null") {
        $.ajax({
            url: "/addnewteamsubmit?teamName=" + teamName + "&pmId=" + pmId + "&poId=" + poId + "&scrummasterId=" + scrummasterId,
            method: 'GET',
            success: function(result){
                alert("Success!");
                window.location.replace("/search");
        }});
    } else {
        $.ajax({
            url: "/editteamsubmit?teamId=" + teamId + "&pmId=" + pmId + "&poId=" + poId + "&scrummasterId=" + scrummasterId,
            method: 'GET',
            success: function(result){
                alert("Success!");
                window.location.replace("/search");
        }});
    }
}