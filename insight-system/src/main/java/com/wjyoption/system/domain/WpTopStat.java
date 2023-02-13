package com.wjyoption.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.wjyoption.common.annotation.Excel;
import com.wjyoption.common.core.domain.BaseEntity;

import java.math.BigDecimal;
import java.util.List;

/**
 * 销售统计对象 wp_top_stat
 * 
 * @author wjyoption
 * @date 2021-09-15
 */
public class WpTopStat extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Integer id;

    /** 销售ID */
    @Excel(name = "销售ID")
    private Integer uid;

    /** 销售号码 */
    @Excel(name = "销售号码")
    private String utel;

    /** 注册总人数 */
    @Excel(name = "注册总人数")
    private Integer registnum;

    /** 一级客户数 */
    @Excel(name = "一级客户数")
    private Integer level1num;

    /** 二级客户数 */
    @Excel(name = "二级客户数")
    private Integer level2num;

    /** 三级客户数 */
    @Excel(name = "三级客户数")
    private Integer level3num;

    /** 一级用户充值金额 */
    @Excel(name = "一级用户充值金额")
    private BigDecimal level1rechargeprice;

    /** 二级用户充值金额 */
    @Excel(name = "二级用户充值金额")
    private BigDecimal level2rechargeprice;

    /** 三级用户充值金额 */
    @Excel(name = "三级用户充值金额")
    private BigDecimal level3rechargeprice;

    /** 一级用户提现金额 */
    @Excel(name = "一级用户提现金额")
    private BigDecimal level1cashprice;

    /** 二级用户提现金额 */
    @Excel(name = "二级用户提现金额")
    private BigDecimal level2cashprice;

    /** 三级用户提现金额 */
    @Excel(name = "三级用户提现金额")
    private BigDecimal level3cashprice;

    /** 充值人数 */
    @Excel(name = "充值人数")
    private Integer totalrechargenum;

    /** 充值金额 */
    @Excel(name = "充值金额")
    private BigDecimal totalrechargeprice;

    /** 当天提现人数 */
    @Excel(name = "当天提现人数")
    private Integer cashnum;

    /** 当天提现金额 */
    @Excel(name = "当天提现金额")
    private BigDecimal cashprice;

    /** 新增IB数 */
    @Excel(name = "新增IB数")
    private Integer ibnum;

    /** 统计日期 */
    @Excel(name = "统计日期")
    private Integer daily;
    
    private List<Integer> uids;

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
    public void setUtel(String utel) 
    {
        this.utel = utel;
    }

    public String getUtel() 
    {
        return utel;
    }
    public void setRegistnum(Integer registnum) 
    {
        this.registnum = registnum;
    }

    public Integer getRegistnum() 
    {
        return registnum;
    }
    public void setLevel1num(Integer level1num) 
    {
        this.level1num = level1num;
    }

    public Integer getLevel1num() 
    {
        return level1num;
    }
    public void setLevel2num(Integer level2num) 
    {
        this.level2num = level2num;
    }

    public Integer getLevel2num() 
    {
        return level2num;
    }
    public void setLevel3num(Integer level3num) 
    {
        this.level3num = level3num;
    }

    public Integer getLevel3num() 
    {
        return level3num;
    }
    public void setLevel1rechargeprice(BigDecimal level1rechargeprice) 
    {
        this.level1rechargeprice = level1rechargeprice;
    }

    public BigDecimal getLevel1rechargeprice() 
    {
        return level1rechargeprice;
    }
    public void setLevel2rechargeprice(BigDecimal level2rechargeprice) 
    {
        this.level2rechargeprice = level2rechargeprice;
    }

    public BigDecimal getLevel2rechargeprice() 
    {
        return level2rechargeprice;
    }
    public void setLevel3rechargeprice(BigDecimal level3rechargeprice) 
    {
        this.level3rechargeprice = level3rechargeprice;
    }

    public BigDecimal getLevel3rechargeprice() 
    {
        return level3rechargeprice;
    }
    public void setLevel1cashprice(BigDecimal level1cashprice) 
    {
        this.level1cashprice = level1cashprice;
    }

    public BigDecimal getLevel1cashprice() 
    {
        return level1cashprice;
    }
    public void setLevel2cashprice(BigDecimal level2cashprice) 
    {
        this.level2cashprice = level2cashprice;
    }

    public BigDecimal getLevel2cashprice() 
    {
        return level2cashprice;
    }
    public void setLevel3cashprice(BigDecimal level3cashprice) 
    {
        this.level3cashprice = level3cashprice;
    }

    public BigDecimal getLevel3cashprice() 
    {
        return level3cashprice;
    }
    public void setTotalrechargenum(Integer totalrechargenum) 
    {
        this.totalrechargenum = totalrechargenum;
    }

    public Integer getTotalrechargenum() 
    {
        return totalrechargenum;
    }
    public void setTotalrechargeprice(BigDecimal totalrechargeprice) 
    {
        this.totalrechargeprice = totalrechargeprice;
    }

    public BigDecimal getTotalrechargeprice() 
    {
        return totalrechargeprice;
    }
    public void setCashnum(Integer cashnum) 
    {
        this.cashnum = cashnum;
    }

    public Integer getCashnum() 
    {
        return cashnum;
    }
    public void setCashprice(BigDecimal cashprice) 
    {
        this.cashprice = cashprice;
    }

    public BigDecimal getCashprice() 
    {
        return cashprice;
    }
    public void setIbnum(Integer ibnum) 
    {
        this.ibnum = ibnum;
    }

    public Integer getIbnum() 
    {
        return ibnum;
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
            .append("utel", getUtel())
            .append("registnum", getRegistnum())
            .append("level1num", getLevel1num())
            .append("level2num", getLevel2num())
            .append("level3num", getLevel3num())
            .append("level1rechargeprice", getLevel1rechargeprice())
            .append("level2rechargeprice", getLevel2rechargeprice())
            .append("level3rechargeprice", getLevel3rechargeprice())
            .append("level1cashprice", getLevel1cashprice())
            .append("level2cashprice", getLevel2cashprice())
            .append("level3cashprice", getLevel3cashprice())
            .append("totalrechargenum", getTotalrechargenum())
            .append("totalrechargeprice", getTotalrechargeprice())
            .append("cashnum", getCashnum())
            .append("cashprice", getCashprice())
            .append("ibnum", getIbnum())
            .append("daily", getDaily())
            .toString();
    }

	public List<Integer> getUids() {
		return uids;
	}

	public void setUids(List<Integer> uids) {
		this.uids = uids;
	}


}
