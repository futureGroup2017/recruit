$(".yesresult").click(function () {
    var studentId = $(".studentId").val();
    $.ajax({
        type: "POST",
        url: "/interview/passInterview",
        data: {
            "studentId": studentId
        },
        async: true,
        success: function (data) {
            location.reload(true);
            alert(data.msg)
        },
        error: function (data) {
            alert(data)
        }
    });
});
$(".outresult").click(function () {
    var studentId = $(".studentId").val();
    $.ajax({
        type: "POST",
        url: "/interview/outInterview",
        data: {
            "studentId": studentId
        },
        async: false,
        success: function (data) {
            location.reload(true);
            alert(data.msg)
        },
        error: function (data) {
            alert(data)
        }
    });
});