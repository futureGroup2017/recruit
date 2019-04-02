// $('.off').on('click',function () {
//     $(this).addClass('on');
//     $('.open').removeClass('on');
// })
// $('.open').on('click',function () {
//     $(this).addClass('on');
//     $('.off').removeClass('on');
// })
// // 获取弹窗
 var modal = document.getElementById('myModal');
// // 打开弹窗的按钮对象
// var btn = document.getElementById("myBtn");
// // 获取 <span> 元素，用于关闭弹窗 that closes the modal
 var span = document.getElementsByClassName("closes")[0];
// // 点击按钮打开弹窗
// btn.onclick = function() {
//     modal.style.display = "block";
// }

//点击 <span> (x), 关闭弹窗
span.onclick = function() {
    modal.style.display = "none";
}

// 在用户点击其他地方时，关闭弹窗
// window.onclick = function(event) {
//     if (event.target == modal) {
//         modal.style.display = "none";
//     }
// }
//删除按钮
$('.delete').on('click',function () {
    var scoreItemId=$(this).next().val();
    console.log(scoreItemId);
    if(confirm('确认要删除吗?')){
        // var scoreItemId=$('.scoreItemId').val();
        //var scoreItemId=$('.delete').next('.scoreItemId').val();
        console.log(scoreItemId);
        $.ajax({
            url:'/scoreItem/deleteScoreItem',
            success: function(data){
                window.parent.location.reload();
                $('body').html(data);
            },
            error: function () {
                alert('删除失败');
            },
            data:{'scoreItemId': scoreItemId},
            type: 'POST',
            dataType: 'text'

        });
    }else{
        return false;
    }

})

//开启
$(".open").on('click', function () {
    $.ajax({
        type: "POST",
        url: "/student/startSignUp",
        async: false,
        success: function (data) {
            //alert(data.msg)
            // $(this).addClass('on');
            // $('.off').removeClass('on');
            location.reload(true);
        },
        error: function (data) {
            alert(data.msg)
        }
    });
});
//关闭
$(".off").on('click', function () {
    $.ajax({
        type: "POST",
        url: "/student/stopSignUp",
        async: false,
        success: function (data) {
            //alert(data.msg)
            // $(this).addClass('on');
            // $('.open').removeClass('on');
            location.reload(true);

        },
        error: function (data) {
            alert(data.msg)
        }
    });
});
//修改
$('.modi').on('click',function () {
    // var zhi=$('.zhi').text;
    $('#myModal').css('display','block');
    $('h4').text('请修改打分项');
    $('.write').attr('placeholder',"请输入修改后的打分项");
    var first=$(this).parent().prev();

    $('.write').val(first.text());
    $(".Sure").addClass('nody').removeClass('add');
    var scoreItemId=$(this).parent().next().children('input.scoreItemId').val();
    console.log(scoreItemId);
    $(".nody").on('click', function () {
        var scoreName=$('.write').val();
        // var scoreItemId=$('.scoreItemId').val();
        $.ajax({
            type: "POST",
            url: "/scoreItem/update",
            async: false,
            data: {
                'scoreName':scoreName,
                'scoreItemId':scoreItemId
            },
            success: function (data) {
                $('.cure').addClass('uu');
                $('.cure').html('修改成功');
                setInterval(function () {
                    $('.cure').css('display','none');
                },2000);
                setTimeout(function () {
                    location.reload(true);
                },1000);

            },
            error: function (data) {
                $('.cure').addClass('uu');
                $('.cure').html(data.msg);
                setInterval(function () {
                    $('.cure').css('display','none');
                },2000);
                setTimeout(function () {
                    location.reload(true);
                },1000);
                //alert(data.msg)
            }
        });
    });
});


//添加
$('#myBtn').on('click',function () {
    $('#myModal').css('display','block');
    $('h4').text('请添加打分项');
    $('.write').val("");
    $('.write').attr('placeholder',"请输入需要的面试打分项");
    $('.Sure').addClass('add').removeClass('nody');
    //提交添加项
    $(".add").on('click', function () {
        var scoreName=$('.write').val();
        console.log(scoreName);
        // var scoreItemId=$('.scoreItemId').val();
        if (scoreName!='') {
            $.ajax({
                type: "POST",
                url: "/scoreItem/addScoreItem",
                async: false,
                data: {
                    'scoreName': scoreName,
                    // 'scoreItemId':scoreItemId
                },
                success: function (data) {
                    $('.cure').addClass('uu');
                    $('.cure').html('添加成功');
                    setInterval(function () {
                        $('.cure').css('display','none');
                    },2000);
                    setTimeout(function () {
                        location.reload(true);
                    },1000)

                }
            });

            return true;
        }else{
            $('.cure').addClass('uu');
            $('.cure').html('不能为空');
            setInterval(function () {
                $('.cure').css('display','none');
            },2000);
            return false;
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
