$(function () {
    var logoutUrl = "/onlinetest/local/logout";
    const me = this;
    layui.use(['table','form','element'], function () {
        var table = layui.table,
            form = layui.form,
            element = layui.element;

        var tableIns = table.render({
            elem: '#userlist'
            ,height: 420
            ,url: '/onlinetest/manage/getuserlist' //数据接口
            ,title: '用户表'
            ,page: true //开启分页
            // ,toolbar: 'default' //开启工具栏，此处显示默认图标，可以自定义模板，详见文档
            ,totalRow: true //开启合计行
            ,cols: [[ //表头
                {type: 'checkbox', fixed: 'left'}
                ,{field: 'userId', title: 'ID', width:80, sort: true, fixed: 'left', totalRowText: '合计：'}
                ,{field: 'username', title: '用户名', width:80}
                ,{field: 'name', title: '姓名', width:100}
                ,{field: 'status', title:'用户权限', width:100, templet: function (d) {
                        return d.status === 0 ? "学生" : "管理员";
                    }}
                ,{fixed: 'right', width: 165, align:'center', toolbar: '#barDemo'}
            ]]
        });

        table.on('tool(userlist)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值
            //编辑用户
            if(layEvent === 'edit'){
                editUser(data);
            } else if(layEvent === 'del'){
                layer.confirm('真的删除么', function(index){
                    //向服务端发送删除指令
                    $.ajax({
                        url: '/onlinetest/manage/deleteuser',
                        async: false,
                        cache: false,
                        type: "post",
                        dataType : "json",
                        data : {userId : data.userId},
                        success: function (res) {
                            if(res.success){
                                layer.msg("删除成功!");
                                obj.del(); //删除对应行（tr）的DOM结构
                                tableIns.reload();
                                layer.close(index);
                            }else{
                                layer.msg("修改失败!");
                            }
                        }
                    });

                });
            }
        });

        //添加用户
        function addUser(edit) {
            var index = layui.layer.open({
                title: "添加用户",
                type: 2,
                area: ['90%', '90%'],
                fixed: false, //不固定
                maxmin: true,
                content: "/onlinetest/adduser",
                success: function(layero, index) {
                    var body = layui.layer.getChildFrame('body', index);
                    if (edit) {
                        body.find("#username").val(edit.username); //登录名
                        body.find("#name").val(edit.name); //会员等级
                        body.find("#status").val(edit.status); //用户状态

                        form.render();
                    }
                    setTimeout(function() {
                        layui.layer.tips('点击此处返回用户列表', '.layui-layer-setwin .layui-layer-close', {
                            tips: 3
                        });
                    }, 500)
                }
            });
            //layui.layer.full(index);
            window.sessionStorage.setItem("index", index);
            //改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
        }

        //修改用户
        function editUser(edit) {
            var index = layui.layer.open({
                title: "修改用户",
                type: 2,
                area: ['90%', '90%'],
                fixed: false, //不固定
                maxmin: true,
                content: "/onlinetest/edituser",
                success: function(layero, index) {
                    var body = layui.layer.getChildFrame('body', index);
                    if (edit) {
                        body.find("#username").val(edit.username); //登录名
                        body.find("#name").val(edit.name); //会员等级
                        body.find("#status").val(edit.status); //用户状态

                        form.render();
                    }
                    setTimeout(function() {
                        layui.layer.tips('点击此处返回用户列表', '.layui-layer-setwin .layui-layer-close', {
                            tips: 3
                        });
                    }, 500)
                }
            });
            //layui.layer.full(index);
            window.sessionStorage.setItem("index", index);
            //改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
        }


        $(".addNews_btn").click(function() {
            addUser();
        });



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
                    me.location.href = "http://tk6tn945wo.54http.tech/onlinetest/index";
                }
            }
        })
    });
});