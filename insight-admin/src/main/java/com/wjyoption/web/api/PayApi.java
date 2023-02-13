package com.wjyoption.web.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;
import com.wjyoption.common.annotation.Login;
import com.wjyoption.common.annotation.NotNullParam;
import com.wjyoption.common.annotation.Sign;
import com.wjyoption.common.config.ServerConfig;
import com.wjyoption.common.core.controller.BaseController;
import com.wjyoption.common.core.domain.AjaxResult;
import com.wjyoption.common.core.domain.Response;
import com.wjyoption.common.core.page.PageDomain;
import com.wjyoption.common.enums.ErrorConstants;
import com.wjyoption.common.utils.DateUtils;
import com.wjyoption.common.utils.ThreadLocals;
import com.wjyoption.common.vo.CashNotifyVo;
import com.wjyoption.common.vo.UserLogin;
import com.wjyoption.system.domain.CodepayOrder;
import com.wjyoption.system.domain.SysCheckoutCounter;
import com.wjyoption.system.domain.SysDictData;
import com.wjyoption.system.domain.SysPayType;
import com.wjyoption.system.domain.SysReceiveAccount;
import com.wjyoption.system.domain.WpBalance;
import com.wjyoption.system.service.ICodepayOrderService;
import com.wjyoption.system.service.ISysCheckoutCounterService;
import com.wjyoption.system.service.ISysDictDataService;
import com.wjyoption.system.service.ISysPayTypeService;
import com.wjyoption.system.service.ISysReceiveAccountService;
import com.wjyoption.system.service.IWpBalanceService;
import com.wjyoption.system.service.IWpConfigService;
import com.wjyoption.system.vo.resp.BalanceResp;
import com.wjyoption.system.vo.resp.PayResp;
import com.wjyoption.system.vo.resp.PaydigitalTypeResp;
import com.wjyoption.web.controller.common.CommonController;


@Api(tags={"支付模块"},value="pay")
@Controller
@RequestMapping("api/pay")
@CrossOrigin
public class PayApi  extends BaseController{

	@Autowired ICodepayOrderService payService;
	@Autowired ServerConfig serverConfig;
	@Autowired IWpBalanceService balanceService;
	@Autowired IWpConfigService configService;
	@Autowired ISysPayTypeService payTypeService;
	@Autowired ISysDictDataService dictDataService;
	@Autowired ISysCheckoutCounterService checkoutCounterService;
	@Autowired ISysReceiveAccountService receiveAccountService;
	
	@Value("${wjyoption.payorderprefix}")
	private String payorderprefix;
	
	@Value("${wjyoption.platform}")
    private String platform;
	@Value("${wjyoption.sms.webSite}")
	private String webSite;
	//1开，其他关
	@Value("${wjyoption.httpsSwitch}")
	private String httpsSwitch;
	
	
	@ApiOperation("获取数字支付通道数据")
	@RequestMapping(value = "channelDigital",method = {RequestMethod.POST})
	@ResponseBody
	@Sign
	@Login
	public Response<List<PaydigitalTypeResp>> channelDigital(){
		List<SysDictData> list2 = this.dictDataService.selectDictDataByType("wp_pay_digital_type");
		Response<List<PaydigitalTypeResp>> response = new Response<>();
		List<PaydigitalTypeResp> retList = new ArrayList<>();
		list2.forEach(obj -> {
			retList.add(new PaydigitalTypeResp(obj.getDictLabel(), obj.getDictValue()));
		});
		response.setData(retList);
		return response;
	}
	
	@ApiOperation("支付")
	@RequestMapping(value = "pay",method = {RequestMethod.POST})
	@ResponseBody
	@ApiImplicitParams({
		@ApiImplicitParam(name = "action", value = "支付方式：如 pay777,yypay", dataType = "String"),
		@ApiImplicitParam(name = "money", value = "每页记录", dataType = "Integer"),
		@ApiImplicitParam(name = "comenewtask", value = "来自新手任务，0不是(默认)，1是", dataType = "Integer"),
	})
	@Sign
	@Login
	@NotNullParam("action||money")
	public Response<String> pay(String action,Integer money,Integer comenewtask){
		Response<String> response = new Response<>();
		UserLogin user = ThreadLocals.getUser();
		String orderid = payorderprefix + user.getUid() + action.toUpperCase() + (System.currentTimeMillis()/1000);
		BigDecimal payMoney = new BigDecimal(money);
		String userpay_min = this.configService.selectWpConfigByKey("userpay_min");
		String userpay_max = this.configService.selectWpConfigByKey("userpay_max");
		try {
			if(StringUtils.isNotBlank(userpay_min) && payMoney.compareTo(new BigDecimal(userpay_min)) < 0){
				ErrorConstants.setResponse(response, ErrorConstants.PAY_MONEY_LIMIT);
				return response;
			}
			if(StringUtils.isNotBlank(userpay_max) && payMoney.compareTo(new BigDecimal(userpay_max)) > 0){
				ErrorConstants.setResponse(response, ErrorConstants.PAY_MONEY_LIMIT);
				return response;
			}
		} catch (Exception e) {
			logger.error("支付判断出错",e);
		}
		SysPayType type = this.payTypeService.selectSysPayTypeByCode(action);
		if(type == null){
			ErrorConstants.setResponse(response, ErrorConstants.FAIL);
			return response;
		}
		if(type.getMinprice() != null && payMoney.compareTo(type.getMinprice()) < 0){
			ErrorConstants.setResponse(response, ErrorConstants.PAY_MINPRICE, type.getMinprice().intValue());
			return response;
		}
		if(type.getMaxprice() != null && payMoney.compareTo(type.getMaxprice()) > 0){
			ErrorConstants.setResponse(response, ErrorConstants.PAY_MAXPRICE, type.getMaxprice().intValue());
			return response;
		}
		hjpay(action, orderid, payMoney, this.webSite, response);
//		PayResult result = PayUtil.pay(type.getPaytype(),type.getPmid(), orderid,payMoney ,this.platform,this.webSite, serverConfig);
		if(response.getRetCode() != 0){
//			ErrorConstants.setResponse(response, ErrorConstants.FAIL);
			return response;
		}
		CodepayOrder order = new CodepayOrder();
		order.setPayId(user.getUid());
		order.setMoney(payMoney);
		order.setPrice(payMoney);
		order.setType(type.getPaynum());
		order.setPayNo(orderid);
		order.setPayTime(System.currentTimeMillis()/1000);
		order.setPayTag(action);
		order.setCreatTime(order.getPayTime());
		order.setUpTime(DateUtils.dateTimeNow());
		order.setThirdid(orderid);
		order.setOnlinepaytype(1);
		order.setUrl(response.getData());
		order.setOperatorid(0);
		order.setComenewtask(comenewtask == null ? 0 : comenewtask);
		this.payService.insertCodepayOrder(order);
		
//		response.setData(result.getPayUrl());
		return response;
	}
	
	
	@ApiOperation("数字支付")
	@RequestMapping(value = "payDigital",method = {RequestMethod.POST})
	@ResponseBody
	@ApiImplicitParams({
		@ApiImplicitParam(name = "onlinepaytype", value = "在线支付方式 2数字货币(默认),3PayPal", dataType = "Integer"),
		@ApiImplicitParam(name = "type", value = "1001:usdt,1002:btc,2001:paypal", dataType = "Integer",required=true),
		@ApiImplicitParam(name = "url", value = "支付图片", dataType = "String",required=true),
		@ApiImplicitParam(name = "param", value = "收款账号", dataType = "String",required=true),
	})
	@Sign
	@Login
	@NotNullParam("type||url||param")
	public Response<String> payDigital(String param,String url,Integer onlinepaytype,Integer type,BigDecimal money,BigDecimal price){
		Response<String> response = new Response<>();
		UserLogin user = ThreadLocals.getUser();
		String orderid = payorderprefix + user.getUid() + type + (System.currentTimeMillis()/1000);
		
		BigDecimal payMoney = new BigDecimal(0);
		CodepayOrder order = new CodepayOrder();
		order.setPayId(user.getUid());
		order.setMoney((money == null ? payMoney : money).multiply(new BigDecimal(1.01)));
		order.setPrice(price == null ? payMoney : price);
		order.setType(type);
		order.setPayNo(orderid);
		order.setPayTime(System.currentTimeMillis()/1000);
//		order.setPayTag(action);
		order.setStatus(0);
		order.setParam(param);
		order.setCreatTime(order.getPayTime());
		order.setUpTime(DateUtils.dateTimeNow());
		order.setOnlinepaytype(onlinepaytype == null ? 2 : onlinepaytype);
		order.setUrl(url);
		order.setOperatorid(0);
		this.payService.insertCodepayOrder(order);
		return response;
	}
	
	@ApiOperation("我的支付记录")
	@RequestMapping(value = "myPay",method = {RequestMethod.POST})
	@ResponseBody
	@ApiImplicitParams({
		@ApiImplicitParam(name = "status", value = "订单状态 0:Wait,1:Success,2:Fail", dataType = "Integer"),
		@ApiImplicitParam(name = "pageNum", value = "当前页", dataType = "Integer"),
		@ApiImplicitParam(name = "pageSize", value = "每页记录", dataType = "Integer"),
		@ApiImplicitParam(name = "orderByColumn", value = "排序字段：如 id", dataType = "String"),
		@ApiImplicitParam(name = "isAsc", value = "排序方式->顺序：asc,倒序：desc", dataType = "String"),
	})
	@Sign
	@Login
	public Response<List<PayResp>> myPay(Integer status){
		Response<List<PayResp>> response = new Response<>();
		PageDomain page = startPageApi();
		CodepayOrder codepayOrder = new CodepayOrder();
		codepayOrder.setPayId(ThreadLocals.getUser().getUid());
		codepayOrder.setStatus(status);
		List<CodepayOrder> list = this.payService.selectCodepayOrderList(codepayOrder);
		List<PayResp> list2 = new ArrayList<PayResp>();
		list.forEach(obj -> {
			list2.add(new PayResp(obj));
		});
		response.setData(list2);
		
		if(page.getPageSize() != null){
			response.setTotal(new PageInfo<CodepayOrder>(list, page.getPageSize()).getTotal());
		}else
			response.setTotal((long) list.size());
		return response;
	}
	@ApiOperation("我的提现记录")
	@RequestMapping(value = "withdrawList",method = {RequestMethod.POST})
	@ResponseBody
	@ApiImplicitParams({
		@ApiImplicitParam(name = "isverified", value = "判断申请是否通过0：待审核，1、通过，2、拒绝", dataType = "Integer"),
		@ApiImplicitParam(name = "pageNum", value = "当前页", dataType = "Integer"),
		@ApiImplicitParam(name = "pageSize", value = "每页记录", dataType = "Integer"),
		@ApiImplicitParam(name = "orderByColumn", value = "排序字段：如 id", dataType = "String"),
		@ApiImplicitParam(name = "isAsc", value = "排序方式->顺序：asc,倒序：desc", dataType = "String"),
	})
	@Sign
	@Login
	public Response<List<BalanceResp>> withdrawList(Integer isverified){
		Response<List<BalanceResp>> response = new Response<>();
		PageDomain page = startPageApi();
		WpBalance param = new WpBalance();
		param.setUid(ThreadLocals.getUser().getUid());
		param.setIsverified(isverified);
		param.setBptype(0);
		List<WpBalance> list = this.balanceService.selectWpBalanceList(param);
		List<BalanceResp> list2 = new ArrayList<>();
		list.forEach(obj -> {
			list2.add(new BalanceResp(obj));
		});
		response.setData(list2);
		
		if(page.getPageSize() != null){
			response.setTotal(new PageInfo<WpBalance>(list, page.getPageSize()).getTotal());
		}else
			response.setTotal((long) list.size());
		return response;
	}
	
//	@ApiOperation(value="充值回调",hidden=true)
//	@RequestMapping(value = "dlocal_notify",method = {RequestMethod.POST})
//	public Response<String> dlocal_notify(PayNotifyVo vo){
//		Response<String> response = new Response<String>();
//		this.payService.payNotify(vo,response);
//		return response;
//	}
	
	@ApiOperation(value="提现回调",hidden=true)
	@RequestMapping(value = "cashNotify",method = {RequestMethod.POST})
	@ResponseBody
	public Response<String> cashNotify(CashNotifyVo vo){
		Response<String> response = new Response<String>();
		synchronized (vo.getOrderid()) {
			this.balanceService.cashNotify(vo,response);
		}
		return response;
	}
	
	
	@ApiOperation("提现")
	@RequestMapping(value = "withdraw",method = {RequestMethod.POST})
	@ResponseBody
	@ApiImplicitParams({
		@ApiImplicitParam(name = "userremark", value = "备注信息", dataType = "String"),
		@ApiImplicitParam(name = "price", value = "金额", dataType = "Integer",required = true),
		@ApiImplicitParam(name = "banktype", value = "提现类型：1、银行卡(默认)，3、数字货币", dataType = "Integer"),
		@ApiImplicitParam(name = "withdrawpsd", value = "提现密码(MD5值)", dataType = "String",required=true),
	})
	@Sign
	@Login
	@NotNullParam("price||withdrawpsd")
	public Response<String> withdraw(String userremark,Integer price,String withdrawpsd,String banktype){
		Response<String> response = new Response<>();
		synchronized (ThreadLocals.getUser().getUid()) {
			this.balanceService.withdraw(userremark,price,withdrawpsd,banktype,response);
		}
		return response;
	}
	
	
	private void hjpay(String action,String order_id,BigDecimal amount,String webSite,Response<String> response){
		String return_url = ("1".equals(httpsSwitch) ? "https" : "http") + "://"+webSite+"/";
		SysCheckoutCounter bean = this.checkoutCounterService.selectByOrderid(order_id);
		if(bean != null){
			response.setRetCode(1);
			response.setMessage("Order already exists");
			return;
		}
		bean = new SysCheckoutCounter();
		SysReceiveAccount sysReceiveAccount = new SysReceiveAccount();
		sysReceiveAccount.setStatus("0");
		sysReceiveAccount.setPmid(action);
		List<SysReceiveAccount> list = this.receiveAccountService.selectSysReceiveAccountList(sysReceiveAccount);
		if(list.size() == 0){
			response.setRetCode(1);
			response.setMessage("No data");
			return;
		}
		UserLogin user = ThreadLocals.getUser();
		
		SysReceiveAccount account = list.get(new Random().nextInt(list.size()));
		bean.setUsername(account.getUsername());
		bean.setIfsc(account.getIfsc());
		bean.setBankname(account.getBankname());
		bean.setBankaccount(account.getBankaccount());
		bean.setPaymoney(amount);
		bean.setRealmoney(bean.getPaymoney());
		bean.setOrderid(order_id);
//		bean.setTransactionid(order_id);
		bean.setReturnurl(return_url);
		bean.setUid(user.getUid());
		bean.setCode(action);
		this.checkoutCounterService.insertSysCheckoutCounter(bean);
		if(bean.getId() == null){
			response.setRetCode(1);
			response.setMessage("Fail");
			return;
		}
		response.setData(return_url + "api/pay/inpay/" + bean.getOrderid());
	}
	
	
	@RequestMapping("/inpay/{orderid}")
    public String inpay(@PathVariable("orderid")String orderid,ModelMap model){
    	if(orderid == null || orderid.length() > 30){
    		model.put("backurl", "javascript:history.go(-1)");
    		return "pay/payment_failure";
    	}
    	String regex = "^[a-z0-9A-Z]+$";
    	if(!orderid.matches(regex)){
    		model.put("backurl", "javascript:history.go(-1)");
    		return "pay/payment_failure";
    	}
    	SysCheckoutCounter bean = this.checkoutCounterService.selectByOrderid(orderid);
    	if(bean == null || bean.getStatus() == 3){
    		model.put("backurl", "javascript:history.go(-1)");
			return "pay/payment_failure";
    	}
    	if(bean.getStatus() == 2 || bean.getStatus() == 1){
    		model.put("backurl", "javascript:history.go(-1)");
			return "pay/payment_success";
    	}
    	model.put("vo", bean);
    	return "pay/hjpay";
    }
	
	@Autowired CommonController commonControlelr;
    @RequestMapping(method = RequestMethod.POST, value = "/hjpaySubmit")
    public String hjpaySubmit(MultipartFile file,ModelMap map){
    	HttpServletRequest request = super.getRequest();
    	String orderid = request.getParameter("orderid");
    	String transactionid = request.getParameter("transactionid");
//    	if(StringUtils.isBlank(orderid)){
		if(StringUtils.isBlank(orderid) ){//|| StringUtils.isBlank(transactionid)){
    		map.put("backurl", "javascript:history.go(-1)");
			return "pay/payment_failure";
    	}
    	SysCheckoutCounter bean = this.checkoutCounterService.selectByOrderid(orderid);
    	try {
    		if(bean == null || bean.getStatus() == 3){
    			logger.info("记录不存在或者失败订单");
    			map.put("backurl", "javascript:history.go(-1)");
    			return "pay/payment_failure";
    		}
    		if(bean.getStatus() == 1 || bean.getStatus() == 2){
    			map.put("backurl", bean.getReturnurl());
    			return "pay/payment_success";
    		}
			AjaxResult result = commonControlelr.uploadFile(file);
			Object fileurl = result.get("url");
			System.out.println(fileurl);
			if(fileurl == null){
				logger.info("没有上传图片");
				map.put("backurl", "javascript:history.go(-1)");
				return "pay/payment_failure";
			}
			bean.setPayurl(fileurl.toString());
			bean.setStatus(1);
			bean.setTransactionid(transactionid);
			this.checkoutCounterService.updateOnly(bean);
		} catch (Exception e) {
			logger.error("hspaySubmit error...",e);
			map.put("backurl", "javascript:history.go(-1)");
			return "pay/payment_failure";
		}
    	map.put("backurl", bean.getReturnurl());
		return "pay/payment_success";
    	
    }
}
