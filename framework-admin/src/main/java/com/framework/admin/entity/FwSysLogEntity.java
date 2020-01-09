package com.framework.admin.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.framework.common.base.entity.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
@Data
@EqualsAndHashCode(callSuper=true)
@Entity
@Table(name="fw_sys_log")
public class FwSysLogEntity extends BaseEntity {
	/**  
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)  
	*/  
	private static final long serialVersionUID = 1L;
	/**
	 * 用户登录名
	 */
	@Column(name = "user_name")
	private String username;
	/**
	 * 操作用户id
	 */
	@Column(name = "user_id")
	private Long userId;
	/**
	 * 用户昵称
	 */
	@Column(name = "nick_name")
	private String nickName;
	/**
	 * 用户操作
	 */
	@Column(name = "operation")
	private String operation;
	/**
	 * 请求方法
	 */
	@Column(name = "method")
	private String method;
	/**
	 * 请求参数
	 */
	@Column(name = "params")
	private String params;
	/**
	 * 执行时长  （毫秒）
	 */
	@Column(name = "time")
	private Long time;
	/**
	 * ip地址
	 */
	@Column(name="ip")
	private String ip;
	/**
	 * 返回值
	 */
	@Column(name="result")
	private String result;
}
