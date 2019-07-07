function submitteam(teamId) {
    var teamName = $('#teamName').val();
    var pmId = $('#pmId').val();
    var poId = $('#poId').val();
    var scrummasterId = $('#scrummasterId').val();

    if(isEmptyOrSpaces(teamName)) {
        alert("Please provide team name.");
        return;
    }


    if (teamId === "null") {
        $.ajax({
            url: "/manageteam/add/submit?teamName=" + teamName + "&pmId=" + pmId + "&poId=" + poId + "&scrummasterId=" + scrummasterId,
            method: 'GET',
            success: function(result){
                alert("Success!");
                window.location.replace("/browseteams");
        }});
    } else {
        $.ajax({
            url: "/manageteam/edit/submit?teamId=" + teamId + "&pmId=" + pmId + "&poId=" + poId + "&scrummasterId=" + scrummasterId,
            method: 'GET',
            success: function(result){
                alert("Success!");
                window.location.replace("/browseteams");
        }});
    }
}

function isEmptyOrSpaces(str){
    return str === null || str.match(/^ *$/) !== null;
}