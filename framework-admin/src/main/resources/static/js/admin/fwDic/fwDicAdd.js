layui.use([ 'form', 'layer','laydate' ], function() {
	var form = layui.form
	layer = layui.layer, 
	$ = layui.jquery,
	laydate = layui.laydate,
	main=layui.cache.main;
	form.on("submit(add)", function(data) {
		// 弹出loading
		var index = top.layer.msg('数据提交中，请稍候', {
			icon : 16,
			time : false,
			shade : 0.8
		});
		// 实际使用时的提交信息
		$.post(main+"/admin/system/fwDic/add",  data.field , function(res) {
			top.layer.close(index);
			if (res.code == 0) {
				top.layer.msg("添加成功！");
				parent.location.reload();
			} else {
				layer.msg(res.msg, {
					icon : 2
				});
			}

		}).error(function(error) {
			top.layer.msg("添加失败！");
		});

		return false;
	})

})