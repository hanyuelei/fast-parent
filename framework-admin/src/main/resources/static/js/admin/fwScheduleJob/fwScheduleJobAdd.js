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
		$.post(main+"/admin/system/fwScheduleJob/add",  data.field , function(res) {
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
	form.on('switch(jobStatusBox)', function(data) {
		if (data.elem.checked) {
			$("#jobStatus").val("0");
		} else {
			$("#jobStatus").val("1");
		}

	});
	$(".chooseCron").click(function(){
        iconShow =layer.open({
            type: 2,
            title: '选择表达式',
            shadeClose: true,
            skin: 'layui-layer-lan', //加上边框
            area : 'auto',
            maxmin: true,
            area: ['600px', '500px'],
            anim: 1,//0平滑放大。默认 1	从上掉落2	从最底部往上滑入 3从左滑入4	从左翻滚5	渐显6	抖动
            content:main+ '/static/page/chooseCorn.html',
            success:function (layero,index) {
                    var iframe = window['layui-layer-iframe' + index];
            }
        });
	});
	
})