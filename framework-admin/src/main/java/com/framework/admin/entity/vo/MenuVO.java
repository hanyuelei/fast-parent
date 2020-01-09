package com.framework.admin.entity.vo;

import java.util.List;

import lombok.Data;

@Data
public class MenuVO {
	/**
	 * 菜单名称
	 */
	private String title;
	/**
	 * 图标
	 */
	private String icon;
	/**
	 * 对应的页面链接。（有子菜单的情况下建议不填）
	 */
	private String href;
	/**
	 * 子菜单是否展开。（默认不展开）
	 */
	private boolean spread;
	/**
	 * 控制对应页面链接的打开方式。不设置的情况下以窗口形式打开，设置后页面整体跳转，如“登录页面”。可选参数：_blank。
	 */
	private String target;
	
	List<MenuVO> children;
	
	
}
