layui.use(['form','layer','laydate','table','laytpl','tree'],function(){
    var form = layui.form,
    layer = layui.layer ,
        $ = layui.jquery,
        laydate = layui.laydate,
        laytpl = layui.laytpl,
        table = layui.table,
        tree = layui.tree,
	main=layui.cache.main;
   
    
   
    //页面初始化渲染
    layer.ready(function(){
        var s=$(".status").val();
        $.post(main+"/admin/system/role/rolePermission",{
        	id : $("#roleId").val(),  //角色id
        },function(res){
        	if(res.code==0){
        		treeRender(res.allPermission,res.prelist);
        	}else{
        		top.layer.msg("获取信息失败！");
        	}
        }).error(function(error){
      	  top.layer.msg("网络异常！");
    	  //处理
    	  });
    });
    //渲染tree
    var ins1 ;
    function treeRender(allPermission,prelist){
    	console.log(allPermission);
    	ins1=tree.render({
           elem: '#permissionAuth'  //绑定元素
          ,data: allPermission
          ,showCheckbox:true//是否显示复选框
          ,showLine:true
          ,id: 'permissionAuthId'
        });
//    	tree.setChecked('permissionAuthId', [2,4]); //批量勾选 id 为 2、3 的节点
    	
      }
    
    var permissionArr=[];
    
    $("#saveUserRole").on('click', function(){
    	var checkData = tree.getChecked('permissionAuthId');
    	console.log(checkData);
    	$.each(checkData,function(i,n){
    		permissionArr.push(checkData[i].id);
    		var children=checkData[i].children;
    		$.each(children,function(j,g){
    			permissionArr.push(children[j].id);
    			var ch=children[j].children;
    			$.each(ch,function(h,y){
        			permissionArr.push(ch[h].id);
        		});
    		});
    	});
    		console.log(permissionArr);
    	
    	submit();
      });
    
    function submit(){
    	   var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
          $.post(main+"/admin/system/role/authrole",{
        	  pers : JSON.stringify(permissionArr),  //权限id
        	  roleId : $("#roleId").val(),  //用户id
          },function(res){
        	  if(res.code==0){
             	  top.layer.close(index);
                  top.layer.msg("角色授权成功！");
        /*          layer.closeAll("iframe");*/
                  //刷新父页面
                  parent.location.reload();
        	  }else{
        		  top.layer.msg(res.msg);
        	  }
      
          }).error(function(error){
        	  top.layer.msg("角色授权失败！");
        	  //处理
        	  });
    	  
    }

})

