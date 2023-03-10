package com.wjyoption.web.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.wjyoption.common.annotation.Login;
import com.wjyoption.common.annotation.NotNullParam;
import com.wjyoption.common.annotation.Sign;
import com.wjyoption.common.core.controller.BaseController;
import com.wjyoption.common.core.domain.Response;
import com.wjyoption.common.core.page.PageDomain;
import com.wjyoption.common.enums.ErrorConstants;
import com.wjyoption.common.utils.ThreadLocals;
import com.wjyoption.common.utils.db.RedisEnum;
import com.wjyoption.system.domain.SysDictData;
import com.wjyoption.system.domain.WpCashFlow;
import com.wjyoption.system.domain.WpCheckinRewardDaily;
import com.wjyoption.system.domain.WpFinancialRecord;
import com.wjyoption.system.domain.WpPriceLog;
import com.wjyoption.system.service.ISysConfigService;
import com.wjyoption.system.service.ISysDictDataService;
import com.wjyoption.system.service.IWpCashFlowService;
import com.wjyoption.system.service.IWpCheckinRewardDailyService;
import com.wjyoption.system.service.IWpCheckinRewardService;
import com.wjyoption.system.service.IWpCheckinRewardUserService;
import com.wjyoption.system.service.IWpFinancialBuyService;
import com.wjyoption.system.service.IWpFinancialDetailService;
import com.wjyoption.system.service.IWpFinancialRecordService;
import com.wjyoption.system.service.IWpFinancialTypeService;
import com.wjyoption.system.service.IWpOnboardService;
import com.wjyoption.system.service.IWpPriceLogService;
import com.wjyoption.system.service.IWpUserinfoService;
import com.wjyoption.system.vo.param.FinancialTypeParam;
import com.wjyoption.system.vo.resp.CashFlowResp;
import com.wjyoption.system.vo.resp.CheckinRewardUserResp;
import com.wjyoption.system.vo.resp.FinancialBuyResp;
import com.wjyoption.system.vo.resp.FinancialDetailResp;
import com.wjyoption.system.vo.resp.FinancialRecordResp;
import com.wjyoption.system.vo.resp.FinancialTypeResp;
import com.wjyoption.system.vo.resp.FinancialTypeTypeResp;
import com.wjyoption.system.vo.resp.HomeMsgResp;
import com.wjyoption.system.vo.resp.PriceLogResp;
import com.wjyoption.system.vo.resp.SignInMsgResp;


@Api(value="????????????",tags={"????????????"})
@RestController
@RequestMapping("api/financial")
@CrossOrigin
public class FinancialApi extends BaseController{

	@Autowired IWpFinancialTypeService financialTypeService;
	@Autowired ISysDictDataService dictDataService;
	@Autowired IWpFinancialRecordService recordService;
	@Autowired IWpUserinfoService userinfoService;
	@Autowired IWpFinancialDetailService detailService;
	@Autowired IWpFinancialBuyService buyService;
	@Autowired ISysConfigService configService;
	@Autowired IWpPriceLogService priceLogService;
	@Autowired IWpCashFlowService cashFlowService;
	@Autowired IWpOnboardService onboardService;
	@Autowired IWpCheckinRewardDailyService checkinRewardDailyService;
	@Autowired IWpCheckinRewardUserService checkinRewardUserService;
	@Autowired IWpCheckinRewardService checkinRewardService;
	
	@SuppressWarnings("rawtypes")
	@Autowired RedisTemplate redisTemplate;
	
	@Value("${wjyoption.symbol}")
	private String symbol;
	
	@ApiOperation("????????????????????????")
	@PostMapping("queryTypeList")
	@Sign
	public Response<List<FinancialTypeTypeResp>> queryTypeList(){
		List<SysDictData> list2 = this.dictDataService.selectDictDataByType("wp_financial_type_type");
		Response<List<FinancialTypeTypeResp>> response = new Response<>();
		List<FinancialTypeTypeResp> retList = new ArrayList<>();
		list2.forEach(obj -> {
			retList.add(new FinancialTypeTypeResp(obj.getDictLabel(), obj.getDictValue()));
		});
		response.setData(retList);
		return response;
	}
	
	@ApiOperation("??????????????????")
    @PostMapping("queryFinancialTypeList")
	@Sign
	public Response<List<FinancialTypeResp>> queryFinancialTypeList(FinancialTypeParam param){
		Response<List<FinancialTypeResp>> response = new Response<>();
		PageDomain page = startPageApi();
		param.setStatus(1);
		List<FinancialTypeResp> list = this.financialTypeService.selectFinancialTypeResp(param);
		response.setData(list);
		if(page.getPageSize() != null){
			response.setTotal(new PageInfo<FinancialTypeResp>(list, page.getPageSize()).getTotal());
		}else
			response.setTotal((long) list.size());
		return response;
	}
	
	
	@ApiOperation("????????????")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "typeid", value = "????????????id", dataType = "Integer",required=true),
		@ApiImplicitParam(name = "money", value = "????????????", dataType = "Integer",required=true),
		@ApiImplicitParam(name = "withdrawpsd", value = "????????????(MD5???)", dataType = "String"),
		@ApiImplicitParam(name = "comenewtask", value = "?????????????????????0??????(??????)???1???", dataType = "Integer"),
	})
	@RequestMapping(value = "buy",method = {RequestMethod.POST})
	@Sign
	@Login
	@NotNullParam("typeid||money")
	public Response<Object> buy(Integer typeid,Integer money,Integer comenewtask,String withdrawpsd){
		Response<Object> response = new Response<>();
		if(money % 1000 != 0 ){
			ErrorConstants.setResponse(response, ErrorConstants.MONEY_ERROR);
			return response;
		}
//		WpUserinfo userinfo = this.userinfoService.selectWpUserinfoById(ThreadLocals.getUser().getUid());
//		if(!Md5Util.MD5(Md5Util.MD5(withdrawpsd)).equals(userinfo.getWithdrawpsd())){
//			ErrorConstants.setResponse(response, ErrorConstants.PASSWORD_ERROR);
//			return response;
//		}
		synchronized (ThreadLocals.getUser().getUid()) {
			this.recordService.buy(typeid,money,comenewtask == null ? 0 : comenewtask,response);
		}
		return response;
	}
	
	@ApiOperation("??????????????????")
	@RequestMapping(value = "freebuy",method = {RequestMethod.POST})
	@Sign
	@Login
//	@NotNullParam("typeid")
	public Response<Object> freebuy(Integer typeid){
		Response<Object> response = new Response<>();
		if(typeid == null){
			typeid = 1;
		}
		synchronized (ThreadLocals.getUser().getUid()) {
			this.recordService.freebuy(typeid,response);
		}
		return response;
	}
	
	@ApiOperation("??????????????????")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "status", value = "?????????0???????????????1????????????", dataType = "Integer"),
		@ApiImplicitParam(name = "pageNum", value = "?????????", dataType = "Integer"),
		@ApiImplicitParam(name = "pageSize", value = "????????????", dataType = "Integer"),
		@ApiImplicitParam(name = "orderByColumn", value = "?????????????????? id", dataType = "String"),
		@ApiImplicitParam(name = "isAsc", value = "????????????->?????????asc,?????????desc", dataType = "String"),
	})
	@RequestMapping(value = "myFinancial",method = {RequestMethod.POST})
	@Sign
	@Login
	public Response<List<FinancialRecordResp>> myFinancial(Integer status){
		Response<List<FinancialRecordResp>> response = new Response<>();
		PageDomain page = startPageApi();
		WpFinancialRecord record = new WpFinancialRecord();
		record.setUid(ThreadLocals.getUser().getUid());
		record.setStatus(status);
		List<FinancialRecordResp> list = this.recordService.selectFinancialRecordList(record);
		response.setData(list);
		if(page.getPageSize() != null){
			response.setTotal(new PageInfo<FinancialRecordResp>(list, page.getPageSize()).getTotal());
		}else
			response.setTotal((long) list.size());
		return response;
	}
	
	@ApiOperation("????????????")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "refid", value = "?????????", dataType = "Integer",required = true),
		@ApiImplicitParam(name = "pageNum", value = "?????????", dataType = "Integer"),
		@ApiImplicitParam(name = "pageSize", value = "????????????", dataType = "Integer"),
	})
	@RequestMapping(value = "financialDetail",method = {RequestMethod.POST})
	@Sign
	@Login
	@NotNullParam("refid")
	public Response<List<FinancialDetailResp>> financialDetail(Integer refid){
		Response<List<FinancialDetailResp>> response = new Response<>();
		PageDomain page = startPageApi();
		List<FinancialDetailResp> list = this.detailService.selectFinancialDetailList(refid);
		response.setData(list);
		if(page.getPageSize() != null){
			response.setTotal(new PageInfo<FinancialDetailResp>(list, page.getPageSize()).getTotal());
		}else
			response.setTotal((long) list.size());
		return response;
	}
	
	@ApiOperation("????????????????????????")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "detailid", value = "?????????", dataType = "Integer",required = true),
		@ApiImplicitParam(name = "pageNum", value = "?????????", dataType = "Integer"),
		@ApiImplicitParam(name = "pageSize", value = "????????????", dataType = "Integer"),
	})
	@RequestMapping(value = "buyDetail",method = {RequestMethod.POST})
	@Sign
	@Login
	@NotNullParam("detailid")
	public Response<List<FinancialBuyResp>> buyDetail(Integer detailid){
		Response<List<FinancialBuyResp>> response = new Response<>();
		PageDomain page = startPageApi();
		List<FinancialBuyResp> list = this.buyService.selectFinancialBuyList(detailid);
		response.setData(list);
		if(page.getPageSize() != null){
			response.setTotal(new PageInfo<FinancialBuyResp>(list, page.getPageSize()).getTotal());
		}else
			response.setTotal((long) list.size());
		return response;
	}
	
	@ApiOperation("????????????")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "????????????ID", dataType = "Integer",required = true),
	})
	@RequestMapping(value = "redemption",method = {RequestMethod.POST})
	@Sign
	@Login
	@NotNullParam("id")
	public Response<Object> redemption(Integer id){
		Response<Object> response = new Response<>();
		synchronized (ThreadLocals.getUser().getUid()) {
			this.recordService.redemption(id,response);
		}
		return response;
	}
	
	@ApiOperation("????????????")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "????????????ID", dataType = "Integer",required = true),
	})
	@RequestMapping(value = "overBySelf",method = {RequestMethod.POST})
	@Sign
	@Login
	@NotNullParam("id")
	public Response<Object> overBySelf(Integer id){
		Response<Object> response = new Response<>();
		synchronized (ThreadLocals.getUser().getUid()) {
			this.recordService.overBySelf(id,response);
		}
		return response;
	}
	
	
	@SuppressWarnings("unchecked")
	@ApiOperation("??????????????????")
	@RequestMapping(value = "homeMsg",method = {RequestMethod.POST})
	@ApiImplicitParams({
		@ApiImplicitParam(name = "pageNum", value = "?????????", dataType = "Integer"),
		@ApiImplicitParam(name = "pageSize", value = "????????????", dataType = "Integer"),
		@ApiImplicitParam(name = "orderByColumn", value = "?????????????????? id", dataType = "String"),
		@ApiImplicitParam(name = "isAsc", value = "????????????->?????????asc,?????????desc", dataType = "String"),
	})
	@Sign
//	@Login
	public Response<HomeMsgResp> homeMsg(){
		Response<HomeMsgResp> response = new Response<HomeMsgResp>();
		ValueOperations<String,HomeMsgResp> opsForValue = this.redisTemplate.opsForValue();
		String key = RedisEnum.HOME_MSG_KEY.getKeyPrefix();
		HomeMsgResp resp = opsForValue.get(key);
		if(resp != null){
			response.setData(resp);
			return response;
		}
		String allUser = StringUtils.defaultIfBlank(this.configService.selectConfigByKey("swift_homemsg_alluser"),"170000");
		String allBuy = StringUtils.defaultIfBlank(this.configService.selectConfigByKey("swift_homemsg_allbuy"),"23000000000");
		String allProfit = StringUtils.defaultIfBlank(this.configService.selectConfigByKey("swift_homemsg_allprofit"),"17000000");
		startPageApi();
		HomeMsgResp vo = new HomeMsgResp();
		vo.setAllBuy(Long.valueOf(allBuy));
		vo.setAllProfit(Long.valueOf(allProfit));
		vo.setAllUser(Long.valueOf(allUser));
		WpFinancialRecord wpFinancialRecord = new WpFinancialRecord();
		wpFinancialRecord.setStatus(0);
		List<FinancialRecordResp> list = this.recordService.selectFinancialRecordList(wpFinancialRecord);
		List<String> list2 = new ArrayList<String>();
		list.forEach(obj -> {
			list2.add(obj.getUtel().substring(0,3) + "****" + obj.getUtel().substring(obj.getUtel().length() - 3) + 
					"," + this.symbol + " " + (obj.getBuymoney().add(obj.getCreditmoney()).intValue()) + " have been invested");
		});
		vo.setScrollMsg(list2);
		response.setData(vo);
		opsForValue.set(key, vo,1,TimeUnit.HOURS);
		return response;
	}
	
	@ApiOperation("????????????")
	@RequestMapping(value = "priceLog",method = {RequestMethod.POST})
	@ApiImplicitParams({
		@ApiImplicitParam(name = "pageNum", value = "?????????", dataType = "Integer"),
		@ApiImplicitParam(name = "pageSize", value = "????????????", dataType = "Integer"),
		@ApiImplicitParam(name = "orderByColumn", value = "?????????????????? id", dataType = "String"),
		@ApiImplicitParam(name = "isAsc", value = "????????????->?????????asc,?????????desc", dataType = "String"),
	})
	@Sign
	@Login
	public Response<List<PriceLogResp>> priceLog(){
		Response<List<PriceLogResp>> response = new Response<>();
		PageDomain page = startPageApi();
		WpPriceLog wpPriceLog = new WpPriceLog();
		wpPriceLog.setUid(ThreadLocals.getUser().getUid());
		List<PriceLogResp> list = this.priceLogService.selectPriceLogList(wpPriceLog);
		response.setData(list);
		if(page.getPageSize() != null){
			response.setTotal(new PageInfo<PriceLogResp>(list, page.getPageSize()).getTotal());
		}else
			response.setTotal((long) list.size());
		return response;
	}
	
	
	@ApiOperation("????????????")
	@RequestMapping(value = "cashFlow",method = {RequestMethod.POST})
	@ApiImplicitParams({
		@ApiImplicitParam(name = "type", value = "??????  1  ??????"
    		+ "2  ????????????"          
    + " 3  ????????????"         
    + " 4  ????????????"         
    + " 5  ????????????"         
    + " 6  ????????????"         
    + " 7  ????????????"         
    + " 8  ??????????????????"    
    + " 9  ???????????? "         
    + "10  ??????IB????????????"  
    + "11  ??????IB????????????"  
    + "12  ??????????????????"    
    + "13  ????????????"          
    + "14  ??????????????????"    
    + "15  ?????????????????? "   
    + "16  ??????IB????????????", dataType = "Integer"),
		@ApiImplicitParam(name = "existsType", value = "????????????????????????????????? ??????1,2,3", dataType = "String"),
		@ApiImplicitParam(name = "types", value = "?????????????????????????????? ??????1,2,3", dataType = "String"),
		@ApiImplicitParam(name = "myself", value = "???????????? 1?????????(??????)???2?????????", dataType = "Integer"),
		@ApiImplicitParam(name = "pageNum", value = "?????????", dataType = "Integer"),
		@ApiImplicitParam(name = "pageSize", value = "????????????", dataType = "Integer"),
		@ApiImplicitParam(name = "orderByColumn", value = "?????????????????? id", dataType = "String"),
		@ApiImplicitParam(name = "isAsc", value = "????????????->?????????asc,?????????desc", dataType = "String"),
	})
	@Sign
	@Login
	public Response<List<CashFlowResp>> cashFlow(Integer myself,Integer type,String existsType,String types){
		Response<List<CashFlowResp>> response = new Response<>();
		PageDomain page = startPageApi();
		CashFlowResp wpCashFlow = new CashFlowResp();
		if(myself != null && myself == 2){
			wpCashFlow.setOid(ThreadLocals.getUser().getUid());
		}else{
			wpCashFlow.setUid(ThreadLocals.getUser().getUid());
		}
		if(type != null){
			wpCashFlow.setType(type);
		}
		if(StringUtils.isNotBlank(existsType)){
			wpCashFlow.setExistsType(existsType);
		}
		if(StringUtils.isNotBlank(types)){
			String[] typess = types.split(",");
			wpCashFlow.setTypes(new ArrayList<Integer>());
			for(String t : typess){
				wpCashFlow.getTypes().add(Integer.valueOf(t));
			}
		}
		List<CashFlowResp> list = this.cashFlowService.selectCashFlowList(wpCashFlow);
		response.setData(list);
		if(page.getPageSize() != null){
			response.setTotal(new PageInfo<CashFlowResp>(list, page.getPageSize()).getTotal());
		}else
			response.setTotal((long) list.size());
		return response;
	}
	
	@ApiOperation("?????????????????????")
	@RequestMapping(value = "cashFlowTotalMoney",method = {RequestMethod.POST})
	@ApiImplicitParams({
		@ApiImplicitParam(name = "types", value = "?????????????????????????????????<br/>"
				+ "1  ??????"
				+ "2  ????????????"          
				+ " 3  ????????????"         
				+ " 4  ????????????"         
				+ " 5  ????????????"         
				+ " 6  ????????????"         
				+ " 7  ????????????"         
				+ " 8  ??????????????????"    
				+ " 9  ???????????? "         
				+ "10  ??????IB????????????"  
				+ "11  ??????IB????????????"  
				+ "12  ??????????????????"    
				+ "13  ????????????"          
				+ "14  ??????????????????"    
				+ "15  ?????????????????? "   
				+ "16  ??????IB????????????", dataType = "Integer",required = true)
	})
	@Sign
	@Login
	@NotNullParam("types")
	public Response<String> cashFlowTotalMoney(String types){
		Response<String> response = new Response<>();
		WpCashFlow wpCashFlow = new WpCashFlow();
		wpCashFlow.setUid(ThreadLocals.getUser().getUid());
		if(StringUtils.isNotBlank(types)){
			String[] typess = types.split(",");
			wpCashFlow.setTypes(new ArrayList<Integer>());
			for(String t : typess){
				wpCashFlow.getTypes().add(Integer.valueOf(t));
			}
		}
		String money = this.cashFlowService.selectTotalMoney(wpCashFlow);
		response.setData(StringUtils.isBlank(money) ? "0" : money);
		return response;
	}
	
	@ApiOperation("??????")
	@RequestMapping(value = "onboard",method = {RequestMethod.POST})
	@Sign
	@Login
	public Response<Object> onboard(){
		Response<Object> response = new Response<>();
		synchronized (ThreadLocals.getUser().getUid()) {
			this.onboardService.onboard(response);
		}
		return response;
	}
	
	@ApiOperation("????????????????????????")
	@RequestMapping(value = "checkinDaily",method = {RequestMethod.POST})
	@Sign
	public Response<List<WpCheckinRewardDaily>> checkinDaily(){
		Response<List<WpCheckinRewardDaily>> response = new Response<>();
		List<WpCheckinRewardDaily> list = this.checkinRewardDailyService.selectAll();
		response.setData(list);
		return response;
	}
	
	@ApiOperation("??????????????????")
	@RequestMapping(value = "checkinReward",method = {RequestMethod.POST})
	@Sign
	@Login
	public Response<List<CheckinRewardUserResp>> checkinReward(){
		Response<List<CheckinRewardUserResp>> response = new Response<>();
		response.setData(this.checkinRewardService.selectMyCheckinRewardList(ThreadLocals.getUser().getUid()));
		return response;
	}
	
	@ApiOperation("??????????????????????????????")
	@RequestMapping(value = "signinTotalReward",method = {RequestMethod.POST})
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "????????????????????????ID", dataType = "Integer",required = true),
	})
	@Sign
	@Login
	@NotNullParam("id")
	public Response<Object> signinTotalReward(Integer id){
		Response<Object> response = new Response<>();
		synchronized (ThreadLocals.getUser().getUid()) {
			this.checkinRewardUserService.signinTotalReward(id,response);
		}
		return response;
	}
	
	@ApiOperation("????????????")
	@RequestMapping(value = "signInMsg",method = {RequestMethod.POST})
	@Sign
	@Login
	public Response<SignInMsgResp> signInMsg(){
		Response<SignInMsgResp> response = new Response<SignInMsgResp>();
		response.setData(this.onboardService.queryMySignIn());
		return response;
	}
}
