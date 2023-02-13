package com.wjyoption.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.wjyoption.common.annotation.Excel;
import com.wjyoption.common.core.domain.BaseEntity;

import java.math.BigDecimal;
import java.util.List;

/**
 * 提现手动充值对象 wp_balance
 * 
 * @author wjyoption
 * @date 2021-06-08
 */
public class WpBalance extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Integer bpid;

    /** 收支类型 */
    @Excel(name = "收支类型",readConverterExp="0=提现,2=后台改动")
    private Integer bptype;

    /** 操作时间 */
    @Excel(name = "操作时间",dateFormat="yyyy-MM-dd HH:mm:ss",isTimestamp=true)
    private Long bptime;

    /** 收支金额 */
    @Excel(name = "收支金额")
    private BigDecimal bpprice;

    /** 实际到账金额 */
    @Excel(name = "实际到账金额")
    private BigDecimal realprice;

    /** 备注 */
    @Excel(name = "备注")
    private String remarks;

    /** 用户id */
    @Excel(name = "用户ID")
    private Integer uid;
    @Excel(name = "用户")
    private String utel;
    private BigDecimal userMoney;
    
    private Integer oid;
    @Excel(name = "上级")
    private String outel;
    
    private Integer topid;
    @Excel(name = "销售")
    private String toputel;

    /** 判断申请是否通过 */
    @Excel(name = "状态",readConverterExp="0=待处理,1=成功,2=失败")
    private Integer isverified;

    /** 审核时间 */
    @Excel(name = "审核时间",dateFormat="yyyy-MM-dd HH:mm:ss",isTimestamp=true)
    private Long cltime;

    /** 银行卡id,对应wp_bankinfo */
    private Integer bankid;

    /** 余额 */
    @Excel(name = "余额")
    private String bpbalance;

    /** 提交时间 */
    private Long btime;

    /** 手续费 */
    private String regPar;

    /** 订单编号 */
    @Excel(name = "订单编号")
    private String balanceSn;

    /** 支付方式 */
    private String payType;

    /** 1、本地银行，2、电汇，3、数字货币 */
    @Excel(name = "类型",readConverterExp="1=银行,3=数字货币")
    private String banktype;

    /** 用户备注(转移用户) */
    @Excel(name = "用户备注")
    private String userremark;

    /** 支付方单号 */
    private String thirdid;

    /** 是否推送0：待推送，1：已推送 */
    @Excel(name = "是否推送",readConverterExp="0=待推送,1=已推送")
    private Integer ispush;
    
    /**
     * bttime查询条件
     */
    private Long beginBtime;
    private Long endBtime;
    private Integer noisverified;//isverified不为此状态，如1：表示除成功之外的其他状态
    
    
    /**
     * 销售组合
     * 查询使用
     */
    private List<Integer> topids;
    /**
     * 正常用户：1、正常，2、非正常用户
     */
    private Integer normaltype;
    
    /**
     * 用户组合
     * 查询使用
     */
    private List<Integer> uids;
    

    public void setBpid(Integer bpid) 
    {
        this.bpid = bpid;
    }

    public Integer getBpid() 
    {
        return bpid;
    }
    public void setBptype(Integer bptype) 
    {
        this.bptype = bptype;
    }

    public Integer getBptype() 
    {
        return bptype;
    }
    public void setBptime(Long bptime) 
    {
        this.bptime = bptime;
    }

    public Long getBptime() 
    {
        return bptime;
    }
    public void setBpprice(BigDecimal bpprice) 
    {
        this.bpprice = bpprice;
    }

    public BigDecimal getBpprice() 
    {
        return bpprice;
    }
    public void setRealprice(BigDecimal realprice) 
    {
        this.realprice = realprice;
    }

    public BigDecimal getRealprice() 
    {
        return realprice;
    }
    public void setRemarks(String remarks) 
    {
        this.remarks = remarks;
    }

    public String getRemarks() 
    {
        return remarks;
    }
    public void setUid(Integer uid) 
    {
        this.uid = uid;
    }

    public Integer getUid() 
    {
        return uid;
    }
    public void setIsverified(Integer isverified) 
    {
        this.isverified = isverified;
    }

    public Integer getIsverified() 
    {
        return isverified;
    }
    public void setCltime(Long cltime) 
    {
        this.cltime = cltime;
    }

    public Long getCltime() 
    {
        return cltime;
    }
    public void setBankid(Integer bankid) 
    {
        this.bankid = bankid;
    }

    public Integer getBankid() 
    {
        return bankid;
    }
    public void setBpbalance(String bpbalance) 
    {
        this.bpbalance = bpbalance;
    }

    public String getBpbalance() 
    {
        return bpbalance;
    }
    public void setBtime(Long btime) 
    {
        this.btime = btime;
    }

    public Long getBtime() 
    {
        return btime;
    }
    public void setRegPar(String regPar) 
    {
        this.regPar = regPar;
    }

    public String getRegPar() 
    {
        return regPar;
    }
    public void setBalanceSn(String balanceSn) 
    {
        this.balanceSn = balanceSn;
    }

    public String getBalanceSn() 
    {
        return balanceSn;
    }
    public void setPayType(String payType) 
    {
        this.payType = payType;
    }

    public String getPayType() 
    {
        return payType;
    }
    public void setBanktype(String banktype) 
    {
        this.banktype = banktype;
    }

    public String getBanktype() 
    {
        return banktype;
    }
    public void setUserremark(String userremark) 
    {
        this.userremark = userremark;
    }

    public String getUserremark() 
    {
        return userremark;
    }
    public void setThirdid(String thirdid) 
    {
        this.thirdid = thirdid;
    }

    public String getThirdid() 
    {
        return thirdid;
    }
    public void setIspush(Integer ispush) 
    {
        this.ispush = ispush;
    }

    public Integer getIspush() 
    {
        return ispush;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("bpid", getBpid())
            .append("bptype", getBptype())
            .append("bptime", getBptime())
            .append("bpprice", getBpprice())
            .append("realprice", getRealprice())
            .append("remarks", getRemarks())
            .append("uid", getUid())
            .append("isverified", getIsverified())
            .append("cltime", getCltime())
            .append("bankid", getBankid())
            .append("bpbalance", getBpbalance())
            .append("btime", getBtime())
            .append("regPar", getRegPar())
            .append("balanceSn", getBalanceSn())
            .append("payType", getPayType())
            .append("banktype", getBanktype())
            .append("userremark", getUserremark())
            .append("thirdid", getThirdid())
            .append("ispush", getIspush())
            .toString();
    }

	public Long getBeginBtime() {
		return beginBtime;
	}

	public Long getEndBtime() {
		return endBtime;
	}

	public void setBeginBtime(Long beginBtime) {
		this.beginBtime = beginBtime;
	}

	public void setEndBtime(Long endBtime) {
		this.endBtime = endBtime;
	}

	public Integer getNoisverified() {
		return noisverified;
	}

	public void setNoisverified(Integer noisverified) {
		this.noisverified = noisverified;
	}

	public String getUtel() {
		return utel;
	}

	public void setUtel(String utel) {
		this.utel = utel;
	}

	public BigDecimal getUserMoney() {
		return userMoney;
	}

	public void setUserMoney(BigDecimal userMoney) {
		this.userMoney = userMoney;
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

	public Integer getNormaltype() {
		return normaltype;
	}

	public void setNormaltype(Integer normaltype) {
		this.normaltype = normaltype;
	}

	public Integer getOid() {
		return oid;
	}

	public String getOutel() {
		return outel;
	}

	public String getToputel() {
		return toputel;
	}

	public void setOid(Integer oid) {
		this.oid = oid;
	}

	public void setOutel(String outel) {
		this.outel = outel;
	}

	public void setToputel(String toputel) {
		this.toputel = toputel;
	}

	public List<Integer> getUids() {
		return uids;
	}

	public void setUids(List<Integer> uids) {
		this.uids = uids;
	}
}
