
layui.use(['form','layer','laydate','table','laytpl'],function(){
    var form = layui.form,
        layer = layui.layer,
        $ = layui.jquery,
        laydate = layui.laydate,
        laytpl = layui.laytpl,
        table = layui.table,
        main=layui.cache.main;

    var tableIns = table.render({
        elem: '#tableId',
        url :  main+'/admin/system/fwScheduleJob/list',
        method:'post',
        cellMinWidth : 95,
        page : true,
        height : "full-80",
        limit : 20,
        limits : [10,15,20,25],
        id : "fwScheduleJobTable",
    	toolbar: true,
		toolbar: "#toolbarTpl",
        size:'sm',
        loading:true,
        cols : [[
            {type: "checkbox", fixed:"left", width:50},
            {field: 'id', title: 'ID', width:60, align:"center"},
	        {field: 'beanName', title: 'bean名称', width:150},
	        {field: 'params', title: ' 参数', width:150},
	        {field: 'method', title: '方法名', width:150},
	        {field: 'cronExpression', title: 'cron表达式', width:150},
	        {field: 'status', title: '任务状态',align:"center",templet:"#jobStatus"},
	        {field: 'remark', title: '备注', width:150},
            {title: '操作', width:150, templet:'#fwScheduleJobBar',fixed:"right",align:"center"}
        ]]
    });



    $(".search_btn").on("click",function(){
    	var search_beanName=$('#beanName').val();
    	var search_method=$('#methodName').val();
        table.reload("fwScheduleJobTable",{
            page: {
                curr: 1
            },
            where: {
                beanName: search_beanName,
                methodName:search_method
               
            }
        })
    });
    $(".fwScheduleJobadd_btn").click(function(){
    	add();
    })
    //添加用户
    function add(edit){
    	var title="添加";
    	var content=main+"/admin/system/fwScheduleJob/add";
    	if(edit){
    		title="修改";
    		content=main+"/admin/system/fwScheduleJob/edit?id="+edit.id;
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
    table.on('tool(fwScheduleJobList)', function(obj){
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
    
	table.on("toolbar(fwScheduleJobList)", function (obj) {
		switch (obj.event) {
			case "delAll_btn":
				delAll();
				break;
			case "pauseAll_btn":
				pauseAll();
				break;
			case "resumeAll_btn":
				resumeAll();
				break;
			case "runAll_btn":
				runAll();
				break;
			case "fwScheduleJobadd_btn":
				add();
				break;
		}
	});
    
    //批量删除
    function delAll(){
    	var ids=getChecks('fwScheduleJobTable');
    	alert(ids);
    	if(ids.length>0){
    	     layer.confirm('确定删除？',{icon:3, title:'提示信息'},function(index){
    	    	 layer.close(index);
    	   		var lindex = top.layer.msg('正在删除，请稍候', {
        			icon : 16,
        			time : false,
        			shade : 0.8
        		});
                 $.get(main+"/admin/system/fwScheduleJob/del",{
                	 jobIds :JSON.stringify(ids) //将需要删除的Id作为参数传入
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
    	}else{
	        layer.msg("请选择数据！");
	    }
    }
    
    //立即执行
    function runAll(){
    	var ids=getChecks('fwScheduleJobTable');
    	if(ids.length>0){
    		layer.confirm('确定立即执行？',{icon:3, title:'提示信息'},function(index){
    			layer.close(index);
    			var lindex = top.layer.msg('正在进行申请，请稍候', {
    				icon : 16,
    				time : false,
    				shade : 0.8
    			});
    			$.get(main+"/admin/system/fwScheduleJob/run",{
    				jobIds :JSON.stringify(ids) //将需要删除的Id作为参数传入
    			},function(data){
    				top.layer.close(lindex);
    				if(data.code==0){
    					top.layer.msg("立即执行完毕！");
    					tableIns.reload();
    				}else{
    					top.layer.msg("立即执行失败！");
    				}
    				
    				
    			})
    		});
    	}else{
    		layer.msg("请选择数据！");
    	}
    }

    //立即暂停
    function pauseAll(){
    	var ids=getChecks('fwScheduleJobTable');
    	if(ids.length == 0){
    		alert('###');
    		layer.msg("请选择数据！");
    		return;
    	}
    	
    	if(ids.length>0){
    		layer.confirm('确定执行暂停操作？',{icon:3, title:'提示信息'},function(index){
    			layer.close(index);
    			var lindex = top.layer.msg('正在进行暂停，请稍候', {
    				icon : 16,
    				time : false,
    				shade : 0.8
    			});
    			$.get(main+"/admin/system/fwScheduleJob/pause",{
    				jobIds :JSON.stringify(ids) //将需要删除的Id作为参数传入
    			},function(data){
    				top.layer.close(lindex);
    				if(data.code==0){
    					top.layer.msg("暂停操作成功！");
    					tableIns.reload();
    				}else{
    					top.layer.msg("暂停操作失败！");
    				}
    				
    				
    			})
    		});
    	}else{
    		layer.msg("请选择数据！");
    	}
    }

    //立即恢复
    function resumeAll(){
    	var ids=getChecks('fwScheduleJobTable');
    	if(ids.length>0){
    		layer.confirm('确定执行恢复操作？',{icon:3, title:'提示信息'},function(index){
    			layer.close(index);
    			var lindex = top.layer.msg('正在进行申请，请稍候', {
    				icon : 16,
    				time : false,
    				shade : 0.8
    			});
    			$.get(main+"/admin/system/fwScheduleJob/resume",{
    				jobIds :JSON.stringify(ids) //将需要删除的Id作为参数传入
    			},function(data){
    				top.layer.close(lindex);
    				if(data.code==0){
    					top.layer.msg("恢复操作成功！");
    					tableIns.reload();
    				}else{
    					top.layer.msg("恢复操作失败！");
    				}
    				
    				
    			})
    		});
    	}else{
    		layer.msg("请选择数据！");
    	}
    }
    
    $(".joblog").click(function(){
    	
        var index = top.layui.layer.open({
            title :'任务日志列表',
            type : 2,
            content : main+"/admin/system/fwScheduleJobLog/list",
            skin: 'layui-layer-molv', //加上边框
            area : [ '1050px', '650px' ],
            maxmin: true,
            anim: 2,//0平滑放大。默认 1	从上掉落2	从最底部往上滑入 3从左滑入4	从左翻滚5	渐显6	抖动
            offset: 'auto',//自动水平垂直居中
            success : function(layero, index){
                setTimeout(function(){
                    layui.layer.tips('点击任务列表', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                },500)
            },
  
        })
//          layui.layer.full(index);
//            //改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
//            $(window).on("resize",function(){
//                layui.layer.full(index);
//            })
    });
    function getChecks (tableName){
        var checkStatus = table.checkStatus(tableName),
        data = checkStatus.data,
        ids = [];
	    if(data.length > 0) {
	        for (var i in data) {
	        	ids.push(data[i].id);
	        }
	    }
	    return ids;
    }
    

})