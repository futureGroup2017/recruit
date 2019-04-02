function stuaa() {
    $('.ipt2').val($('.ipt1').val());
    $('.ipt3').val($('.ipt1').val());
    // console.log($('.ipt2').val());
}

var btn = $('.submit_btn');
var ipt = $(".ipt");
ipt.focus(function () {
    btn.css("right", "0px");
});
ipt.blur(function () {
    btn.css("right", "50px");
});
var a1 = $('.a1');
a1.click(function () {
    $(".windows").css("display", "block");
});
$('.cancel').click(function () {
    $(".windows").css("display", "none");
});

//删除
function deleteStudent(studentId) {
    var isDel = confirm("您确认要删除吗？");
    if (isDel) {
        //要删除
        location.href = "/student/deleteStudent?studentId=" + studentId;
    }
}

$(".a3").on('click', function () {
    var studentId = $(this).prev().val();
    $.ajax({
        type: "POST",
        url: "/interview/joinInterview",
        data: {
            "studentId": studentId
        },
        async: false,
        success: function (data) {
            $('.cure').addClass('uu');
            $('.cure').html(data.msg);
            setTimeout(function () {
                $('.cure').css('display','none');
            },500);
            setTimeout(function () {
                location.reload(true);
            },500);
            // alert(data.msg)
        },
        error: function (data) {
            $('.cure').addClass('uu');
            $('.cure').html(data.msg);
            setTimeout(function () {
                $('.cure').css('display','none');
            },500);
            setTimeout(function () {
                location.reload(true);
            },500);

            alert(data.msg)
        }
    });
});
$(".a4").on('click', function () {
    var studentId = $(this).prev().val();
    $.ajax({
        type: "POST",
        url: "/interview/exitInterview",
        data: {
            "studentId": studentId
        },
        async: false,
        success: function (data) {
            $('.cure').addClass('uu');
            $('.cure').html(data.msg);
            setTimeout(function () {
                $('.cure').css('display','none');
            },500);
            // alert(data.msg)
            setTimeout(function () {
                location.reload(true);
            },500);

        },
        error: function (data) {
            $('.cure').addClass('uu');
            $('.cure').html(data.msg);
            setTimeout(function () {
                $('.cure').css('display','none');
            },500);
            // alert(data.msg)
            setTimeout(function () {
                location.reload(true);
            },500);

            alert(data.msg)
        }
    });
});
// 获取弹窗
var modal = document.getElementById('myModal');

// 获取图片插入到弹窗 - 使用 "alt" 属性作为文本部分的内容

var img = document.getElementById('imgshow');
var modalImg = document.getElementById("img01");
var captionText = document.getElementById("caption");
$('#myImg').click (function () {
    modal.style.display = "block";
    modalImg.src = img.src;
    captionText.innerHTML = img.alt;
}) ;

// 获取 <span> 元素，设置关闭按钮
var span = document.getElementsByClassName("close")[0];

// 当点击 (x), 关闭弹窗
$('.close').click(function () {
    modal.style.display = "none";
});

// 获取弹窗
var modal = document.getElementById('myModal');

// 获取图片插入到弹窗 - 使用 "alt" 属性作为文本部分的内容
var img1 = document.getElementById('imgshow1');
var modalImg1 = document.getElementById("img01");
var captionText1 = document.getElementById("caption");
$('#myImg1').click(function () {
    modal.style.display = "block";
    modalImg1.src = img1.src;
    captionText1.innerHTML = img1.alt;
});

// 获取 <span> 元素，设置关闭按钮
var span = document.getElementsByClassName("close")[0];

// 当点击 (x), 关闭弹窗
$('.close').click(function () {
    modal.style.display = "none";
});
function check() {
    if (($('#imgshow')[0].src != '' && $('#imgshow1')[0].src != '') || ($('#imgshow')[0].src == '' && $('#imgshow1')[0].src == '')) {
        return true;
    }
    else {
        $(function () {
            $('.cure').addClass('uu');
            $('.cure').html('成功');
            setInterval(function () {
                $('.cure').css('display', 'none');
            }, 2000);
            return false;
        });
    }
}
//删除按钮
    $('.deletes').on('click',function () {
        if (confirm('确认要删除吗?')) {
            var studentId=$(this).next().val();
            $.ajax({
                url: '/student/deleteStudent',
                success: function (data) {
                    window.parent.location.reload();
                    $('body').html(data);
                },
                error: function () {
                    alert('删除失败');
                },
                data: {'studentId': studentId},
                type: 'POST',
                dataType: 'text'

            });
        } else {
            window.event.returnValue = false;
        }
    })


