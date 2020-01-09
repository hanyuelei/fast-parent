package com.framework.admin.entity;


import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.framework.common.base.entity.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
* @ClassName: FwRoleEntity  
* @Description: TODO(用户角色实体类)  
* @author hanyl
* @date 2019年5月24日 下午1:55:35  
*
 */
@Data
@EqualsAndHashCode(callSuper=true)
@Entity
@Table(name="fw_role")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })//转换json排除hibernateLazyInitializer 这个属性
public class FwRoleEntity extends BaseEntity {
	private static final long serialVersionUID = 1L;

	/**
	 * 角色名称
	 */
	@Column(name="role_name")
	private String roleName;
	/**
	 * 备注
	 */
	@Column(name="remark")
	private String remark;
	/**
	 * 角色编码  唯一
	 */
	@Column(name="role_code")
	private String roleCode;
	/**
	 * 创建id
	 */
	@Column(name="create_user_id")
	private Long createUserId;
	/**
	 * 所有父id集合多个之前[1],[2],[3]隔开
	 */
	@Column(name = "parent_ids")
	private String parentIds;

	@ManyToMany
	@JoinTable(name="fw_permission_role",joinColumns={@JoinColumn(name="role_id")},inverseJoinColumns={@JoinColumn(name="permission_id")})
	private Set<FwPermissionEntity> permissions;
	
	@ManyToMany(mappedBy = "roles")
	 @JsonIgnore 
	private List<FwUserEntity> fwUsers;
	@Transient
	private String createName;
}
