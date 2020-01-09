package com.framework.admin.entity.vo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;

import com.framework.admin.entity.FwPermissionEntity;

import lombok.Data;

@Data
public class PermissionTreeVO {

	private String title;
	
	private Long id;

	/**
	 * 点击节点弹出新窗口对应的 url。需开启 isJump 参数
	 */
	private String href;
	/**
	 * 节点是否为禁用状态。默认 false
	 */
	private boolean disabled=false;

	//节点是否初始展开，默认 false
	private boolean spread;
	//节点是否初始为选中状态（如果开启复选框的话），默认 false
	private boolean checked;
	
	
	private Long parentId; 
	
	private List<PermissionTreeVO> children =new ArrayList<PermissionTreeVO>();

	
	  public void addChild(PermissionTreeVO node) {
	        children.add(node);
	  }
}
