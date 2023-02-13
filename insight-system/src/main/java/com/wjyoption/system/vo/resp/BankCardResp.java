package com.wjyoption.system.vo.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

import com.wjyoption.system.domain.WpBankcard;

@ApiModel("用户银行Model")
public class BankCardResp implements Serializable{

	private static final long serialVersionUID = 4630342490214832606L;

	public BankCardResp(){}
	public BankCardResp(WpBankcard bank){
		this.id = bank.getId();
		this.bankno = bank.getBankno();
		this.accntnm = bank.getAccntnm();
		this.accntnm2 = bank.getAccntnm2();
		this.address = bank.getAddress();
		this.uid = bank.getUid();
		this.accntno = bank.getAccntno();
		this.branchname = bank.getBranchname();
		this.cryptocurrency = bank.getCryptocurrency();
		this.walletaddr = bank.getWalletaddr();
	}
	
	/** 记录ID */
	@ApiModelProperty("记录ID")
    private Long id;

    /** 银行名称 */
    @ApiModelProperty(value = "银行名称",required=true)
    private String bankno;

    /** 持卡人姓名 */
    @ApiModelProperty(value = "持卡人姓名",required = true)
    private String accntnm;

    /** 持卡人名称 */
    @ApiModelProperty(value= "持卡人名称",required = true)
    private String accntnm2;

    /** 地址 */
    @ApiModelProperty(value = "地址",required = true)
    private String address;

    /** 用户id */
    @ApiModelProperty("用户id")
    private Integer uid;

    /** 银行账号 */
    @ApiModelProperty(value = "银行账号",required = true)
    private String accntno;

    /** IFSC */
    @ApiModelProperty(value = "IFSC",required = true)
    private String branchname;
    
    /** 数字货币 */
    @ApiModelProperty("数字货币类型如：USDT、BTC")
    private String cryptocurrency;

    /** 数字货币-钱包地址 */
    @ApiModelProperty("钱包地址")
    private String walletaddr;

	public Long getId() {
		return id;
	}

	public String getBankno() {
		return bankno;
	}

	public String getAccntnm() {
		return accntnm;
	}

	public String getAccntnm2() {
		return accntnm2;
	}

	public String getAddress() {
		return address;
	}

	public Integer getUid() {
		return uid;
	}

	public String getAccntno() {
		return accntno;
	}

	public String getBranchname() {
		return branchname;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setBankno(String bankno) {
		this.bankno = bankno;
	}

	public void setAccntnm(String accntnm) {
		this.accntnm = accntnm;
	}

	public void setAccntnm2(String accntnm2) {
		this.accntnm2 = accntnm2;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public void setAccntno(String accntno) {
		this.accntno = accntno;
	}

	public void setBranchname(String branchname) {
		this.branchname = branchname;
	}
	public String getCryptocurrency() {
		return cryptocurrency;
	}
	public String getWalletaddr() {
		return walletaddr;
	}
	public void setCryptocurrency(String cryptocurrency) {
		this.cryptocurrency = cryptocurrency;
	}
	public void setWalletaddr(String walletaddr) {
		this.walletaddr = walletaddr;
	}


	
}
