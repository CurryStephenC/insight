package com.wjyoption.system.domain;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.wjyoption.common.annotation.Excel;
import com.wjyoption.common.core.domain.BaseEntity;

/**
 * 流水对象 wp_cash_flow
 * 
 * @author wjyoption
 * @date 2021-06-04
 */
public class WpCashFlow extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Integer id;

    /** 用户ID */
    private Integer uid;
    
    @Excel(name = "用户")
    private String utel;
    @Excel(name = "上级")
    private String putel;
    @Excel(name = "销售")
    private String toputel;
    private Integer topid;

    /** 类型 */
//    @Excel(name = "类型",readConverterExp="1=注册2=投资盈利,3=投资亏损,4=账户提现,5=账户充值,6=手动充值,7=购买理财,8=赎回理财本金,9=理财收益,10=一级IB用户收益,11=二级IB用户收益,"
//    		+ "12=手动赠送红包,13=言论红包,14=每日签到奖励,15=累计签到奖励,16=三级IB用户收益,17=一级首购收益,18=二级首购收益,19=三级首购收益,20=新手任务奖励,21=提前赎回扣款")
    private Integer type;
//    @Excel(name = "类型名")
    private String name;
    @Excel(name = "类型名-英文")
    private String name_en;

    /** 变动金额 */
    @Excel(name = "变动金额")
    private String money;

    /** 说明 */
    @Excel(name = "说明")
    private String content;

    /** 时间 */
    @Excel(name = "时间",dateFormat="yyyy-MM-dd HH:mm:ss",isTimestamp=true)
    private Long time;

    /** 此刻余额 */
    @Excel(name = "此刻余额")
    private String nowmoney;
    
    
    /**************查询条件***************/
    /**
     * 类型列表
     */
    private List<Integer> types;
    private Long beginTime;
    private Long endTime;
    /**
     * 销售组合
     * 查询使用
     */
    private List<Integer> topids;

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
    public void setType(Integer type) 
    {
        this.type = type;
    }

    public Integer getType() 
    {
        return type;
    }
    public void setMoney(String money) 
    {
        this.money = money;
    }

    public String getMoney() 
    {
        return money;
    }
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }
    public void setTime(Long time) 
    {
        this.time = time;
    }

    public Long getTime() 
    {
        return time;
    }
    public void setNowmoney(String nowmoney) 
    {
        this.nowmoney = nowmoney;
    }

    public String getNowmoney() 
    {
        return nowmoney;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("uid", getUid())
            .append("type", getType())
            .append("money", getMoney())
            .append("content", getContent())
            .append("time", getTime())
            .append("nowmoney", getNowmoney())
            .toString();
    }

	public String getUtel() {
		return utel;
	}

	public String getPutel() {
		return putel;
	}

	public String getToputel() {
		return toputel;
	}

	public void setUtel(String utel) {
		this.utel = utel;
	}

	public void setPutel(String putel) {
		this.putel = putel;
	}

	public void setToputel(String toputel) {
		this.toputel = toputel;
	}

	public List<Integer> getTypes() {
		return types;
	}

	public Long getBeginTime() {
		return beginTime;
	}

	public Long getEndTime() {
		return endTime;
	}

	public void setTypes(List<Integer> types) {
		this.types = types;
	}

	public void setBeginTime(Long beginTime) {
		this.beginTime = beginTime;
	}

	public void setEndTime(Long endTime) {
		this.endTime = endTime;
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

	public void setTopids(List<Integer> topids) {
		this.topids = topids;
	}

	public String getName_en() {
		return name_en;
	}

	public void setName_en(String name_en) {
		this.name_en = name_en;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
