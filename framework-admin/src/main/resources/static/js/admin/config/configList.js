
layui.use(['form','layer','laydate','table','laytpl'],function(){
    var form = layui.form,
        layer = layui.layer,
        $ = layui.jquery,
        laydate = layui.laydate,
        laytpl = layui.laytpl,
        table = layui.table,
        main=layui.cache.main;
    // 用户列表
    var tableIns = table.render({
        elem: '#configList',
        url :  main+'/admin/system/config/list',
        method:'post',
        cellMinWidth : 95,
        page : true,
        height : "full-80",
        limit : 20,
        limits : [10,15,20,25],
        id : "configListTable",
        size:'sm',
        loading:true,
        cols : [[
            {type: "checkbox", fixed:"left", width:50},
            {field: 'id', title: 'ID', width:60, align:"center"},
            {field: 'paramKey', title: '参数标识', width:250},
            {field: 'paramValue', title: '参数值', width:350},
            {field: 'remark', title: '备注',  width:250},
            {title: '操作', width:300, templet:'#configBar',fixed:"right",align:"center"}
        ]]
    });


    //搜索【此功能需要后台配合，所以暂时没有动态效果演示】
    $(".search_btn").on("click",function(){
        table.reload("configListTable",{
            page: {
                curr: 1 //重新从第 1 页开始
            },
            where: {
            	paramKey: $("#paramKey").val(),
            	paramValue: $("#paramValue").val(),
            	remark: $("#remark").val()
            }
        })
    });
    $(".addConfig_btn").click(function(){
    	add();
    })
    //添加用户
    function add(edit){
    	var title="添加配置";
    	var content=main+"/admin/system/config/add";
    	if(edit){
    		title="修改配置";
    		content=main+"/admin/system/config/edit?id="+edit.id;
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
      /*  layui.layer.full(index);*/
        //改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
      /*  $(window).on("resize",function(){
            layui.layer.full(index);
        })*/
    }
    //列表操作
    table.on('tool(configList)', function(obj){
        var layEvent = obj.event,
            data = obj.data;

        if(layEvent === 'edit'){ //编辑
        	add(data);
        } else if(layEvent === 'del') { //删除
            layer.confirm('确定删除该该行配置？',{icon:3, title:'提示信息'},function(index){
            	 layer.close(index);
         		var lindex = top.layer.msg('正在删除，请稍候', {
        			icon : 16,
        			time : false,
        			shade : 0.8
        		});
                 $.get(main+"/admin/system/config/del",{
                     id : data.id 
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