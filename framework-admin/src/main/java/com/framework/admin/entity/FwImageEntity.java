package com.framework.admin.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.framework.common.base.entity.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * 图库表
 *
 * @author hanyl
 * @date 2019-06-23 15:26:39
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "fw_image")
public class FwImageEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
    /**
	 * 本地url
	 */
    @Column(name="local_url")
	private String localUrl;
    /**
	 * 云端url
	 */
    @Column(name="cloud_url")
	private String cloudUrl;
    /**
	 * 备注
	 */
    @Column(name="remark")
	private String remark;
    /**
     * 类型  字典标识：IMAGE_STATUS
     */
    @Column(name="type")
	private String type;
	

	
}