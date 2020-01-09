layui.use(['layer','transfer'],function(){
    var
    layer =  layui.layer ,
        $ = layui.jquery,
        transfer = layui.transfer,
	   main=layui.cache.main;

    
    var right=[];
    layer.ready(function(){
    		var userRole=[];
          $.post(main+"/admin/system/user/role",{
              userid : $("#userId").val(),  //用户id
          },function(res){
        	/*  userRole=res.userRole;*/
           	  $.each(res.userRole, function (index, obj) {
           		userRole.push(obj.value);
             });
           	  console.log(userRole);
        	   //定义标题及数据源
        	  transfer.render({
        	       elem: '#userRole'
        	      ,title: ['未分配', '已分配']  //自定义标题
        	      ,showSearch:true
        	      ,id:'key123'
        	      ,data: res.allRole
        	      ,value:userRole
        	      
        	    })
          });
    	});     
    
    

    $("#saveUserRole").on('click', function(){
    	var getData = transfer.getData('key123'); //获取右侧数据
    	$.each(getData,function(i,n){
    		right.push(getData[i].value);
    	});
    	
      submit();
    });
   
    function submit(){
    	console.log(right)
    	   var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
          $.post(main+"/admin/system/user/authrole",{
        	  roles : JSON.stringify(right),  //权限id
        	  userid : $("#userId").val(),  //用户id
          },function(res){
        	  if(res.code==0){
             	  top.layer.close(index);
                  top.layer.msg("用户授权成功！");
        /*          layer.closeAll("iframe");*/
                  //刷新父页面
                  parent.location.reload();
        	  }else{
        		  top.layer.msg("用户授权失败！");
        	  }
      
          }).error(function(error){
        	  top.layer.msg("用户授权失败！");
        	  //处理
        	  });
    	  
    }
})