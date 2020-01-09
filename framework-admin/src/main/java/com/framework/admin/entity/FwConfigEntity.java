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
@Table(name = "fw_config")
public class FwConfigEntity extends BaseEntity {
	/**  
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)  
	*/  
	private static final long serialVersionUID = 7938413770817633014L;
	/**
	 * 参数key
	 */
	@Column(name="param_key")
	private String paramKey;
	/**
	 * 参数value
	 */
	@Column(name="param_value")
	private String paramValue;
	/**
	 * 备注
	 */
	@Column(name="remark")
	private String remark;
}
