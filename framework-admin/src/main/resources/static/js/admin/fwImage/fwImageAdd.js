layui.use([ 'form', 'layer','laydate' ,'upload'], function() {
	var form = layui.form
	layer = layui.layer, 
	$ = layui.jquery,
	laydate = layui.laydate,
	upload = layui.upload,
	main=layui.cache.main;
	
	
	  var files={};
	  var loadIndex="";
	  //记录失败个数
	  var failCount=0;
	  //拖拽上传
	  upload.render({
	    elem: '#test10'
	   ,url: main+'/admin/system/fwImage/upload'
	   ,auto: false
	   ,multiple: true
	   ,acceptMime: 'image/*'
	   ,size:"10240"  //大小控制  单位kb
	   ,data: {
			 imageType: function(){
				    return $('#imageType').val();
				  },
		     remark: function(){
			    return $('#remark').val();
			  }
			}
		   
	   ,bindAction: '#uploadImage'
	   ,before: function(obj){ //obj参数包含的信息，跟 choose回调完全一致，可参见上文。
		   loadIndex=layer.load(); //上传loading
		   var b = $.isEmptyObject(files);
		   if(b ){
			   layer.close(loadIndex);
				top.layer.msg("请上传文件！");
			   return false;
		   }
		   failCount=0;
		    
		  }
	    ,done: function(res,index, upload){
	      console.log(res, index, upload)
	      if(res.code==0){
	    	  //上传成功 移出图片
	    	  $("."+index).parents("li").hide(1000);
	            setTimeout(function(){ $("."+index).parents("li").remove();},950);
	            delete files[index];
	      }else{
	    	  //上传接口返回失败的时候 失败+1
	    	  failCount+=1;
	      }
	     
	    }
	    ,error: function(index, upload){
	    	  layer.close(loadIndex);
	    }
	  ,choose: function(obj){
		    //将每次选择的文件追加到文件队列
		     files = obj.pushFile();
		    
		    //预读本地文件，如果是多文件，则会遍历。(不支持ie8/9)
		    obj.preview(function(index, file, result){
//		      console.log(index); //得到文件索引
//		      console.log(file); //得到文件对象
//		      console.log(result); //得到文件base64编码，比如图片
	          $('#Images').prepend('<li><img layer-src="'+ result +'" src="'+ result +'" alt="'+ file.name +'" class="layui-upload-img"><div class="operate"><div class="check"><input type="checkbox" name="belle" lay-filter="choose" lay-skin="primary" title="'+file.name+'"  index="'+index+'"></div><i class="layui-icon img_del '+index+'" index="'+index+'">&#xe640;</i></div></li>')
              //设置图片的高度
              $("#Images li img").height($("#Images li img").width());
              form.render("checkbox");
		      //obj.resetFile(index, file, '123.jpg'); //重命名文件名，layui 2.3.0 开始新增
		      
		      //这里还可以做一些 append 文件列表 DOM 的操作
		      
		      //obj.upload(index, file); //对上传失败的单个文件重新上传，一般在某个事件中使用
		      //delete files[index]; //删除列表中对应的文件，一般在某个事件中使用
		    });
	  }
	  ,allDone: function(obj){ //当文件全部被提交后，才触发
//		    console.log(obj.total); //得到总文件数
//		    console.log(obj.successful); //请求成功的文件数
//		    console.log(obj.aborted); //请求失败的文件数
	      layer.msg('共上传'+obj.total+"个文件，成功"+(obj.successful-failCount)+"个，失败"+failCount+"个<br/>可以点击重新上传按钮尝试重新上传", {
	          time: 10000, //10s后自动关闭
	          btn: ['好的']
	        });
		    layer.close(loadIndex);
		  if(failCount>0){
			  $('#uploadImage').html('重新上传');
		  }else{
			  $('#uploadImage').html('立即上传');
		  }
		  }

	  //,accept: 'file' //允许上传的文件类型
	  //,size: 50 //最大允许上传的文件大小
	  });
	  
	    //删除单张图片
	    $("body").on("click",".img_del",function(){
	        var _this = $(this);
	        layer.confirm('确定删除图片"'+_this.siblings().find("input").attr("title")+'"吗？',{icon:3, title:'提示信息'},function(index){
	            _this.parents("li").hide(1000);
	            setTimeout(function(){_this.parents("li").remove();},950);
	            layer.close(index);
	            console.log(files);
	            delete files[_this.attr("index")];
	            console.log(files);
	            $('.layui-upload-file').attr("type","hidden");
	            $('.layui-upload-file').attr("type","file");
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

	    //批量删除
	    $(".batchDel").click(function(){
	        var $checkbox = $('#Images li input[type="checkbox"]');
	        var $checked = $('#Images li input[type="checkbox"]:checked');
	        if($checkbox.is(":checked")){
	            layer.confirm('确定删除选中的图片？',{icon:3, title:'提示信息'},function(index){
	                var index = layer.msg('删除中，请稍候',{icon: 16,time:false,shade:0.8});
                    //删除数据
	                var  count = 0;
                    $checked.each(function(){
                        $(this).parents("li").hide(1000);
                        delete files[ $(this).attr("index")];
                        setTimeout(function(){$(this).parents("li").remove();},950);
                        $('.layui-upload-file').attr("type","hidden");
        	            $('.layui-upload-file').attr("type","file");
        	            
        	            if (++count === $checked.length) {
        	                $('#Images li input[type="checkbox"],#selectAll').prop("checked",false);
                            form.render();
                            layer.close(index);
                            layer.msg("删除成功");
        	            }
                    })
               
	            })
	        }else{
	            layer.msg("请选择需要删除的图片");
	        }
	    })
})