layui.use([ 'form', 'layer' ], function() {
	var form = layui.form
	layer = layui.layer, $ = layui.jquery,
    main=layui.cache.main;
	form.verify({
		validateCode : [ /^[A-Za-z]+$/, '登录账号只能为字母' ]
	});
	form.on("submit(editUser)", function(data) {
		var index = top.layer.msg('数据提交中，请稍候', {
			icon : 16,
			time : false,
			shade : 0.8
		});
		var s = $(".status").val();
		$.post(main+"/admin/system/user/edit",data.field , function(res) {
			top.layer.close(index);
			if (res.code == 0) {
				top.layer.msg("用户修改成功！");
				parent.location.reload();
			} else {

				layer.msg(res.msg, {
					icon : 2
				});
			}
		}).error(function(error) {
			top.layer.msg("用户修改失败！");
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