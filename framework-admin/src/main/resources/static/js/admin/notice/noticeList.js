
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
        elem: '#userList',
        url :  main+'/admin/system/notice/list',
        method:'post',
        cellMinWidth : 95,
        page : true,
        height : "full-80",
        limit : 20,
        limits : [10,15,20,25],
        id : "noticeListTable",
        size:'sm',
        loading:true,
        cols : [[
            {type: "checkbox", fixed:"left", width:50},
            {field: 'id', title: 'ID', width:60, align:"center"},
            {field: 'title', title: '标题', width:350,align:"center"},
      /*      {field: 'content', title: '内容', width:350},*/
            {field: 'status', title: '状态',  width:350, align:'center',templet:"#noticeStatus"},
            {title: '操作', width:400, templet:'#noticeBar',fixed:"right",align:"center"}
        ]]
    });


    $(".search_btn").on("click",function(){
        table.reload("noticeListTable",{
            page: {
                curr: 1 //重新从第 1 页开始
            },
            where: {
            	title: $("#title").val(),
            	content: $("#content").val(),
            	status: $("#status").val()
            }
        })
    });
    $(".addNotice_btn").click(function(){
    	addNotice();
    })
    //添加与修改
    function addNotice(edit){
    	var title="添加公告";
    	var content=main+"/admin/system/notice/add";
    	if(edit){
    		title="修改公告";
    		content=main+"/admin/system/notice/edit?id="+edit.id;
    	}
        var index = layui.layer.open({
            title :title,
            type : 2,
            content : content,
            skin: 'layui-layer-molv', //加上边框
            area : [ '950px', '550px' ],
            maxmin: true,
            anim: 2,//0平滑放大。默认 1	从上掉落2	从最底部往上滑入 3从左滑入4	从左翻滚5	渐显6	抖动
            offset: 'auto',//自动水平垂直居中
            success : function(layero, index){
                setTimeout(function(){
                    layui.layer.tips('点击此处返公告列表', '.layui-layer-setwin .layui-layer-close', {
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
    table.on('tool(userList)', function(obj){
        var layEvent = obj.event,
            data = obj.data;

        if(layEvent === 'edit'){ //编辑
        	addNotice(data);
        } else if(layEvent === 'del') { //删除
            layer.confirm('确定删除该公告？',{icon:3, title:'提示信息'},function(index){
            	layer.close(index);
          		var lindex = top.layer.msg('正在删除，请稍候', {
         			icon : 16,
         			time : false,
         			shade : 0.8
         		});
                 $.get(main+"/admin/system/notice/del",{
                     id : data.id  //将需要删除的newsId作为参数传入
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
        } else if(layEvent === 'detail'){
        	detail(data);
        }
    });
    //添加用户
    function detail(edit){
        var index = layui.layer.open({
            title :"公告详情",
            type : 2,
            content : main+"/admin/system/notice/detail?id="+edit.id,
            skin: 'layui-layer-molv', //加上边框
            area : [ '950px', '550px' ],
            maxmin: true,
            anim: 3,//0平滑放大。默认 1	从上掉落2	从最底部往上滑入 3从左滑入4	从左翻滚5	渐显6	抖动
            offset: 'auto',//自动水平垂直居中
            success : function(layero, index){
                setTimeout(function(){
                    layui.layer.tips('点击此处返公告列表', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                },500)
            },
	        end:function(layero, index){
	        /*	  layer.close(index);*/
	        }
        })
    }
})