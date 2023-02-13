package com.wjyoption.system.domain;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.wjyoption.common.annotation.Excel;
import com.wjyoption.common.core.domain.BaseEntity;

/**
 * 前端用户对象 wp_userinfo
 * 
 * @author hs
 * @date 2021-06-03
 */
public class WpUserinfo extends BaseEntity
{

	private static final long serialVersionUID = 3810952688081913149L;

	/** 用户ID */
    @Excel(name = "用户ID")
    private Integer uid;

    /** 用户名称 */
    private String username;

    /** 用户密码 */
    private String upwd;

    /** 电话号码 */
    @Excel(name = "客户手机")
    private String utel;

    /** 注册时间 */
    @Excel(name = "注册时间",dateFormat = "yyyy-MM-dd HH:mm:ss",isTimestamp=true)
    private Long utime;

    /** 0普通客户，1申请经纪人中，2经纪人 */
    private Long agenttype;

    /** 用户类型 */
    @Excel(name = "用户类型",readConverterExp="0=客户,101=销售")
    private Integer otype;

    /** 状态 */
    @Excel(name = "状态",readConverterExp="0=正常,1=冻结")
    private Integer ustatus;

    /** 正常用户 */
    @Excel(name = "正常用户",readConverterExp="1=正常,2=非正常")
    private Integer normaltype;

    /** 上线字段 */
    private Integer oid;

    /** 地址 */
    private String address;

    /** 头像 */
    private String portrait;

    /** 最后登录时间 */
    @Excel(name = "最后登录时间",dateFormat = "yyyy-MM-dd HH:mm:ss",isTimestamp=true)
    private Long lastlog;

    /** 上线用户名 */
    private String managername;

    /** 公司名称 */
    private String comname;

    /** 公司资质 */
    private String comqua;

    /** 返点 */
    private String rebate;

    /** 手续费返点 */
    private String feerebate;

    /** 0不是微信用户。1是微信用户 */
    private Long usertype;

    /** 1表示微信还没注册，0微信已注册成会员。 */
    private Long wxtype;

    /** 存微信用户的id */
    private String openid;

    /** 用户昵称 */
    private String nickname;

    /** 登录时间 */
    private Long logintime;

    /** 用户余额 */
    @Excel(name = "用户余额")
    private BigDecimal usermoney;

    /** 积分 */
    private Long userpoint;

    /** null */
    private Double minprice;

    /** 用户真实姓名 */
    private String realname;

    /** 邮箱 */
    @Excel(name = "邮箱")
    private String email;

    /** 身份证 */
    private String idcard;

    /** 城市名 */
    private String city;

    /** 街道 */
    private String street;

    /** 房屋编号 */
    private String number;

    /** vip等级表id */
    private Integer vipid;

    /** 是否为顶级 */
    private Integer istop;

    /** 保证金 */
    private Integer deposit;

    /** 顶级代理商 */
    private Integer topid;

    /** ib状态 */
    @Excel(name = "IB身份",readConverterExp="0=非IB,1=IB")
    private Integer ibstatus;
    
    /**
     * 首次成为IB时间
     */
    private Long ibtime;

    /** 用户头像 */
    private String userphoto;
    
    /**提现密码*/
    private String withdrawpsd;
    
    /**
     * 新手任务：0未完成，1完成
     */
    private Integer newtask;
    
    /**
     * 来自新手任务，0不是，1是
     */
    private Integer comenewtask;
    
    /**
     * 最大下单金额
     */
    @Excel(name = "最大下单金额")
    private BigDecimal ordermaxprice;
    
    @Excel(name = "上级")
    private String outel;
    @Excel(name = "销售")
    private String toputel;
    /***红包金额***/
    private BigDecimal redmoney;
    
    /**
     * 销售组合
     * 查询使用
     */
    private List<Integer> topids;
    
    /**
     * 上级组合
     */
    private List<Integer> oids;

    public void setUid(Integer uid) 
    {
        this.uid = uid;
    }

    public Integer getUid() 
    {
        return uid;
    }
    public void setUsername(String username) 
    {
        this.username = username;
    }

    public String getUsername() 
    {
        return username;
    }
    public void setUpwd(String upwd) 
    {
        this.upwd = upwd;
    }

    public String getUpwd() 
    {
        return upwd;
    }
    public void setUtel(String utel) 
    {
        this.utel = utel;
    }

    public String getUtel() 
    {
        return utel;
    }
    public void setUtime(Long utime) 
    {
        this.utime = utime;
    }

    public Long getUtime() 
    {
        return utime;
    }
    public void setAgenttype(Long agenttype) 
    {
        this.agenttype = agenttype;
    }

    public Long getAgenttype() 
    {
        return agenttype;
    }
    public void setOtype(Integer otype) 
    {
        this.otype = otype;
    }

    public Integer getOtype() 
    {
        return otype;
    }
    public void setUstatus(Integer ustatus) 
    {
        this.ustatus = ustatus;
    }

    public Integer getUstatus() 
    {
        return ustatus;
    }
    public void setNormaltype(Integer normaltype) 
    {
        this.normaltype = normaltype;
    }

    public Integer getNormaltype() 
    {
        return normaltype;
    }
    public void setOid(Integer oid) 
    {
        this.oid = oid;
    }

    public Integer getOid() 
    {
        return oid;
    }
    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
    }
    public void setPortrait(String portrait) 
    {
        this.portrait = portrait;
    }

    public String getPortrait() 
    {
        return portrait;
    }
    public void setLastlog(Long lastlog) 
    {
        this.lastlog = lastlog;
    }

    public Long getLastlog() 
    {
        return lastlog;
    }
    public void setManagername(String managername) 
    {
        this.managername = managername;
    }

    public String getManagername() 
    {
        return managername;
    }
    public void setComname(String comname) 
    {
        this.comname = comname;
    }

    public String getComname() 
    {
        return comname;
    }
    public void setComqua(String comqua) 
    {
        this.comqua = comqua;
    }

    public String getComqua() 
    {
        return comqua;
    }
    public void setRebate(String rebate) 
    {
        this.rebate = rebate;
    }

    public String getRebate() 
    {
        return rebate;
    }
    public void setFeerebate(String feerebate) 
    {
        this.feerebate = feerebate;
    }

    public String getFeerebate() 
    {
        return feerebate;
    }
    public void setUsertype(Long usertype) 
    {
        this.usertype = usertype;
    }

    public Long getUsertype() 
    {
        return usertype;
    }
    public void setWxtype(Long wxtype) 
    {
        this.wxtype = wxtype;
    }

    public Long getWxtype() 
    {
        return wxtype;
    }
    public void setOpenid(String openid) 
    {
        this.openid = openid;
    }

    public String getOpenid() 
    {
        return openid;
    }
    public void setNickname(String nickname) 
    {
        this.nickname = nickname;
    }

    public String getNickname() 
    {
        return nickname;
    }
    public void setLogintime(Long logintime) 
    {
        this.logintime = logintime;
    }

    public Long getLogintime() 
    {
        return logintime;
    }
    public void setUsermoney(BigDecimal usermoney) 
    {
        this.usermoney = usermoney;
    }

    public BigDecimal getUsermoney() 
    {
        return usermoney;
    }
    public void setUserpoint(Long userpoint) 
    {
        this.userpoint = userpoint;
    }

    public Long getUserpoint() 
    {
        return userpoint;
    }
    public void setMinprice(Double minprice) 
    {
        this.minprice = minprice;
    }

    public Double getMinprice() 
    {
        return minprice;
    }
    public void setRealname(String realname) 
    {
        this.realname = realname;
    }

    public String getRealname() 
    {
        return realname;
    }
    public void setEmail(String email) 
    {
        this.email = email;
    }

    public String getEmail() 
    {
        return email;
    }
    public void setIdcard(String idcard) 
    {
        this.idcard = idcard;
    }

    public String getIdcard() 
    {
        return idcard;
    }
    public void setCity(String city) 
    {
        this.city = city;
    }

    public String getCity() 
    {
        return city;
    }
    public void setStreet(String street) 
    {
        this.street = street;
    }

    public String getStreet() 
    {
        return street;
    }
    public void setNumber(String number) 
    {
        this.number = number;
    }

    public String getNumber() 
    {
        return number;
    }
    public void setVipid(Integer vipid) 
    {
        this.vipid = vipid;
    }

    public Integer getVipid() 
    {
        return vipid;
    }
    public void setIstop(Integer istop) 
    {
        this.istop = istop;
    }

    public Integer getIstop() 
    {
        return istop;
    }
    public void setDeposit(Integer deposit) 
    {
        this.deposit = deposit;
    }

    public Integer getDeposit() 
    {
        return deposit;
    }
    public void setTopid(Integer topid) 
    {
        this.topid = topid;
    }

    public Integer getTopid() 
    {
        return topid;
    }
    public void setIbstatus(Integer ibstatus) 
    {
        this.ibstatus = ibstatus;
    }

    public Integer getIbstatus() 
    {
        return ibstatus;
    }
    public void setUserphoto(String userphoto) 
    {
        this.userphoto = userphoto;
    }

    public String getUserphoto() 
    {
        return userphoto;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("uid", getUid())
            .append("username", getUsername())
            .append("upwd", getUpwd())
            .append("utel", getUtel())
            .append("utime", getUtime())
            .append("agenttype", getAgenttype())
            .append("otype", getOtype())
            .append("ustatus", getUstatus())
            .append("normaltype", getNormaltype())
            .append("oid", getOid())
            .append("address", getAddress())
            .append("portrait", getPortrait())
            .append("lastlog", getLastlog())
            .append("managername", getManagername())
            .append("comname", getComname())
            .append("comqua", getComqua())
            .append("rebate", getRebate())
            .append("feerebate", getFeerebate())
            .append("usertype", getUsertype())
            .append("wxtype", getWxtype())
            .append("openid", getOpenid())
            .append("nickname", getNickname())
            .append("logintime", getLogintime())
            .append("usermoney", getUsermoney())
            .append("userpoint", getUserpoint())
            .append("minprice", getMinprice())
            .append("realname", getRealname())
            .append("email", getEmail())
            .append("idcard", getIdcard())
            .append("city", getCity())
            .append("street", getStreet())
            .append("number", getNumber())
            .append("vipid", getVipid())
            .append("istop", getIstop())
            .append("deposit", getDeposit())
            .append("topid", getTopid())
            .append("ibstatus", getIbstatus())
            .append("userphoto", getUserphoto())
            .toString();
    }

	public String getWithdrawpsd() {
		return withdrawpsd;
	}

	public void setWithdrawpsd(String withdrawpsd) {
		this.withdrawpsd = withdrawpsd;
	}

	public String getOutel() {
		return outel;
	}

	public String getToputel() {
		return toputel;
	}

	public void setOutel(String outel) {
		this.outel = outel;
	}

	public void setToputel(String toputel) {
		this.toputel = toputel;
	}

	public BigDecimal getRedmoney() {
		return redmoney;
	}

	public void setRedmoney(BigDecimal redmoney) {
		this.redmoney = redmoney;
	}

	public List<Integer> getTopids() {
		return topids;
	}

	public void setTopids(List<Integer> topids) {
		this.topids = topids;
	}

	public Integer getNewtask() {
		return newtask;
	}

	public Integer getComenewtask() {
		return comenewtask;
	}

	public void setNewtask(Integer newtask) {
		this.newtask = newtask;
	}

	public void setComenewtask(Integer comenewtask) {
		this.comenewtask = comenewtask;
	}

	public BigDecimal getOrdermaxprice() {
		return ordermaxprice;
	}

	public void setOrdermaxprice(BigDecimal ordermaxprice) {
		this.ordermaxprice = ordermaxprice;
	}

	public Long getIbtime() {
		return ibtime;
	}

	public void setIbtime(Long ibtime) {
		this.ibtime = ibtime;
	}

	public List<Integer> getOids() {
		return oids;
	}

	public void setOids(List<Integer> oids) {
		this.oids = oids;
	}
}
