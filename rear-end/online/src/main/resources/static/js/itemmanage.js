$(function () {
    var logoutUrl = "/onlinetest/local/logout";
    const me = this;
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
    layui.use(['table','form','element'], function () {

        var table = layui.table,
            form = layui.form,
            element = layui.element;

        //数据渲染
        var tableIns = table.render({
            elem: '#itemlist'
            ,height: 420
            ,url: '/onlinetest/item/queryitemlist' //数据接口
            ,title: '用户表'
            ,page: true //开启分页
            ,totalRow: true //开启合计行
            ,cols: [[ //表头
                {type: 'checkbox', fixed: 'left'}
                ,{field: 'sid', title: '题目ID', width:100, sort: true, fixed: 'left', totalRowText: '合计：'}
                ,{field: 'scontent', title: '题干', width:200}
                ,{field: 'sa', title: '选项A', width:100}
                ,{field: 'sb', title: '选项B', width:100}
                ,{field: 'sc', title: '选项C', width:100}
                ,{field: 'sd', title: '选项D', width:100}
                ,{field: 'ans', title: '答案', width:80}
                ,{fixed: 'right', width: 165, align:'center', toolbar: '#barDemo'}
            ]]
        });

        //监控命令行
        table.on('tool(itemlist)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值
            //编辑用户
            if(layEvent === 'edit'){
                editItem(data);
            } else if(layEvent === 'del'){
                layer.confirm('真的删除么', function(index){
                    //向服务端发送删除指令
                    $.ajax({
                        url: '/onlinetest/item/deleteitem',
                        async: false,
                        cache: false,
                        type: "post",
                        dataType : "json",
                        data : {sid : data.sid},
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

        //修改试题
        function editItem(edit) {
            var index = layui.layer.open({
                title: "修改试题",
                type: 2,
                area: ['90%', '90%'],
                fixed: false, //不固定
                maxmin: true,
                content: "/onlinetest/edititem",
                success: function(layero, index) {
                    var body = layui.layer.getChildFrame('body', index);
                    if (edit) {
                        body.find("#scontent").val(edit.scontent); //题干
                        body.find("#sa").val(edit.sa); //选项A
                        body.find("#sb").val(edit.sb); //选项B
                        body.find("#sc").val(edit.sc); //选项C
                        body.find("#sd").val(edit.sd); //选项D
                        body.find("#ans").val(edit.ans); //答案
                        body.find("#sid").val(edit.sid);//试题Id
                        form.render();
                    }
                    setTimeout(function() {
                        layui.layer.tips('点击此处返回试题列表', '.layui-layer-setwin .layui-layer-close', {
                            tips: 3
                        });
                    }, 500)
                }
            });
            //layui.layer.full(index);
            window.sessionStorage.setItem("index", index);
            //改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
        }

        //添加试题
        function addItem(edit) {
            var index = layui.layer.open({
                title: "修改试题",
                type: 2,
                area: ['90%', '90%'],
                fixed: false, //不固定
                maxmin: true,
                content: "/onlinetest/additem",
                success: function(layero, index) {

                    setTimeout(function() {
                        layui.layer.tips('点击此处返回试题列表', '.layui-layer-setwin .layui-layer-close', {
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
            addItem();
        });
    })


});