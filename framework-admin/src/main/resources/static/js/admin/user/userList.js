
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
        url :  main+'/admin/system/user/list',
        method:'post',
        cellMinWidth : 95,
        page : true,
        height : "full-80",
        limit : 20,
        limits : [10,15,20,25],
        id : "userListTable",
        size:'sm',
        cols : [[
            {type: "checkbox", fixed:"left", width:50},
            {field: 'id', title: 'ID', width:60, align:"center"},
            {field: 'userName', title: '登录名', width:150},
            {field: 'nickName', title: '昵称', width:150},
            {field: 'mobile', title: '电话', align:'center'},
            {field: 'status', title: '状态',  align:'center',templet:"#userStatus"},
            {field: 'email', title: '邮箱', align:'center'},
            {field: 'createName', title: '创建用户', align:'center'},
            {title: '操作', width:220, templet:'#userListBar',fixed:"right",align:"center"}
        ]]
    });



    $(".search_btn").click(function(){
        table.reload("userListTable",{
            page: {
                curr: 1 //重新从第 1 页开始
            },
            where: {
            	userName: $("#userName").val(),
            	nickName: $("#nickName").val(),  
            	mobile: $("#mobile").val()
            }
        })
    });
    $(".addUser_btn").click(function(){
    	adduser();
    })
    //添加用户
    function adduser(edit){
    	var title="添加用户";
    	var content=main+"/admin/system/user/add";
    	if(edit){
    		title="修改用户";
    		content=main+"/admin/system/user/edit?id="+edit.id;
    	}
        var index = layui.layer.open({
            title :title,
            type : 2,
            content : content,
            skin: 'layui-layer-molv', //加上边框
            area : [ '650px', '560px' ],
            maxmin: true,
            anim: 2,//0平滑放大。默认 1	从上掉落2	从最底部往上滑入 3从左滑入4	从左翻滚5	渐显6	抖动
            offset: 'auto',//自动水平垂直居中
            success : function(layero, index){
                setTimeout(function(){
                    layui.layer.tips('点击此处返用户列表', '.layui-layer-setwin .layui-layer-close', {
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

    //用户授权
    function authorize(data){
    	var title="用户授权";
    	var content=main+"/admin/system/user/authorize?id="+data.id;
        var index = layui.layer.open({
            title :title,
            type : 2,
            content : content,
            skin: 'layui-layer-molv', //加上边框
            area : [ '700px', '560px' ],
            maxmin: true,
            anim: 2,//0平滑放大。默认 1	从上掉落2	从最底部往上滑入 3从左滑入4	从左翻滚5	渐显6	抖动
            offset: 'auto',//自动水平垂直居中
            success : function(layero, index){
                setTimeout(function(){
                    layui.layer.tips('点击此处返用户列表', '.layui-layer-setwin .layui-layer-close', {
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

    //列表操作
    table.on('tool(userList)', function(obj){
        var layEvent = obj.event,
            data = obj.data;

        if(layEvent === 'edit'){ //编辑
        	adduser(data);
        } else if(layEvent === 'del') { //删除
            layer.confirm('确定 删除该用户？',{icon:3, title:'提示信息'},function(index){
             	layer.close(index);
          		var lindex = top.layer.msg('正在删除，请稍候', {
         			icon : 16,
         			time : false,
         			shade : 0.8
         		});
                 $.get(main+"/admin/system/user/del",{
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
        } else if(layEvent === 'authorize'){
        	authorize(data);
        }
    });
    /* 下拉按钮组 */
    $(".btn-group").click(function (e) {
        e.stopPropagation();
        $this = $(this);
        $this.toggleClass("show");
        $(document).one("click", function () {
            if ($this.hasClass("show")) {
                $this.removeClass("show");
            }
        });
    });

})