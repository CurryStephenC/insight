package com.wjyoption.common.enums;

import com.wjyoption.common.core.domain.Response;


public enum ErrorConstants {
	
	/**
	 * 系统错误
	 */
	SYSTEM_ERROR(1,"system error"),
	
	/**
	 * 用户已存在
	 */
	USER_ALREADY_EXIST(2,"User already exists"),
	
	/**
	 * 验证码错误
	 */
	VERIFY_ERROR(3,"Verification code error"),
	
	/**
	 * 验证码过期
	 */
	VERIFY_OUTTIME(4,"Verification code expired"),
	
	/**
	 * 手机号码不能为空
	 */
	PHONE_IS_NULL(5,"Phone number can not be blank"),
	
	/**
	 * 没有权限
	 */
	NO_POWER(6,"Permission denied"),
	/**
	 * 文件不能为空
	 */
	FILE_NOT_NULL(7,"File cannot be empty"),
	/**
	 * 旧密码错误
	 */
	PASSWORD_ERROR(8,"wrong password"),
	/**
	 * 新密码不能为空
	 */
	PASSWORD_NOT_NULL(9,"password can not be blank"),
	
	/**
	 * Email不能为空
	 */
	EMAIL_IS_NULL(10,"Email cannot be empty"),
	
	/**
	 * Email已存在
	 */
	EMAIL_ALREADY_EXISTS(11,"Email already exists"),
	
	/**手机已存在**/
	PHONE_ALREADY_EXISTS(12,"Phone already exists"),
	
	/**参数错误**/
	PARAMS_ERROR(13,"Parameter error"),
	
	CONSULTATIONUSER_PARAM_NULL(14,"Please enter corresponding info!"),
	/**用户号码已注册**/
	PHONE_REGISTERED(15,"User number has been registered"),
	/**验证码过期**/
	VERIFY_EXPIRED(16,"Verification code expired"),//验证码过期
	/**验证码错误**/
	VERIFY_FAIL(17,"Verification code error"),//验证码错误
	/**发送次数过多，稍后再试**/
	VERIFY_SENDMUCH(18,"Send too many times, try again later"),//短信发送过多
	/**用户不存在**/
	USER_NOT_EXISTS(19,"User does not exist"),
	
	/**手机号码不正确**/
	PHONENUM_ERROR(20,"the phone number is incorrect"),
	/**参数不对**/
	WRONG_PARAMETER(21,"The parameter is wrong"),
	/**模板不存在*/
	TEMPLATE_NOT_EXISTS(22,"does not exist."),
	/**数据格式不对*/
	DATA_FORMAT_WRONG(23,"wrong format"),
	/**
	 * 账号或密码错误
	 */
	PHONE_OR_PASSWORD_ERROR(24,"Incorrect username or password"),
	
	/**
	 * 账号被冻结
	 */
	USER_FREEZE(25,"Account is frozen"),
	
	/**
	 * 用户已实名
	 */
	USER_VERIFIED(26,"The user has a real name"),
	
	/**
	 * 身份证格式不对
	 */
	IDCARD_ERROR(27,"Bad ID format"),
	
	/**
	 * 实名认证失败
	 */
	REALNAME_VERIFIED_ERROR(28,"Real-name authentication failed"),
	/**
	 * 已存在
	 */
	RECORD_EXISTS(30,"Existed"),
	
	/**
	 * 余额不足
	 */
	WALLET_SMALL(31,"Insufficient balance"),
	/**
	 * 无法更新
	 */
	NO_UPDATE(32,"Unable to update"),
	
	/**
	 * 金额不对
	 */
	MONEY_ERROR(33,"Wrong amount"),
	
	/**
	 * 记录不存在
	 */
	RECORD_NOT_EXISTS(34,"Record does not exist"),
	
	/**
	 * 错误
	 */
	ERROR(35,"error"),
	
	/**
	 * 无法操作
	 */
	NOT_OPERA(36,"Inoperable"),
	/**
	 * 如赠金不足请联系客服
	 */
	NO_MUCH_MONEY(37,"If the bonus is insufficient, please contact customer service"),
	/**
	 * 金额过小
	 */
	MONEY_TOO_SMALL(38,"The amount is too small"),
	/**
	 * 失败
	 */
	FAIL(39,"failure"),
	
	
	/**
	 * 暂时无法出金
	 */
	WITHDRAW_NOT(40,"Sorry! Withdrawal is unavailable temporarily."),
	
	/**
	 * 单笔最低提现金额为：
	 */
	WITHDRAW_MIN(41,"Minimum amount of single withdrawal shall be:"),
	/**
	 * 单笔最高提现金额为：
	 */
	WITHDRAW_MAX(42,"Maximum amount of single withdrawal shall be:"),
	/**
	 * 最多提现次数：
	 */
	WITHDRAW_TIMES(43,"Daily Maximum number of withdrawal shall be:"),
	/**
	 * 当日累计最高提现金额为：
	 */
	WITHDRAW_MAXMONEY(44,"The maximum withdrawal amount accumulated on the very day shall be:"),
	/**
	 * 银行卡信息不能为空
	 */
	BANKCARD_NOT_NULL(45,"Bank card information cannot be empty"),
	
	/**
	 * 已参与过
	 */
	HAVE_PARTICIPATED(46,"Have participated"),
	
	/**
	 * 支付金额超出限制
	 */
	PAY_MONEY_LIMIT(47,"The payment amount exceeds the limit"),
	
	/**
	 * 已达上限
	 */
	REACHED_LIMIT(48,"Reached the limit"),
	
	/**
	 * 邀请码不能为空
	 */
	INVITATION_CODE_NOT_NULL(49,"Please fill in the invitation code"),
	
	/**
	 * 邀请码错误
	 */
	INVITATION_CODE_ERROR(50,"Wrong invitation code"),
	
	/**
	 * 金额过大
	 */
	MONEY_TOO_LARGE(51,"The amount is too large"),
	/**
	 * 最大尺寸数：
	 */
	ORDER_MAX_COUNT(52,"Maximum holdings:"),
	/**
	 * 持仓最多：10000
	 */
	ORDER_MAX_PRICE(53,"The most open interest:"),
	/**
	 * 产品休市中
	 */
	PRODUCT_CLOSED(54,"closed"),
	
	/**
	 * 产品下单时间过短
	 */
	PRODUCT_PLACEORDER_TIME_SMALL(55,"Operation is too frequent"),
	
	/**
	 * 没有购买权限
	 */
	NO_TRADING_AUTHORITY(56,"No trading authority"),

	/**
	 * 任务未完成
	 */
	TASK_NOT_COMPLETED(57,"Task not completed"),
	/**
	 * 重复操作
	 */
	REPEAT_OPERATION(58,"Repeat operation"),
	
	/**
	 * 充值最小金额
	 */
	PAY_MINPRICE(59,"The minimum recharge is Rs "),
	
	/**
	 * 充值最大金额
	 */
	PAY_MAXPRICE(60,"The maximum recharge is Rs "),
	
	/**
	 * 每日下单数量限制
	 */
	ORDER_DAILY_LIMIT(61,"The number of transactions has been used up"),
	
	/**
	 * 相同银行卡无法提现
	 */
	BANK_SAME_NOT_WITHDRAW(62,"You can use the same bank account to withdraw cash after buying a formal financial product"),
	/**
	 * 超过每日最高限制
	 */
	ORDER_MAXPRICE_DAY(63,"Transaction quota has been used up"),
	
	/**
	 * 空白错误，自己输入内容
	 */
	BLANK_ERROR(10000,""),
	
	
	/**
	 * 没有登录
	 */
	NO_LOGIN(102,"Not logged in"),
	/**
	 * 签名错误
	 */
	SIGN_ERROR(103,"Signature error"),
	
	;
	
	private ErrorConstants(Integer retCode,String message){
		this.retCode=retCode;
		this.message = message;
	}
	
	
	public static void setResponse(Response<?> response,ErrorConstants error){
		response.setRetCode(error.retCode);
		response.setMessage(error.message);
	}
	
	public static void setResponse(Response<?> response,ErrorConstants error,Object appendMsg){
		response.setRetCode(error.retCode);
		response.setMessage(error.message + appendMsg);
	}
	
	public static <T> Response<T> error(ErrorConstants error){
		Response<T> response = new Response<>();
		response.setRetCode(error.retCode);
		response.setMessage(error.message);
		return response;
	}
	
	
	private Integer retCode;
	
	private String message;

	
}