$(".adiv").on('click',function () {
    var studentId = $(this).prev().val();
    $.ajax({
        type: "POST",
        url: "/interview/cancelResult",
        data: {
            "studentId": studentId
        },
        async: false,
        success: function (data) {
            alert(data.msg);
            location.reload(true);
        },
        error: function (data) {
            alert(data.msg)
        }
    });
});
var btn = $('.submit_btn');
var ipt = $(".ipt");
ipt.focus(function () {
    btn.css("right", "0px");
});
ipt.blur(function () {
    btn.css("right", "50px");
});