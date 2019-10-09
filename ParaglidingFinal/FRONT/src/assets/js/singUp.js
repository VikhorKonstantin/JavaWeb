$(document).ready(function () {
    $("#sportsmenCheck").click(function () { // задаем функцию при нажатиии на элемент с классом toggle
        $("#sportsmenForm").toggle(); //  скрываем, или отображаем все элементы <div>
    });
    if ($('#sportsmenCheck').is(!':checked')) {
        $('#sportsmenForm :input').attr('disabled', true);
    } else {
        $('#sportsmenForm :input').removeAttr('disabled');
    }
});