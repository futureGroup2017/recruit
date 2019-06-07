// 获取弹窗
var modal = document.getElementById('myModal');
var modal2 = document.getElementById('myModal2');

// 打开弹窗的按钮对象
var btn2 = document.getElementsByClassName("details")[0];
var btn = document.getElementsByClassName("ti")[0];

// 获取 <span> 元素，用于关闭弹窗 that closes the modal
var span = document.getElementsByClassName("close")[0];
var span1 = document.getElementsByClassName("close")[1];

// 点击按钮打开弹窗
// btn.onclick = function() {
//     modal.style.display = "block";
// }
btn.onclick = function () {
    modal.style.display = "block";
}
btn2.onclick = function () {
    modal2.style.display = "block";
}
// 点击 <span> (x), 关闭弹窗
span.onclick = function () {
    modal.style.display = "none";
}
span1.onclick = function () {
    modal2.style.display = "none";
}
// 在用户点击其他地方时，关闭弹窗
window.onclick = function (event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
};

$(".ti").click(function () {
    $.ajax({
        type: "Get",
        url: "/question/findOne",
        data: {},
        async: false,
        success: function (data) {
            $('.question').text("问题："+data.data);
        },
        error: function (data) {
            alert(data)
        }
    });
});
// $("#btn").click(function () {
//     var txt = $(".txt");
//     var bb="";
//     for(let i=0;i<txt.length;i++){
//         bb = bb + $(txt[i]).text() + ",";
//     }
//     bb=bb.substring(0,bb.length-1);
//     //console.log(bb);
//     var score = $('.score');
//     var aa="";
//     for(let i=0;i<score.length;i++){
//         aa=aa+$(score[i]).val()+",";
//     }
//     console.log(aa);
//     aa=aa.substring(0,aa.length-1);
//     var cc =bb+"-"+aa;
//     var postData = {
//         "detail": cc
//     }
//     $.ajax({
//         async: false,
//         cache: false,
//         type: 'POST',
//         url: '/interview/submitInterview',
//         data: postData,
//         error: function () {
//             alert("123")
//         },
//         success: function (data) {
//              alert("成功")
//         }
//     });
// });


var ccc=0;
$(".btn").on('click', function () {
    var ipts=$('.ipt');
     console.log(ipts);
    for (let i=0;i<ipts.length;i++){
        var reg=/^([0-9](\.[0-9]+)?|10)$/ ;
        const aa=$(ipts[i]).val();
        var dd=$(ipts[i])
        var cures=dd.parent().parent().children('th').text();
        var ipt2=$('.ipt2');
        if(ipt2.val()==''){
            ipt2.val('无备注信息');
        }
        if(!reg.exec(aa)){
            ccc=0;
            $('.cure').addClass('uu');
            $('.cure').html(cures +'分值有误');
            setInterval(function () {
                $('.cure').removeClass('uu');
                $('.cure').html('');
            }, 2000);
            break
        }else (
            ccc=1
        )
        // console.log(reg.exec(aa));
    }
    var txt = $(".txt");
    var bb = "";
    for (let i = 0; i < txt.length; i++) {
        bb = bb + $(txt[i]).text() + ",";
    }
    bb = bb.substring(0, bb.length - 1);
    var score = $('.score');
    var aa = "";
    for (let i = 0; i < score.length; i++) {
        aa = aa + $(score[i]).val() + ",";
    }
    aa = aa.substring(0, aa.length - 1);
    var cc = bb + "-" + aa;
    $('.postData').val(cc);
    $(".btn").attr('type', 'submit');
    var studentId = $('.studentId').val();

    var postData = {
        "detail": cc,
        "studentId": studentId
    };
    if(ccc==1){
        $.ajax({
            async: false,
            cache: false,
            type: 'POST',
            url: '/interview/submitInterview',
            data: postData,
            error: function () {
                alert("123")
            },
            success: function (data) {
                // alert("成功")
                $('.cure').addClass('uu');
                $('.cure').html('打分成功，重新打分将覆盖原始成绩');
                setInterval(function () {
                    $('.cure').removeClass('uu');
                    $('.cure').html('');
                }, 2000);
                setTimeout(function () {
                    location.reload(true);
                }, 2000);
            }
        });
    }

});
