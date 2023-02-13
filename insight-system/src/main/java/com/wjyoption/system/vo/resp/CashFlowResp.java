package com.wjyoption.system.vo.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

@ApiModel("资金流水model")
public class CashFlowResp implements Serializable{

	private static final long serialVersionUID = 4254507931422822970L;

	/** 类型 */
    @ApiModelProperty("类型  1  注册"
    		+ "2  投资盈利"          
    + " 3  投资亏损"         
    + " 4  账户提现"         
    + " 5  账户充值"         
    + " 6  手动充值"         
    + " 7  购买理财"         
    + " 8  赎回理财本金"    
    + " 9  理财收益 "         
    + "10  一级IB用户收益"  
    + "11  二级IB用户收益"  
    + "12  手动赠送红包"    
    + "13  言论红包"          
    + "14  每日签到奖励"    
    + "15  累计签到奖励 "   
    + "16  三级IB用户收益 ")
    private Integer type;
    
    @ApiModelProperty("类型名称_英文")
    private String name_en;
    
    /** 变动金额 */
    @ApiModelProperty("变动金额")
    private String money;

    /** 时间 */
    @ApiModelProperty("时间")
    private Long time;

    /** 此刻余额 */
    @ApiModelProperty("此刻余额")
    private String nowmoney;
    
    @ApiModelProperty("用户手机")
    private String utel;
    
    @ApiModelProperty(value = "用户ID",hidden = true)
    private Integer uid;
    @ApiModelProperty(value = "上级ID",hidden = true)
    private Integer oid;
    
    @ApiModelProperty(value = "查询条件，不包含此类型")
    private String existsType;
    
    @ApiModelProperty(value="不包含此类型",hidden = true)
    private List<Integer> noType;
    
    @ApiModelProperty(value="类型列表",hidden = true)
    private List<Integer> types;
    
    

	public Integer getType() {
		return type;
	}

	public String getMoney() {
		return money;
	}

	public Long getTime() {
		return time;
	}

	public String getNowmoney() {
		return nowmoney;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public void setTime(Long time) {
		this.time = time;
	}

	public void setNowmoney(String nowmoney) {
		this.nowmoney = nowmoney;
	}

	public Integer getUid() {
		return uid;
	}

	public List<Integer> getNoType() {
		return noType;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public void setNoType(List<Integer> noType) {
		this.noType = noType;
	}

	public String getExistsType() {
		return existsType;
	}

	public void setExistsType(String existsType) {
		this.existsType = existsType;
	}

	public Integer getOid() {
		return oid;
	}

	public List<Integer> getTypes() {
		return types;
	}

	public void setOid(Integer oid) {
		this.oid = oid;
	}

	public void setTypes(List<Integer> types) {
		this.types = types;
	}

	public String getUtel() {
		return utel;
	}

	public void setUtel(String utel) {
		this.utel = utel;
	}

	public String getName_en() {
		return name_en;
	}

	public void setName_en(String name_en) {
		this.name_en = name_en;
	}
	
}
