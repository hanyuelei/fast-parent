
layui.use(['form','layer','laydate','table','laytpl'],function(){
    var form = layui.form,
        layer = layui.layer,
        $ = layui.jquery,
        laydate = layui.laydate,
        laytpl = layui.laytpl,
        table = layui.table,
        main=layui.cache.main,
        salt=layui.cache.salt;

    var tableIns = table.render({
        elem: '#fwDicList',
        url :  main+'/admin/system/fwDic/list',
        method:'post',
        cellMinWidth : 95,
        page : true,
        height : "full-80",
        limit : 20,
        limits : [10,15,20,25],
        id : "fwDicListTable",
        size:'sm',
        loading:true,
        cols : [[
            {type: "checkbox", fixed:"left", width:50},
            {field: 'id', title: 'ID', width:60, align:"center"},
	        {field: 'name', title: '字典标识', width:150},
	        {field: 'title', title: '字典标题', width:150},
/*	        {field: '键值对', title: '类型', width:100},*/
	        {field: 'value', title: '字典值', width:430},
	        {field: 'remark', title: '备注', width:150},
            {title: '操作', width:300, templet:'#fwDicBar',fixed:"right",align:"center"}
        ]]
    });



    $(".search_btn").on("click",function(){
        table.reload("fwDicListTable",{
            page: {
                curr: 1
            },
            where: {
            	name: $("#name").val()  ,
            	title: $("#title").val()  
            }
        })
    });
    $(".fwDicadd_btn").click(function(){
    	add();
    })
    //添加用户
    function add(edit){
    	alert(salt);
    	var title="添加";
    	var content=main+"/admin/system/fwDic/add";
    	if(edit){
    		title="修改";
    		content=main+"/admin/system/fwDic/edit?id="+edit.id;
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
    table.on('tool(fwDicList)', function(obj){
        var layEvent = obj.event,
            data = obj.data;

        if(layEvent === 'edit'){ //编辑
        	add(data);
        } else if(layEvent === 'del') { //删除
            layer.confirm('确定删除？',{icon:3, title:'提示信息'},function(index){
            	 layer.close(index);
        		var lindex = top.layer.msg('正在删除，请稍候', {
        			icon : 16,
        			time : false,
        			shade : 0.8
        		});
                 $.get(main+"/admin/system/config/del",{
                     id : data.id  //将需要删除的Id作为参数传入
                 },function(data){
                	 top.layer.close(lindex);
	        	  	if(data.code==0){
	            		top.layer.msg("删除成功！");
	            	    tableIns.reload();
	                   
	            	}else{
	            		top.layer.msg("删除失败！");
	            	}
          
                   
                 })
            });
        }
    });

})