// 获取弹窗
var modal = document.getElementById('myModal');
// 打开弹窗的按钮对象
var btn = document.getElementById("myBtn");
// 获取 <span> 元素，用于关闭弹窗 that closes the modal
var span = document.getElementsByClassName("closes")[0];
// 点击按钮打开弹窗
btn.onclick = function() {
    modal.style.display = "block";
}

// 点击 <span> (x), 关闭弹窗
span.onclick = function() {
    modal.style.display = "none";
}

// 在用户点击其他地方时，关闭弹窗
window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}
//删除按钮
$('.delete').on('click',function () {
    if(confirm('确认要删除吗?')){
        var questionId=$(this).prev().val();
        $.ajax({
            url:'/question/'+questionId,
            type: 'DELETE',
            //data:{'questionId': questionId},
            dataType: 'json',
            success: function(data){
                window.parent.location.reload();
                $('body').html(data);
            },
            error: function () {
                alert('删除失败');
            }
        });
    }else{
        return false;
    }
});

// 添加
$('#myBtn').on('click',function () {
    $('#myModal').css('display','block');
    $('h4').text('添加面试试题');
    $('.write').val("");
    $('.write').attr('placeholder',"请输入需要的面试试题");
    $('.Sure').addClass('add').removeClass('nody');
    //提交添加项
    $(".add").on('click', function () {
        var questionName=$('.write').val();
        //console.log(questionName);
        // var scoreItemId=$('.scoreItemId').val();
        if (questionName!='') {
            $.ajax({
                type: "POST",
                url: "/question/addQuestion",
                async: false,
                data: {
                    'questionName': questionName,
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
                    },1000);

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
//修改
$('.modi').on('click',function () {
    // var zhi=$('.zhi').text;
    $('#myModal').css('display','block');
    $('h4').text('请修改面试题');
    $('.write').attr('placeholder',"请输入修改后的面试题");
    var first=$(this).parent().prev();
    $('.write').val(first.text());
    $(".Sure").addClass('nody').removeClass('add');
    var questionId=$(this).parent().next().children('input.questionId').val();
     $(".nody").on('click', function () {
        var questionName=$('.write').val();

        $.ajax({
            type: "PUT",
            url: "/question/update",
            data: {
                'questionName':questionName,
                'questionId':questionId
            },
            async: true,
            success: function (data) {
                $('.cure').addClass('uu');
                $('.cure').html('修改成功');
                setInterval(function () {
                    $('.cure').css('display','none');
                },2000);
                setTimeout(function () {
                    location.reload(true);
                },1000)

            },
            error: function (data) {
                $('.cure').addClass('uu');
                $('.cure').html(data.msg);
                setInterval(function () {
                    $('.cure').css('display','none');
                },2000);
                setTimeout(function () {
                    location.reload(true);
                },1000)
                //alert(data.msg)
            }
        });
    });
});