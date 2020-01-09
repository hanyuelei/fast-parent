
layui.use(['form','layer','laydate','table','laytpl','flow'],function(){
    var form = layui.form,
        layer = layui.layer,
        $ = layui.jquery,
        laydate = layui.laydate,
        laytpl = layui.laytpl,
        table = layui.table,
        flow = layui.flow,
        main=layui.cache.main;

    //流加载图片
    flowImage('','');
/*    var imgNums = 10;  //单页显示图片数量
    flow.load({
        elem: '#Images', //流加载容器
        done: function(page, next){ //加载下一页
            $.post(main+'/admin/system/fwImage/list?page='+page+"&limit="+imgNums,function(res){
                //模拟插入
                var imgList = [],
                data = res.data;
                for(var i=0; i<data.length; i++){
                    imgList.push('<li><img layer-src="http://localhost:8888/image/'+ data[i].localUrl 
                    		+'" src="http://localhost:8888/image/'+ data[i].localUrl 
                    		+'" alt="上传图片"><div class="operate"><div class="check">'
                    		+'<input type="checkbox" name="belle" lay-filter="choose" lay-skin="primary" title="上传的图片" id="'+data[i].id+'">'
                    		+'</div><i class="layui-icon img_del" id="'+data[i].id+'">&#xe640;</i></div></li>');
                }
                next(imgList.join(''), page < res.pages);
                form.render();
                //图片懒加载
//                flow.lazyimg(); 
            });
        }
    });*/
    //弹出层
    $("body").on("click","#Images img",function(){
        var id=$(this).parents("li").find('input[type=checkbox]').attr('id');
        var index = layui.layer.open({
            title :'详情',
            type : 2,
            content : main+"/admin/system/fwImage/detail?id="+id,
            skin: 'layui-layer-molv', //加上边框
            area : [ '850px', '550px' ],
            maxmin: true,
            anim: 2,//0平滑放大。默认 1	从上掉落2	从最底部往上滑入 3从左滑入4	从左翻滚5	渐显6	抖动
            offset: 'auto',//自动水平垂直居中
            success : function(layero, index){
                setTimeout(function(){
                    layui.layer.tips('点击此处返回列表', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                },500)
            },
  
        })
    })
    //设置图片的高度
    $(window).resize(function(){
        $("#Images li img").height($("#Images li img").width());
    })

    $(".search_btn").on("click",function(){
    	  var imageType=$('#imageType').val();
    	  var remark=$('#remark').val();
    	  flowImage(imageType,remark);
    });
    
    $(".fwImageadd_btn").click(function(){
    	add();
    })
    function add(edit){
    	var title="添加";
    	var content=main+"/admin/system/fwImage/add";
    	if(edit){
    		title="修改";
    		content=main+"/admin/system/fwImage/edit?id="+edit.id;
    	}
        var index = layui.layer.open({
            title :title,
            type : 2,
            content : content,
            skin: 'layui-layer-molv', //加上边框
           /*           area : [ '650px', '550px' ],
            maxmin: true,
            anim: 2,//0平滑放大。默认 1	从上掉落2	从最底部往上滑入 3从左滑入4	从左翻滚5	渐显6	抖动
            offset: 'auto',//自动水平垂直居中
*/            success : function(layero, index){
                setTimeout(function(){
                    layui.layer.tips('点击此处返配置列表', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                },500)
            },
  
        })
          layui.layer.full(index);
            //改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
            $(window).on("resize",function(){
                layui.layer.full(index);
            })
    }
    //列表操作
    table.on('tool(fwImageList)', function(obj){
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
    //删除单张图片
    $("body").on("click",".img_del",function(){
        var _this = $(this);
        layer.confirm('确定删除图片吗？',{icon:3, title:'提示信息'},function(index){
       	  $.get(main+"/admin/system/fwImage/del?id="+_this.attr('id'), function(result){
     			if(result.code==0){
     				top.layer.msg(" 删除成功！");
     		         _this.parents("li").hide(1000);
                     setTimeout(function(){_this.parents("li").remove();},950);
                     layer.close(index);
                 	
     			}else{
     				top.layer.msg("删除失败！");
           	 }
     	});
  
          
        });
    })

    //全选
    form.on('checkbox(selectAll)', function(data){
        var child = $("#Images li input[type='checkbox']");
        child.each(function(index, item){
            item.checked = data.elem.checked;
        });
        form.render('checkbox');
    });


    //批量删除
    $(".batchDelList").click(function(){
    	var success=0;
    	var fail=0;
        var $checkbox = $('#Images li input[type="checkbox"]');
        var $checked = $('#Images li input[type="checkbox"]:checked');
        if($checkbox.is(":checked")){
            layer.confirm('确定删除选中的图片？',{icon:3, title:'提示信息'},function(index){
              	layer.close(index);
          		var lindex = top.layer.msg('正在删除，请稍候', {
         			icon : 16,
         			time : false,
         			shade : 0.8
         		});
                var index = layer.msg('删除中，请稍候',{icon: 16,time:false,shade:0.8});
                //删除数据
                var  count = 0;
                $checked.each(function(){
                    var _this=$(this);
               	  $.get(main+"/admin/system/fwImage/del?id="+$(this).attr('id'), function(result){
               		  	top.layer.close(lindex);
               			if(result.code==0){
               				_this.parents("li").hide(1000);
                            setTimeout(function(){_this.parents("li").remove();},950);
                            success+=1;
               			}else{
               				fail+=1;
                     	 }
               		    if (++count === $checked.length) {
               		        $('#Images li input[type="checkbox"],#selectAll').prop("checked",false);
                            form.render();
                            layer.msg('删除成功'+success+"个文件，失败"+fail+"个", {
                  	          time: 3000, //3s后自动关闭
                  	          btn: ['好的']
                  	        });
               		    }
                 	  });
                })
        
            })
        }else{
            layer.msg("请选择需要删除的图片");
        }
    })
    function  flowImage(imageType,remark){
    	$('#Images').html('');
    	/*$('.image-list').append('<ul class="layer-photos-demo" id="Images"></ul>');*/
        //流加载图片
    	var img_prefix=$('#configAttr').attr('attr');
    	$(document).off('scroll');
        var imgNums = 10;  //单页显示图片数量
        flow.load({
            elem: '#Images', //流加载容器
            isAuto: true, 
           /* scrollElem: '.layadmin-tabsbody-item' ,//滚动条所在元素，一般不用填，此处只是演示需要。
*/            done: function(page, next){ //加载下一页
                $.post(main+'/admin/system/fwImage/list?page='+page+"&limit="+imgNums+"&imageType="+imageType+"&remark="+remark,function(res){
                    //模拟插入
                    var imgList = [],
                    data = res.data;
                    
                    for(var i=0; i<data.length; i++){
                        imgList.push('<li><img layer-src="'+img_prefix+ data[i].localUrl 
                        		+'" src="'+img_prefix+ data[i].localUrl 
                        		+'" alt="上传图片"><div class="operate"><div class="check">'
                        		+'<input type="checkbox" name="belle" lay-filter="choose" lay-skin="primary" title="上传的图片" id="'+data[i].id+'">'
                        		+'</div><i class="layui-icon img_del" id="'+data[i].id+'">&#xe640;</i></div></li>');
                    }
                    next(imgList.join(''), page < res.pages);
                    $("#Images li img").height($("#Images li img").width());
                    form.render();
                    //图片懒加载
//                    flow.lazyimg(); 
                });
            }
        });
    }

    //通过判断是否全部选中来确定全选按钮是否选中
    form.on("checkbox(choose)",function(data){
        var child = $(data.elem).parents('#Images').find('li input[type="checkbox"]');
        var childChecked = $(data.elem).parents('#Images').find('li input[type="checkbox"]:checked');
        if(childChecked.length == child.length){
            $(data.elem).parents('#Images').siblings("blockquote").find('input#selectAll').get(0).checked = true;
        }else{
            $(data.elem).parents('#Images').siblings("blockquote").find('input#selectAll').get(0).checked = false;
        }
        form.render('checkbox');
    })
})