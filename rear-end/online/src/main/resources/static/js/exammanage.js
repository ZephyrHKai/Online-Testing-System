$(function () {
    var logoutUrl = "/onlinetest/local/logout";
    const me = this;
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
                //发放试卷
                if(data.pageId == 1){
                    layer.msg("已发放!");
                }
                else {
                    $.ajax({
                        url: '/onlinetest/page/updatepage',
                        async: false,
                        cache: false,
                        type: "post",
                        dataType: "json",
                        data: {
                            pageId: data.pageId,
                            status: 1
                        },
                        success: function (res) {
                            if (res.success) {
                                layer.msg("发放成功!");
                            } else {
                                layer.msg("发放失败!");
                            }
                        }
                    });
                }
            }else if(layEvent === 'del'){
                //回收试卷
                if(data.pageId === 0){
                    layer.msg("已回收");
                }
                else {
                    $.ajax({
                        url: '/onlinetest/page/updatepage',
                        async: false,
                        cache: false,
                        type: "post",
                        dataType: "json",
                        data: {
                            pageId: data.pageId,
                            status: 0
                        },
                        success: function (res) {
                            if (res.success) {
                                layer.msg("回收成功!");
                            } else {
                                layer.msg("回收失败!");
                            }
                        }
                    });
                }
            }
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
        })



    });
});