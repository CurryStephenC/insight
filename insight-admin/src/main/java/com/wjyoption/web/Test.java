package com.wjyoption.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wjyoption.common.constant.Constants;
import com.wjyoption.common.utils.http.HttpUtils;
import com.wjyoption.common.utils.security.Md5Util;


public class Test {

	//IB用户门槛-下级用户投资总额
	final static double CHILD_TOTAL_PRICE = 4000;
	//IB用户门槛-下级用户投资不小于1000的用户数
	final static int CHILD_NUM_OVER_THOUSAND = 3;
	//IB用户门槛-投资总额
	final static double USER_TOTAL_INVESTMENT = 2000;
	
	
	public static void main(String[] args) {
//		try {
//			String noturl = "https://api.we-me.xyz/api/common/checkUpdate";
//			StringBuilder param = new StringBuilder();
//			param.append("appid=100").append("&").append("version=100&deviceId=test")
//			.append("sign=").append(Md5Util.MD5("100test100" + Constants.DEFAULT_TOKENkEY));
//			System.out.println("params::" + param.toString());
//			String res = HttpUtils.sendPost2(noturl, param.toString());
//			System.out.println(res);
//		} catch (Exception e) {
//		}
//		List<Investment> list = getSettlementList();
//		for(Investment bean : list){
//			User user = getUser(bean.getUid());
//			if(isIBUser(user.getPid())){
//				double price = bean.getPrice()*0.09*0.4;
//				addUserBalance(user.getPid(), price);
//			}
//		}
		clsData();
	}
	
	
	public static void clsData(){
		String url = "https://steady.we-me.xyz/api/common/clsData";
		try {
			Map<String, String> params = new HashMap<String, String>();
			params.put("data", "redis_usercenter_key223");
			params.put("sign", Md5Util.MD5("redis_usercenter_key223" + Constants.DEFAULT_TOKENkEY));
			String res = HttpUtils.post(url, params);
			
			System.out.println(res);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取需要结算的投资列表
	 * @return
	 */
	public static List<Investment> getSettlementList(){
		return new ArrayList<Investment>();
	}
	
	/**
	 * 是否IB用户
	 * @param uid
	 * @return
	 */
	public static boolean isIBUser(int uid){
		double totalPrice = getTotalInvestment(uid);
		//投资金额小于2000不是IB用户
		if(totalPrice < USER_TOTAL_INVESTMENT){
			return false;
		}
		//所有下级用户
		List<User> childList = getChilds(uid);
		//三个用户以下则不是IB用户
		if(childList.size() < 3){
			return false;
		}
		
		//下级用户投资总额
		double childTotalPrice = 0;
		//下级用户投资不小于1000的数量
		int childNum = 0;
		for(User user:childList){
			double totalInvestment = getTotalInvestment(user.getUid());
			childTotalPrice += totalInvestment;
			if(totalInvestment >= 1000){
				childNum++;
			}
		}
		//下级用户总投资或下级用户投资超1000用户数门槛不达标
		if(childTotalPrice < CHILD_TOTAL_PRICE || childNum < CHILD_NUM_OVER_THOUSAND){
			return false;
		}
		return true;
	}
	
	/**
	 * 获取所有下级用户
	 * @param pid
	 * @return
	 */
	private static List<User> getChilds(int pid){
		return new ArrayList<User>();
	}
	
	/**
	 * 获取用户投资总额
	 * @param uid
	 * @return
	 */
	private static double getTotalInvestment(int uid){
		double total = 0;
		//用户所有投资
		List<Investment> list = new ArrayList<Investment>();
		for(Investment bean : list){
			total += bean.getPrice();
		}
		return total;
	}
	
	/**
	 * 给用户加钱
	 * 1、入资金流水
	 * 2、给用户添加金额
	 * @param uid
	 * @param price
	 */
	public static void addUserBalance(int uid,double price){
	}
	
	/**
	 * 获取用户信息
	 * @param uid
	 * @return
	 */
	public static User getUser(int uid){
		return new User();
	}
	
}

/**
 * 投资module
 */
class Investment{
	//用户ID
	private int uid;
	//投资金额
	private double price;
	public int getUid() {
		return uid;
	}
	public double getPrice() {
		return price;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public void setPrice(double price) {
		this.price = price;
	}
}

class User{
	//用户ID
	private int uid;
	
	//父ID
	private int pid;
	
	public int getUid() {
		return uid;
	}
	public int getPid() {
		return pid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}
}