package com.wjyoption.system.vo.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;


@ApiModel("地区VO")
public class RegioncodeVo implements Serializable {

	private static final long serialVersionUID = 3292013918584606398L;

	@ApiModelProperty("区域ID")
    private Integer regioncodeid;

    /** 省、直辖市名称 */
    @ApiModelProperty("省、直辖市名称")
    private String province;

    /** 城市名称 */
    @ApiModelProperty("城市名称")
    private String city;

    /** 区域名称 */
    @ApiModelProperty("区域名称")
    private String areae;

	public Integer getRegioncodeid() {
		return regioncodeid;
	}

	public String getProvince() {
		return province;
	}

	public String getCity() {
		return city;
	}

	public String getAreae() {
		return areae;
	}

	public void setRegioncodeid(Integer regioncodeid) {
		this.regioncodeid = regioncodeid;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setAreae(String areae) {
		this.areae = areae;
	}

	
}
