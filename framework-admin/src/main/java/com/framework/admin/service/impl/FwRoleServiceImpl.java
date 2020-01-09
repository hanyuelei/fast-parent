package com.framework.admin.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.framework.admin.entity.FwPermissionEntity;
import com.framework.admin.entity.FwRoleEntity;
import com.framework.admin.entity.FwUserEntity;
import com.framework.admin.entity.vo.PermissionTreeVO;
import com.framework.admin.repository.FwRoleRepository;
import com.framework.admin.service.FwPermissionService;
import com.framework.admin.service.FwRoleService;
import com.framework.admin.service.FwUserService;
import com.framework.admin.util.ConvertBeanUtil;
import com.framework.admin.util.SecurityUserUtil;
import com.framework.common.base.service.impl.BaseServiceImpl;
import com.framework.common.util.Constants;
import com.framework.common.util.result.Result;
@Service
public class FwRoleServiceImpl extends BaseServiceImpl<FwRoleEntity, Long> implements FwRoleService {

	private FwRoleRepository fwRoleRepository;
	@Autowired
	public FwRoleServiceImpl(FwRoleRepository fwRoleRepository) {
		super(fwRoleRepository);
		// TODO Auto-generated constructor stub
		this.fwRoleRepository=fwRoleRepository;
	}
	@Autowired
	private FwUserService fwUserService;
	@Autowired
	private FwRoleService fwRoleService;
	@Autowired
	private FwPermissionService fwPermissionService;
	@Override
	public boolean checkRoleCode(String code, Long id) {
		// TODO Auto-generated method stub
		List<Object> paramList =new ArrayList<Object>();
		String jpql="from FwRoleEntity where roleCode = ?1";
	
		paramList.add(code);
		if(null!=id) {
			jpql+=" and id <> ?2";
			paramList.add(id);
		}
		List<FwRoleEntity>  list= fwRoleRepository.queryByJpql(jpql, paramList);
		if(list.size()>0) {
			return false;
		}
		return true;
	}
	@Override
	public List<FwRoleEntity> getRolesBycreateUserId(Long createUserId) {
		// TODO Auto-generated method stub
		return fwRoleRepository.getRolesBycreateUserId(createUserId);
	}
	@Override
	public List<FwRoleEntity> getRolesLikeParentIds(Long userId) {
		// TODO Auto-generated method stub
		List<Object> objList = new ArrayList<Object>();
		String hql = "from FwRoleEntity where  parentIds like ?1";
		 objList.add("%["+String.valueOf(userId) +"]%");
		 return fwRoleRepository.queryByJpql(hql, objList);
	}
	@Override
	public Result getList(Integer page, Integer limit, HttpServletRequest request) {
		Result r = Result.success();
		try {
			List<Object> objList = new ArrayList<Object>();
	        int paramPosition = 1;
			
			FwUserEntity  fwUserEntity =SecurityUserUtil.getUser(fwUserService);
			String jpql = "from FwRoleEntity where 1=1 ";
			//不是admin用户只能查看所创建的角色
		    if(!fwUserEntity.getUserName().equals(Constants.DEFAULT_ADMIN)) {
		    	jpql+="and parentIds like ?"+paramPosition+"";
	     	   objList.add("%["+String.valueOf(fwUserEntity.getId()) +"]%");
	            paramPosition = paramPosition + 1;
	        }
		    String roleName=request.getParameter("roleName");
		    if(StringUtils.isNotEmpty(roleName)) {
		      	jpql+="and roleName like ?"+paramPosition+"";
		     	   objList.add("%"+roleName+"%");
		            paramPosition = paramPosition + 1;
		    }
		    String roleCode=request.getParameter("roleCode");
		    if(StringUtils.isNotEmpty(roleCode)) {
		    	jpql+="and roleCode like ?"+paramPosition+"";
		    	objList.add("%"+roleCode+"%");
		    	paramPosition = paramPosition + 1;
		    }
		    jpql+="order by id desc";
			Pageable pageable = PageRequest.of(page, limit, null);
			PageImpl pageData = (PageImpl) fwRoleService.getPageByJpql(jpql, objList, pageable);
	    	List<FwRoleEntity> roleList=pageData.getContent();
	    	roleList.forEach(role ->{
	    		Long cid=role.getCreateUserId();
	    		if(null!=cid) {
	    			FwUserEntity creatUser=fwUserService.findById(cid);
	    		    role.setCreateName(creatUser!=null?creatUser.getNickName():null);
	    	
	    		}
	    		
	    	});
			r.setAny("count", pageData.getTotalElements());
			r.setAny("data", roleList);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return r;
	}
	@Override
	public Result saveOrUpdate(FwRoleEntity role,Long roleId) {
		Result r = Result.success();
		boolean boo=fwRoleService.checkRoleCode(role.getRoleCode(), roleId);
		if(!boo) {
			return Result.failure("角色编码不可重复");
		}
		if(null==roleId) {
			FwUserEntity fwUserEntity=SecurityUserUtil.getUser(fwUserService);
			role.setCreateUserId(fwUserEntity.getId());
			role.setCreateUserId(fwUserEntity.getId());
	        String parentIds=fwUserService.getParentIds(fwUserEntity.getId());
	        role.setParentIds(parentIds);
			fwRoleService.save(role);
			
//			角色自动添加给管理员
//			FwUserEntity admin=SecurityUserUtil.getAdmin(fwUserService);
//			Set<FwRoleEntity> adminRoles=admin.getRoles();
//			adminRoles.add(role);
//			admin.setRoles(adminRoles);
//			fwUserService.update(admin);
		}else {
			if(role.getRoleCode().equals(Constants.DEFAULT_ADMIN_ROLECODE)) {
				return Result.failure("不能操作管理员角色");
			}
			FwRoleEntity updateRole=fwRoleService.get(roleId);
			updateRole.setRoleName(role.getRoleName());
			updateRole.setRemark(role.getRemark());
			updateRole.setRoleCode(role.getRoleCode());
			fwRoleService.update(updateRole);
		}
		
		return r;
	}
	@Override
	public FwRoleEntity getRoleByCode(String roleCode) {
		List<Object> paramList =new ArrayList<Object>();
		String jpql="from FwRoleEntity where roleCode = ?1";
		paramList.add(roleCode);
		List<FwRoleEntity>  list= fwRoleRepository.queryByJpql(jpql, paramList);
		if(list.size()>0) {
			return list.get(0);
		}
		return null;
	}
	@Override
	public Result rolePermission(Long roleId) {
		Result r=Result.success();
//		FwRoleEntity role = fwRoleService.get(id);
		FwUserEntity fwUser=SecurityUserUtil.getUser(fwUserService);
		Set<FwRoleEntity> roles=fwUser.getRoles();
		List<PermissionTreeVO> voList=new ArrayList<PermissionTreeVO>();
		roles.forEach(role->{
			Set<FwPermissionEntity> permissions=role.getPermissions();
			permissions.forEach(per->{	
//				allPermissions.add(per);
				PermissionTreeVO permissionTreeVO=ConvertBeanUtil.converPermissionVo(per,fwPermissionService.querypsermissionByRoleId(roleId));
				voList.add(permissionTreeVO);
			});
		});
		//去重
		 List<PermissionTreeVO> distinctList = voList.stream().distinct().collect(Collectors.toList());
		 //根据id降序排列
		 List<PermissionTreeVO> sortList = distinctList.stream().sorted((a, b) ->  b.getId().intValue()-a.getId().intValue() ).collect(Collectors.toList());
		 
		 List<PermissionTreeVO> treeList =build(sortList);
		 forEach(treeList);
		 r.setAny("allPermission", treeList);
		return r;
	}
	public void  forEach( List<PermissionTreeVO> treeList) {
		 for(PermissionTreeVO onLevel:treeList) {
			 if(onLevel.getChildren().size()>0) {
				 onLevel.setChecked(false);
			 }
			 for(PermissionTreeVO twoLevel:onLevel.getChildren()) {
				 if(twoLevel.getChildren().size()>0) {
					 twoLevel.setChecked(false);
				 }
//				 for(PermissionTreeVO threeLevel:twoLevel.getChildren()) {
//					 
//				 }
			 }
		 }
	}
   /**
             * 构建 tree
    *
    * @param ts
    * @param <T>
    * @return
    */
    private   <T extends PermissionTreeVO> List<T> build(List<T> ts) {
       // 顶级节点的父id=0
       List<T> treeRoot = ts.stream().filter(tree -> 0 == tree.getParentId()).collect(Collectors.toList());
       treeRoot.forEach(root -> getTree(ts, root));
       return treeRoot;
   }

   /**
    * 递归获取无限级子节点
    *
    * @param ts
    * @param t
    * @return
    */
   private  <T extends PermissionTreeVO> T getTree(List<T> ts, T t) {
       List<T> childList = ts.stream().filter(t_ -> t.getId().equals(t_.getParentId())).collect(Collectors.toList());
       if (CollectionUtils.isNotEmpty(childList)) {
           childList.forEach(child -> t.addChild(getTree(ts, child)));
       }
       return t;
   }

	@Override
	public Result authrole(Long roleId, String pers) {
		Result r = Result.success();
		try {
			FwRoleEntity role = fwRoleService.get(roleId);
			if(role.getRoleCode().equals(Constants.DEFAULT_ADMIN_ROLECODE)) {
				return Result.failure("不能操作管理员角色");
			}
			Set<FwPermissionEntity> permissions = new HashSet<FwPermissionEntity>();
			if (StringUtils.isNotEmpty(pers)) {
				JSONArray jsonArray = JSONArray.parseArray(pers);
				for (int i = 0; i < jsonArray.size(); i++) {
					Long pid = jsonArray.getLong(i);
					FwPermissionEntity fwPermissionEntity = fwPermissionService.get(pid);
					permissions.add(fwPermissionEntity);
				}
			}
			role.setPermissions(permissions);
			fwRoleService.update(role);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			r.setAny("code", 1);
		}
		return r;
	}

}
