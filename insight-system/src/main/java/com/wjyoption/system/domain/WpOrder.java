package com.wjyoption.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.wjyoption.common.annotation.Excel;
import com.wjyoption.common.core.domain.BaseEntity;

import java.math.BigDecimal;
import java.util.List;

/**
 * 订单模块对象 wp_order
 * 
 * @author wjyoption
 * @date 2021-07-08
 */
public class WpOrder extends BaseEntity
{

	private static final long serialVersionUID = -3829908303896365059L;

	/** 订单ID */
    private Integer oid;

    /** 用户ID */
    private Integer uid;
    @Excel(name = "用户")
    private String utel;
    
    private Integer topid;
    @Excel(name = "销售")
    private String toputel;

    /** 产品ID */
    private Integer pid;

    /** 方向0涨 1跌， */
    @Excel(name = "方向",readConverterExp="0=涨,1=跌")
    private Integer ostyle;

    /** 建仓时间 */
    @Excel(name = "建仓时间",dateFormat="yyyy-MM-dd HH:mm:ss",isTimestamp=true)
    private Long buytime;

    /** 手数 */
    private Integer onumber;

    /** 平仓时间 */
    private Long selltime;

    /** 0交易，1平仓 */
    @Excel(name = "状态",readConverterExp="0=交易,1=平仓")
    private Integer ostaus;

    /** 入仓价 */
    @Excel(name = "入仓价")
    private BigDecimal buyprice;

    /** 平仓价 */
    @Excel(name = "平仓价")
    private BigDecimal sellprice;

    /** 点数/分钟数 */
    @Excel(name = "点数/分钟数(秒)")
    private String endprofit;

    /** 盈亏比例 */
    private Integer endloss;

    /** 总费用 */
    @Excel(name = "总费用")
    private BigDecimal fee;

    /** 1点位、2时间(s) */
    private BigDecimal eid;

    /** 订单编号 */
    private String orderno;

    /** 商品名称 */
    @Excel(name = "商品名称")
    private String ptitle;

    /** 买后余额 */
    @Excel(name = "买后余额")
    private BigDecimal commission;

    /** 盈亏 */
    @Excel(name = "盈亏")
    private BigDecimal ploss;

    /** 0,可查询，1不可查询 */
    private Integer display;

    /** null */
    private Integer isshow;

    /** 是否盈利1盈利2亏损3无效 */
//    @Excel(name = "是否盈利1盈利2亏损3无效")
    private Integer isWin;

    /** 1盈0不控利2亏损 */
    private Integer kongType;

    /** 手续费 */
//    @Excel(name = "手续费")
    private BigDecimal sxFee;

    /** 是否可控:1，可控，2，不可控 */
    private Integer canKong;
    
    /**
     * 销售组合
     * 查询使用
     */
    private List<Integer> topids;
    /**
     * 正常用户：1、正常，2、非正常用户
     */
    private Integer normaltype;

    public void setOid(Integer oid) 
    {
        this.oid = oid;
    }

    public Integer getOid() 
    {
        return oid;
    }
    public void setUid(Integer uid) 
    {
        this.uid = uid;
    }

    public Integer getUid() 
    {
        return uid;
    }
    public void setPid(Integer pid) 
    {
        this.pid = pid;
    }

    public Integer getPid() 
    {
        return pid;
    }
    public void setOstyle(Integer ostyle) 
    {
        this.ostyle = ostyle;
    }

    public Integer getOstyle() 
    {
        return ostyle;
    }
    public void setBuytime(Long buytime) 
    {
        this.buytime = buytime;
    }

    public Long getBuytime() 
    {
        return buytime;
    }
    public void setOnumber(Integer onumber) 
    {
        this.onumber = onumber;
    }

    public Integer getOnumber() 
    {
        return onumber;
    }
    public void setSelltime(Long selltime) 
    {
        this.selltime = selltime;
    }

    public Long getSelltime() 
    {
        return selltime;
    }
    public void setOstaus(Integer ostaus) 
    {
        this.ostaus = ostaus;
    }

    public Integer getOstaus() 
    {
        return ostaus;
    }
    public void setBuyprice(BigDecimal buyprice) 
    {
        this.buyprice = buyprice;
    }

    public BigDecimal getBuyprice() 
    {
        return buyprice;
    }
    public void setSellprice(BigDecimal sellprice) 
    {
        this.sellprice = sellprice;
    }

    public BigDecimal getSellprice() 
    {
        return sellprice;
    }
    public void setEndprofit(String endprofit) 
    {
        this.endprofit = endprofit;
    }

    public String getEndprofit() 
    {
        return endprofit;
    }
    public void setEndloss(Integer endloss) 
    {
        this.endloss = endloss;
    }

    public Integer getEndloss() 
    {
        return endloss;
    }
    public void setFee(BigDecimal fee) 
    {
        this.fee = fee;
    }

    public BigDecimal getFee() 
    {
        return fee;
    }
    public void setEid(BigDecimal eid) 
    {
        this.eid = eid;
    }

    public BigDecimal getEid() 
    {
        return eid;
    }
    public void setOrderno(String orderno) 
    {
        this.orderno = orderno;
    }

    public String getOrderno() 
    {
        return orderno;
    }
    public void setPtitle(String ptitle) 
    {
        this.ptitle = ptitle;
    }

    public String getPtitle() 
    {
        return ptitle;
    }
    public void setCommission(BigDecimal commission) 
    {
        this.commission = commission;
    }

    public BigDecimal getCommission() 
    {
        return commission;
    }
    public void setPloss(BigDecimal ploss) 
    {
        this.ploss = ploss;
    }

    public BigDecimal getPloss() 
    {
        return ploss;
    }
    public void setDisplay(Integer display) 
    {
        this.display = display;
    }

    public Integer getDisplay() 
    {
        return display;
    }
    public void setIsshow(Integer isshow) 
    {
        this.isshow = isshow;
    }

    public Integer getIsshow() 
    {
        return isshow;
    }
    public void setIsWin(Integer isWin) 
    {
        this.isWin = isWin;
    }

    public Integer getIsWin() 
    {
        return isWin;
    }
    public void setKongType(Integer kongType) 
    {
        this.kongType = kongType;
    }

    public Integer getKongType() 
    {
        return kongType;
    }
    public void setSxFee(BigDecimal sxFee) 
    {
        this.sxFee = sxFee;
    }

    public BigDecimal getSxFee() 
    {
        return sxFee;
    }
    public void setCanKong(Integer canKong) 
    {
        this.canKong = canKong;
    }

    public Integer getCanKong() 
    {
        return canKong;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("oid", getOid())
            .append("uid", getUid())
            .append("pid", getPid())
            .append("ostyle", getOstyle())
            .append("buytime", getBuytime())
            .append("onumber", getOnumber())
            .append("selltime", getSelltime())
            .append("ostaus", getOstaus())
            .append("buyprice", getBuyprice())
            .append("sellprice", getSellprice())
            .append("endprofit", getEndprofit())
            .append("endloss", getEndloss())
            .append("fee", getFee())
            .append("eid", getEid())
            .append("orderno", getOrderno())
            .append("ptitle", getPtitle())
            .append("commission", getCommission())
            .append("ploss", getPloss())
            .append("display", getDisplay())
            .append("isshow", getIsshow())
            .append("isWin", getIsWin())
            .append("kongType", getKongType())
            .append("sxFee", getSxFee())
            .append("canKong", getCanKong())
            .toString();
    }

	public String getUtel() {
		return utel;
	}

	public String getToputel() {
		return toputel;
	}

	public void setUtel(String utel) {
		this.utel = utel;
	}

	public void setToputel(String toputel) {
		this.toputel = toputel;
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

	public Integer getNormaltype() {
		return normaltype;
	}

	public void setTopids(List<Integer> topids) {
		this.topids = topids;
	}

	public void setNormaltype(Integer normaltype) {
		this.normaltype = normaltype;
	}
}
