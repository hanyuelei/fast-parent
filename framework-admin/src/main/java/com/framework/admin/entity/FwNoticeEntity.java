package com.framework.admin.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.framework.common.base.entity.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "fw_notice")
public class FwNoticeEntity extends BaseEntity {/**  
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)  
	*/  
	private static final long serialVersionUID = 1L;
	
	/**
	 * 标题
	 */
	@Column(name = "title")
	private String title;
	
	/**
	 * 内容
	 */
	@Column(name = "content")
	private String content;
	
	/**
	 * 时间
	 */
	@Column(name = "time")
	private String time;
	
	/**
	 * 状态 0关闭 1开启
	 */
	@Column(name = "status")
	private Integer status;
	
	

}
