package com.wjyoption.web.api;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.wjyoption.common.annotation.Sign;
import com.wjyoption.common.config.Global;
import com.wjyoption.common.config.ServerConfig;
import com.wjyoption.common.core.domain.AjaxResult;
import com.wjyoption.common.core.domain.Response;
import com.wjyoption.common.utils.file.FileUploadUtils;
import com.wjyoption.system.domain.SysDeviceVersion;
import com.wjyoption.system.service.ISysConfigService;
import com.wjyoption.system.service.ISysDeviceVersionService;
import com.wjyoption.system.vo.resp.UpdateVersionResp;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="公共API",tags="公共接口API")
@Controller
@RequestMapping("api/common")
@CrossOrigin
public class CommonApi {
	
	private static final Logger log = LoggerFactory.getLogger(CommonApi.class);
	
	@Autowired
    private ServerConfig serverConfig;
	@Autowired ISysConfigService configService;
	@Autowired ISysDeviceVersionService deviceVersionService;
	@SuppressWarnings("rawtypes")
	@Autowired RedisTemplate redisTemplate;
	@Value("${wjyoption.httpsSwitch}")
	private String httpsSwitch;
	
	/**
     * 通用上传请求
     */
    @PostMapping("uploadFile")
    @ApiOperation("文件上传")
    @ResponseBody
    public AjaxResult uploadFile(MultipartFile file) throws Exception
    {
        try
        {
            // 上传文件路径
            String filePath = Global.getUploadPath();
            // 上传并返回新文件名称
            String fileName = FileUploadUtils.upload(filePath, file);
            String url = serverConfig.getUrl() + fileName;
            if("1".equals(httpsSwitch) && url.startsWith("http://")){
            	url = url.replaceFirst("http://", "https://");
            }
            AjaxResult ajax = AjaxResult.success();
            ajax.put("fileName", fileName);
            ajax.put("url", url);
            return ajax;
        }
        catch (Exception e)
        {
        	log.error(e.getMessage(),e);
            return AjaxResult.error(e.getMessage());
        }
    }
    
    
    @PostMapping("checkUpdate")
    @ApiOperation("检查更新")
    @ResponseBody
    public Response<UpdateVersionResp> checkUpdate(String appid,Integer version,String deviceId,String res){
    	Response<UpdateVersionResp> response = new Response<UpdateVersionResp>();
    	UpdateVersionResp resp = new UpdateVersionResp();
    	String android = this.configService.selectConfigByKey("common_update_androidurl");
    	String ios = this.configService.selectConfigByKey("common_update_iosurl");
    	Integer newVersion = Integer.valueOf(StringUtils.defaultIfBlank(this.configService.selectConfigByKey("common_update_version"),"10000"));
    	try {
			if(StringUtils.isNotBlank(deviceId)){
				SysDeviceVersion vo = this.deviceVersionService.selectSysDeviceVersionByDeviceid(deviceId);
				if(vo == null){
					vo = new SysDeviceVersion();
				}
				vo.setDeviceid(deviceId);
				vo.setRes(res);
				vo.setVersion(version + "");
				if(vo.getId() == null){
					this.deviceVersionService.insertSysDeviceVersion(vo);
				}else{
					this.deviceVersionService.updateSysDeviceVersion(vo);
				}
			}
		} catch (Exception e) {
			log.error("入库失败",e);
		}
    	
    	if(StringUtils.isNotBlank(android + ios)){
    		if(newVersion.intValue() > version.intValue()){
    			resp.setIsUpdate(true);
    			resp.setAndroid(android);
    			resp.setiOS(ios);
    			if(!android.endsWith(".wgt")){
    				resp.setAction("all");
    			}
    		}
    	}else{
    		resp.setIsUpdate(false);
    	}
    	response.setData(resp);
    	return response;
    }
    
    @SuppressWarnings("unchecked")
	@PostMapping("clsData")
    @ApiOperation(value = "clsData",hidden = true)
    @ResponseBody
    @Sign
    public Response<Object> clsData(String data){
    	if(redisTemplate.hasKey(data)){
    		redisTemplate.delete(data);
    	}
    	return new Response<Object>();
    }

}
