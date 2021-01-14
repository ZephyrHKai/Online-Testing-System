$(function () {
    //登录验证的controller url
    var loginUrl = "/onlinetest/local/login";

    const me = this;

    $('#submit').click(function () {
        //获取输入的账号
        var username = $('#username').val();
        var userpwd = $('#userpwd').val();
        if(username.length === 0 || userpwd.length === 0){
            alert("用户名密码不能为空!");
        }else{
            $.ajax({
                url: loginUrl,
                async: false,
                cache: false,
                type: "post",
                dataType: "json",
                data: {
                    username : username,
                    userpwd: userpwd
                },
                success: function(res){
                    if(res.success){
                       alert('登录成功');
                        //登录
                        if(res.data.status == 0){
                            //学生端
                            me.location.href = "/onlinetest/stexam"
                        }else{
                            //管理端
                            me.location.href = "/onlinetest/manage";
                        }
                    }
                    else{
                        alert("登录失败!" + res.Msg);

                    }
                }
            })
        }
    })
});