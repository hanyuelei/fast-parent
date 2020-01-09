package com.framework.admin.service.impl;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.framework.admin.entity.FwRoleEntity;
import com.framework.admin.entity.FwUserEntity;
import com.framework.admin.repository.FwUserRepository;
import com.framework.admin.service.FwRoleService;
import com.framework.admin.service.FwUserService;
import com.framework.admin.util.ConvertBeanUtil;
import com.framework.admin.util.PasswordEncode;
import com.framework.admin.util.SecurityUserUtil;
import com.framework.common.base.service.impl.BaseServiceImpl;
import com.framework.common.util.Constants;
import com.framework.common.util.result.Result;

@Service
public class FwUserServiceImpl extends BaseServiceImpl<FwUserEntity, Long> implements FwUserService,UserDetailsService {

	private FwUserRepository fwUserRepository;

	@Autowired
	public FwUserServiceImpl(FwUserRepository fwUserRepository) {
		super(fwUserRepository);
		// TODO Auto-generated constructor stub
		this.fwUserRepository=fwUserRepository;
	}
   @Autowired
   private FwUserService fwUserService;

   @Autowired
   private FwRoleService fwRoleService;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		FwUserEntity fwUserEntity=	fwUserRepository.findByUserNameEquals(username);
		if(fwUserEntity==null) {
			 throw new UsernameNotFoundException("用户名不存在"); 
		}
		   List<SimpleGrantedAuthority> authorities = new ArrayList<>();
	        for (FwRoleEntity role : fwUserEntity.getRoles()) {
	        	System.out.println(role.getRoleName());
	            authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
	        }
	        authorities.add(new SimpleGrantedAuthority("ROLE_aa"));
	        authorities.add(new SimpleGrantedAuthority("ROLE_bb"));
	    return new User(fwUserEntity.getUserName(), fwUserEntity.getPassWord(), authorities);
	}


	@Override
	public FwUserEntity getUserByUsername(String userName) {
		// TODO Auto-generated method stub
		return fwUserRepository.findByUserNameEquals(userName);
	}


	@Override
	public boolean checkUserName(String userName,Long id) {
		List<Object> paramList =new ArrayList<Object>();
		String jpql="from FwUserEntity where userName = ?1";
	
		paramList.add(userName);
		if(null!=id) {
			jpql+=" and id <> ?2";
			paramList.add(id);
		}
		List<FwUserEntity>  list= fwUserRepository.queryByJpql(jpql, paramList);
		if(list.size()>0) {
			return false;
		}
		return true;
	}


	@Override
	public String getParentIds(Long userId) {
		// TODO Auto-generated method stub
		FwUserEntity fwuser=fwUserRepository.getOne(userId);
		StringBuffer ids=new StringBuffer();
		ids.append("["+fwuser.getId()+"],");
		ids=getParent(fwuser.getCreateUserId(), ids);
		return ids.toString();
	}
	public StringBuffer getParent(Long parentId,StringBuffer ids) {
		if(null==parentId) {
			return ids;
		}
		FwUserEntity user=fwUserRepository.getOne(parentId);
		if(user==null||parentId==null) {
			return ids;
		}
		ids.append("["+user.getId()+"],");

		return getParent(user.getCreateUserId(),ids);
	}


	@Override
	public Result getUserList(Integer page, Integer limit, HttpServletRequest request) {
		// TODO Auto-generated method stub
		Result r = Result.success();
        try {
        	List<Object> objList = new ArrayList<Object>();
            int paramPosition = 1;
        	FwUserEntity  fwUserEntity =SecurityUserUtil.getUser(fwUserService);
            String jpql="from FwUserEntity where 1=1 ";
            //不是admin用户 只能查看所创建的
            if(!fwUserEntity.getUserName().equals(Constants.DEFAULT_ADMIN)) {
            	jpql+="and parentIds like ?"+paramPosition+"";
            	   objList.add("%["+String.valueOf(fwUserEntity.getId()) +"]%");
                   paramPosition = paramPosition + 1;
            }
            String userName=request.getParameter("userName");
            if(StringUtils.isNotEmpty(userName)) {
            	jpql+="and userName like ?"+paramPosition+"";
         	   objList.add("%"+userName+"%");
                paramPosition = paramPosition + 1;
            }
            String nickName=request.getParameter("nickName");
            if(StringUtils.isNotEmpty(nickName)) {
            	jpql+="and nickName like ?"+paramPosition+"";
         	   objList.add("%"+nickName+"%");
                paramPosition = paramPosition + 1;
            }
            String mobile=request.getParameter("mobile");
            if(StringUtils.isNotEmpty(mobile)) {
            	jpql+="and mobile like ?"+paramPosition+"";
            	objList.add("%"+mobile+"%");
            	paramPosition = paramPosition + 1;
            }
            jpql+="order by id desc";
            Pageable pageable = PageRequest.of(page, limit, null);
        	PageImpl pageData = (PageImpl)fwUserService.getPageByJpql(jpql, objList, pageable);
        	List<FwUserEntity> userList=pageData.getContent();
        	userList.forEach(user ->{
        		Long cid=user.getCreateUserId();
        		if(null!=cid) {
        			FwUserEntity  creatUser=fwUserService.findById(cid);
        			user.setCreateName(creatUser!=null?creatUser.getNickName():null);
        		}
        		
        	});
            r.setAny("count",pageData.getTotalElements());
            r.setAny("data",userList);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return r;
	}


	@Override
	public Result saveOrUpdateUser(FwUserEntity user, Long userId) {
        boolean boo=fwUserService.checkUserName(user.getUserName(), userId);
        if(!boo) {
        	return Result.failure("该用户名已经被注册了");
        }
        if(null==userId) {
            user.setPassWord(PasswordEncode.PASSWORD_ENCODE(null));
            user.setPass(Constants.DEFAULT_PASSWORD);
            Long loginUserId=SecurityUserUtil.getUser(fwUserService).getId();
            user.setCreateUserId(loginUserId);
            String ids=fwUserService.getParentIds(loginUserId);
            user.setParentIds(ids);
            fwUserService.save(user);
        }else {
        
        	FwUserEntity updateUser = fwUserService.get(userId);
        	updateUser.setUserName(user.getUserName());
        	updateUser.setEmail(user.getEmail());
        	updateUser.setNickName(user.getNickName());
        	updateUser.setMobile(user.getMobile());
        	updateUser.setStatus(user.getStatus());
        	updateUser.setRemark(user.getRemark());
        	updateUser.setSex(user.getSex());
        	updateUser.setAvatar(user.getAvatar());
            fwUserService.update(updateUser);
        }

		return Result.success();
	}


	@Override
	public Result getRoleByUserId(Long userId) {
		Result r = Result.success();
        r.setAny("code",0);
        r.setAny("msg","");
    	FwUserEntity  fwUserEntity =fwUserService.get(userId);
    	Set<FwRoleEntity> roleSet=fwUserEntity.getRoles();
    	r.setAny("userRole", ConvertBeanUtil.converAuthorizeVo(roleSet));
    	FwUserEntity loginUser =SecurityUserUtil.getUser(fwUserService);
		List<FwRoleEntity>  roleList=fwRoleService.getRolesLikeParentIds(loginUser.getId());
		Set<FwRoleEntity> loginUserRoles=loginUser.getRoles();
		loginUserRoles.forEach(role ->{
			roleList.add(role);
		});
		roleSet.forEach(role ->{
			roleList.add(role);
		});
		//去重
		 List<FwRoleEntity> distinctList = roleList.stream().distinct().collect(Collectors.toList());
		 //根据id降序排列
		 List<FwRoleEntity> sortList = distinctList.stream().sorted((a, b) ->  b.getId().intValue()-a.getId().intValue() ).collect(Collectors.toList());
		r.setAny("allRole", ConvertBeanUtil.converAuthorizeVo(sortList));
    	
		return r;
	}


	@Override
	public Result authrole(Long userid, String roles) {
		Result r = Result.success();
		try {
	    	FwUserEntity  fwUserEntity =fwUserService.get(userid);
	    	Set<FwRoleEntity> roleSet = new HashSet<FwRoleEntity>();
	    	if(StringUtils.isNotEmpty(roles)) {
	    		JSONArray jsonArray=JSONArray.parseArray(roles);
	    	  	for(int i=0;i<jsonArray.size();i++) {
		    		Long rid=jsonArray.getLong(i);
		    		FwRoleEntity  fwRoleEntity =fwRoleService.get(rid);
		    		roleSet.add(fwRoleEntity);
		    	}
	    	}
	    	fwUserEntity.setRoles(roleSet);
	    	fwUserService.update(fwUserEntity);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
	        r.setAny("code",1);
		}
		return r;
	}
	public static void main(String[] args) {
System.out.println(PasswordEncode.PASSWORD_ENCODE(null));
	}
	
	
}
