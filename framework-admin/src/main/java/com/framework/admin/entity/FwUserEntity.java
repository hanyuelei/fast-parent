package com.framework.admin.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.framework.common.base.entity.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * @ClassName: FwUserEntity
 * @Description: TODO(用户实体类)
 * @author hanyl
 * @date 2019年5月24日 下午1:55:58
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "fw_user")
public class FwUserEntity extends BaseEntity {

	/**  
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)  
	*/  
	private static final long serialVersionUID = 4476571078122625635L;
	/**
	 * 用户名
	 */
	@Column(name = "user_name")
	private String userName;
	/**
	 * 用户密码
	 */
	@Column(name = "pass_word")
	private String passWord;
	/**
	 * 盐
	 */
	@Column(name = "salt")
	private String salt;
	/**
	 * 邮箱
	 */
	@Column(name = "email")
	private String email;
	/**
	 * 电话
	 */
	@Column(name = "mobile")
	private String mobile;
	/**
	 * 状态 0：禁用 1：正常
	 */
	@Column(name = "status")
	private Integer status;
	/**
	 * 创建id
	 */
	@Column(name = "create_user_id")
	private Long createUserId;
	/**
	 * 昵称
	 */
	@Column(name = "nick_name")
	private String nickName;
	/**
	 * 昵称
	 */
	@Column(name = "pass")
	private String pass;
	/**
	 * 所有父id集合多个之前[1],[2],[3]隔开
	 */
	@Column(name = "parent_ids")
	private String parentIds;
	/**
	 * 性别1男2女
	 */
	@Column(name = "sex")
	private Integer sex;
	/**
	 * 头像地址
	 */
	@Column(name = "avatar")
	private String avatar;
	/**
	 * 备注
	 */
	@Column(name = "remark")
	private String remark;
	@JSONField(serialize = false)
	@Transient
	private String createName;
	
	
	@JsonIgnore //不转换json
	@JSONField(serialize = false)
	@ManyToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinTable(name="fw_role_user",joinColumns={@JoinColumn(name="user_id")},inverseJoinColumns={@JoinColumn(name="role_id")})
	private  Set<FwRoleEntity> roles;
	//1、关系维护端，负责多对多关系的绑定和解除
    //2、@JoinTable注解的name属性指定关联表的名字，joinColumns指定外键的名字，关联到关系维护端(FwUserEntity)
    //3、inverseJoinColumns指定外键的名字，要关联的关系被维护端(FwRoleEntity)
    //4、其实可以不使用@JoinTable注解，默认生成的关联表名称为主表表名+下划线+从表表名，
    //主表就是关系维护端对应的表，从表就是关系被维护端对应的表
}
