package com.wjyoption.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.wjyoption.common.annotation.Excel;
import com.wjyoption.common.core.domain.BaseEntity;
import java.math.BigDecimal;

/**
 * 统计记录对象 wp_stat_record
 * 
 * @author wjyoption
 * @date 2021-06-21
 */
public class WpStatRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Integer id;

    /** 当天注册人数 */
    @Excel(name = "当天注册人数")
    private Integer registnum;

    /** 当天赠送人数 */
    @Excel(name = "当天赠送人数")
    private Integer giveusernum;

    /** 当天赠送总金额 */
    @Excel(name = "当天赠送总金额")
    private BigDecimal giveprice;

    /** 新充值人数 */
    @Excel(name = "新充值人数")
    private Integer newrechargenum;

    /** 新充值金额 */
    @Excel(name = "新充值金额")
    private BigDecimal newrechargeprice;

    /** 总充值人数 */
    @Excel(name = "总充值人数")
    private Integer totalrechargenum;

    /** 总充值金额 */
    @Excel(name = "总充值金额")
    private BigDecimal totalrechargeprice;

    /** 当天提现人数 */
    @Excel(name = "当天提现人数")
    private Integer cashnum;

    /** 当天提现金额 */
    @Excel(name = "当天提现金额")
    private BigDecimal cashprice;

    /** 统计日期 */
    @Excel(name = "统计日期")
    private Integer daily;

    public void setId(Integer id) 
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
    }
    public void setRegistnum(Integer registnum) 
    {
        this.registnum = registnum;
    }

    public Integer getRegistnum() 
    {
        return registnum;
    }
    public void setGiveusernum(Integer giveusernum) 
    {
        this.giveusernum = giveusernum;
    }

    public Integer getGiveusernum() 
    {
        return giveusernum;
    }
    public void setGiveprice(BigDecimal giveprice) 
    {
        this.giveprice = giveprice;
    }

    public BigDecimal getGiveprice() 
    {
        return giveprice;
    }
    public void setNewrechargenum(Integer newrechargenum) 
    {
        this.newrechargenum = newrechargenum;
    }

    public Integer getNewrechargenum() 
    {
        return newrechargenum;
    }
    public void setNewrechargeprice(BigDecimal newrechargeprice) 
    {
        this.newrechargeprice = newrechargeprice;
    }

    public BigDecimal getNewrechargeprice() 
    {
        return newrechargeprice;
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
            .append("registnum", getRegistnum())
            .append("giveusernum", getGiveusernum())
            .append("giveprice", getGiveprice())
            .append("newrechargenum", getNewrechargenum())
            .append("newrechargeprice", getNewrechargeprice())
            .append("totalrechargenum", getTotalrechargenum())
            .append("totalrechargeprice", getTotalrechargeprice())
            .append("cashnum", getCashnum())
            .append("cashprice", getCashprice())
            .append("daily", getDaily())
            .toString();
    }
}
