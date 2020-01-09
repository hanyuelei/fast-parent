layui.use(['form','layer'],function(){
    var form = layui.form
    layer =  layui.layer ,
        $ = layui.jquery,
    main=layui.cache.main;
  	var menuType=$('#menuType').val();
	if(menuType==0){
		$('#preUrl').hide();
		$('#preUrl input').removeAttr('lay-verify');
	}
	if(menuType!=1){//如果不是目录级别，给隐藏域parentId赋值
		$('#parentIdInput').attr('name','parentId');
	}
	
	
    form.on("submit(editPermissionSave)",function(data){
        //弹出loading
        var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
         $.post(main+"/admin/system/menu/edit",data.field,function(res){
        	  top.layer.close(index);
        	 if(res.code==0){
	              top.layer.msg("修改权限成功！");
	              parent.location.reload();
        	 }else{
        		 layer.msg(res.msg, {icon: 2});
//        		  top.layer.msg("修改权限失败！");
        	 }
        
         }).error(function(error){
        	  top.layer.close(index);
          	  top.layer.msg("修改权限失败！");
        	  //处理
        	 });
    	 return false; 
    })

       //选择图标
    $("#selectIcon").on("click",function () {
        iconShow =layer.open({
            type: 2,
            title: '选择图标',
            shadeClose: true,
            skin: 'layui-layer-lan', //加上边框
            area : 'auto',
            maxmin: true,
            area: ['600px', '500px'],
            anim: 1,//0平滑放大。默认 1	从上掉落2	从最底部往上滑入 3从左滑入4	从左翻滚5	渐显6	抖动
            content: main+'/static/page/icon.html',
            success:function (layero,index) {
                    var iframe = window['layui-layer-iframe' + index];
            }
        });
        /*layer.full(iconShow);*/
    });
    form.on('switch(userstatus)', function(data){
    	if(data.elem.checked){
    		 $("#userstatus").val("1");
    	}else{
    		 $("#userstatus").val("0");
    	}
       
    });
})