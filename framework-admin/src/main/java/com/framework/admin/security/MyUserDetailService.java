package com.framework.admin.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.framework.admin.entity.FwPermissionEntity;
import com.framework.admin.entity.FwRoleEntity;
import com.framework.admin.entity.FwUserEntity;
import com.framework.admin.service.FwUserService;
@Service
public class MyUserDetailService implements UserDetailsService {

	   @Autowired
	    private FwUserService fwUserService;

	    @Override
	    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
	        //取得用户
	    	FwUserEntity  fwUserEntity  = fwUserService.getUserByUsername(userName);
	        if (fwUserEntity == null) {
	            throw new UsernameNotFoundException("用户不存在");
	        }
	        // 取得用户的权限
	        Collection<GrantedAuthority> grantedAuths = obtionGrantedAuthorities(fwUserEntity);
//	        Set<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>();
	        //放入角色信息，角色和权限公用grantedAuths ，这里权限信息可以不放入
	        for (FwRoleEntity role : fwUserEntity.getRoles()) {
	        	grantedAuths.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
	        }
	        boolean isLock=true;
	        if(fwUserEntity.getStatus()==0) {
	        	isLock=false;
	        }
	        // 封装成spring security的user
	        User userDetail = new User(fwUserEntity.getUserName(), fwUserEntity.getPassWord(),
	                true,//是否可用
	                true,//是否过期
	                true,//证书不过期为true
	                isLock,//账户未锁定为true ,
	                grantedAuths);
	        return userDetail;
	    }

	    // 取得用户的权限
	    private Set<GrantedAuthority> obtionGrantedAuthorities(FwUserEntity fwUserEntity) {
	        List<FwPermissionEntity> resources = new ArrayList<FwPermissionEntity>();
	        //获取用户的角色
	        Set<FwRoleEntity> roles = fwUserEntity.getRoles();
	        for (FwRoleEntity role : roles) {
	            Set<FwPermissionEntity> res = role.getPermissions();
	            for (FwPermissionEntity per : res) {
	            	if(per.getType()==2) {//按钮资源
	                    resources.add(per);
	            	}
	            }
	        }
	        Set<GrantedAuthority> authSet = new HashSet<GrantedAuthority>();
	        for (FwPermissionEntity r : resources) {
	            //用户可以访问的资源名称（或者说用户所拥有的权限）
	            authSet.add(new SimpleGrantedAuthority(r.getUrl()));
	        }
	        return authSet;
	    }

}
