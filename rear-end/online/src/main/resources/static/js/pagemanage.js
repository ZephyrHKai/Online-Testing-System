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
    });
    layui.use(['table','form','element'], function () {
        var table = layui.table,
            form = layui.form,
            element = layui.element;

        var tableIns = table.render({
            elem: '#pagelist'
            ,height: 420
            ,url: '/onlinetest/page/getpagelist' //数据接口
            ,title: '试卷表'
            ,page: true //开启分页
            ,totalRow: true //开启合计行
            ,cols: [[ //表头
                {type: 'checkbox', fixed: 'left'}
                ,{field: 'pageId', title: '试卷ID', width:100, sort: true, fixed: 'left', totalRowText: '合计：'}
                ,{field: 'pageName', title: '试卷名', width:100}
                ,{field: 'total', title: '题目个数', width:100}
                ,{field: 'status', title:'试卷状态', width:100, templet: function (d) {
                        return d.status === 0 ? "不可用" : "可用";
                    }}
                ,{fixed: 'right', width: 165, align:'center', toolbar: '#barDemo'}
            ]]
        });

        table.on('tool(pagelist)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值
            //编辑试卷
            if(layEvent === 'edit'){
                //editPage(data);
            } else if(layEvent === 'del'){
                layer.confirm('真的删除么', function(index){
                    //向服务端发送删除指令
                    $.ajax({
                        url: '/onlinetest/page/deletepage',
                        async: false,
                        cache: false,
                        type: "post",
                        dataType : "json",
                        data : {pageId : data.pageId},
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

        //自动组卷
        function addPage() {
            var index = layui.layer.open({
                title: "添加试卷",
                type: 2,
                area: ['90%', '90%'],
                fixed: false, //不固定
                maxmin: true,
                content: "/onlinetest/addpage",
                success: function(layero, index) {
                    setTimeout(function() {
                        layui.layer.tips('点击此处返回试卷列表', '.layui-layer-setwin .layui-layer-close', {
                            tips: 3
                        });
                    }, 500)
                }
            });
            //layui.layer.full(index);
            window.sessionStorage.setItem("index", index);
            //改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
        }

        //修改试卷
        function editPage(edit) {
            var index = layui.layer.open({
                title: "修改用户",
                type: 2,
                area: ['90%', '90%'],
                fixed: false, //不固定
                maxmin: true,
                content: "/onlinetest/editpage",
                success: function(layero, index) {
                    var body = layui.layer.getChildFrame('body', index);
                    if (edit) {
                        body.find("#pageName").val(edit.pageName); //试卷名
                        body.find("#total").val(edit.total);
                        body.find("#status").val(edit.status); //试卷状态

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

        //自动组卷
        $(".addauto_btn").click(function () {
            addPage();
        });


    });


});