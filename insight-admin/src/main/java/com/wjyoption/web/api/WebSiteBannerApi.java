package com.wjyoption.web.api;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wjyoption.common.core.controller.BaseController;
import com.wjyoption.common.core.domain.Response;
import com.wjyoption.system.domain.SysWebsitBanner;
import com.wjyoption.system.service.ISysWebsitBannerService;
import com.wjyoption.system.vo.WebSiteBannerVo;
import com.wjyoption.system.vo.param.WebSiteBannerParam;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@Api(value="Banner模块",tags={"Banner模块"})
@RestController
@RequestMapping("api/websitebanner")
@CrossOrigin
public class WebSiteBannerApi extends BaseController{

	@Autowired ISysWebsitBannerService websitBannerService;
	
	
	@ApiOperation("获取Banner列表")
    @PostMapping("queryBannerList")
	public Response<List<WebSiteBannerVo>> queryBannerList(WebSiteBannerParam param){
		Response<List<WebSiteBannerVo>> response = new Response<List<WebSiteBannerVo>>();
		SysWebsitBanner sysWebsitBanner = new SysWebsitBanner();
		sysWebsitBanner.setCode(param.getCode());
		sysWebsitBanner.setBannertype(param.getBannertype());
		sysWebsitBanner.setBannerstatus(0);
		List<SysWebsitBanner> list = this.websitBannerService.selectSysWebsitBannerList(sysWebsitBanner);
		List<WebSiteBannerVo> retList = list.stream().map(obj -> new WebSiteBannerVo(obj)).collect(Collectors.toList());
		response.setData(retList);
		return response;
	}
	
	
}
