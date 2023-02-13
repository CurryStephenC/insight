package com.wjyoption.system.domain;

import java.math.BigDecimal;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.wjyoption.common.annotation.Excel;
import com.wjyoption.common.core.domain.BaseEntity;

/**
 * 收款账号对象 sys_receive_account
 * 
 * @author hs
 * @date 2021-06-25
 */
public class SysReceiveAccount extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 姓名 */
    @Excel(name = "姓名")
    private String username;

    /** 支付code */
    @Excel(name = "支付code")
    private String ifsc;

    /** 银行名称 */
    @Excel(name = "银行名称")
    private String bankname;

    /** 银行卡号 */
    @Excel(name = "银行卡号")
    private String bankaccount;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    /** 单笔最大金额 */
    @Excel(name = "单笔最大金额")
    private BigDecimal singlemoney;
    
    private String pmid;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setUsername(String username) 
    {
        this.username = username;
    }

    public String getUsername() 
    {
        return username;
    }
    public void setIfsc(String ifsc) 
    {
        this.ifsc = ifsc;
    }

    public String getIfsc() 
    {
        return ifsc;
    }
    public void setBankname(String bankname) 
    {
        this.bankname = bankname;
    }

    public String getBankname() 
    {
        return bankname;
    }
    public void setBankaccount(String bankaccount) 
    {
        this.bankaccount = bankaccount;
    }

    public String getBankaccount() 
    {
        return bankaccount;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setSinglemoney(BigDecimal singlemoney) 
    {
        this.singlemoney = singlemoney;
    }

    public BigDecimal getSinglemoney() 
    {
        return singlemoney;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("username", getUsername())
            .append("ifsc", getIfsc())
            .append("bankname", getBankname())
            .append("bankaccount", getBankaccount())
            .append("status", getStatus())
            .append("singlemoney", getSinglemoney())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }

	public String getPmid() {
		return pmid;
	}

	public void setPmid(String pmid) {
		this.pmid = pmid;
	}
}
