layui.use([ 'form', 'layer' ], function() {
	var form = layui.form
	layer = layui.layer, 
	$ = layui.jquery,
	main=layui.cache.main;
	form.verify({
		validateCode : [ /^[A-Za-z]+$/, '登录账号只能为字母' ]
	});
	$("form :input.required").each(function(){ 
		var $required = $("<strong class='high'> *</strong>");    //创建元素 
		$(this).parent().append($required);    //追加到文档中，注意parent()方法的使用 
		}) 
	form.on("submit(addUser)", function(data) {
		// 弹出loading
		var index = top.layer.msg('数据提交中，请稍候', {
			icon : 16,
			time : false,
			shade : 0.8
		});
		// 实际使用时的提交信息
		$.post(main+"/admin/system/user/add",  data.field , function(res) {
			top.layer.close(index);
			if (res.code == 0) {
				top.layer.msg("用户添加成功！");
				parent.location.reload();
			} else {
				layer.msg(res.msg, {
					icon : 2
				});
			}

		}).error(function(error) {
			top.layer.msg("用户添加失败败！");
			// 处理
		});

		return false;
	})

	form.on('switch(userstatus)', function(data) {
		if (data.elem.checked) {
			$("#userstatus").val("1");
		} else {
			$("#userstatus").val("0");
		}

	});

})