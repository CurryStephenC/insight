package com.wjyoption.system.domain;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.wjyoption.common.annotation.Excel;
import com.wjyoption.common.core.domain.BaseEntity;
import com.wjyoption.system.vo.resp.BankCardResp;

/**
 * 用户银行信息对象 wp_bankcard
 * 
 * @author wjyoption
 * @date 2021-06-08
 */
public class WpBankcard extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    public WpBankcard(){}
    public WpBankcard(BankCardResp resp){
    	this.accntnm = resp.getAccntnm();
    	this.accntnm2 = resp.getAccntnm2();
    	this.accntno = resp.getAccntno();
    	this.address = resp.getAddress();
    	this.bankno = resp.getBankno();
    	this.branchname = resp.getBranchname();
    	this.uid = resp.getUid();
    	this.cryptocurrency = resp.getCryptocurrency();
    	this.walletaddr = resp.getWalletaddr();
    }
    
    /** 记录ID */
    private Long id;

    /** 银行名称 */
    @Excel(name = "银行名称")
    private String bankno;

    /** 持卡人姓名 */
    @Excel(name = "持卡人姓名")
    private String accntnm;

    /** 持卡人名称 */
    @Excel(name = "持卡人名称")
    private String accntnm2;

    /** 城市代码 */
    private String cityno;

    /** 地址 */
    @Excel(name = "地址")
    private String address;

    /** 用户id */
    @Excel(name = "用户id")
    private Integer uid;
    @Excel(name = "用户号码")
    private String utel;
    
    private Integer topid;

    /** 银行账号 */
    @Excel(name = "银行账号")
    private String accntno;

    /** 是否删除 */
    private Integer isdelete;

    /** 内容 */
    private String content;

    /** 号码 */
    private String phone;

    /** 身份证号码 */
    private String scard;

    /** 省份id */
    private Long provinceid;

    /** IFSC */
    @Excel(name = "IFSC")
    private String branchname;

    /** 国际电汇-银行名称 */
    private String bankname2;

    /** 国际电汇-银行地址 */
    private String bankaddr;

    /** 国际电汇-SWIFT */
    private String swift;

    /** 国际电汇-收款人名称 */
    private String username;

    /** 国际电汇-收款人账号 */
    private String useraccount;

    /** 国际电汇-收款人货币 */
    private String currency;

    /** 国际电汇-收款人地址 */
    private String useraddr;

    /** 数字货币 */
    private String cryptocurrency;

    /** 数字货币-钱包地址 */
    private String walletaddr;

    /** PayPal邮箱地址 */
    private String paypal;
    
    
    /**
     * 销售组合
     * 查询使用
     */
    private List<Integer> topids;
    /**
     * 正常用户：1、正常，2、非正常用户
     */
    private Integer normaltype;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setBankno(String bankno) 
    {
        this.bankno = bankno;
    }

    public String getBankno() 
    {
        return bankno;
    }
    public void setAccntnm(String accntnm) 
    {
        this.accntnm = accntnm;
    }

    public String getAccntnm() 
    {
        return accntnm;
    }
    public void setAccntnm2(String accntnm2) 
    {
        this.accntnm2 = accntnm2;
    }

    public String getAccntnm2() 
    {
        return accntnm2;
    }
    public void setCityno(String cityno) 
    {
        this.cityno = cityno;
    }

    public String getCityno() 
    {
        return cityno;
    }
    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
    }
    public void setUid(Integer uid) 
    {
        this.uid = uid;
    }

    public Integer getUid() 
    {
        return uid;
    }
    public void setAccntno(String accntno) 
    {
        this.accntno = accntno;
    }

    public String getAccntno() 
    {
        return accntno;
    }
    public void setIsdelete(Integer isdelete) 
    {
        this.isdelete = isdelete;
    }

    public Integer getIsdelete() 
    {
        return isdelete;
    }
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }
    public void setPhone(String phone) 
    {
        this.phone = phone;
    }

    public String getPhone() 
    {
        return phone;
    }
    public void setScard(String scard) 
    {
        this.scard = scard;
    }

    public String getScard() 
    {
        return scard;
    }
    public void setProvinceid(Long provinceid) 
    {
        this.provinceid = provinceid;
    }

    public Long getProvinceid() 
    {
        return provinceid;
    }
    public void setBranchname(String branchname) 
    {
        this.branchname = branchname;
    }

    public String getBranchname() 
    {
        return branchname;
    }
    public void setBankname2(String bankname2) 
    {
        this.bankname2 = bankname2;
    }

    public String getBankname2() 
    {
        return bankname2;
    }
    public void setBankaddr(String bankaddr) 
    {
        this.bankaddr = bankaddr;
    }

    public String getBankaddr() 
    {
        return bankaddr;
    }
    public void setSwift(String swift) 
    {
        this.swift = swift;
    }

    public String getSwift() 
    {
        return swift;
    }
    public void setUsername(String username) 
    {
        this.username = username;
    }

    public String getUsername() 
    {
        return username;
    }
    public void setUseraccount(String useraccount) 
    {
        this.useraccount = useraccount;
    }

    public String getUseraccount() 
    {
        return useraccount;
    }
    public void setCurrency(String currency) 
    {
        this.currency = currency;
    }

    public String getCurrency() 
    {
        return currency;
    }
    public void setUseraddr(String useraddr) 
    {
        this.useraddr = useraddr;
    }

    public String getUseraddr() 
    {
        return useraddr;
    }
    public void setCryptocurrency(String cryptocurrency) 
    {
        this.cryptocurrency = cryptocurrency;
    }

    public String getCryptocurrency() 
    {
        return cryptocurrency;
    }
    public void setWalletaddr(String walletaddr) 
    {
        this.walletaddr = walletaddr;
    }

    public String getWalletaddr() 
    {
        return walletaddr;
    }
    public void setPaypal(String paypal) 
    {
        this.paypal = paypal;
    }

    public String getPaypal() 
    {
        return paypal;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("bankno", getBankno())
            .append("accntnm", getAccntnm())
            .append("accntnm2", getAccntnm2())
            .append("cityno", getCityno())
            .append("address", getAddress())
            .append("uid", getUid())
            .append("accntno", getAccntno())
            .append("isdelete", getIsdelete())
            .append("content", getContent())
            .append("phone", getPhone())
            .append("scard", getScard())
            .append("provinceid", getProvinceid())
            .append("branchname", getBranchname())
            .append("bankname2", getBankname2())
            .append("bankaddr", getBankaddr())
            .append("swift", getSwift())
            .append("username", getUsername())
            .append("useraccount", getUseraccount())
            .append("currency", getCurrency())
            .append("useraddr", getUseraddr())
            .append("cryptocurrency", getCryptocurrency())
            .append("walletaddr", getWalletaddr())
            .append("paypal", getPaypal())
            .toString();
    }
	public String getUtel() {
		return utel;
	}
	public Integer getTopid() {
		return topid;
	}
	public void setUtel(String utel) {
		this.utel = utel;
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
	public Integer getNormaltype() {
		return normaltype;
	}
	public void setNormaltype(Integer normaltype) {
		this.normaltype = normaltype;
	}
}
