package com.framework.admin.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.framework.admin.entity.FwPermissionEntity;
import com.framework.admin.entity.FwRoleEntity;
import com.framework.admin.entity.vo.PermissionTreeVO;
import com.framework.admin.repository.FwPermissionRepository;
import com.framework.admin.service.FwPermissionService;
import com.framework.admin.service.FwRoleService;
import com.framework.common.base.service.impl.BaseServiceImpl;
import com.framework.common.util.Constants;
import com.framework.common.util.result.Result;
@Service
public class FwPermissionServiceImpl extends BaseServiceImpl<FwPermissionEntity, Long> implements FwPermissionService {

	private FwPermissionRepository fwPermissionRepository;

	public FwPermissionServiceImpl(FwPermissionRepository fwPermissionRepository) {
		super(fwPermissionRepository);
		// TODO Auto-generated constructor stub
		this.fwPermissionRepository=fwPermissionRepository;
	}

	@Autowired
	private FwPermissionService fwPermissionService;
	@Autowired
	private FwRoleService fwRoleService;
	@Override
	public List<FwPermissionEntity> queryListByParentId(Long id) {
		// TODO Auto-generated method stub
		List<Object> paramList =new ArrayList<Object>();
		String jpql="from FwPermissionEntity where parentId = ?1 order by sort desc";
		paramList.add(id);
		return fwPermissionRepository.queryByJpql(jpql, paramList);
	}

	@Override
	public List<FwPermissionEntity> queryTreePermission( Map<String, String> authMap) {
		List<FwPermissionEntity> parentList = new ArrayList<FwPermissionEntity>();
		
		List<FwPermissionEntity>  list=queryListByParentId(0L);
		if(null!=list&&list.size()>0) {
			for(FwPermissionEntity entity:list) {
				List<FwPermissionEntity> allchildList=queryListByParentId(entity.getId());
				
				List<FwPermissionEntity> childlist = new ArrayList<FwPermissionEntity>();
				for(FwPermissionEntity child:allchildList ) {
//					String url=child.getUrl();
//					List qlist=queryByParentIdAndUrl(child.getId(), child.getUrl());
					String authUrl=authMap.get( child.getUrl());
					if(null!=authUrl) {
						childlist.add(child);
					}
				}
				entity.setChildren(childlist);
				if(childlist.size()>0) {
					parentList.add(entity);
				}
			}
		}
		return parentList;
	}
//	@Override
//	public List<FwPermissionEntity> queryTreePermission() {
//		
//		List<FwPermissionEntity>  list=queryListByParentId(0L);
//		if(null!=list&&list.size()>0) {
//			for(FwPermissionEntity entity:list) {
//				List<FwPermissionEntity> allchildList=queryListByParentId(entity.getId());
//				entity.setChildren(allchildList);
//			}
//		}
//		return list;
//	}
	@Override
	public List<FwPermissionEntity> queryParentPermission() {
		// TODO Auto-generated method stub
		String jpql="from FwPermissionEntity where type=0 order by sort desc";
		return fwPermissionRepository.queryByJpql(jpql, null);
	}

	@Override
	public List<PermissionTreeVO> queryPermissionTree(Long roleId) {
		// TODO Auto-generated method stub
		Map<Long, Object>  map=querypsermissionByRoleId(roleId);
		List<PermissionTreeVO> permissionTreeVOList =new ArrayList<PermissionTreeVO>();
		//默认查询一级权限
		List<FwPermissionEntity>  list=queryParentPermission();
		for(FwPermissionEntity parent:list) {
			PermissionTreeVO permissionTreeVO=ConvertBeanUtil(parent,map);
			permissionTreeVO.setChildren(getChild(parent.getId(),map));
			permissionTreeVOList.add(permissionTreeVO);
		}
		return permissionTreeVOList;
	}
	//递归调用查询
	public List<PermissionTreeVO> getChild(Long parentId,Map<Long, Object>  map){
		List<PermissionTreeVO> permissionTreeVOList =new ArrayList<PermissionTreeVO>();
		List<FwPermissionEntity>  list=queryListByParentId(parentId);
		for(FwPermissionEntity parent:list) {
			PermissionTreeVO permissionTreeVO=ConvertBeanUtil(parent,map);
			permissionTreeVO.setChildren(getChild(parent.getId(),map));
			permissionTreeVOList.add(permissionTreeVO);
		}
		return permissionTreeVOList;
	}
	public PermissionTreeVO ConvertBeanUtil(FwPermissionEntity fwPermissionEntity,Map<Long, Object>  map) {
		PermissionTreeVO permissionTreeVO=new PermissionTreeVO();
		permissionTreeVO.setTitle(fwPermissionEntity.getName());
		permissionTreeVO.setId(fwPermissionEntity.getId());
		Object object=map.get(fwPermissionEntity.getId());
		if(null!=object) {//如果不为空证明用户拥有该权限
			
		}else {
			permissionTreeVO.setSpread(true);
		}
		return permissionTreeVO;
	}

	@Override
	public Map<Long, Object> querypsermissionByRoleId(Long roleId) {
		// TODO Auto-generated method stub
		String sql="select permission_id as pid from fw_permission_role where role_id="+roleId;
		List list=fwPermissionRepository.excuteFindBySql(sql);
		 Map<Long, Object> map = new HashMap<Long, Object>();
		if(list.size()>0) {
			for(Object obj:list) {
				map.put( Long.valueOf(String.valueOf(obj)), String.valueOf(obj));
			}
		
		}
		return map;
	}

	@Override
	public List queryPerListByRoleId(Long roleId) {
		// TODO Auto-generated method stub
		String sql="select permission_id as pid from fw_permission_role where role_id="+roleId;
		return fwPermissionRepository.excuteFindBySql(sql);
	}

	@Override
	public List<FwPermissionEntity> queryByParentIdAndUrl(Long parentId, String url) {
		// TODO Auto-generated method stub
		List<Object> paramList =new ArrayList<Object>();
		String jpql="from FwPermissionEntity where parentId = ?1 and url=?2";
		paramList.add(parentId);
		paramList.add(url);
		return fwPermissionRepository.queryByJpql(jpql, paramList);
	}

	@Override
	public List<FwPermissionEntity> queryByType(Integer type) {
		// TODO Auto-generated method stub
		List<Object> paramList =new ArrayList<Object>();
		String jpql="from FwPermissionEntity where type = ?1 order by sort desc";
		paramList.add(type);
		return fwPermissionRepository.queryByJpql(jpql, paramList);
	}

	@Override
	public boolean checkUrl(Integer type, String url,Long id) {
		// TODO Auto-generated method stub
		List<Object> paramList =new ArrayList<Object>();
		String jpql="from FwPermissionEntity where type = ?1 and url=?2";
	
		paramList.add(type);
		paramList.add(url);
		if(null!=id) {
			jpql+=" and id <> ?3";
			paramList.add(id);
		}
	
		List<FwPermissionEntity>  list= fwPermissionRepository.queryByJpql(jpql, paramList);
		if(list.size()>0) {
			return false;
		}
		return true;
	}

	@Override
	public Result saveOrUpdate(FwPermissionEntity permission, Long pid) {
		Result r = Result.success();
		try {
			if(pid==null) {
				if(permission.getParentId() == null) {
					permission.setType(0);
					permission.setParentId(0L);
				}else {
					FwPermissionEntity parent=fwPermissionService.get(permission.getParentId());
					if(null==parent) {
						 return Result.failure("父菜单不存在");
					}
					boolean boo=fwPermissionService.checkUrl(parent.getType()+1, permission.getUrl(),null);
					if(!boo) {
						 return Result.failure("权限URL不可重复"); 
					}
					permission.setParentId(parent.getId());
					permission.setType(parent.getType()+1);
				}
				fwPermissionService.save(permission);
				//目录不自动分配权限
				if (permission.getType()!=0) {
					// 权限自动分配给admin
					FwRoleEntity fwRoleEntity=fwRoleService.getRoleByCode(Constants.DEFAULT_ADMIN_ROLECODE);
					Set<FwPermissionEntity> permissions=fwRoleEntity.getPermissions();
					permissions.add(permission);
					fwRoleEntity.setPermissions(permissions);
					fwRoleService.update(fwRoleEntity);
				}
			}else {
				FwPermissionEntity fwPermissionEntity=fwPermissionService.get(pid);
				boolean boo=fwPermissionService.checkUrl(fwPermissionEntity.getType(), permission.getUrl(),pid);
				if(!boo) {
					 return Result.failure("权限URL不可重复"); 
				}
				fwPermissionEntity.setName(permission.getName());
				fwPermissionEntity.setUrl(permission.getUrl());
				fwPermissionEntity.setIcon(permission.getIcon());
				fwPermissionEntity.setSort(permission.getSort());
				fwPermissionEntity.setParentId(permission.getParentId());
				fwPermissionService.update(fwPermissionEntity);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return r;
	}

	@Override
	public List<FwPermissionEntity> findAllListBySort() {
		// TODO Auto-generated method stub
		String jpql="from FwPermissionEntity order by sort desc";
		return fwPermissionRepository.queryByJpql(jpql, null);
	}



}
