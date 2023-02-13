package com.wjyoption.system.domain;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.wjyoption.common.annotation.Excel;
import com.wjyoption.common.annotation.Excel.Type;
import com.wjyoption.common.core.domain.BaseEntity;

/**
 * 收银台对象 sys_checkout_counter
 * 
 * @author hs
 * @date 2021-06-25
 */
public class SysCheckoutCounter extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 姓名 */
    @Excel(name = "姓名")
    private String username;

    /** 支付code */
    @Excel(name = "IFSC")
    private String ifsc;

    /** 银行名称 */
    @Excel(name = "银行名称")
    private String bankname;

    /** 银行卡号 */
    @Excel(name = "银行卡号")
    private String bankaccount;

    /** 状态：1 待处理，2、成功，3、失败 */
    @Excel(name = "状态",readConverterExp="1=待处理,2=成功,3=失败")
    private Integer status;

    /** 金额 */
    @Excel(name = "金额")
    private BigDecimal paymoney;

    /** 实际金额 */
    @Excel(name = "实际金额")
    private BigDecimal realmoney;

    /** 支付图片 */
    private String payurl;

    /** 订单号 */
    @Excel(name = "订单号")
    private String orderid;

    /** 交易单号 */
    @Excel(name = "交易单号")
    private String transactionid;


    /** 前台跳转地址 */
    private String returnurl;
    
    private String reqparam;
    
    @Excel(name = "用户ID")
    private Integer uid;
    
    private String utel;
    
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss", type = Type.EXPORT)
    private Date createTime;
    
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss", type = Type.EXPORT)
    private Date updateTime;
    
    private String code;

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
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }
    public void setPaymoney(BigDecimal paymoney) 
    {
        this.paymoney = paymoney;
    }

    public BigDecimal getPaymoney() 
    {
        return paymoney;
    }
    public void setRealmoney(BigDecimal realmoney) 
    {
        this.realmoney = realmoney;
    }

    public BigDecimal getRealmoney() 
    {
        return realmoney;
    }
    public void setPayurl(String payurl) 
    {
        this.payurl = payurl;
    }

    public String getPayurl() 
    {
        return payurl;
    }
    public void setOrderid(String orderid) 
    {
        this.orderid = orderid;
    }

    public String getOrderid() 
    {
        return orderid;
    }
    public void setTransactionid(String transactionid) 
    {
        this.transactionid = transactionid;
    }

    public String getTransactionid() 
    {
        return transactionid;
    }

    public void setReturnurl(String returnurl) 
    {
        this.returnurl = returnurl;
    }

    public String getReturnurl() 
    {
        return returnurl;
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
            .append("paymoney", getPaymoney())
            .append("realmoney", getRealmoney())
            .append("payurl", getPayurl())
            .append("orderid", getOrderid())
            .append("transactionid", getTransactionid())
            .append("returnurl", getReturnurl())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("updateBy", getUpdateBy())
            .toString();
    }

	public String getReqparam() {
		return reqparam;
	}

	public void setReqparam(String reqparam) {
		this.reqparam = reqparam;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getUtel() {
		return utel;
	}

	public void setUtel(String utel) {
		this.utel = utel;
	}

}
