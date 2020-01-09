package com.hanyl;

import java.io.OutputStream;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.framework.common.annotation.AccessLimit;
import com.framework.common.annotation.Login;
import com.framework.common.annotation.LoginUser;
import com.framework.common.config.resolver.UserVO;
import com.framework.common.redis.RedisService;
import com.framework.common.util.result.Result;
import com.framework.common.util.verifycode.ImageCode;
import com.framework.common.util.verifycode.VerifyCodeUtil;
import com.hanyl.admin.vo.LoginVo;
import com.hanyl.rabbitmq.MQSender;

@Controller
public class TestController {

	@Autowired
	RedisService redisService;
	
	@Autowired
	MQSender mqSender;
	/**
	 * 测试redis
	 */
//	@RequestMapping("/test")
//	public String test() {
//		redisService.set(SysConfigKey.getConfig,"key1", "2222");
//		String str=redisService.get(SysConfigKey.getConfig,"key1", String.class);
//		System.out.println(str);
//		return "hello spring boot";
//	}
	
	/**
	 * 测试rabbitmq
	 */
	@RequestMapping("/mq")
	@ResponseBody
	public Result send() {
		
		mqSender.send("hello word");
		return Result.success();

	}
	@RequestMapping("/verificate")
	public String verificate(HttpServletResponse response) {
		
//		String result = aa.drawImageVerificate(response);
//		System.out.println("result:"+result);
		return "code";
		
	}
	/**
	 * 测试数字验证码
	 */
	@RequestMapping("/getCode")
	public void getCode(HttpServletResponse response) {
		
		ImageCode result = VerifyCodeUtil.drawImageVerificate();
		try {
			 OutputStream out= response.getOutputStream();
		      ImageIO.write(result.getImage(), "JPEG", out);
		      out.flush();
		      out.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 测试jr303参数校验
	 */
	@RequestMapping("/login2")
	@ResponseBody
	@AccessLimit(seconds = 10,maxCount=3,needLogin =false)
	public Result login(HttpServletRequest  request,@Valid LoginVo loginVo,UserVO userVo) {
		System.out.println("mobile:"+loginVo.getMobile());
		System.out.println("name:"+loginVo.getPassword());
		HttpSession session = request.getSession();
		session.setAttribute("userId", "12");
		return Result.success();
			
	}
	/**
	 * argumentResolver测试
	 */
	@Login
	@RequestMapping("/list")
	@ResponseBody
	public Result list(HttpServletRequest  request,@LoginUser UserVO userVo) {
		System.out.println("userVo"+userVo);
		try {
			if(null != userVo) {
				System.out.println("userVo:"+userVo.toString());
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return Result.success();
	}
	/**
	 * xss测试
	 */
	@RequestMapping("/xss")
	@ResponseBody
	public Result xss(HttpServletRequest  request,String keyword) {
		try {
			System.out.println("keyword:"+keyword);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return Result.success(keyword);
	}
	 public static void main(String[] args) {
	        Scanner sc = new Scanner(System.in);
	        int n = sc.nextInt();
	        int max = 0;
	        int temp;
	        // 仔细审题  是1-N中的最大值 不是N的最大值
	        for (int i = 2; i < n; i++) {
	            temp = i;
	            while (temp != 1) {
	                if (temp % 2 == 0) {
	                    temp /= 2;
	                } else {
	                    temp = temp * 3 + 1;
	                }
	                if (max < temp) {
	                    max = temp;
	                }
	            }
	        }
	        System.out.println(max);
	    }
}
