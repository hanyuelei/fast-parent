package com.framework.admin.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.framework.common.base.entity.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * 任务记录表
 *
 * @author hanyl
 * @date 2019-06-26 21:15:48
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "fw_schedule_job")
public class FwScheduleJobEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;
	/**
	 * 任务调度参数key
	 */
    public static final String JOB_PARAM_KEY = "JOB_PARAM_KEY";
    /**
	 * spring bean名称
	 */
    @Column(name = "bean_name")
	private String beanName;
    /**
     * 方法
     */
    @Column(name = "method")
    private String method;
    /**
	 *  参数
	 */
    @Column(name = "params")
	private String params;
    /**
	 * cron表达式
	 */
    @Column(name = "cron_expression")
	private String cronExpression;
    /**
	 * 任务状态  0：正常  1：暂停
	 */
    @Column(name = "status")
	private Integer status;
    /**
	 * 备注
	 */
    @Column(name = "remark")
	private String remark;

	
//	/**
//	 * 此OneToMany设置在删除主表时,子表对应外键设置为null,且数据库中对应外键配置成删除时 Set NUll
//	 */
//	@OneToMany(targetEntity=FwScheduleJobLogEntity.class,cascade={CascadeType.MERGE,CascadeType.PERSIST},fetch=FetchType.LAZY)
//	@JoinColumn(name="job_id",updatable=false)
//	@OrderBy("id")
//	private Set<FwScheduleJobLogEntity>  fwScheduleJobLogSet;


	
}