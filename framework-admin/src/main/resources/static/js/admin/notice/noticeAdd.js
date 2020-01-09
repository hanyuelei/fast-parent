layui.use([ 'form', 'layer','laydate' ], function() {
	var form = layui.form
	layer = layui.layer, 
	$ = layui.jquery,
	laydate = layui.laydate,
	main=layui.cache.main;
	
    var E = window.wangEditor
    var editor = new E('#div1')
    var $text1 = $('#text1')
    editor.customConfig.onchange = function (html) {
        // 监控变化，同步更新到 textarea
        $text1.val(html)
    }
    // 隐藏“网络图片”tab
//    editor.customConfig.showLinkImg = false
 // 配置服务器端地址
    editor.customConfig.uploadImgServer = main+'/admin/system/fwImage/editorUpload'
	// 将图片大小限制为 3M
	editor.customConfig.uploadImgMaxSize = 3 * 1024 * 1024
	// 限制一次最多上传 5 张图片
	editor.customConfig.uploadImgMaxLength = 1
	editor.customConfig.uploadImgParams = {
		    // 如果版本 <=v3.1.0 ，属性值会自动进行 encode ，此处无需 encode
		    // 如果版本 >=v3.1.1 ，属性值不会自动 encode ，如有需要自己手动 encode
		    imageType: '4'
		}
    editor.customConfig.uploadFileName = 'file'
    	editor.customConfig.uploadImgHooks = {
    	    before: function (xhr, editor, files) {
    	        // 图片上传之前触发
    	        // xhr 是 XMLHttpRequst 对象，editor 是编辑器对象，files 是选择的图片文件
    	        
    	        // 如果返回的结果是 {prevent: true, msg: 'xxxx'} 则表示用户放弃上传
    	        // return {
    	        //     prevent: true,
    	        //     msg: '放弃上传'
    	        // }
    	    },
    	    success: function (xhr, editor, result) {
    	        // 图片上传并返回结果，图片插入成功之后触发
    	        // xhr 是 XMLHttpRequst 对象，editor 是编辑器对象，result 是服务器端返回的结果
    	    },
    	    fail: function (xhr, editor, result) {
    	        // 图片上传并返回结果，但图片插入错误时触发
    	        // xhr 是 XMLHttpRequst 对象，editor 是编辑器对象，result 是服务器端返回的结果
    	    },
    	    error: function (xhr, editor) {
    	        // 图片上传出错时触发
    	        // xhr 是 XMLHttpRequst 对象，editor 是编辑器对象
    	    },
    	    timeout: function (xhr, editor) {
    	        // 图片上传超时时触发
    	        // xhr 是 XMLHttpRequst 对象，editor 是编辑器对象
    	    },

    	    // 如果服务器端返回的不是 {errno:0, data: [...]} 这种格式，可使用该配置
    	    // （但是，服务器端返回的必须是一个 JSON 格式字符串！！！否则会报错）
    	    customInsert: function (insertImg, res, editor) {
    	        // 图片上传并返回结果，自定义插入图片的事件（而不是编辑器自动插入图片！！！）
    	        // insertImg 是插入图片的函数，editor 是编辑器对象，result 是服务器端返回的结果

    	        // 举例：假如上传图片成功后，服务器端返回的是 {url:'....'} 这种格式，即可这样插入图片：
    	    	if(res.code==0){
    	    		   insertImg(res.img_prefix+res.fileName)
    	    	}else{
    	    		   top.layer.msg(res.msg);
    	    	}
    	     

    	        // result 必须是一个 JSON 格式字符串！！！否则报错
    	    }
    	    }
	editor.customConfig.customAlert = function (info) {
	    // info 是需要提示的内容
	    top.layer.msg(info);
	}
    editor.create()
    // 初始化 textarea 的值
    $text1.val(editor.txt.html())
	
	form.on("submit(addNotice)", function(data) {
		// 弹出loading
		var index = top.layer.msg('数据提交中，请稍候', {
			icon : 16,
			time : false,
			shade : 0.8
		});
		// 实际使用时的提交信息
		$.post(main+"/admin/system/notice/add",  data.field , function(res) {
			top.layer.close(index);
			if (res.code == 0) {
				top.layer.msg("公告添加成功！");
				parent.location.reload();
			} else {
				layer.msg(res.message, {
					icon : 2
				});
			}

		}).error(function(error) {
			top.layer.msg("公告添加失败败！");
			// 处理
		});

		return false;
	})

	form.on('switch(noticeStatus)', function(data) {
		if (data.elem.checked) {
			$("#noticeStatus").val("1");
		} else {
			$("#noticeStatus").val("0");
		}

	});
	
	
	  //执行一个laydate实例（日期控件）
	  laydate.render({
	    elem: '#dateTime' //指定元素
	    ,type: 'datetime'
	  });
	  

})