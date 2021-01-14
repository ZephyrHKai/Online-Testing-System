$(function () {
    const  me = this;
    //登出url
    var logoutUrl = "/onlinetest/local/logout";

    layui.use('element', function () {
        var element = layui.element;

        //…
    });

    //登出
    $('#logoutbtn').click(function () {
        $.ajax({
            url: logoutUrl,
            async: false,
            cache: false,
            type: "get",
            dataType: "json",
            success: function(res){
                if(res.success){
                    alert('登出成功');
                    me.location.href = "/onlinetest/index";
                }
            }
        })
    })
});