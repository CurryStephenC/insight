package com.wjyoption.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.wjyoption.common.annotation.Excel;
import com.wjyoption.common.core.domain.BaseEntity;

/**
 * 银行信息对象 sys_banks
 * 
 * @author hs
 * @date 2021-03-29
 */
public class SysBanks extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Integer id;

    /** 名称 */
    @Excel(name = "名称")
    private String bankName;

    /** icon */
    @Excel(name = "icon")
    private String bankIcon;

    /** 是否显示 */
    @Excel(name = "是否显示")
    private Integer isShow;

    public void setId(Integer id) 
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
    }
    public void setBankName(String bankName) 
    {
        this.bankName = bankName;
    }

    public String getBankName() 
    {
        return bankName;
    }
    public void setBankIcon(String bankIcon) 
    {
        this.bankIcon = bankIcon;
    }

    public String getBankIcon() 
    {
        return bankIcon;
    }
    public void setIsShow(Integer isShow) 
    {
        this.isShow = isShow;
    }

    public Integer getIsShow() 
    {
        return isShow;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("bankName", getBankName())
            .append("bankIcon", getBankIcon())
            .append("isShow", getIsShow())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
