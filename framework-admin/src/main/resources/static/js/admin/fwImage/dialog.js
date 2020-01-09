layui.use(['form','jquery','layer','element','laydate' ,'upload','flow'],function(){
    var form = layui.form,
        layer = layui.layer,
        $ = layui.jquery,
        element = layui.element,
    	laydate = layui.laydate,
    	upload = layui.upload,
    	  flow = layui.flow,
        main=layui.cache.main;
    
    var tabindex=0;
    $(".addImage").click(function(){
    	add();	
    })
     function add(){
    	var index2 = parent.layer.getFrameIndex(window.name);
    	var content=main+"/admin/system/fwImage/dialog";
       parent.layer.open({
            title :"上传图片",
            type : 2,
            content : content,
            shadeClose: true,
            maxmin: true,
            skin: 'layui-layer-lan', //加上边框
            area : [ '800px', '500px' ],
            moveOut : true,
            anim: 2,//0平滑放大。默认 1	从上掉落2	从最底部往上滑入 3从左滑入4	从左翻滚5	渐显6	抖动
            offset:   'rb',//自动水平垂直居中
            success : function(layero, index){
                 var contentWindow = window.parent.layui.$("#layui-layer-iframe" + index)[0].contentWindow;
                contentWindow.aa(index2);
              

            }
        })
    }
    upload.render({
    	   elem: '#uploadImage'
    	  ,url: main+'/admin/system/fwImage/upload'
    	  ,data: {imageType:1,remark:'其他'} //可选项。额外的参数，如：{id: 123, abc: 'xxx'}
    	  ,auto: true
	      ,multiple: true
	      ,acceptMime: 'image/*'
	      ,size:"10240"  //大小控制  单位kb
    	  ,done: function(res, index, upload){
    		    //假设code=0代表上传成功
    		    if(res.code == 0){
    		      //do something （比如将res返回的图片链接保存到表单的隐藏域）
    		    		var result=res.img_prefix+res.fileName;
    		          $('#upImages').prepend('<li id="'+res.fileName+'"><img layer-src="'+ result +'" src="'+ result +'" alt="" class="layui-upload-img"></li>')
    	              //设置图片的高度
    		          $("#upImages li img").height('100px');
    		          $("#upImages li img").width('100px');
    		    }else{
    		    	alert('上传失败');
    		    }
    		  }
    	}); 
    
    //一些事件监听
    element.on('tab(imageElement)', function(data){
    		if(data.index==1){
    			tabindex=1;
    			var imgNums = 10;  //单页显示图片数量
    			var img_prefix="";
    	        flow.load({
    	            elem: '#onlineImages', //流加载容器
    	            isAuto: false,
    	            end:'只有这么多了',
    	            done: function(page, next){ //加载下一页
    	                $.post(main+'/admin/system/fwImage/list?page='+page+"&limit="+imgNums,function(res){
    	                    //模拟插入
    	                    var imgList = [],
    	                    data = res.data;
    	                    img_prefix=res.img_prefix;
    	                    for(var i=0; i<data.length; i++){
    	                    	var liImg='<li id="'+data[i].localUrl+'"><img layer-src="'+ img_prefix+data[i].localUrl +'" src="'+  img_prefix+data[i].localUrl +'" alt="" class="layui-upload-img"></li>';
    	                        imgList.push(liImg);
    	                    }
    	                    next(imgList.join(''), page < res.pages);
    	                      $("#onlineImages li img").height('100px');
    	    		          $("#onlineImages li img").width('100px');
    	                    form.render();
    	                    //图片懒加载
    	                    flow.lazyimg(); 
    	                });
    	            }
    	        });

    		}else{
    			tabindex=0;
    		}
    }); 
    $(document).on('click','#closeButton',function(){
    	var index = parent.layer.getFrameIndex(window.name);
    	parent.layer.close(index);//关闭当前页
    });
    
 //==================上传图片=================
    $("body").on("click","#upImages>li",function(){
    	  $(this).toggleClass('selected');
    	  if ($('#upImages>li.selected').length == 0)
    	    $('#upImages').children('.select').removeClass('selected');
    	  else
    		  $('#upImages').children('.select').addClass('selected');
    	  counter();
    })
    // all item selection
    $('#clearfix').children('.select').click(function () {
      if ($('#upImages>li.selected').length == 0) {
        $('#upImages>li').addClass('selected');
        $('#clearfix').children('.select').addClass('selected');
      }
      else {
        $('#upImages>li').removeClass('selected');
        $('#clearfix').children('.select').removeClass('selected');
      }
      counter();
    });

    // number of selected items
    function counter() {
      if ($('#upImages>li.selected').length > 0)
        $('#clearfix').children('.send').addClass('selected');
      else
        $('#clearfix').children('.send').removeClass('selected');
      
      $('#clearfix').children('.send').attr('data-counter',$('#upImages>li.selected').length);
    }
    
    
    //==============在线图片===================
      $("body").on("click","#onlineImages>li",function(){
      	  $(this).toggleClass('selected');
      	  if ($('#onlineImages>li.selected').length == 0)
      	    $('#onlineImages').children('.select').removeClass('selected');
      	  else
      		  $('#onlineImages').children('.select').addClass('selected');
      	counter2();
      })
      // all item selection
      $('#onlineclearfix').children('.select').click(function () {
        if ($('#onlineImages>li.selected').length == 0) {
          $('#onlineImages>li').addClass('selected');
          $('#onlineclearfix').children('.select').addClass('selected');
        }
        else {
          $('#onlineImages>li').removeClass('selected');
          $('#onlineclearfix').children('.select').removeClass('selected');
        }
        counter2();
      });

      // number of selected items
      function counter2() {
        if ($('#onlineImages>li.selected').length > 0)
          $('#onlineclearfix').children('.send').addClass('selected');
        else
          $('#onlineclearfix').children('.send').removeClass('selected');
        
        $('#onlineclearfix').children('.send').attr('data-counter',$('#onlineImages>li.selected').length);
      } 
      
      //===========添加选中图片===========
      
      $(".add_imag_submit").on("click",function () {	
      	var idx=$('#idex').val();
      	var lis="";
      	if(tabindex==1){
      		lis=$('#onlineImages li.selected');
      	}else{
      		lis=$('#upImages li.selected');
      	}
      	if(lis.length==0){
      		top.layer.msg("请选择图片！");
      		return ;
      	}
        var imgs = [] //数组存放值
        for(var i = 0; i < lis.length; i++){
             var src=lis[i].id;
             imgs[i]=src;
        }
        console.log(imgs);
      /*	 window.parent.layui.$("#layui-layer-iframe1").contents().find("#image_aa").val("1133333111111");给兄弟页面赋值*/
      	//调用兄弟页面方法
      	  var contentWindow = window.parent.layui.$("#layui-layer-iframe"+idx)[0].contentWindow;
           contentWindow.selectImg(imgs);
      	index = parent.layer.getFrameIndex(window.name);
  	    parent.layer.close(index);
      	
      });
})