package com.wjyoption.system.domain;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.wjyoption.common.annotation.Excel;
import com.wjyoption.common.core.domain.BaseEntity;

/**
 * 用户新手任务进度对象 wp_user_novicetask_rate
 * 
 * @author wjyoption
 * @date 2021-07-28
 */
public class WpUserNovicetaskRate extends BaseEntity
{

	private static final long serialVersionUID = 6621555941281668876L;

	/** 主键 */
    private Integer id;

    /** 任务 */
    @Excel(name = "任务")
    private Integer taskid;
    private String title;

    /** 用户 */
    @Excel(name = "用户")
    private Integer uid;
    private String utel;
    private String toputel;//销售号码

    /** 状态 */
    @Excel(name = "状态")
    private Integer status;

    /** 任务上传图片 */
    @Excel(name = "任务上传图片")
    private String picurl;

    /** 完成时间 */
    @Excel(name = "完成时间")
    private Long finishtime;
    
    private Long createtime;
    
    /**
     * 销售组合
     * 查询使用
     */
    private List<Integer> topids;
    private Integer topid;

    public void setId(Integer id) 
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
    }
    public void setTaskid(Integer taskid) 
    {
        this.taskid = taskid;
    }

    public Integer getTaskid() 
    {
        return taskid;
    }
    public void setUid(Integer uid) 
    {
        this.uid = uid;
    }

    public Integer getUid() 
    {
        return uid;
    }
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }
    public void setPicurl(String picurl) 
    {
        this.picurl = picurl;
    }

    public String getPicurl() 
    {
        return picurl;
    }
    public void setFinishtime(Long finishtime) 
    {
        this.finishtime = finishtime;
    }

    public Long getFinishtime() 
    {
        return finishtime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("taskid", getTaskid())
            .append("uid", getUid())
            .append("status", getStatus())
            .append("picurl", getPicurl())
            .append("createtime", getCreatetime())
            .append("finishtime", getFinishtime())
            .toString();
    }

	public Long getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Long createtime) {
		this.createtime = createtime;
	}

	public String getTitle() {
		return title;
	}

	public String getUtel() {
		return utel;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setUtel(String utel) {
		this.utel = utel;
	}

	public List<Integer> getTopids() {
		return topids;
	}

	public Integer getTopid() {
		return topid;
	}

	public void setTopids(List<Integer> topids) {
		this.topids = topids;
	}

	public void setTopid(Integer topid) {
		this.topid = topid;
	}

	public String getToputel() {
		return toputel;
	}

	public void setToputel(String toputel) {
		this.toputel = toputel;
	}
}
