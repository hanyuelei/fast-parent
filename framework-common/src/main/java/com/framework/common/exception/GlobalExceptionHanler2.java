package com.framework.common.exception;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.framework.common.util.result.CodeMsg;
import com.framework.common.util.result.Result;
/**
 *      全局异常处理
 *1. 如果是ajax请求则返回json视图，非ajax请求则返回对应页面 
 *2.调试模式：请求地址加debug=true  不判断请求 则直接返回json格式
 */
//@ControllerAdvice
public class GlobalExceptionHanler2 {
	
	
	private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHanler2.class);
	
	private static final String NOTFOUND_PAGE = "/cmow/404";
	
	private static final String ERROR_PAGE = "/cmow/500";
	

	@ExceptionHandler(value=Exception.class)
	private ModelAndView exceptionhandler(HttpServletRequest request,Exception e){
		if(e instanceof GlobalException) {
			GlobalException ex = (GlobalException)e;
			return errorResult(NOTFOUND_PAGE,Result.failure(ex.getCm()), request);
		}else if (e instanceof NoHandlerFoundException) {
			log.error(e.getMessage(),e);
			return errorResult("请求url错误", NOTFOUND_PAGE,Result.failure(CodeMsg.SERVER_ERROR), request);
            }
		else {
			log.error(e.getMessage(),e);
			e.printStackTrace();
//			return errorResult("服务器错误", ERROR_PAGE,Result.failure(CodeMsg.SERVER_ERROR), request);
			return errorResult("服务器错误", NOTFOUND_PAGE,Result.failure(CodeMsg.SERVER_ERROR), request);
		}
		
	}
	
	   /**
     * 判断是否ajax请求
     *
     * @param request 请求对象
     * @return true:ajax请求  false:非ajax请求
     */
    private boolean isAjax(HttpServletRequest request) {
    	//调试模式
    	if(null != request.getParameter("debug")) {
    		return true;
    	}
        return "XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With"));
    }
    /**
     * 	自定义全局异常  返回错误信息
     *
     * @param message 错误信息
     * @param url     错误页url
     * @param request 请求对象
     * @return 模型视图对象
     */
    private ModelAndView errorResult( String url,Result result, HttpServletRequest request) {
        if (isAjax(request)) {
            return jsonResult(result);
        } else {
            return normalResult(null, url);
        }
    }
    /**
     * 返回错误信息
     *
     * @param message 错误信息
     * @param url     错误页url
     * @param request 请求对象
     * @return 模型视图对象
     */
    private ModelAndView errorResult(String message, String url,Result result, HttpServletRequest request) {
    	log.warn("请求处理失败，请求url=[{}], 失败原因 : {}", request.getRequestURI(), message);
        if (isAjax(request)) {
            return jsonResult(result);
        } else {
            return normalResult(message, url);
        }
    }

    /**
               * 返回错误页
     *
     * @param message 错误信息
     * @param url     错误页url
     * @return 模型视图对象
     */
    private ModelAndView normalResult(String message, String url) {
        Map<String, String> model = new HashMap<String, String>();
        model.put("msg", message);
        return new ModelAndView(url, model);
    }

    /**
               * 返回错误数据
     *
     * @param message 错误信息
     * @return 模型视图对象
     */
    private ModelAndView jsonResult(Result result) {
    
        ModelAndView mv = new ModelAndView();
        MappingJackson2JsonView jacksonView = new MappingJackson2JsonView();
        jacksonView.setAttributesMap((JSONObject) JSON.toJSON(result));
        mv.setView(jacksonView);
        return mv;
    }
    
    
}
