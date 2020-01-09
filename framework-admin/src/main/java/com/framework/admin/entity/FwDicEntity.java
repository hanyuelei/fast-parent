package com.framework.admin.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.framework.common.base.entity.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * 系统字典表
 *
 * @author hanyl
 * @date 2019-06-28 21:39:01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "fw_dic")
public class FwDicEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
    /**
	 * 字典标识
	 */
    @Column(name="name")
	private String name;
    /**
	 * 字典标题
	 */
    @Column(name="title")
	private String title;
    /**
	 * 类型，目前只支持键值对
	 */
    @Column(name="type")
	private Integer type;
    /**
	 * 字典值
	 */
    @Column(name="value")
	private String value;
    /**
	 * 
	 */
    @Column(name="remark")
	private String remark;

	

	
}