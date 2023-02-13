package com.wjyoption.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.wjyoption.common.annotation.Excel;
import com.wjyoption.common.core.domain.BaseEntity;

/**
 * 用户累计签到奖励对象 wp_checkin_reward_user
 * 
 * @author wjyoption
 * @date 2021-06-12
 */
public class WpCheckinRewardUser extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 配置ID */
    private Integer id;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Integer uid;
    
    private String utel;
    private Integer topid;

    /** 奖项ID */
    @Excel(name = "奖项ID")
    private Integer refid;

    /** 奖金 */
    @Excel(name = "奖金")
    private Integer money;

    /** 状态 */
    @Excel(name = "状态")
    private Integer status;

    private Long createtime;
    
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
    public void setRefid(Integer refid) 
    {
        this.refid = refid;
    }

    public Integer getRefid() 
    {
        return refid;
    }
    public void setMoney(Integer money) 
    {
        this.money = money;
    }

    public Integer getMoney() 
    {
        return money;
    }
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("uid", getUid())
            .append("refid", getRefid())
            .append("money", getMoney())
            .append("status", getStatus())
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

	public Integer getTopid() {
		return topid;
	}

	public void setUtel(String utel) {
		this.utel = utel;
	}

	public void setTopid(Integer topid) {
		this.topid = topid;
	}
}
