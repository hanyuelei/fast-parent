layui.use([ 'form', 'layer' ], function() {
	var form = layui.form
	layer = parent.layer === undefined ? layui.layer : top.layer,
			$ = layui.jquery,
		main=layui.cache.main;
	form.verify({
		validateCode : [ 
						/^[A-Za-z0-9_\-]+$/ig
			             , '角色编码只能为数字、字母、下划线' 
			            ]
	});
	form.on("submit(addRole)", function(data) {
		// 弹出loading
		var index = top.layer.msg('数据提交中，请稍候', {
			icon : 16,
			time : false,
			shade : 0.8
		});
		// 实际使用时的提交信息
		$.post(main+"/admin/system/role/add", data.field , function(res) {
			top.layer.close(index);
			if (res.code == 0) {
				top.layer.msg("添加角色成功！");
				parent.location.reload();
			} else {
				layer.msg(res.msg, {
					icon : 2
				});
			}

		}).error(function(error) {
			top.layer.msg("添加角色失败败！");
			// 处理
		});

		return false;
	})
    $(document).on('click','#closeButton',function(){
    	var index = parent.layer.getFrameIndex(window.name);
    	parent.layer.close(index);//关闭当前页
    });

})