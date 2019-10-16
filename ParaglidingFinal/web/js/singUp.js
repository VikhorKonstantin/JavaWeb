$(document).ready(function () {
    $("#sportsmenCheck").click(function () {
        $("#sportsmenForm").toggle();
        if ($('#sportsmenCheck').is(':checked')) {
            $('#sportsmenForm :input').removeAttr('disabled');
        } else {
            $('#sportsmenForm :input').attr('disabled','disabled');
        }
    });
});