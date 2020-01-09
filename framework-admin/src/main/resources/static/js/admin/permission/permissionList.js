layui.config({
    base: layui.cache.main+'/static/'
}).extend({
    treetable: 'js/treetable'
}).use(['form','layer','laydate','table','laytpl','treetable'],function(){
    var form = layui.form,
    layer = layui.layer,
        $ = layui.jquery,
        laydate = layui.laydate,
        laytpl = layui.laytpl,
        table = layui.table;
    	treetable = layui.treetable,
    	main=layui.cache.main;
  var tableIns= function (){ 
	  	treetable.render({
        treeColIndex: 1, //树形图标显示在第几列
        treeSpid: '0', //最上级的父级id
        treeIdName: 'id', //id字段的名称
        treePidName: 'parentId', //pid字段的名称
        treeDefaultClose: true, //是否默认折叠
        elem: '#permissionList',
        url : main+'/admin/system/menu/treeMenu',
        page: false, //分页，即使设置为true也不进行分页
        treeLinkage: false, //父级展开时是否自动展开所有子级
        size:'sm',
        height: '530px',
        cols: [[
            {type: 'numbers',title: 'ID'},
            {field: 'name', minWidth: 100, title: '权限名称'},
            {field: 'url', title: '权限URL', width: '25%'},
            {field: 'sort', width: '8%', align: 'center', title: '排序号'},
            {
                field: 'type', width: '10%', align: 'center', templet: function (d) {
                    if (d.type==0) {
                    	return '<span class="layui-badge layui-bg-red">目录</span>';
                    }
                    if(d.type==1){
                    	 return '<span class="layui-badge layui-bg-blue">菜单</span>';
                    }
                    if(d.type==2){
                    	   return '<span class="layui-badge layui-bg-green">按钮</span>';
                   }
                }, title: '类型'
            },
         /*   {templet: '#authBar', width: '30%', align: 'center', title: '操作'}*/
              {templet:  function (d) {
            	  var tem='';
            	  if(d.type!=2){
            		  tem+='<a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="addChild" sec:authorize="hasAnyAuthority("/admin/system/menu/addChild")">添加子菜单</a>';
            	  }
            	  tem+=' <a class="layui-btn layui-btn-xs" lay-event="editPermission" sec:authorize="hasAnyAuthority("/admin/system/menu/edit")">编辑 </a>'+
                  '<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del" sec:authorize="hasAnyAuthority("/admin/system/menu/del")">删除</a>';
                  return tem;
              }, width: '20%', align: 'center', title: '操作'}
        ]],
        done: function () {
            layer.closeAll('loading');
        }
    });
  }
  tableIns();
    var active={
        btnExpand : function() {
            treetable.expandAll('#permissionList');
        },
        btnFold : function () {
            treetable.foldAll('#permissionList');
        },
/*        addPermission : function(){
            var addIndex = layer.open({
                title : "添加系统权限",
                type : 2,
                content : "/admin/system/permission/add",
                success : function(layero, addIndex){
                    setTimeout(function(){
                        layer.tips('点击此处返回角色列表', '.layui-layer-setwin .layui-layer-close', {
                            tips: 3
                        });
                    },500);
                }
            });
            layuiresize(addIndex);
        }
*/
    };

    $('.layui-inline .layui-btn').on('click', function(){
        
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });
    
    $(".addPermission_btn").click(function(){
    	addPermission();
    })
    //修改
    function editPermission(edit){
    	var title="添加权限";
    	var content=main+"/admin/system/menu/add";
    	if(edit){
    		title="修改权限";
    		content=main+"/admin/system/menu/edit?id="+edit.id;
    	}
        var index = layui.layer.open({
            title :title,
            type : 2,
            content : content,
            skin: 'layui-layer-molv', //加上边框
            area : [ '800px', '550px' ],
            maxmin: true,
            anim: 2,//0平滑放大。默认 1	从上掉落2	从最底部往上滑入 3从左滑入4	从左翻滚5	渐显6	抖动
            offset: 'auto',//自动水平垂直居中
            success : function(layero, index){
                setTimeout(function(){
                    layui.layer.tips('点击此处返回权限列表', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                },500)
            }
        })
      /*  layui.layer.full(index);*/
        //改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
   /*     $(window).on("resize",function(){
            layui.layer.full(index);
        })*/
    }
    
    //添加
    function addPermission(add){
    	var title="添加权限";
    	var content=main+"/admin/system/menu/add";
    	if(add){
    		content+="?parentId="+add.id;
    	}
        var index = layui.layer.open({
            title :title,
            type : 2,
            content : content,
            skin: 'layui-layer-molv', //加上边框
            area : [ '800px', '550px' ],
            maxmin: true,
            anim: 2,//0平滑放大。默认 1	从上掉落2	从最底部往上滑入 3从左滑入4	从左翻滚5	渐显6	抖动
            id:'addPer',
            zIndex: 100, //优先级
            offset: 'auto',//自动水平垂直居中
            success : function(layero, index){
            	/*layui.layer.setTop(layero); //重点2
*/                setTimeout(function(){
                    layui.layer.tips('点击此处返回权限列表', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                },500)
            }
        })
      /*  layui.layer.full(index);*/
        //改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
   /*     $(window).on("resize",function(){
            layui.layer.full(index);
        })*/
    }

    $(".addPermission_transfer").click(function(){
        iconShow =layer.open({
            type: 2,
            title: '选择图标',
            shadeClose: true,
            skin: 'layui-layer-lan', //加上边框
            area : 'auto',
            maxmin: true,
            area: ['600px', '500px'],
            anim: 1,//0平滑放大。默认 1	从上掉落2	从最底部往上滑入 3从左滑入4	从左翻滚5	渐显6	抖动
            content:main+ '/static/page/transfer.html',
            success:function (layero,index) {
                    var iframe = window['layui-layer-iframe' + index];
            }
        });
    })
    $(".addPermission_tree").click(function(){
    	iconShow =layer.open({
    		type: 2,
    		title: '选择图标',
    		shadeClose: true,
    		skin: 'layui-layer-lan', //加上边框
    		area : 'auto',
    		maxmin: true,
    		area: ['600px', '500px'],
    		anim: 1,//0平滑放大。默认 1	从上掉落2	从最底部往上滑入 3从左滑入4	从左翻滚5	渐显6	抖动
    		content: main+'/static/page/tree.html',
    		success:function (layero,index) {
    			var iframe = window['layui-layer-iframe' + index];
    		}
    	});
    })
    //列表操作
    table.on('tool(permissionList)', function(obj){
        var layEvent = obj.event,
            data = obj.data;
        if(layEvent === 'editPermission'){ //编辑
        	editPermission(data);
        }else if(layEvent === 'addChild'){
        	addPermission(data);
        } else if(layEvent === 'del'){ //删除
            layer.confirm('确定删除该权限？',{icon:3, title:'提示信息'},function(index){
            	  layer.close(index);
        		var lindex = top.layer.msg('正在删除，请稍候', {
        			icon : 16,
        			time : false,
        			shade : 0.8
        		});
                 $.get(main+"/admin/system/menu/del",{
                     id : data.id  //将需要删除的newsId作为参数传入
                 },function(data){
                	 top.layer.close(lindex);
                	 if(data.code==0){
                		 top.layer.msg("删除成功！");
                		 tableIns();
                	 }else{
                		 layer.msg(data.msg, {icon: 2});
//                		 layer.alert(data.message, {icon: 5});
//                		 layer.close(index);
                	 }
                   
                 })
            });
        } 
    });


    
})