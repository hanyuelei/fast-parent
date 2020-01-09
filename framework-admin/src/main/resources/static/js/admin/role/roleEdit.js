layui.use([ 'form', 'layer' ], function() {
	var form = layui.form
	layer = parent.layer === undefined ? layui.layer : top.layer,
			$ = layui.jquery,
			   main=layui.cache.main;
	// 自定义验证规则
	form.verify({
		validateCode : [ 
			/^[A-Za-z0-9_\-]+$/ig
             , '角色编码只能为数字、字母、下划线' 
            ]
	});
	form.on("submit(roleEdit)", function(data) {
		// 弹出loading
		var index = top.layer.msg('数据提交中，请稍候', {
			icon : 16,
			time : false,
			shade : 0.8
		});
		$.post(main+"/admin/system/role/edit",  data.field , function(res) {
			top.layer.close(index);
			if (res.code == 0) {
				/*top.layer.msg("用户角色成功！");*/
				layer.open({
					  title:'信息',
					  skin:'layui-layer-lan',
					  type: 1, 
					  area: ['200px', '150px'],
					  offset: 'rb',
					  time:1000,
					  anim:2,
					  content: '传入任意的文本或html' //这里content是一个普通的String
					});
				parent.location.reload();
			} else {
				layer.msg(res.msg, {
					icon : 2
				});
			}
		}).error(function(error) {
			top.layer.msg("修改角色失败！");
			// 处理
		});

		return false;
	})

	// 格式化时间
	function filterTime(val) {
		if (val < 10) {
			return "0" + val;
		} else {
			return val;
		}
	}
	// 定时发布
	var time = new Date();
	var submitTime = time.getFullYear() + '-' + filterTime(time.getMonth() + 1)
			+ '-' + filterTime(time.getDate()) + ' '
			+ filterTime(time.getHours()) + ':' + filterTime(time.getMinutes())
			+ ':' + filterTime(time.getSeconds());

	form.on('switch(userstatus)', function(data) {
		if (data.elem.checked) {
			$("#userstatus").val("1");
		} else {
			$("#userstatus").val("0");
		}

	});
})