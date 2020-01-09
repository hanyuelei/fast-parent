layui.use([ 'form', 'layer' ,'laydate'], function() {
	var form = layui.form
	layer = layui.layer, $ = layui.jquery,
	laydate = layui.laydate,
    main=layui.cache.main;
	form.on("submit(edit)", function(data) {
		var index = top.layer.msg('数据提交中，请稍候', {
			icon : 16,
			time : false,
			shade : 0.8
		});
		$.post(main+"/admin/system/fwScheduleJobLog/edit",data.field , function(res) {
			top.layer.close(index);
			if (res.code == 0) {
				top.layer.msg("修改成功！");
				parent.location.reload();
			} else {

				layer.msg(res.msg, {
					icon : 2
				});
			}
		}).error(function(error) {
			top.layer.msg("修改失败！");
			// 处理
		});

		return false;
	})
})