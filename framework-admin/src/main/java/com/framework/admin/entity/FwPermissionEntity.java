package com.framework.admin.entity;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.framework.common.base.entity.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
* @ClassName: FwPermissionEntity  
* @Description: TODO(用户权限实体类)  
* @author hanyl
* @date 2019年5月24日 下午1:55:17  
*
 */
@Data
@EqualsAndHashCode(callSuper=true)
@Entity
@Table(name="fw_permission")
public class FwPermissionEntity extends BaseEntity {
	/**  
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)  
	*/  
	private static final long serialVersionUID = 2134232480208151307L;
	/**
	 * 权限名称
	 */
	@Column(name="name")
	private String name;
	/**
	 * 权限url
	 */
	@Column(name="url")
	private String url;
	/**
	 * 类型   0：目录   1：菜单   2：按钮
	 */
	@Column(name="type")
	private Integer type;
	/**
	 * 授权
	 */
	@Column(name="perms")
	private String perms;
	/**
	 * 排序
	 */
	@Column(name="sort")
	private Integer sort;
	/**
	 * 父id
	 */
	@Column(name="parent_id")
	private Long parentId;
	/**
	 * 是否显示0显示 1不显示
	 */
	@Column(name="is_show")
	private Integer show;
	/**
	 * 打开方式
	 */
	@Column(name="target")
	private String target;
	/**
	 * 菜单图标
	 */
	@Column(name="icon")
	private String icon;
	/**
	 * 创建者
	 */
	@Column(name="create_by")
	private Long createBy;


  @ManyToMany(mappedBy = "permissions")
  @JsonIgnore 
  private List<FwRoleEntity> roles;
	 
	
	@Transient
	private List<FwPermissionEntity> children= new ArrayList<>();;
	
  public void addChild(FwPermissionEntity node) {
        children.add(node);
    }

	
}
