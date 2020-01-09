package com.framework.admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.framework.admin.annotation.SysLog;
import com.framework.admin.entity.FwUserEntity;
import com.framework.admin.service.FwUserService;
import com.framework.admin.util.PasswordEncode;
import com.framework.admin.util.SecurityUserUtil;
import com.framework.admin.util.SysConfigRedis;
import com.framework.common.util.Constants;
import com.framework.common.util.result.Result;

/**
 * 
 * @ClassName: UserController
 * @Description: TODO(用户控制类)
 * @author hanyl
 * @date 2019年5月27日 下午9:38:55
 *
 */
@Controller
@RequestMapping("admin/system/user")
public class FwUserController {
	@Autowired
	private FwUserService fwUserService;

	/**
	 * 用户列表跳转
	 */
	@GetMapping(value = "list")
	public String list(HttpSession sessione) {
		return "admin/user/userList";
	}

	/**
	 * 
	 * @Title: list @Description: TODO(用户列表) @param @param page @param @param
	 * limit @param @param request @param @return 设定文件 @return ResponseEntity
	 * 返回类型 @throws
	 */
	@PostMapping(value = "list")
	@ResponseBody
	public Result list(@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "limit", defaultValue = "10") Integer limit, HttpServletRequest request) {
		return fwUserService.getUserList(page, limit, request);

	}

	@GetMapping(value = "add")
	public String add(HttpSession sessione) {
		return "admin/user/userAdd";
	}
	
	@GetMapping(value = "userinfo")
	public String userinfo(ModelMap modelmap) {
		FwUserEntity fwUserEntity = SecurityUserUtil.getUser(fwUserService);
		modelmap.put("user", fwUserEntity);
		return "admin/user/userInfo";
	}
	@SysLog("用户添加")
	@PostMapping(value = "add")
	@ResponseBody
	public Result save(FwUserEntity user) {
		user.setAvatar(SysConfigRedis.getConfigByKey("default_avatar"));
		return fwUserService.saveOrUpdateUser(user, null);

	}

//	@PreAuthorize("hasAuthority('/admin/system/user/edit')")
	@GetMapping(value = "edit")
	public String edit(ModelMap modelmap, Long id) {
		FwUserEntity fwUserEntity = fwUserService.get(id);
		modelmap.put("user", fwUserEntity);
		return "admin/user/userEdit";
	}

	/**
	 * 
	 * @Title: edit @Description: TODO(用户修改) @param @param sessione @param @param
	 * id @param @param userName @param @param userEmail @param @param
	 * mobile @param @param status @param @param request @param @return 设定文件 @return
	 * ResponseEntity 返回类型 @throws
	 */
	@SysLog("修改用户")
	@PostMapping(value = "edit")
	@ResponseBody 
	public Result edit(@RequestParam(value = "uid") Long uid, FwUserEntity user) {
		return fwUserService.saveOrUpdateUser(user, uid);

	}
	@SysLog("修改用户")
	@PostMapping(value = "editinfo")
	@ResponseBody 
	public Result editinfo( FwUserEntity user) {
		FwUserEntity fwUserEntity = SecurityUserUtil.getUser(fwUserService);
		return fwUserService.saveOrUpdateUser(user, fwUserEntity.getId());

	}
	
	/**
	 * 
	 * @Title: del @Description: TODO(用户删除) @param @param sessione @param @param
	 * id @param @return 设定文件 @return ResponseEntity 返回类型 @throws
	 */
	@SysLog("删除用户")
	@GetMapping(value = "del")
	@ResponseBody
	public Result del( @RequestParam(value = "id") Long id) {
		FwUserEntity user = fwUserService.get(id);
		if(user.getUserName().equals(Constants.DEFAULT_ADMIN)) {
			return Result.failure("不能删除管理员账户");
		}
		fwUserService.delete(user);
		return Result.success();

	}

	/**
	 * 
	 * @Title: authorize @Description: TODO(分配角色) @param @param
	 * modelmap @param @param id @param @return 设定文件 @return String 返回类型 @throws
	 */
	@GetMapping(value = "authorize")
	public String authorize(ModelMap modelmap, @RequestParam Long id) {
		FwUserEntity fwUserEntity = fwUserService.get(id);
		modelmap.put("user", fwUserEntity);
		return "admin/user/authorize";
	}

	/**
	 * 
	 * @Title: role @Description: TODO(获取已分配和未分配的权限) @param @param
	 * sessione @param @param userid @param @return 设定文件 @return ResponseEntity
	 * 返回类型 @throws
	 */
	@PostMapping(value = "role")
	@ResponseBody
	public Result role(@RequestParam Long userid) {

		return fwUserService.getRoleByUserId(userid);
	}

	/**
	 * 
	 * @Title: authrole @Description: TODO(授权保存) @param @param userid
	 * 用户id @param @param roles 用户权限 @param @return 设定文件 @return ResponseEntity
	 * 返回类型 @throws
	 */
	@PostMapping(value = "authrole")
	@ResponseBody
	@SysLog("用户分配角色")
	public Result authrole(@RequestParam Long userid, String roles) {

		return fwUserService.authrole(userid, roles);
	}

	/**
	 * 
	 * @Title: checkpwd @Description: TODO(校验密码是否正确) @param @param
	 * password @param @param request @param @return 设定文件 @return ResponseEntity
	 * 返回类型 @throws
	 */
	@PostMapping(value = "checkpwd")
	@ResponseBody
	public Result checkpwd(@RequestParam(value = "password") String password, HttpServletRequest request) {
		Result r = Result.success();
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(4);
		FwUserEntity fwUserEntity = SecurityUserUtil.getUser(fwUserService);
		boolean boo = passwordEncoder.matches(password, fwUserEntity.getPassWord());
		r.setAny("code", boo);
		return r;

	}
	@GetMapping(value = "changePwd")
	public String changePwd(ModelMap modelmap) {
		FwUserEntity fwUserEntity = SecurityUserUtil.getUser(fwUserService);
		modelmap.put("user", fwUserEntity);
		return "admin/user/changePwd";
	}
	
	/**
	 * 
	* @Title: changePwd  
	* @Description: TODO(用户更改密码)  
	* @param @param response
	* @param @param oldPwd
	* @param @param newPwd
	* @param @return    设定文件  
	* @return ResponseEntity    返回类型  
	* @throws
	 */
	@SysLog("修改密码")
	@PostMapping(value = "changePwd")
	@ResponseBody
	public Result changePwd(@RequestParam String oldPwd,
			@RequestParam String newPwd) {
		Result r = Result.success();
		FwUserEntity fwUserEntity = SecurityUserUtil.getUser(fwUserService);
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(4);
		boolean boo = passwordEncoder.matches(oldPwd, fwUserEntity.getPassWord());
		if (boo) {
			fwUserEntity.setPass(newPwd);
			fwUserEntity.setPassWord(PasswordEncode.PASSWORD_ENCODE(newPwd));
		} else {
			r.setAny("code", 1);
			r.setAny("msg", "原始密码错误");
		}
		fwUserService.update(fwUserEntity);
		return r;

	}

}
