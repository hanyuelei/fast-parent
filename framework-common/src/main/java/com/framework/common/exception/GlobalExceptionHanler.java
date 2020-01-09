package com.framework.common.exception;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.framework.common.util.result.CodeMsg;
import com.framework.common.util.result.Result;
/**
 * 全局异常处理
 * Created by hanyl on 2019年12月17日.
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHanler {

	
	@ExceptionHandler(value=Exception.class)
	private Result exceptionhandler(HttpServletRequest request,Exception e){
			e.printStackTrace();
		if(e instanceof GlobalException) {
			GlobalException ex = (GlobalException)e;
			return Result.failure(ex.getCm());
		}else if(e instanceof BindException) {//jr303校验错误异常
			BindException ex = (BindException) e;
			List<ObjectError> errors = ex.getAllErrors();
			String msg = errors.get(0).getDefaultMessage();
			return Result.failure(msg);
		}else {
			return Result.failure(CodeMsg.SERVER_ERROR);
		}
	}
}
