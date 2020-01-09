package com.framework.common.base.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 实体类 - 基类
 * ============================================================================
 */

@MappedSuperclass
public class BaseEntity implements Serializable {

	private static final long serialVersionUID = -6718838800112233445L;
	
	public static final String CREATE_DATE_PROPERTY_NAME = "createDate";// "创建日期"属性名称
	public static final String MODIFY_DATE_PROPERTY_NAME = "modifyDate";// "修改日期"属性名称
	public static final String ON_SAVE_METHOD_NAME = "onSave";// "保存"方法名称
	public static final String ON_UPDATE_METHOD_NAME = "onUpdate";// "更新"方法名称
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	protected Long id;// ID
	
	@Column(name = "base_create_time", updatable=false)
	protected String baseCreateTime;// 创建日期
	
	@Column(name = "base_update_time",insertable=false)
	protected String baseUpdateTime;// 修改日期
	
	@Column(name = "create_ip")
	protected String createIp;

	
	@Transient
	public void onSave() {}
	
	

	/**
	
	 * 获取id
	
	 * @return Long 
	
	 */
	public Long getId() {
		return id;
	}



	/**
	
	 * 设定:主键id
	
	 * @param id 
	 * 			主键id
	
	 */
	
	public void setId(Long id) {
		this.id = id;
	}

    public String getBaseCreateTime() {
		return baseCreateTime;
	}



	public void setBaseCreateTime(String baseCreateTime) {
		this.baseCreateTime = baseCreateTime;
	}



	public String getBaseUpdateTime() {
		return baseUpdateTime;
	}



	public void setBaseUpdateTime(String baseUpdateTime) {
		this.baseUpdateTime = baseUpdateTime;
	}


    /**
	
	 * 获取 创建记录ip
	
	 * @return String 
	
	 */
	public String getCreateIp() {
		return createIp;
	}



	/**
	
	 * 设定:创建记录ip
	
	 * @param createIp 
	 *        创建记录ip
	 */
	
	public void setCreateIp(String createIp) {
		this.createIp = createIp;
	}



	@Transient
	public void onUpdate() {}

	@Override
	public boolean equals(Object object) {
		if (object == null) {
			return false;
		}
		if (object instanceof BaseEntity) {
			BaseEntity baseEntity = (BaseEntity) object;
			if (this.getId() == null || baseEntity.getId() == null) {
				return false;
			} else {
				return (this.getId().equals(baseEntity.getId()));
			}
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return id == null ? System.identityHashCode(this) : (this.getClass().getName() + this.getId()).hashCode();
	}

}