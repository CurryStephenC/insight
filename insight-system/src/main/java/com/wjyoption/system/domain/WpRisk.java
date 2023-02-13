package com.wjyoption.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.wjyoption.common.annotation.Excel;
import com.wjyoption.common.core.domain.BaseEntity;
import java.math.BigDecimal;

/**
 * 风控对象 wp_risk
 * 
 * @author wjyoption
 * @date 2021-07-08
 */
public class WpRisk extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Integer id;

    /** 指定客户赢利 */
    @Excel(name = "指定客户赢利")
    private String toWin;

    /** 指定客户亏损 */
    @Excel(name = "指定客户亏损")
    private String toLoss;

    /** 风控概率 */
    @Excel(name = "风控概率")
    private String chance;

    /** 最小风控值 */
    @Excel(name = "最小风控值")
    private BigDecimal minPrice;

    public void setId(Integer id) 
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
    }
    public void setToWin(String toWin) 
    {
        this.toWin = toWin;
    }

    public String getToWin() 
    {
        return toWin;
    }
    public void setToLoss(String toLoss) 
    {
        this.toLoss = toLoss;
    }

    public String getToLoss() 
    {
        return toLoss;
    }
    public void setChance(String chance) 
    {
        this.chance = chance;
    }

    public String getChance() 
    {
        return chance;
    }
    public void setMinPrice(BigDecimal minPrice) 
    {
        this.minPrice = minPrice;
    }

    public BigDecimal getMinPrice() 
    {
        return minPrice;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("toWin", getToWin())
            .append("toLoss", getToLoss())
            .append("chance", getChance())
            .append("minPrice", getMinPrice())
            .toString();
    }
}
