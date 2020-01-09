package com.framework.admin.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.framework.admin.entity.FwPermissionEntity;
import com.framework.admin.entity.FwRoleEntity;
import com.framework.admin.entity.vo.AuthorizeVO;
import com.framework.admin.entity.vo.MenuVO;
import com.framework.admin.entity.vo.PermissionTreeVO;

public class ConvertBeanUtil {

	public static List<MenuVO> converMenuVo(List<FwPermissionEntity> list) {

		if (null == list || list.size() == 0) {
			return null;
		}
		List<MenuVO> menuList = new ArrayList<MenuVO>();
		for (FwPermissionEntity fwPermissionEntity : list) {
			MenuVO menuVO = new MenuVO();
			menuVO.setHref(fwPermissionEntity.getUrl());
			menuVO.setSpread(false);
			menuVO.setTitle(fwPermissionEntity.getName());
			menuVO.setTarget(null);
			menuVO.setIcon(null != fwPermissionEntity.getIcon() ? fwPermissionEntity.getIcon() : "&#xe857;");
			List<FwPermissionEntity> childList = fwPermissionEntity.getChildren();
			List<MenuVO> menuChild = new ArrayList<MenuVO>();
			if (null != childList && childList.size() > 0) {
				for (FwPermissionEntity child : childList) {
					MenuVO childVo = new MenuVO();
					childVo.setHref(child.getUrl());
					childVo.setSpread(false);
					childVo.setTitle(child.getName());
					childVo.setTarget(null);
					childVo.setIcon(null != child.getIcon() ? child.getIcon() : "&#xe857;");
					menuChild.add(childVo);
				}
			}
			menuVO.setChildren(menuChild);
			menuList.add(menuVO);
		}
		return menuList;
	}

	public static List<AuthorizeVO> converAuthorizeVo(List<FwRoleEntity> list) {
		List<AuthorizeVO> aulist = new ArrayList<AuthorizeVO>();
		if (list.size() > 0) {
			list.forEach((r) -> {
				AuthorizeVO authorizeVO = new AuthorizeVO();
				authorizeVO.setValue(r.getId().toString());
				authorizeVO.setTitle(r.getRoleName());
				authorizeVO.setDisabled(false);
				aulist.add(authorizeVO);
			});
		}
		return aulist;

	}
	public static List<AuthorizeVO> converAuthorizeVo(Set<FwRoleEntity> list) {
		List<AuthorizeVO> aulist = new ArrayList<AuthorizeVO>();
		if (list.size() > 0) {
			list.forEach((r) -> {
				AuthorizeVO authorizeVO = new AuthorizeVO();
				authorizeVO.setValue(r.getId().toString());
				authorizeVO.setTitle(r.getRoleName());
				authorizeVO.setDisabled(false);
				aulist.add(authorizeVO);
			});
		}
		return aulist;

	}
	public static PermissionTreeVO converPermissionVo(FwPermissionEntity fwPermissionEntity,Map<Long, Object>  map) {
		PermissionTreeVO permissionTreeVO=new PermissionTreeVO();
		permissionTreeVO.setTitle(fwPermissionEntity.getName());
		permissionTreeVO.setId(fwPermissionEntity.getId());
		permissionTreeVO.setParentId(fwPermissionEntity.getParentId());
		Object object=map.get(fwPermissionEntity.getId());
		if(null!=object) {//如果不为空证明用户拥有该权限
			permissionTreeVO.setChecked(true);
		}else {
			permissionTreeVO.setSpread(true);
		}
		return permissionTreeVO;
	}
}
