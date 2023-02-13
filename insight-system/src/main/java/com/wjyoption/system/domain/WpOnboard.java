package com.wjyoption.system.domain;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.wjyoption.common.annotation.Excel;
import com.wjyoption.common.core.domain.BaseEntity;

/**
 * 用户签到对象 wp_onboard
 * 
 * @author wjyoption
 * @date 2021-06-11
 */
public class WpOnboard extends BaseEntity
{

	private static final long serialVersionUID = -7374278747150526546L;

	/** 主键ID */
    private Integer id;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Integer uid;
    
    private String utel;
    
    private Integer topid;

    /** 签到日期 */
    @Excel(name = "签到日期")
    private Integer daily;
    
    private Long createtime;
    
    /**
     * 开始日期
     */
    private Integer beginDaily;
    /**
     * 结束日期
     */
    private Integer endDaily;
    
    /**
     * 销售组合
     * 查询使用
     */
    private List<Integer> topids;
    /**
     * 正常用户：1、正常，2、非正常用户
     */
    private Integer normaltype;

    public void setId(Integer id) 
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
    }
    public void setUid(Integer uid) 
    {
        this.uid = uid;
    }

    public Integer getUid() 
    {
        return uid;
    }
    public void setDaily(Integer daily) 
    {
        this.daily = daily;
    }

    public Integer getDaily() 
    {
        return daily;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("uid", getUid())
            .append("daily", getDaily())
            .append("createtime", getCreatetime())
            .toString();
    }

	public Long getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Long createtime) {
		this.createtime = createtime;
	}

	public String getUtel() {
		return utel;
	}

	public void setUtel(String utel) {
		this.utel = utel;
	}

	public Integer getBeginDaily() {
		return beginDaily;
	}

	public Integer getEndDaily() {
		return endDaily;
	}

	public void setBeginDaily(Integer beginDaily) {
		this.beginDaily = beginDaily;
	}

	public void setEndDaily(Integer endDaily) {
		this.endDaily = endDaily;
	}

	public Integer getTopid() {
		return topid;
	}

	public void setTopid(Integer topid) {
		this.topid = topid;
	}

	public List<Integer> getTopids() {
		return topids;
	}

	public Integer getNormaltype() {
		return normaltype;
	}

	public void setTopids(List<Integer> topids) {
		this.topids = topids;
	}

	public void setNormaltype(Integer normaltype) {
		this.normaltype = normaltype;
	}
}
