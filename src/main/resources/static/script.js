$(document).ready(function() {
    $("#next").click(function () {
        $.ajax({
            url: "/api/next",
            success: function (data) {
                $('#wrapper').css("background-image", "url(/image/" + data +")");
            }
        });
    });

    $("#prev").click(function () {
        $.ajax({
            url: "/api/prev",
            success: function (data) {
                $('#wrapper').css("background-image", "url(/image/" + data +")");
            }
        });
    });
});
