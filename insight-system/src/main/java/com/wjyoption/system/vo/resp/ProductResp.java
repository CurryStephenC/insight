package com.wjyoption.system.vo.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

@ApiModel("产品model")
public class ProductResp implements Serializable{

	private static final long serialVersionUID = -6448195919483098665L;


	@ApiModelProperty("产品id")
	private Integer pid;
	
	
	@ApiModelProperty("产品标题")
	private String ptitle;
	
	/** 当前价格 */
	@ApiModelProperty("当前价格")
    private BigDecimal price;

    /** 最高 */
	@ApiModelProperty("最高")
    private String high;

    /** 最低 */
	@ApiModelProperty("最低")
    private String low;
	
	@ApiModelProperty("更新时间")
	private Long updatetime;
	
	@ApiModelProperty("时间玩法间隔(分)")
	private String protime;
	
	@ApiModelProperty("盈亏比例")
	private String proscale;
	
	@ApiModelProperty("ICON")
	private String icon;
	
	@ApiModelProperty(value = "开盘时间",hidden = true)
	private String opentime;
	@ApiModelProperty(value = "0=休市,1=开市",hidden = true)
	private Integer isopen;
	
	@ApiModelProperty(value = "是否开盘")
	private boolean openFlag;
	
	
	public Integer getPid() {
		return pid;
	}

	public String getPtitle() {
		return ptitle;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public String getHigh() {
		return high;
	}

	public String getLow() {
		return low;
	}

	public Long getUpdatetime() {
		return updatetime;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public void setPtitle(String ptitle) {
		this.ptitle = ptitle;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public void setHigh(String high) {
		this.high = high;
	}

	public void setLow(String low) {
		this.low = low;
	}

	public void setUpdatetime(Long updatetime) {
		this.updatetime = updatetime;
	}

	public String getProtime() {
		return protime;
	}

	public String getProscale() {
		return proscale;
	}

	public void setProtime(String protime) {
		this.protime = protime;
	}

	public void setProscale(String proscale) {
		this.proscale = proscale;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getOpentime() {
		return opentime;
	}

	public void setOpentime(String opentime) {
		this.opentime = opentime;
	}

	/**
	 * 0=休市,1=开市
	 * @return
	 */
	public Integer getIsopen() {
		return isopen;
	}

	public void setIsopen(Integer isopen) {
		this.isopen = isopen;
	}

	public boolean isOpenFlag() {
		return openFlag;
	}

	public void setOpenFlag(boolean openFlag) {
		this.openFlag = openFlag;
	}
}
