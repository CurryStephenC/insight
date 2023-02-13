package com.wjyoption.system.vo.report;

import java.io.Serializable;
import java.util.List;

public class ProductKlineRedisVo implements Serializable{

	private static final long serialVersionUID = 6616829484461189719L;

	private String procode;
	
	private Integer pid;
	
	private List<ProductKlineVo> data;

	public String getProcode() {
		return procode;
	}

	public Integer getPid() {
		return pid;
	}

	public List<ProductKlineVo> getData() {
		return data;
	}

	public void setProcode(String procode) {
		this.procode = procode;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public void setData(List<ProductKlineVo> data) {
		this.data = data;
	}
	
}
