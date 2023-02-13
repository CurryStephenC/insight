package com.wjyoption.web.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wjyoption.common.annotation.Sign;
import com.wjyoption.common.core.controller.BaseController;
import com.wjyoption.common.core.domain.Response;
import com.wjyoption.system.domain.SysNavigationBar;
import com.wjyoption.system.service.ISysNavigationBarService;
import com.wjyoption.system.vo.resp.NavigationBarResp;


@Api(value="导航模块",tags={"导航模块"})
@RestController
@RequestMapping("api/navigationBar")
@CrossOrigin
public class NavigationBarApi extends BaseController {

	@Autowired ISysNavigationBarService navigationBarService;
	
	@ApiOperation("获取导航列表")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "bartype", value = "类型：1、PC，2、H5", dataType = "Integer",required=true)
	})
	@RequestMapping(value = "queryNavigationBarList",method = {RequestMethod.POST})
	@Sign
	public Response<List<NavigationBarResp>> queryBarList(SysNavigationBar param){
		Response<List<NavigationBarResp>> response = new Response<>();
//		SysNavigationBar param = new SysNavigationBar();
		param.setBarstatus(0);//正常状态
		List<SysNavigationBar> list = this.navigationBarService.selectSysNavigationBarList(param);
		List<NavigationBarResp> reList = new ArrayList<>();
		for(SysNavigationBar bean : list){
			NavigationBarResp v = new NavigationBarResp();
			BeanUtils.copyProperties(bean, v);
			reList.add(v);
		}
		response.setData(reList);
		return response;
	}
	
	
}
