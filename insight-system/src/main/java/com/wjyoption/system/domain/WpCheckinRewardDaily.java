package com.wjyoption.system.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.wjyoption.common.annotation.Excel;
import com.wjyoption.common.core.domain.BaseEntity;

/**
 * 用户每日签到奖项对象 wp_checkin_reward_daily
 * 
 * @author wjyoption
 * @date 2021-06-11
 */
@ApiModel("每日签到奖项")
public class WpCheckinRewardDaily extends BaseEntity
{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1500916813906492428L;

	/** 配置ID */
	@ApiModelProperty(value = "主键ID",hidden=true)
    private Integer id;

    /** 理财最小金额 */
    @Excel(name = "理财最小金额")
    @ApiModelProperty(value="理财金额范围最小值")
    private Integer minmoney;

    /** 理财最大金额 */
    @Excel(name = "理财最大金额")
    @ApiModelProperty(value="理财金额范围最大值")
    private Integer maxmoney;

    /** 奖金 */
    @Excel(name = "奖金")
    @ApiModelProperty(value="初始签到奖金")
    private Integer money;

    /** 奖金2 */
    @Excel(name = "奖金2")
    @ApiModelProperty(value="奖金2(继续签到奖金)")
    private Integer money2;

    /** 连续签到次数(达到此次数之后用奖金2) */
    @Excel(name = "连续签到次数(达到此次数之后用奖金2)")
    @ApiModelProperty(value="连续签到达到此次数之后签到奖励使用奖金2字段")
    private Integer times;

    @ApiModelProperty(value="时间",hidden=true)
    private Long createtime;
    
    public void setId(Integer id) 
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
    }
    public void setMinmoney(Integer minmoney) 
    {
        this.minmoney = minmoney;
    }

    public Integer getMinmoney() 
    {
        return minmoney;
    }
    public void setMaxmoney(Integer maxmoney) 
    {
        this.maxmoney = maxmoney;
    }

    public Integer getMaxmoney() 
    {
        return maxmoney;
    }
    public void setMoney(Integer money) 
    {
        this.money = money;
    }

    public Integer getMoney() 
    {
        return money;
    }
    public void setMoney2(Integer money2) 
    {
        this.money2 = money2;
    }

    public Integer getMoney2() 
    {
        return money2;
    }
    public void setTimes(Integer times) 
    {
        this.times = times;
    }

    public Integer getTimes() 
    {
        return times;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("minmoney", getMinmoney())
            .append("maxmoney", getMaxmoney())
            .append("money", getMoney())
            .append("money2", getMoney2())
            .append("times", getTimes())
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
