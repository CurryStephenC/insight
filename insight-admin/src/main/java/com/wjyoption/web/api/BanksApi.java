//package com.wjyoption.web.api;
//
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.wjyoption.common.annotation.Sign;
//import com.wjyoption.common.core.controller.BaseController;
//import com.wjyoption.common.core.domain.Response;
//import com.wjyoption.system.service.ISysBanksService;
//import com.wjyoption.system.vo.resp.BanksResp;
//
//@Api(tags={"银行模块"})
//@RestController
//@RequestMapping("api/banks")
//@CrossOrigin
//public class BanksApi extends BaseController{
//
//	@Autowired ISysBanksService banksService;
//	
//	
//	@ApiOperation("获取银行信息列表")
//	@RequestMapping(value = "bankList",method = {RequestMethod.POST})
//	@Sign
//	public Response<List<BanksResp>> bankList(){
//		Response<List<BanksResp>> response = new Response<>();
//		response.setData(this.banksService.selectBanksList());
//		return response;
//	}
//	
//}
