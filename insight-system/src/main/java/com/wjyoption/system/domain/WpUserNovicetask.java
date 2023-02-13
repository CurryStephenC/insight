package com.wjyoption.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.wjyoption.common.annotation.Excel;
import com.wjyoption.common.core.domain.BaseEntity;

/**
 * 用户新手任务对象 wp_user_novicetask
 * 
 * @author wjyoption
 * @date 2021-07-28
 */
public class WpUserNovicetask extends BaseEntity
{

	private static final long serialVersionUID = -1183995479026100707L;

	/** 主键 */
    private Integer id;

    /** 标题 */
    @Excel(name = "标题")
    private String title;

    /** 状态 */
    @Excel(name = "状态（0正常 1停用）")
    private Integer status;

    /** 奖励金额 */
    @Excel(name = "奖励金额")
    private Integer reward;

    /** 顺序 */
    @Excel(name = "顺序")
    private Integer orderby;

    /** 任务类型，1普通任务，2定制任务 */
    @Excel(name = "任务类型，1普通任务，2定制任务")
    private Integer tasktype;

    /** 审核：1、不需要，2、需要 */
    @Excel(name = "审核：1、不需要，2、需要")
    private Integer audit;
    
    private Long createtime;

    public void setId(Integer id) 
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
    }
    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }
    public void setReward(Integer reward) 
    {
        this.reward = reward;
    }

    public Integer getReward() 
    {
        return reward;
    }
    public void setOrderby(Integer orderby) 
    {
        this.orderby = orderby;
    }

    public Integer getOrderby() 
    {
        return orderby;
    }
    public void setTasktype(Integer tasktype) 
    {
        this.tasktype = tasktype;
    }

    public Integer getTasktype() 
    {
        return tasktype;
    }
    public void setAudit(Integer audit) 
    {
        this.audit = audit;
    }

    public Integer getAudit() 
    {
        return audit;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("title", getTitle())
            .append("status", getStatus())
            .append("reward", getReward())
            .append("orderby", getOrderby())
            .append("tasktype", getTasktype())
            .append("audit", getAudit())
            .append("createtime", getCreatetime())
            .toString();
    }

	public Long getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Long createtime) {
		this.createtime = createtime;
	}
}
