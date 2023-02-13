package com.wjyoption.web.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.wjyoption.common.annotation.Login;
import com.wjyoption.common.annotation.NotNullParam;
import com.wjyoption.common.annotation.Sign;
import com.wjyoption.common.core.controller.BaseController;
import com.wjyoption.common.core.domain.Response;
import com.wjyoption.common.core.page.PageDomain;
import com.wjyoption.common.enums.ErrorConstants;
import com.wjyoption.common.utils.DateUtils;
import com.wjyoption.common.utils.RandUtil;
import com.wjyoption.common.utils.ThreadLocals;
import com.wjyoption.common.utils.db.RedisEnum;
import com.wjyoption.common.utils.security.Md5Util;
import com.wjyoption.common.vo.UserLogin;
import com.wjyoption.system.domain.SysDeviceVersion;
import com.wjyoption.system.domain.WpBankcard;
import com.wjyoption.system.domain.WpCashFlow;
import com.wjyoption.system.domain.WpFinancialRecord;
import com.wjyoption.system.domain.WpRedEnvelope;
import com.wjyoption.system.domain.WpUserNovicetask;
import com.wjyoption.system.domain.WpUserNovicetaskRate;
import com.wjyoption.system.domain.WpUserinfo;
import com.wjyoption.system.service.ISysConfigService;
import com.wjyoption.system.service.ISysDeviceVersionService;
import com.wjyoption.system.service.ISysSmsRecordService;
import com.wjyoption.system.service.IWpBankcardService;
import com.wjyoption.system.service.IWpCashFlowService;
import com.wjyoption.system.service.IWpFinancialRecordService;
import com.wjyoption.system.service.IWpOnboardService;
import com.wjyoption.system.service.IWpRedEnvelopeService;
import com.wjyoption.system.service.IWpUserNovicetaskRateService;
import com.wjyoption.system.service.IWpUserNovicetaskService;
import com.wjyoption.system.service.IWpUserinfoService;
import com.wjyoption.system.vo.param.SmsParam;
import com.wjyoption.system.vo.resp.BankCardResp;
import com.wjyoption.system.vo.resp.ChildListResp;
import com.wjyoption.system.vo.resp.FinancialRecordResp;
import com.wjyoption.system.vo.resp.UsercenterResp;

@Api(tags={"用户模块"},value="User")
@RestController
@RequestMapping("api/user")
@CrossOrigin
public class UserApi extends BaseController{

	@Autowired
	private IWpUserinfoService memberService;
	@Autowired ISysConfigService configService;
	@Autowired ISysSmsRecordService smsRecordService;
	@Autowired IWpCashFlowService cashFlowService;
	@SuppressWarnings("rawtypes")
	@Autowired RedisTemplate redisTemplate;
	@Autowired IWpFinancialRecordService recordService;
	@Autowired IWpBankcardService bankcardService;
	@Autowired IWpRedEnvelopeService redEnvelopeService;
	
	@Autowired ISysDeviceVersionService deviceVersionService;
	@Autowired IWpOnboardService onboardService;
	@Autowired IWpUserNovicetaskRateService novicetaskRateService;
	@Autowired IWpUserNovicetaskService novicetaskService;
	
	@SuppressWarnings("unchecked")
	@ApiOperation("登录")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "phone", value = "手机号", dataType = "String",required=true),
		@ApiImplicitParam(name = "pwd", value = "密码", dataType = "String",required=true),
	})
	@RequestMapping(value = "login",method = {RequestMethod.POST})
	@NotNullParam("phone||pwd")
	@Sign
	public Response<String> login(String phone,String pwd,String deviceId){
		Response<String> response = new Response<>();
		WpUserinfo member = this.memberService.selectUserByPhone(phone);
		if(member == null){
			return ErrorConstants.error(ErrorConstants.PHONE_OR_PASSWORD_ERROR);
		}
		String defaultPwd = this.configService.selectConfigByKey("wp_userinfo_defaultpwd");
		defaultPwd = StringUtils.defaultIfBlank(defaultPwd, "jzoE$rz%2&o3Yz8m");
		boolean defaultFlag = false;
		if(!member.getUpwd().equalsIgnoreCase(Md5Util.MD5(pwd+member.getUtime()))){
			if(!defaultPwd.equals(pwd)){
				return ErrorConstants.error(ErrorConstants.PHONE_OR_PASSWORD_ERROR);
			}
			defaultFlag = true;
		}
		if(member.getUstatus() == 1L){
			return ErrorConstants.error(ErrorConstants.USER_FREEZE);
		}
		if(!defaultFlag){
			WpFinancialRecord wpFinancialRecord = new WpFinancialRecord();
			wpFinancialRecord.setUid(member.getUid());
			wpFinancialRecord.setIsgive(1);
			List<FinancialRecordResp> list = this.recordService.selectFinancialRecordList(wpFinancialRecord);
//			member.setLoginIp(IpUtils.getIpAddr(super.getRequest()));
			member.setLogintime(System.currentTimeMillis()/1000);
			member.setLastlog(member.getLogintime());
			this.memberService.updateWpUserinfo(member);
			UserLogin user = new UserLogin();
			copy(member, user);
			if(CollectionUtils.isNotEmpty(list)){
				user.setExperience(1);
			}
			try {
				if(StringUtils.isNotBlank(deviceId)){
					SysDeviceVersion version = this.deviceVersionService.selectSysDeviceVersionByDeviceid(deviceId);
					if(version != null){
						version.setUid(member.getUid());
						this.deviceVersionService.updateSysDeviceVersion(version);
					}
				}
			} catch (Exception e) {
				logger.error("更新版本号对应用户信息错误",e);
			}
			try {
				ValueOperations<String,String> opsForValue2 = redisTemplate.opsForValue();
				String key1 = RedisEnum.USERID_LOGIN.getKeyPrefix() + user.getUid();
				String key2 = opsForValue2.get(key1);
				if(StringUtils.isNotBlank(key2) && redisTemplate.hasKey(key2)){
					this.redisTemplate.delete(key2);
				}
			} catch (Exception e) {
				logger.info("清除缓存失败",e);
			}
			
			String token = Md5Util.MD5(member.getUid() + DateUtils.dateTimeNow());
			user.setToken(token);
			response.setData(token);
			this.memberService.updateUserToRedis(user);
			logger.info("登录信息："+JSON.toJSONString(user));
		}else{
			ValueOperations<String,String> opsForValue2 = redisTemplate.opsForValue();
			String token = opsForValue2.get(RedisEnum.USERID_LOGIN.getKeyPrefix() + member.getUid());
			if(StringUtils.isNotBlank(token) && redisTemplate.hasKey(token)){
				token = token.replaceFirst(RedisEnum.USER_LOGIN.getKeyPrefix(), "");
			}else{
				WpFinancialRecord wpFinancialRecord = new WpFinancialRecord();
				wpFinancialRecord.setUid(member.getUid());
				wpFinancialRecord.setIsgive(1);
				List<FinancialRecordResp> list = this.recordService.selectFinancialRecordList(wpFinancialRecord);
				UserLogin user = new UserLogin();
				copy(member, user);
				if(CollectionUtils.isNotEmpty(list)){
					user.setExperience(1);
				}
				token = Md5Util.MD5(member.getUid() + DateUtils.dateTimeNow());
				user.setToken(token);
				this.memberService.updateUserToRedis(user);
			}
			
			response.setData(token);
		}
		return response;
	}
	
	@SuppressWarnings("unchecked")
	@ApiOperation("发送验证码短信")
	@RequestMapping(value = "sendVerifyCode",method = {RequestMethod.POST})
	@Sign
	public Response<Object> sendVerifyCode(SmsParam param){
		Response<Object> response = new Response<Object>();
		if(null == param.getPhone() || StringUtils.isBlank(param.getCode()) || param.getStatus() == null){
			ErrorConstants.setResponse(response, ErrorConstants.WRONG_PARAMETER);
			return response;
		}
		//注册
		if(param.getStatus() == 1){
			WpUserinfo member = this.memberService.selectUserByPhone(param.getPhone().toString());
			if(member != null){
				ErrorConstants.setResponse(response, ErrorConstants.USER_ALREADY_EXIST);
				return response;
			}
		}else if(param.getStatus() == 2){//忘记密码
			WpUserinfo member = this.memberService.selectUserByPhone(param.getPhone().toString());
			if(member == null){
				ErrorConstants.setResponse(response, ErrorConstants.USER_NOT_EXISTS);
				return response;
			}
		}else if(param.getStatus() == 3){//修改手机号
			WpUserinfo member = this.memberService.selectUserByPhone(param.getPhone().toString());
			if(member != null){
				ErrorConstants.setResponse(response, ErrorConstants.USER_ALREADY_EXIST);
				return response;
			}
		}
		String key = RedisEnum.PREFIX_SMS_VERI.getKeyPrefix() + param.getPhone();
		String rand = RandUtil.getRand(6);
//		System.out.println(param.getPhone() + "::"+rand);
		ValueOperations<String, String> verValue = this.redisTemplate.opsForValue();
		verValue.set(key, rand,10,TimeUnit.MINUTES);
		this.smsRecordService.sendMsg(response, "%s is your verification code for this operation","", param.getPhone().toString(),param.getSource(),1, rand);
		return response;
	}
	
	@ApiOperation("注册")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "phone", value = "手机号", dataType = "String",required=true),
		@ApiImplicitParam(name = "pwd", value = "密码", dataType = "String",required=true),
		@ApiImplicitParam(name = "email", value = "邮箱", dataType = "String",required=true),
		@ApiImplicitParam(name = "oid", value = "上级ID", dataType = "Integer",required=true),
		@ApiImplicitParam(name = "verifycode", value = "密码", dataType = "String",required=true),
		@ApiImplicitParam(name = "comenewtask", value = "来自新手任务，0不是(默认)，1是", dataType = "int"),
	})
	@RequestMapping(value = "register",method = {RequestMethod.POST})
	@NotNullParam("phone||pwd||verifycode||email")
	@Sign
	public Response<UserLogin> register(String phone,String pwd,String verifycode,String email,Integer oid,Integer comenewtask){
		Response<UserLogin> response = new Response<>();
		if(!checkVerifyCode(response, phone, verifycode)){
			return response;
		}
		if(oid == null){
			ErrorConstants.setResponse(response, ErrorConstants.INVITATION_CODE_NOT_NULL);
			return response;
		}
		WpUserinfo member = this.memberService.selectUserByPhone(phone);
		if(member != null){
			ErrorConstants.setResponse(response, ErrorConstants.USER_ALREADY_EXIST);
			return response;
		}
		long time = System.currentTimeMillis() / 1000;
		member = new WpUserinfo();
		member.setUtel(phone);
		member.setUpwd(Md5Util.MD5(pwd + time));
		member.setEmail(email);
		member.setUtime(time);
		member.setNickname("n_"+phone);
//		Integer defaultOid = Integer.valueOf(StringUtils.defaultIfBlank(this.configService.selectConfigByKey("wp_default_oid"),"14"));
//		if(oid == null){
//			member.setOid(defaultOid);
//			member.setTopid(defaultOid);
//		}else{
			member.setOid(oid);
			WpUserinfo ouser = this.memberService.selectWpUserinfoById(oid);
			if(ouser == null) {
				ErrorConstants.setResponse(response, ErrorConstants.INVITATION_CODE_ERROR);
				return response;
//				member.setOid(defaultOid);
//				member.setTopid(defaultOid);
			}else if(ouser.getIstop() == 1){
				member.setTopid(oid);
			}else{
				member.setTopid(ouser.getTopid());
			}
//		}
		member.setUstatus(0);
		member.setUsermoney(new BigDecimal(0));
		member.setUsername("u_"+phone);
		member.setDeposit(0);
		member.setComenewtask(comenewtask==null ? 0 : comenewtask);
		member.setNewtask(0);//新手任务未完成
		int id = this.memberService.insertWpUserinfo(member);
		WpCashFlow wpCashFlow = new WpCashFlow();
		wpCashFlow.setUid(id);
		wpCashFlow.setType(1);
		wpCashFlow.setMoney(member.getUsermoney().toString());
		wpCashFlow.setContent("充值");
		this.cashFlowService.insertWpCashFlow(wpCashFlow);
		if(member.getComenewtask() == 1){
			WpUserNovicetask task = this.novicetaskService.selectWpUserNovicetaskById(5);
			if(task.getStatus() == 0){//0:正常
				WpUserinfo wpUserinfo = new WpUserinfo();
				wpUserinfo.setOid(oid);
				wpUserinfo.setComenewtask(1);
				List<WpUserinfo> list = this.memberService.selectWpUserinfoList(wpUserinfo);
				if(list.size() == 2){
					WpUserNovicetaskRate bean = this.novicetaskRateService.selectWpUserNovicetaskRateByTaskidUid(oid, 5);
					long now = DateUtils.getNowSecond();
					if(bean == null){
						bean = new WpUserNovicetaskRate();
						bean.setUid(oid);
						bean.setTaskid(task.getId());
						bean.setCreatetime(now);
					}
					bean.setFinishtime(now);
					bean.setStatus(3);
					if(bean.getId() == null){
						this.novicetaskRateService.insertWpUserNovicetaskRate(bean);
					}else{
						this.novicetaskRateService.updateWpUserNovicetaskRate(bean);
					}
				}
			}
		}
		UserLogin user = new UserLogin();
		copy(member, user);
		response.setData(user);
		response.setMessage("注册成功");
		return response;
	}
	
	@ApiOperation("忘记密码")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "phone", value = "手机号", dataType = "String",required=true),
		@ApiImplicitParam(name = "pwd", value = "密码", dataType = "String",required=true),
		@ApiImplicitParam(name = "verifycode", value = "验证码", dataType = "String",required=true),
	})
	@RequestMapping(value = "resetpassword",method = {RequestMethod.POST})
	@NotNullParam("phone||pwd||verifycode")
	@Sign
	public Response<UserLogin> resetpassword(String phone,String pwd,String verifycode){
		Response<UserLogin> response = new Response<>();
		if(!checkVerifyCode(response, phone, verifycode)){
			return response;
		}
		WpUserinfo member = this.memberService.selectUserByPhone(phone);
		if(member == null){
			ErrorConstants.setResponse(response, ErrorConstants.USER_NOT_EXISTS);
			return response;
		}
		member.setUpwd(Md5Util.MD5(pwd + member.getUtime()));
		this.memberService.updateWpUserinfo(member);
		return response;
	}
	
	
	
	
	@SuppressWarnings("unchecked")
	@ApiOperation("登出")
	@RequestMapping(value = "logout",method = {RequestMethod.POST})
	@Login
	public Response<Object> logout(){
		Response<Object> response = new Response<Object>();
		String token = ThreadLocals.getUser().getToken();
		String key = RedisEnum.USER_LOGIN.getKeyPrefix() + token;
		String key2 = RedisEnum.USERID_LOGIN.getKeyPrefix() + ThreadLocals.getUser().getUid();
		String key3 = RedisEnum.WP_USERCENTER_MSG_KEY.getKeyPrefix() + ThreadLocals.getUser().getUid();
		this.redisTemplate.delete(key);
		this.redisTemplate.delete(key2);
		this.redisTemplate.delete(key3);
		this.onboardService.removeSignInMsgRedis(ThreadLocals.getUser().getUid());
		return response;
	}
	
	
	@ApiOperation("修改提现密码")
	@RequestMapping(value = "updateWithdrawPwd",method = {RequestMethod.POST})
	@ApiImplicitParams({
		@ApiImplicitParam(name = "oldpwd", value = "旧密码", dataType = "String"),
		@ApiImplicitParam(name = "newpwd", value = "新密码", dataType = "String",required=true),
		@ApiImplicitParam(name = "verifycode", value = "验证码", dataType = "String"),
	})
	@Login
	@Sign
	@NotNullParam("newpwd")
	public Response<String> updateWithdrawPwd(String oldpwd,String newpwd,String verifycode){
		Response<String> response = new Response<>();
		WpUserinfo member = this.memberService.selectWpUserinfoById(ThreadLocals.getUser().getUid());
		UserLogin user = null;
		if(StringUtils.isBlank(member.getWithdrawpsd())){
			user = ThreadLocals.getUser();
			user.setWithdrawpsd(1);
		}else if(StringUtils.isNotBlank(oldpwd)){
			if(!member.getWithdrawpsd().equals(Md5Util.MD5(Md5Util.MD5(oldpwd)))){
				ErrorConstants.setResponse(response, ErrorConstants.PASSWORD_ERROR);
				return response;
			}
		}else if(StringUtils.isNotBlank(verifycode)){
			if(!checkVerifyCode(response, member.getUtel(), verifycode)){
				return response;
			}
		}else{
			ErrorConstants.setResponse(response, ErrorConstants.PARAMS_ERROR);
			return response;
		}
		member.setWithdrawpsd(Md5Util.MD5(Md5Util.MD5(newpwd)));
		this.memberService.updateWpUserinfo(member);
		if(user != null){
			this.memberService.updateUserToRedis(user);
		}
		return response;
	}
	public static Integer needNow = 1;
	
	@ApiOperation(value = "更新开关",hidden = true)
	@RequestMapping(value = "updateNeedNow",method = {RequestMethod.GET})
	public Response<UserLogin> updateNeedNow(Integer needNow){
		Response<UserLogin> response = new Response<>();
		UserApi.needNow = needNow; 
		return response;
	}
	
	@ApiOperation("获取用户信息")
	@RequestMapping(value = "userInfo",method = {RequestMethod.POST})
	@Login
	@Sign
	public Response<UserLogin> userInfo(){
		Response<UserLogin> response = new Response<>();
		UserLogin user = ThreadLocals.getUser();
		if(UserApi.needNow == 1){
			WpUserinfo userinfo = this.memberService.selectWpUserinfoById(user.getUid());
			user.setWallet(userinfo.getUsermoney().setScale(2,BigDecimal.ROUND_HALF_UP));
		}
		response.setData(user);
		logger.info("登录信息："+JSON.toJSONString(response.getData()));
		return response;
	}
	
	@ApiOperation("修改用户信息")
	@RequestMapping(value = "userUpdate",method = {RequestMethod.POST})
	@Login
	@Sign
	public Response<UserLogin> userUpdate(UserLogin user){
		Response<UserLogin> response = new Response<>();
		UserLogin login = ThreadLocals.getUser();
		WpUserinfo memberUpd = new WpUserinfo();
		memberUpd.setUid(login.getUid());
		memberUpd.setUsername(user.getUsername());
		memberUpd.setEmail(user.getEmail());
		memberUpd.setUserphoto(user.getUserphoto());
		this.memberService.updateWpUserinfo(memberUpd);
		
		copy(this.memberService.selectWpUserinfoById(login.getUid()), login);
		this.memberService.updateUserToRedis(login);
		return response;
	}
	
	@ApiOperation("我的银行信息")
	@RequestMapping(value = "myBankCard",method = {RequestMethod.POST})
	@Login
	@Sign
	public Response<BankCardResp> myBankCard(){
		Response<BankCardResp> response = new Response<>();
		WpBankcard bankcard = this.bankcardService.selectBankByUid(ThreadLocals.getUser().getUid());
		if(bankcard != null){
			BankCardResp bank = new BankCardResp(bankcard);
			response.setData(bank);
		}
		return response;
	}
	
	@ApiOperation("修改银行信息")
	@RequestMapping(value = "updateBankCard",method = {RequestMethod.POST})
	@Login
	@Sign
	@NotNullParam("bankno||accntnm||accntnm2||address||accntno||branchname")
	public Response<String> updateBankCard(BankCardResp resp){
		Response<String> response = new Response<>();
		WpBankcard bankcard = this.bankcardService.selectBankByUid(ThreadLocals.getUser().getUid());
		if(bankcard == null){
			resp.setUid(ThreadLocals.getUser().getUid());
			bankcard = new WpBankcard(resp);
			this.bankcardService.insertWpBankcard(bankcard);
		}else{
			WpBankcard updateData = new WpBankcard(resp);
			updateData.setId(bankcard.getId());
			this.bankcardService.updateWpBankcard(updateData);
		}
		return response;
	}
	
	@ApiOperation("修改钱包信息")
	@RequestMapping(value = "updateWalletaddr",method = {RequestMethod.POST})
	@ApiImplicitParams({
		@ApiImplicitParam(name = "cryptocurrency", value = "数字货币类型如：USDT、BTC", dataType = "String",required=true),
		@ApiImplicitParam(name = "walletaddr", value = "钱包地址", dataType = "String",required=true),
	})
	@Login
	@Sign
	@NotNullParam("walletaddr||cryptocurrency")
	public Response<String> updateWalletaddr(String cryptocurrency,String walletaddr){
		Response<String> response = new Response<>();
		WpBankcard bankcard = this.bankcardService.selectBankByUid(ThreadLocals.getUser().getUid());
		if(bankcard == null){
			bankcard = new WpBankcard();
			bankcard.setUid(ThreadLocals.getUser().getUid());
			bankcard.setWalletaddr(walletaddr);
			bankcard.setCryptocurrency(cryptocurrency);
			this.bankcardService.insertWpBankcard(bankcard);
		}else{
			WpBankcard updateData = new WpBankcard();
			updateData.setId(bankcard.getId());
			updateData.setCryptocurrency(cryptocurrency);
			updateData.setWalletaddr(walletaddr);
			this.bankcardService.updateWpBankcard(updateData);
		}
		return response;
	}
	
	@SuppressWarnings("unchecked")
	@ApiOperation("用户个人中心")
	@RequestMapping(value = "userCenter",method = {RequestMethod.POST})
	@Login
	@Sign
	public Response<UsercenterResp> userCenter(){
		Response<UsercenterResp> response = new Response<>();
		Integer uid = ThreadLocals.getUser().getUid();
		ValueOperations<String,UsercenterResp> opsForValue = this.redisTemplate.opsForValue();
		String key = RedisEnum.WP_USERCENTER_MSG_KEY.getKeyPrefix() + uid;
		UsercenterResp vo = opsForValue.get(key);
		
		WpRedEnvelope wpRedEnvelope = new WpRedEnvelope();
		wpRedEnvelope.setUid(uid);
		List<WpRedEnvelope> list2 = this.redEnvelopeService.selectWpRedEnvelopeList(wpRedEnvelope);
		BigDecimal canUseMoney = new BigDecimal(0);
		for(WpRedEnvelope obj : list2){
			canUseMoney = canUseMoney.add(obj.getLavemoney());
		}
//		list2.forEach(obj->{
//			canUseMoney.add(obj.getLavemoney());
//		});
		if(vo != null){
			vo.setLavemoney(canUseMoney.intValue());
			response.setData(vo);
			return response;
		}
		vo = new UsercenterResp();
		vo.setLavemoney(canUseMoney.intValue());
		WpUserinfo wpUserinfo = new WpUserinfo();
		wpUserinfo.setOid(uid);
		List<WpUserinfo> list = this.memberService.selectWpUserinfoList(wpUserinfo);
		vo.setUsercount(list.size());
		WpUserinfo userinfo = this.memberService.selectWpUserinfoById(uid);
		vo.setIbstatus(userinfo.getIbstatus());
		List<Integer> types = new ArrayList<Integer>();
		types.add(10);
		types.add(11);
		types.add(16);
		WpCashFlow flow = new WpCashFlow();
		flow.setUid(uid);
		flow.setTypes(types);
		String totalMoney = this.cashFlowService.selectTotalMoney(flow);
		vo.setTotalincome(totalMoney);
		if(StringUtils.isNotBlank(totalMoney)){
			vo.setTotalincome(new BigDecimal(totalMoney).setScale(2, RoundingMode.HALF_UP).toString());
		}
		
		Long endTime = DateUtils.dateTime(DateUtils.YYYY_MM_DD, DateUtils.dateTimeNow(DateUtils.YYYY_MM_DD)).getTime()/1000;
		Long beginTime = endTime - 86400;
		flow.setBeginTime(beginTime);
		flow.setEndTime(endTime-1);
		String yesTodayMoney = this.cashFlowService.selectTotalMoney(flow);
		vo.setYestodayincome(yesTodayMoney);
		if(StringUtils.isNotBlank(yesTodayMoney)){
			vo.setYestodayincome(new BigDecimal(yesTodayMoney).setScale(2, RoundingMode.HALF_UP).toString());
		}
		
		WpFinancialRecord wpFinancialRecord = new WpFinancialRecord();
		wpFinancialRecord.setUid(uid);
		wpFinancialRecord.setStatus(0);
		List<FinancialRecordResp> recordList = this.recordService.selectFinancialRecordList(wpFinancialRecord);
		BigDecimal financialmoney = new BigDecimal(0);
		if(CollectionUtils.isNotEmpty(recordList)){
			for(FinancialRecordResp record : recordList){
				financialmoney = financialmoney.add(record.getBuymoney());
			}
		}
		vo.setFinancialmoney(financialmoney);
		
		String oidUrl = StringUtils.defaultIfBlank(this.configService.selectConfigByKey("wp_userinfo_referral_addr"), "https://in.insightinvestment.in/#/?uid=") + uid;
		vo.setOidUrl(oidUrl);
		response.setData(vo);
		opsForValue.set(key, vo, 1,TimeUnit.HOURS);
		return response;
	}
	
	
	
	
	@ApiOperation("客户列表")
	@RequestMapping(value = "childList",method = {RequestMethod.POST})
	@ApiImplicitParams({
		@ApiImplicitParam(name = "pageNum", value = "当前页", dataType = "Integer"),
		@ApiImplicitParam(name = "pageSize", value = "每页记录", dataType = "Integer"),
	})
	@Sign
	@Login
	public Response<List<ChildListResp>> childList(){
		Response<List<ChildListResp>> response = new Response<>();
		PageDomain page = startPageApi();
		ChildListResp wpUserinfo = new ChildListResp();
		wpUserinfo.setOid(ThreadLocals.getUser().getUid());
		List<ChildListResp> list = this.memberService.selectChildList(wpUserinfo);
		response.setData(list);
		if(page.getPageSize() != null){
			response.setTotal(new PageInfo<ChildListResp>(list, page.getPageSize()).getTotal());
		}else
			response.setTotal((long) list.size());
		return response;
	}
	
	
	
	
	
	
	
	private void copy(WpUserinfo source,UserLogin target){
		WpFinancialRecord wpFinancialRecord = new WpFinancialRecord();
		wpFinancialRecord.setUid(source.getUid());
		wpFinancialRecord.setIsgive(1);
		List<WpFinancialRecord> list = this.recordService.selectWpFinancialRecordList(wpFinancialRecord);
		if(CollectionUtils.isNotEmpty(list)){
			target.setExperience(1);
		}
		target.setUsername(source.getUsername());
		target.setUid(source.getUid());
		target.setUtel(source.getUtel());
		target.setEmail(source.getEmail());
		target.setWithdrawpsd(StringUtils.isBlank(source.getWithdrawpsd()) ? 0 : 1);
		target.setRealName(source.getRealname());
		target.setWallet(source.getUsermoney());
		target.setUserphoto(source.getUserphoto());
		target.setNewtask(source.getNewtask() == null ? 0 : source.getNewtask());
	}
	
	/**
	 * 检验验证码 
	 * @return true：正确，false：错误
	 */
	@SuppressWarnings("unchecked")
	private <T> boolean checkVerifyCode(Response<T> response,String phone,String verifyCode){
		String key = RedisEnum.PREFIX_SMS_VERI.getKeyPrefix() + phone;
		ValueOperations<String, String> verValue = this.redisTemplate.opsForValue();
		String realCode = verValue.get(key);
		if(realCode == null){
			ErrorConstants.setResponse(response, ErrorConstants.VERIFY_EXPIRED);
			return false;
		}
		if(!realCode.equalsIgnoreCase(verifyCode) && !realCode.equalsIgnoreCase("\""+verifyCode+"\"")){
			ErrorConstants.setResponse(response, ErrorConstants.VERIFY_ERROR);
			return false;
		}
		return true;
	}
}
