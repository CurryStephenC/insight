package com.wjyoption.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.wjyoption.common.annotation.Excel;
import com.wjyoption.common.core.domain.BaseEntity;

import java.math.BigDecimal;
import java.util.List;

/**
 * 资金日志对象 wp_price_log
 * 
 * @author wjyoption
 * @date 2021-06-05
 */
public class WpPriceLog extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Integer id;

    /** 用户ID */
    private Integer uid;

    /** 关联ID */
    @Excel(name = "关联ID")
    private Integer oid;
    
    @Excel(name = "用户号码")
    private String utel;
    private String putel;
    private String toputel;
    
    private Integer topid;

    /** 1增加2减少 */
    @Excel(name = "1增加2减少")
    private Integer type;

    /** 变动金额 */
    @Excel(name = "变动金额")
    private String account;

    /** 标题 */
    @Excel(name = "标题")
    private String title;

    /** 说明 */
    @Excel(name = "说明")
    private String content;

    /** 时间 */
    @Excel(name = "时间")
    private Long time;

    /** 此刻余额 */
    @Excel(name = "此刻余额")
    private BigDecimal nowmoney;
    
    
    /**
     * 销售组合
     * 查询使用
     */
    private List<Integer> topids;

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
    public void setOid(Integer oid) 
    {
        this.oid = oid;
    }

    public Integer getOid() 
    {
        return oid;
    }
    public void setType(Integer type) 
    {
        this.type = type;
    }

    public Integer getType() 
    {
        return type;
    }
    public void setAccount(String account) 
    {
        this.account = account;
    }

    public String getAccount() 
    {
        return account;
    }
    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }
    public void setTime(Long time) 
    {
        this.time = time;
    }

    public Long getTime() 
    {
        return time;
    }
    public void setNowmoney(BigDecimal nowmoney) 
    {
        this.nowmoney = nowmoney;
    }

    public BigDecimal getNowmoney() 
    {
        return nowmoney;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("uid", getUid())
            .append("oid", getOid())
            .append("type", getType())
            .append("account", getAccount())
            .append("title", getTitle())
            .append("content", getContent())
            .append("time", getTime())
            .append("nowmoney", getNowmoney())
            .toString();
    }

	public String getUtel() {
		return utel;
	}

	public String getPutel() {
		return putel;
	}

	public String getToputel() {
		return toputel;
	}

	public void setUtel(String utel) {
		this.utel = utel;
	}

	public void setPutel(String putel) {
		this.putel = putel;
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

	public void setTopids(List<Integer> topids) {
		this.topids = topids;
	}
}
