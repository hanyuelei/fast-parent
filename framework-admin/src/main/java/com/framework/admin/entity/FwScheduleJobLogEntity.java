package com.framework.admin.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.framework.common.base.entity.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * 
 *
 * @author hanyl
 * @date 2019-06-26 21:15:48
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "fw_schedule_job_log")
public class FwScheduleJobLogEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
    /**
	 * 类名
	 */
    @Column(name="bean_name")
	private String beanName;
    /**
	 * 参数
	 */
    @Column(name="params")
	private String params;
    /**
	 * 任务状态    0：成功    1：失败
	 */
    @Column(name="status")
	private Integer status;
    /**
	 * 失败信息
	 */
    @Column(name="error")
	private String error;
    /**
	 * 执行时间 毫秒
	 */
    @Column(name="times")
	private Integer times;
    /**
     * 方法名
     */
    @Column(name="method")
    private String method;

    /**
	 * 任务记录表
	 */
	@ManyToOne(targetEntity=FwScheduleJobEntity.class,cascade=CascadeType.MERGE,fetch=FetchType.LAZY)
	@JoinColumn(name="job_id")
	@JsonIgnore
	private FwScheduleJobEntity fwScheduleJob;
//    @Column(name="job_id")
//	private Long jobId;	

	
}