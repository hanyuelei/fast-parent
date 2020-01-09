
layui.use(['form','layer','laydate','table','laytpl'],function(){
    var form = layui.form,
        layer = layui.layer,
        $ = layui.jquery,
        laydate = layui.laydate,
        laytpl = layui.laytpl,
        table = layui.table,
        main=layui.cache.main;

    var tableIns = table.render({
        elem: '#fwScheduleJobLogList',
        url :  main+'/admin/system/fwScheduleJobLog/list',
        method:'post',
        cellMinWidth : 95,
        page : true,
        height : "full-80",
        limit : 20,
        limits : [10,15,20,25],
        id : "fwScheduleJobLogTable",
        size:'sm',
        loading:true,
        cols : [[
         /*   {type: "checkbox", fixed:"left", width:50},*/
            {field: 'id', title: 'ID', width:60, align:"center"},
	        {field: 'beanName', title: '类名', width:250},
	        {field: 'method', title: '方法名', width:150},
	        {field: 'params', title: '参数', width:100},
	        {field: 'status', title: '任务状态',align:"center", width:100,templet:"#jobLogStatus"},
	        {field: 'error', title: '失败信息', width:150},
	        {field: 'times', title: '执行时间 毫秒', width:150},
        ]]
    });



    $(".search_btn").on("click",function(){
    	var search_beanName=$('#beanName').val();
    	var search_method=$('#methodName').val();
        table.reload("fwScheduleJobLogTable",{
            page: {
                curr: 1
            },
            where: {
                beanName: search_beanName,
                methodName:search_method
               
            }
        })
    });
    $(".fwScheduleJobLogadd_btn").click(function(){
    	add();
    })
    //添加用户
    function add(edit){
    	var title="添加";
    	var content=main+"/admin/system/fwScheduleJobLog/add";
    	if(edit){
    		title="修改";
    		content=main+"/admin/system/fwScheduleJobLog/edit?id="+edit.id;
    	}
        var index = layui.layer.open({
            title :title,
            type : 2,
            content : content,
            skin: 'layui-layer-molv', //加上边框
            area : [ '650px', '550px' ],
            maxmin: true,
            anim: 2,//0平滑放大。默认 1	从上掉落2	从最底部往上滑入 3从左滑入4	从左翻滚5	渐显6	抖动
            offset: 'auto',//自动水平垂直居中
            success : function(layero, index){
                setTimeout(function(){
                    layui.layer.tips('点击此处返配置列表', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                },500)
            },
	        end:function(layero, index){
	        /*	  layer.close(index);*/
	        }
        })

    }
    //列表操作
    table.on('tool(fwScheduleJobLogList)', function(obj){
        var layEvent = obj.event,
            data = obj.data;

        if(layEvent === 'edit'){ //编辑
        	add(data);
        } else if(layEvent === 'del') { //删除
            layer.confirm('确定删除？',{icon:3, title:'提示信息'},function(index){
                 $.get(main+"/admin/system/config/del",{
                     id : data.id  //将需要删除的Id作为参数传入
                 },function(data){
                    tableIns.reload();
                    layer.close(index);
                 })
            });
        }
    });

})