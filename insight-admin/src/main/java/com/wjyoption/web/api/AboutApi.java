package com.wjyoption.web.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wjyoption.common.annotation.Sign;
import com.wjyoption.common.core.controller.BaseController;
import com.wjyoption.common.core.domain.Response;
import com.wjyoption.common.utils.db.RedisEnum;
import com.wjyoption.system.domain.SysDictData;
import com.wjyoption.system.service.ISysConfigService;
import com.wjyoption.system.service.ISysDictDataService;
import com.wjyoption.system.vo.resp.AboutResp;


@Api(tags={"关于模块"},value="about")
@RestController
@RequestMapping("api/about")
@CrossOrigin
public class AboutApi  extends BaseController{

	@Autowired ISysConfigService configService;
	@Autowired ISysDictDataService dataService;
	
	@SuppressWarnings("rawtypes")
	@Autowired RedisTemplate redisTemplate;
	
	@SuppressWarnings("unchecked")
	@ApiOperation("内容")
	@RequestMapping(value = "content",method = {RequestMethod.POST})
//	@ApiImplicitParams({
//		@ApiImplicitParam(name = "action", value = "支付方式：如 pay777,yypay", dataType = "String"),
//		@ApiImplicitParam(name = "money", value = "每页记录", dataType = "Integer"),
//	})
	@Sign
//	@Login
	public Response<AboutResp> content(){
		Response<AboutResp> response = new Response<>();
		String redisKey = RedisEnum.ABOUT_REDIS_KEY.getKeyPrefix();
		BoundValueOperations<String,AboutResp> boundValueOps = this.redisTemplate.boundValueOps(redisKey);
		AboutResp resp = boundValueOps.get();
		if(resp != null){
			response.setData(resp);
			return response;
		}
		resp = new AboutResp();
		List<String> picList = new ArrayList<>();
		List<String> baoxianList = new ArrayList<>();
		String https = "https://ic.swiftoption.net/public/static/about/";
		String defaultStr = "1.png,2.jpg,3.jpg,4.jpg";
		String pics = this.configService.selectConfigByKey("wp_about_piclist");
		if(StringUtils.isBlank(pics)){
			String[] piclists = defaultStr.split(",");
			for(int i = 0;i < piclists.length;i++){
				picList.add(https + piclists[i]);
			}
		}else{
			String[] lists = pics.split(",");
			for(String pic : lists){
				picList.add(pic);
			}
		}
		String baoxians = this.configService.selectConfigByKey("wp_about_baoxianlist");
		if(StringUtils.isBlank(baoxians)){
			baoxianList.add(https + "baoxian.png");
		}else{
			String[] bxs = baoxians.split(",");
			for(String key : bxs){
				baoxianList.add(key);
			}
		}
		List<SysDictData> list = this.dataService.selectDictDataByType("wp_about_newaddr");
		Map<String, String> newAddr = new LinkedHashMap<String, String>();
		if(CollectionUtils.isNotEmpty(list)){
			for(SysDictData data : list){
				newAddr.put(data.getDictLabel(), data.getDictLabel() + ": " + data.getDictValue());
			}
		}
		resp.setNewaddress(newAddr);
		String wp_about_addr = this.configService.selectConfigByKey("wp_about_addr");
		String wp_about_email = this.configService.selectConfigByKey("wp_about_email");
		String wp_about_fb = this.configService.selectConfigByKey("wp_about_fb");
		String wp_about_twitter = this.configService.selectConfigByKey("wp_about_twitter");
		String wp_about_youtube = this.configService.selectConfigByKey("wp_about_youtube");
		String wp_about_phone = this.configService.selectConfigByKey("wp_about_phone");
		resp.setBaoxianList(baoxianList);
		resp.setPicList(picList);
		resp.setAddress(StringUtils.defaultIfBlank(wp_about_addr, "Plot No. C-70, G Block, Bandra Kurla Complex (BKC), Bandra (East), Mumbai 400051, India"));
		resp.setEmail(StringUtils.defaultIfBlank(wp_about_email, "sfoption@yahoo.com"));
		resp.setFacbook(StringUtils.defaultIfBlank(wp_about_fb, "sfoption@yahoo.com"));
		resp.setTwitter(StringUtils.defaultIfBlank(wp_about_twitter, "@SF24286548"));
		resp.setYoutube(StringUtils.defaultIfBlank(wp_about_youtube, "sfoption8@gmail.com"));
		resp.setPhone(wp_about_phone);
		response.setData(resp);
		boundValueOps.set(resp, 4, TimeUnit.HOURS);
		return response;
	}
	
	
}
