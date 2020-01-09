package com.framework.common.util.result;

public class CodeMsg {

	private int code;
	private String msg;
	//通用模块
	public static CodeMsg SUCCESS = new CodeMsg(CodeMsg.SUCCESS_CODE,"success");
	public static CodeMsg SERVER_ERROR = new CodeMsg(CodeMsg.SYSERR_CODE,CodeMsg.SYSERR_MSG);
	public static CodeMsg LIMIT_ERROR = new CodeMsg(500102,"访问太频繁");
	
	
	//登录模块
	public static CodeMsg SESSION_EXPIRE = new CodeMsg(100001,"请先登录");
	public static CodeMsg NAME_PASS_ERROR = new CodeMsg(100002,"错误的用户名密码");

	//带参数错误
	public static CodeMsg RUNTIME_EXCEPTION = new CodeMsg(100100,"出现错误：%s");		
	
	
	
	private CodeMsg(int code, String msg) {
		// TODO Auto-generated constructor stub
		this.code = code;
		this.msg = msg;
	}
	public int getCode() {
		return code;
	}
	public String getMsg() {
		return msg;
	}
	
	public CodeMsg fillArgs(Object...args) {
		int code = this.code;
		String message = String.format(this.msg, args);
		return new CodeMsg(code,message);
	}
	/**
	 * 普通错误通用错误码
	 */
	public static CodeMsg error(String msg) {
		
		return new CodeMsg(FAIL_CODE,msg);
	}
	
	/**
	 * 成功通用码
	 */
	public static CodeMsg success() {
		
		return new CodeMsg(FAIL_CODE,CodeMsg.SUCCESS_MSG);
	}
	@Override
	public String toString() {
		return "CodeMsg [code=" + code + ", msg=" + msg + "]";
	}
	
	public final static int SUCCESS_CODE = 0;  //通用成功返回码
	public final static int FAIL_CODE = -1;	//通用失败错误码
	public final static String SUCCESS_MSG = "操作成功";
	public final static String FAIL_MSG = "操作失败";
	public final static int SYSERR_CODE = -500;//系统异常返回码
	public final static String SYSERR_MSG = "系统异常";
}
