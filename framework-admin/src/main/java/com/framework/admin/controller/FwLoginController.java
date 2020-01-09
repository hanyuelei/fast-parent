package com.framework.admin.controller;

import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.ServletWebRequest;

import com.framework.admin.entity.FwUserEntity;
import com.framework.admin.service.FwUserService;
import com.framework.admin.util.SecurityUserUtil;
import com.framework.admin.util.SysConfigRedis;
import com.framework.common.util.verifycode.ImageCode;
import com.framework.common.util.verifycode.ImageCodeUtil;

@Controller
@RequestMapping("admin")
public class FwLoginController {
	
	public final static String SALT = "1a2b3c";
	
	private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

	public static final String SESSION_KEY = "SESSION_KEY_IMAGE_CODE";
	@Autowired
	private FwUserService fwUserService;

	@GetMapping(value = "index")
	public String index(ModelMap modelMap) {
		FwUserEntity fwUserEntity = SecurityUserUtil.getUser(fwUserService);
		System.out.println("登录成功");
		modelMap.put("img_prefix", SysConfigRedis.getConfigByKey("img_prefix"));
		modelMap.put("user", fwUserEntity);
		return "admin/index";
	}

	@GetMapping(value = "main")
	public String main(ModelMap modelMap) {
		FwUserEntity fwUserEntity = SecurityUserUtil.getUser(fwUserService);
		modelMap.put("user", fwUserEntity);
		return "admin/main";
	}

	@GetMapping(value = "login")
	public String login(ModelMap modelmap) {
		modelmap.put("default_avatar",
				SysConfigRedis.getConfigByKey("img_prefix") + SysConfigRedis.getConfigByKey("default_avatar"));
		return "admin/login";
	}

	@GetMapping(value = "403")
	public String no(HttpSession sessione) {
		return "admin/403";
	}

	/**
	 * 
	 * @Title: createCode @Description: TODO(session存放验证码) @param @param
	 *         request @param @param response @param @throws IOException
	 *         设定文件 @return void 返回类型 @throws
	 */
	@GetMapping("/code/image")
	public void createCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 1.根据随机数生成图片
		ImageCode imageCode = ImageCodeUtil.createImageCode(request);
		// 2.将图片存入session中
		sessionStrategy.setAttribute(new ServletWebRequest(request), SESSION_KEY, imageCode);
		// 3.将生成的图片写入到接口响应中
		ImageIO.write(imageCode.getImage(), "JPEG", response.getOutputStream());
	}

}
