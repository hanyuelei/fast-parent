package com.framework.common.util.result;

import java.util.HashMap;

/**
 * 
* @ClassName: R  
* @Description: TODO(返回类)  
* @author hanyl
* @date 2019年6月27日 下午5:20:27  
*
 */
public class Result extends HashMap<String, Object> {


	//set任何值
    public Result setAny(String key, Object value) {
        if (key != null && value != null) put(key, value);
        return this;
    }
    public Result setCode(int code) {
        put("code", code);
       return this;
   }
   public Result setMsg(String msg) {
       if (msg != null) put("msg", msg);
       return this;
   }
   	//成功
    public static Result success(){
    	
       return createResult(CodeMsg.SUCCESS_CODE,CodeMsg.SUCCESS_MSG);
    }
    
    public static Result success(String msg){
    	
        return createResult(CodeMsg.SUCCESS_CODE,msg);
    }
    
    public static Result success(Object obj){
    	
        return successResult(obj);
    }
    
    
    //失败
    public static Result failure(){
    	
        return createResult(CodeMsg.FAIL_CODE,CodeMsg.FAIL_MSG);
    }
    
    public static Result failure(String msg){
    	
        return createResult(CodeMsg.FAIL_CODE,msg);
    }
    
    public static Result failure(CodeMsg cm){
    	
        return createResult(cm.getCode(),cm.getMsg());
    }
    
    //结果
    public static Result result(CodeMsg cm){
        
    	return createResult(cm.getCode(),cm.getMsg());
    	
    }
    
    
	private static Result createResult(int code,String msg) {
		
    	Result result = new Result();
    	result.setCode(code);
    	result.setMsg(msg);
    	return result;
    	
    }
	private static Result successResult(Object obj) {
    	Result result = new Result();
    	result.setCode(CodeMsg.SUCCESS_CODE);
    	result.setMsg(CodeMsg.SUCCESS_MSG);
    	result.setAny("data",obj);
    	return result;
    	
    }
    
}
