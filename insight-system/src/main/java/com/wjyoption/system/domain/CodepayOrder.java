package com.wjyoption.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.wjyoption.common.annotation.Excel;
import com.wjyoption.common.core.domain.BaseEntity;

import java.math.BigDecimal;
import java.util.List;

/**
 * 支付对象 codepay_order
 * 
 * @author wjyoption
 * @date 2021-06-07
 */
public class CodepayOrder extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Long id;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Integer payId;
    @Excel(name = "用户")
    private String utel;
    @Excel(name = "上级")
    private String putel;
    @Excel(name = "销售")
    private String toputel;
    
    private Integer topid;

    /** 实际金额 */
    @Excel(name = "实际金额")
    private BigDecimal money;

    /** 原价 */
    @Excel(name = "原价")
    private BigDecimal price;

    /** 支付方式 */
    private Integer type;

    /** 流水号 */
    @Excel(name = "流水号")
    private String payNo;

    /** 自定义参数 */
    private String param;

    /** 付款时间 */
    private Long payTime;

    /** 金额的备注 */
    private String payTag;

    /** 订单状态 */
    @Excel(name = "订单状态",readConverterExp="0=待处理,1=成功,2=失败")
    private Integer status;

    /** 创建时间 */
    @Excel(name = "创建时间",dateFormat="yyyy-MM-dd HH:mm:ss",isTimestamp=true)
    private Long creatTime;

    /** 更新时间 */
    @Excel(name = "完成时间")
    private String upTime;

    /** 第三方ID */
    @Excel(name = "第三方ID")
    private String thirdid;

    /** 支付类型：1、在线支付，2、数字货币 */
    @Excel(name = "支付类型",readConverterExp="1=在线支付,2=数字货币")
    private Integer onlinepaytype;

    /** 支付上传图 */
//    @Excel(name = "支付上传图")
    private String url;

    /** 操作人ID */
//    @Excel(name = "操作人ID")
    private Integer operatorid;
    
    /**
     * 来自新手任务，0不是，1是
     */
    private Integer comenewtask;
    

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
    private List<Integer> payIds;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setPayId(Integer payId) 
    {
        this.payId = payId;
    }

    public Integer getPayId() 
    {
        return payId;
    }
    public void setMoney(BigDecimal money) 
    {
        this.money = money;
    }

    public BigDecimal getMoney() 
    {
        return money;
    }
    public void setPrice(BigDecimal price) 
    {
        this.price = price;
    }

    public BigDecimal getPrice() 
    {
        return price;
    }
    public void setType(Integer type) 
    {
        this.type = type;
    }

    public Integer getType() 
    {
        return type;
    }
    public void setPayNo(String payNo) 
    {
        this.payNo = payNo;
    }

    public String getPayNo() 
    {
        return payNo;
    }
    public void setParam(String param) 
    {
        this.param = param;
    }

    public String getParam() 
    {
        return param;
    }
    public void setPayTime(Long payTime) 
    {
        this.payTime = payTime;
    }

    public Long getPayTime() 
    {
        return payTime;
    }
    public void setPayTag(String payTag) 
    {
        this.payTag = payTag;
    }

    public String getPayTag() 
    {
        return payTag;
    }
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }
    public void setCreatTime(Long creatTime) 
    {
        this.creatTime = creatTime;
    }

    public Long getCreatTime() 
    {
        return creatTime;
    }
    public void setUpTime(String upTime) 
    {
        this.upTime = upTime;
    }

    public String getUpTime() 
    {
        return upTime;
    }
    public void setThirdid(String thirdid) 
    {
        this.thirdid = thirdid;
    }

    public String getThirdid() 
    {
        return thirdid;
    }
    public void setOnlinepaytype(Integer onlinepaytype) 
    {
        this.onlinepaytype = onlinepaytype;
    }

    public Integer getOnlinepaytype() 
    {
        return onlinepaytype;
    }
    public void setUrl(String url) 
    {
        this.url = url;
    }

    public String getUrl() 
    {
        return url;
    }
    public void setOperatorid(Integer operatorid) 
    {
        this.operatorid = operatorid;
    }

    public Integer getOperatorid() 
    {
        return operatorid;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("payId", getPayId())
            .append("money", getMoney())
            .append("price", getPrice())
            .append("type", getType())
            .append("payNo", getPayNo())
            .append("param", getParam())
            .append("payTime", getPayTime())
            .append("payTag", getPayTag())
            .append("status", getStatus())
            .append("creatTime", getCreatTime())
            .append("upTime", getUpTime())
            .append("thirdid", getThirdid())
            .append("onlinepaytype", getOnlinepaytype())
            .append("url", getUrl())
            .append("operatorid", getOperatorid())
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

	public Integer getNormaltype() {
		return normaltype;
	}

	public void setNormaltype(Integer normaltype) {
		this.normaltype = normaltype;
	}

	public Integer getComenewtask() {
		return comenewtask;
	}

	public void setComenewtask(Integer comenewtask) {
		this.comenewtask = comenewtask;
	}

	public List<Integer> getPayIds() {
		return payIds;
	}

	public void setPayIds(List<Integer> payIds) {
		this.payIds = payIds;
	}
}
