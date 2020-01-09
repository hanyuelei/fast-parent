package com.framework.common.util;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.framework.common.util.result.CodeMsg;
import com.framework.common.util.result.Result;

public class WebUtil {

	public static void render(HttpServletResponse response,CodeMsg cm) throws IOException {
		response.setContentType("application/json;charset=utf-8");
		OutputStream out = response.getOutputStream();
		String str = JSON.toJSONString(Result.result(cm));
		out.write(str.getBytes("utf-8"));
		out.flush();
		out.close();
		// TODO Auto-generated method stub
		
	}
}
