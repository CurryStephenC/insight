package com.wjyoption.system.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.wjyoption.common.annotation.Excel;
import com.wjyoption.common.core.domain.BaseEntity;

/**
 * 累计签到奖项对象 wp_checkin_reward
 * 
 * @author wjyoption
 * @date 2021-06-11
 */
@ApiModel("累计签到Model")
public class WpCheckinReward extends BaseEntity
{

	private static final long serialVersionUID = 7494542933662779730L;

	/** 配置ID */
    @ApiModelProperty(value = "累计签到奖项ID")
    private Integer id;

    /** 名称 */
    @Excel(name = "名称")
    @ApiModelProperty(value = "奖励名称")
    private String name;

    /** 签到次数 */
    @Excel(name = "签到次数")
    @ApiModelProperty(value = "签到次数")
    private Integer times;

    /** 奖金 */
    @Excel(name = "奖金")
    @ApiModelProperty(value = "奖金")
    private Integer money;

    /** 排序 */
    @Excel(name = "排序")
    @ApiModelProperty(value = "排序")
    private Integer sort;
    
    @ApiModelProperty(value = "时间",hidden = true)
    private Long createtime;

    public void setId(Integer id) 
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setTimes(Integer times) 
    {
        this.times = times;
    }

    public Integer getTimes() 
    {
        return times;
    }
    public void setMoney(Integer money) 
    {
        this.money = money;
    }

    public Integer getMoney() 
    {
        return money;
    }
    public void setSort(Integer sort) 
    {
        this.sort = sort;
    }

    public Integer getSort() 
    {
        return sort;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("times", getTimes())
            .append("money", getMoney())
            .append("sort", getSort())
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
