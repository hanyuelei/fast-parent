layui.use(['form','layer','laydate','table','laytpl'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laydate = layui.laydate,
        laytpl = layui.laytpl,
        table = layui.table,
        main=layui.cache.main;

    // 用户列表
    var tableIns = table.render({
        elem: '#roleList',
        url : main+'/admin/system/role/list',
        method:'post',
        cellMinWidth : 95,
        page : true,
        height : "full-80",
        limit : 20,
        limits : [10,15,20,25],
        id : "roleListTable",
        size:'sm',
        loading:true,
        cols : [[
            {type: "checkbox", fixed:"left", width:50},
            {field: 'id', title: 'ID', width:60, align:"center"},
            {field: 'roleName', title: '角色名', width:150},
            {field: 'roleCode', title: '角色编码', width:150},
            {field: 'remark', title: '备注', align:'center'},
            {field: 'createName', title: '创建用户', align:'center'},

            {title: '操作', width:170, templet:'#roleListBar',fixed:"right",align:"center"}
        ]]
    });


    $(".search_btn").on("click",function(){
        table.reload("roleListTable",{
            page: {
                curr: 1 //重新从第 1 页开始
            },
            where: {
            	roleName: $("#roleName").val() ,
            	roleCode: $("#roleCode").val() 
            }
        })
    });
    $(".addRole_btn").click(function(){
    	addRole();
    })
    //添加文章
    function addRole(edit){
    	var title="添加角色";
    	var content=main+"/admin/system/role/add";
    	if(edit){
    		title="修改角色";
    		content=main+"/admin/system/role/edit?id="+edit.id;
    	}
        var index = layui.layer.open({
            title :title,
            type : 2,
            content : content,
            skin: 'layui-layer-molv', //加上边框
            area : [ '600px', '500px' ],
            maxmin: true,
            anim: 2,//0平滑放大。默认 1	从上掉落2	从最底部往上滑入 3从左滑入4	从左翻滚5	渐显6	抖动
            offset: 'auto',//自动水平垂直居中
            moveOut:true,
            success : function(layero, index){
                setTimeout(function(){
                    layui.layer.tips('点击此处返回角色列表', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                },500)
            }
        })
    }


    //列表操作
    table.on('tool(roleList)', function(obj){
        var layEvent = obj.event,
            data = obj.data;

        if(layEvent === 'edit'){ //编辑
        	addRole(data);
        } else if(layEvent === 'del'){ //删除
            layer.confirm('确定删除该角色？',{icon:3, title:'提示信息'},function(index){
            	 layer.close(index);
         		var lindex = top.layer.msg('正在删除，请稍候', {
        			icon : 16,
        			time : false,
        			shade : 0.8
        		});
                 $.get(main+"/admin/system/role/del",{
                     id : data.id  //将需要删除的newsId作为参数传入
                 },function(data){
                	top.layer.close(lindex);
 	        	  	if(data.code==0){
 	            		top.layer.msg("删除成功！");
 	            	    tableIns.reload();
 	                   
 	            	}else{
 	            		top.layer.msg(data.msg);
 	            	}
              
                 })
            });
        } else if(layEvent === 'authPermission'){
        	authPermission(data);
        }
    });
    
    function authPermission(data){
    	var title="角色授权";
    	var content=main+"/admin/system/role/authPermission?id="+data.id;
        var index = layui.layer.open({
            title :title,
            type : 2,
            content : content,
            skin: 'layui-layer-molv', //加上边框
            area : [ '800px', '550px' ],
            maxmin: true,
            anim: 4,//0平滑放大。默认 1	从上掉落2	从最底部往上滑入 3从左滑入4	从左翻滚5	渐显6	抖动
            offset: 'auto',//自动水平垂直居中
            success : function(layero, index){
            	/*layui.layer.setTop(layero); //重点2
*/                setTimeout(function(){
                    layui.layer.tips('点击此处返回角色列表', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                },500)
            }
        })
      /*  layui.layer.full(index);*/
        //改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
  /*      $(window).on("resize",function(){
            layui.layer.full(index);
        })*/
    }

})