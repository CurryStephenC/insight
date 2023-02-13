package com.wjyoption.system.domain;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.wjyoption.common.annotation.Excel;
import com.wjyoption.common.core.domain.BaseEntity;

/**
 * 理财记录对象 wp_financial_record
 * 
 * @author wjyoption
 * @date 2021-06-05
 */
public class WpFinancialRecord extends BaseEntity
{

	private static final long serialVersionUID = 5227253401283716121L;

	/** 主键ID */
    private Integer id;

    /** 理财产品类型 */
    private Integer typeid;
    @Excel(name = "理财产品")
    private String typename;

    /** 用户 */
    private Integer uid;
    @Excel(name = "用户")
    private String utel;
    
    private Integer topid;
    @Excel(name = "销售")
    private String toputel;

    /** 利率 */
    private BigDecimal rete;

    /** 购买金额 */
    @Excel(name = "购买金额")
    private BigDecimal buymoney;

    /** 赠送金额 */
    @Excel(name = "赠送金额")
    private BigDecimal creditmoney;

    /** 利润 */
    @Excel(name = "利润")
    private BigDecimal profit;

    /** 本金利润和 */
    private BigDecimal totalmoney;

    /** 开始计息时间 */
    @Excel(name = "开始计息时间",dateFormat="yyyy-MM-dd",isTimestamp=true)
    private Long begintime;

    /** 结束计息时间 */
    @Excel(name = "结束计息时间",dateFormat="yyyy-MM-dd",isTimestamp=true)
    private Long endtime;

    /** 可操作下一步时间 */
    private Long returntime;

    /** 冻结天数 */
    @Excel(name = "冻结天数")
    private Integer freezedate;

    /** 签到天数 */
    private Integer onboarddate;

    /** 一级计息天数 */
    private Integer datep1;

    /** 二级计息天数 */
    private Integer datep2;

    /** 状态：0、收益中，1、已赎回 */
    @Excel(name = "状态",readConverterExp="0=收益中,1=已赎回")
    private Integer status;

    /** 下一个步骤：0、到期继续，1、到期赎回 */
//    @Excel(name = "下一个步骤：0、到期继续，1、到期赎回")
    private Integer nextstep;

    /** 来源ID(到期继续时有) */
    private Integer pid;

    /** 第几个月 */
    private Integer month;

    /** 类型 */
    @Excel(name = "类型",readConverterExp="1=自然,2=添加")
    private Integer type;

    /** 手动赎回时间 */
    @Excel(name = "手动赎回时间",dateFormat="yyyy-MM-dd HH:mm:ss",isTimestamp=true)
    private Long manualtime;

    /** 是否赠送 */
    @Excel(name = "是否赠送",readConverterExp="1=是,0=否,2=否")
    private Integer isgive;

    /** 用户手动赎回：1、否，2、赎回中，3、已赎回 */
//    @Excel(name = "用户手动赎回：1、否，2、赎回中，3、已赎回")
    private Integer overself;
    
    private Long createtime;
    
    /**
     * 来自新手任务，0不是，1是
     */
    private Integer comenewtask;
    
    /**********查询条件  结束时间范围***********/
    private Long endtimeBegin;
    private Long endtimeEnd;
    
    /**
     * 销售组合
     * 查询使用
     */
    private List<Integer> topids;
    /**
     * 正常用户：1、正常，2、非正常用户
     */
    private Integer normaltype;
    
    @Excel(name = "折扣")
    private BigDecimal rebate;

    public void setId(Integer id) 
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
    }
    public void setTypeid(Integer typeid) 
    {
        this.typeid = typeid;
    }

    public Integer getTypeid() 
    {
        return typeid;
    }
    public void setUid(Integer uid) 
    {
        this.uid = uid;
    }

    public Integer getUid() 
    {
        return uid;
    }
    public void setRete(BigDecimal rete) 
    {
        this.rete = rete;
    }

    public BigDecimal getRete() 
    {
        return rete;
    }
    public void setBuymoney(BigDecimal buymoney) 
    {
        this.buymoney = buymoney;
    }

    public BigDecimal getBuymoney() 
    {
        return buymoney;
    }
    public void setCreditmoney(BigDecimal creditmoney) 
    {
        this.creditmoney = creditmoney;
    }

    public BigDecimal getCreditmoney() 
    {
        return creditmoney;
    }
    public void setProfit(BigDecimal profit) 
    {
        this.profit = profit;
    }

    public BigDecimal getProfit() 
    {
        return profit;
    }
    public void setTotalmoney(BigDecimal totalmoney) 
    {
        this.totalmoney = totalmoney;
    }

    public BigDecimal getTotalmoney() 
    {
        return totalmoney;
    }
    public void setBegintime(Long begintime) 
    {
        this.begintime = begintime;
    }

    public Long getBegintime() 
    {
        return begintime;
    }
    public void setEndtime(Long endtime) 
    {
        this.endtime = endtime;
    }

    public Long getEndtime() 
    {
        return endtime;
    }
    public void setReturntime(Long returntime) 
    {
        this.returntime = returntime;
    }

    public Long getReturntime() 
    {
        return returntime;
    }
    public void setFreezedate(Integer freezedate) 
    {
        this.freezedate = freezedate;
    }

    public Integer getFreezedate() 
    {
        return freezedate;
    }
    public void setOnboarddate(Integer onboarddate) 
    {
        this.onboarddate = onboarddate;
    }

    public Integer getOnboarddate() 
    {
        return onboarddate;
    }
    public void setDatep1(Integer datep1) 
    {
        this.datep1 = datep1;
    }

    public Integer getDatep1() 
    {
        return datep1;
    }
    public void setDatep2(Integer datep2) 
    {
        this.datep2 = datep2;
    }

    public Integer getDatep2() 
    {
        return datep2;
    }
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }
    public void setNextstep(Integer nextstep) 
    {
        this.nextstep = nextstep;
    }

    public Integer getNextstep() 
    {
        return nextstep;
    }
    public void setPid(Integer pid) 
    {
        this.pid = pid;
    }

    public Integer getPid() 
    {
        return pid;
    }
    public void setMonth(Integer month) 
    {
        this.month = month;
    }

    public Integer getMonth() 
    {
        return month;
    }
    public void setType(Integer type) 
    {
        this.type = type;
    }

    public Integer getType() 
    {
        return type;
    }
    public void setManualtime(Long manualtime) 
    {
        this.manualtime = manualtime;
    }

    public Long getManualtime() 
    {
        return manualtime;
    }
    public void setIsgive(Integer isgive) 
    {
        this.isgive = isgive;
    }

    public Integer getIsgive() 
    {
        return isgive;
    }
    public void setOverself(Integer overself) 
    {
        this.overself = overself;
    }

    public Integer getOverself() 
    {
        return overself;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("typeid", getTypeid())
            .append("uid", getUid())
            .append("rete", getRete())
            .append("buymoney", getBuymoney())
            .append("creditmoney", getCreditmoney())
            .append("profit", getProfit())
            .append("totalmoney", getTotalmoney())
            .append("begintime", getBegintime())
            .append("endtime", getEndtime())
            .append("returntime", getReturntime())
            .append("freezedate", getFreezedate())
            .append("onboarddate", getOnboarddate())
            .append("datep1", getDatep1())
            .append("datep2", getDatep2())
            .append("status", getStatus())
            .append("nextstep", getNextstep())
            .append("pid", getPid())
            .append("month", getMonth())
            .append("createtime", getCreatetime())
            .append("type", getType())
            .append("manualtime", getManualtime())
            .append("isgive", getIsgive())
            .append("overself", getOverself())
            .toString();
    }

	public Long getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Long createtime) {
		this.createtime = createtime;
	}

	public String getTypename() {
		return typename;
	}

	public String getUtel() {
		return utel;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

	public void setUtel(String utel) {
		this.utel = utel;
	}

	public Integer getTopid() {
		return topid;
	}

	public void setTopid(Integer topid) {
		this.topid = topid;
	}

	public Long getEndtimeBegin() {
		return endtimeBegin;
	}

	public Long getEndtimeEnd() {
		return endtimeEnd;
	}

	public void setEndtimeBegin(Long endtimeBegin) {
		this.endtimeBegin = endtimeBegin;
	}

	public void setEndtimeEnd(Long endtimeEnd) {
		this.endtimeEnd = endtimeEnd;
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

	public String getToputel() {
		return toputel;
	}

	public void setToputel(String toputel) {
		this.toputel = toputel;
	}

	public Integer getComenewtask() {
		return comenewtask;
	}

	public void setComenewtask(Integer comenewtask) {
		this.comenewtask = comenewtask;
	}

	public BigDecimal getRebate() {
		return rebate;
	}

	public void setRebate(BigDecimal rebate) {
		this.rebate = rebate;
	}
}
