package com.framework.admin.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import com.framework.admin.entity.FwUserEntity;
import com.framework.admin.service.FwUserService;
import com.framework.common.util.Constants;

public class SecurityUserUtil {
	/**
	 * 
	* @Title: getUser  
	* @Description: TODO(获取登录用户名)  
	* @param @param fwUserService
	* @param @return    设定文件  
	* @return FwUserEntity    返回类型  
	* @throws
	 */
	public static FwUserEntity  getUser( FwUserService fwUserService) {
		  User SecurityUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		  return fwUserService.getUserByUsername(SecurityUser.getUsername());
	}
	/**
	 * 
	* @Title: getUser  
	* @Description: TODO(获取管理员)  
	* @param @param fwUserService
	* @param @return    设定文件  
	* @return FwUserEntity    返回类型  
	* @throws
	 */
	public static FwUserEntity  getAdmin( FwUserService fwUserService) {
		return fwUserService.getUserByUsername(Constants.DEFAULT_ADMIN);
	}
	/**
	 * 
	* @Title: getAuth  
	* @Description: TODO(获取当前用户所有权限)  
	* @param @return    设定文件  
	* @return List<String>    返回类型  
	* @throws
	 */
	public static List<String> getAuth() {
		 List<String> plist= new ArrayList<String>();
		 Map<String, String> authMap = new HashMap<String, String>();
       List<GrantedAuthority> list = (List<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
       if(list!=null){
           for(GrantedAuthority grantedAuthority:list){
               if(!StringUtils.isEmpty(grantedAuthority.getAuthority())){
               	authMap.put(grantedAuthority.getAuthority(), grantedAuthority.getAuthority());
                   plist.add(grantedAuthority.getAuthority());
               }
           }
       }
       return plist;
	}
	public static  Map<String, String> getAuthMap() {
		 Map<String, String> authMap = new HashMap<String, String>();
      List<GrantedAuthority> list = (List<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
      if(list!=null){
          for(GrantedAuthority grantedAuthority:list){
              if(!StringUtils.isEmpty(grantedAuthority.getAuthority())){
              	authMap.put(grantedAuthority.getAuthority(), grantedAuthority.getAuthority());
              }
          }
      }
      return authMap;
	}
}
