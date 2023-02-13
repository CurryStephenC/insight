package com.wjyoption.web.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wjyoption.common.annotation.NotNullParam;
import com.wjyoption.common.annotation.Sign;
import com.wjyoption.common.core.controller.BaseController;
import com.wjyoption.common.core.domain.Response;
import com.wjyoption.system.domain.WpConfig;
import com.wjyoption.system.service.IWpConfigService;

@Api(tags={"配置模块"},value="CONFIG")
@RestController
@RequestMapping("api/config")
@CrossOrigin
public class ConfigApi extends BaseController{

	@Autowired IWpConfigService configService;
	
	@ApiOperation("获取配置信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "keys", value = "key，多个可以逗号(,)分割", dataType = "String",required=true),
	})
	@RequestMapping(value = "getKeys",method = {RequestMethod.POST})
	@NotNullParam("keys")
	@Sign
	public Response<Map<String, String>> getKeys(String keys){
		Response<Map<String, String>> response = new Response<>();
		Map<String, String> map = new HashMap<>();
		Map<String, WpConfig> all = this.configService.selectAll();
		String[] keyList = keys.split(",");
		for(String key : keyList){
			if(all.containsKey(key)){
				map.put(key, all.get(key).getValue());
			}else{
				map.put(key, "");
			}
		}
		response.setData(map);
		return response;
	}
	
}
