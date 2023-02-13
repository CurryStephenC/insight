package com.wjyoption.web.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.wjyoption.common.annotation.Login;
import com.wjyoption.common.annotation.NotNullParam;
import com.wjyoption.common.annotation.Sign;
import com.wjyoption.common.config.Global;
import com.wjyoption.common.config.ServerConfig;
import com.wjyoption.common.core.controller.BaseController;
import com.wjyoption.common.core.domain.Response;
import com.wjyoption.common.enums.ErrorConstants;
import com.wjyoption.common.utils.DateUtils;
import com.wjyoption.common.utils.ThreadLocals;
import com.wjyoption.common.utils.file.FileUploadUtils;
import com.wjyoption.system.domain.WpCommunicateGroup;
import com.wjyoption.system.domain.WpUserNovicetask;
import com.wjyoption.system.domain.WpUserNovicetaskRate;
import com.wjyoption.system.service.IWpCommunicateGroupService;
import com.wjyoption.system.service.IWpUserNovicetaskRateService;
import com.wjyoption.system.service.IWpUserNovicetaskService;
import com.wjyoption.system.vo.resp.UserNovicetaskResp;


@Api(value="新手任务模块",tags={"新手任务模块"})
@RestController
@RequestMapping("api/novicetask")
@CrossOrigin
public class NovicetaskApi extends BaseController{

	@Autowired IWpUserNovicetaskService novicetaskService;
	@Autowired IWpUserNovicetaskRateService novicetaskRateService;
	@Autowired IWpCommunicateGroupService groupService;
	
	@Autowired ServerConfig serverConfig;
	@Value("${wjyoption.httpsSwitch}")
	private String httpsSwitch;
	
	@ApiOperation("任务列表")
	@RequestMapping(value = "taskList",method = {RequestMethod.POST})
//	@ApiImplicitParams({
//		@ApiImplicitParam(name = "noticeType", value = "公告类型（1通知 2公告）", dataType = "String",required = true),
//	})
	@Sign
	@Login
//	@NotNullParam("noticeType")
	public Response<List<UserNovicetaskResp>> taskList(String noticeType){
		Response<List<UserNovicetaskResp>> response = new Response<>();
		Integer uid = ThreadLocals.getUser().getUid();
		List<UserNovicetaskResp> list = this.novicetaskRateService.selectUserNovicetaskList(uid);
		for(UserNovicetaskResp bean : list){
			if(bean.getRateid() == null || bean.getRateid() == 0){
				WpUserNovicetaskRate rate = new WpUserNovicetaskRate();
				rate.setUid(uid);
				rate.setTaskid(bean.getId());
				rate.setCreatetime(DateUtils.getNowSecond());
				rate.setStatus(1);
				this.novicetaskRateService.insertWpUserNovicetaskRate(rate);
			}
		}
		response.setData(list);
		return response;
	}
	
	@ApiOperation("提交任务")
    @PostMapping("submitTask")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "taskid", value = "任务ID", dataType = "int",required = true),
	})
    @ResponseBody
    @Login
    @Sign
    @NotNullParam("taskid")
    public Response<Object> submitTask(MultipartFile file) throws Exception
    {
		Response<Object> response = new Response<Object>();
        try
        {
        	Integer uid = ThreadLocals.getUser().getUid();
        	Integer taskid = Integer.valueOf(super.getRequest().getParameter("taskid"));
        	WpUserNovicetask task = this.novicetaskService.selectWpUserNovicetaskById(taskid);
        	if(task == null || task.getStatus() == 1){
        		ErrorConstants.setResponse(response, ErrorConstants.RECORD_NOT_EXISTS);
        		return response;
        	}
        	WpUserNovicetaskRate bean = this.novicetaskRateService.selectWpUserNovicetaskRateByTaskidUid(uid, taskid);
        	if(bean == null){
        		bean = new WpUserNovicetaskRate();
        		bean.setTaskid(taskid);
        		bean.setUid(uid);
        		bean.setStatus(1);
        		bean.setCreatetime(DateUtils.getNowSecond());
        	}
        	if(bean.getStatus() == 3 || bean.getStatus() == 2){
        		ErrorConstants.setResponse(response, ErrorConstants.REPEAT_OPERATION);
        		return response;
        	}
            // 上传文件路径
            String filePath = Global.getUploadPath();
            // 上传并返回新文件名称
            String fileName = FileUploadUtils.upload(filePath, file);
            String url = serverConfig.getUrl() + fileName;
            if("1".equals(httpsSwitch) && url.startsWith("http://")){
            	url = url.replaceFirst("http://", "https://");
            }
            bean.setPicurl(url);
            bean.setStatus(2);
            if(bean.getId() == null){
            	this.novicetaskRateService.insertWpUserNovicetaskRate(bean);
            }else{
            	this.novicetaskRateService.updateWpUserNovicetaskRate(bean);
            }
            return response;
        }
        catch (Exception e)
        {
        	logger.error(e.getMessage(),e);
        	ErrorConstants.setResponse(response, ErrorConstants.FAIL);
            return response;
        }
    }
	
	@ApiOperation("完成任务")
	@PostMapping("finishTask")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "taskid", value = "任务ID", dataType = "int",required = true),
	})
	@ResponseBody
	@Login
	@Sign
	@NotNullParam("taskid")
	public Response<Object> finishTask(Integer taskid) throws Exception
	{
		Response<Object> response = new Response<Object>();
		try
		{
			Integer uid = ThreadLocals.getUser().getUid();
			WpUserNovicetask task = this.novicetaskService.selectWpUserNovicetaskById(taskid);
			if(task == null || task.getStatus() == 1){
				ErrorConstants.setResponse(response, ErrorConstants.RECORD_NOT_EXISTS);
				return response;
			}
			if(task.getAudit() == 2){
				ErrorConstants.setResponse(response, ErrorConstants.NO_POWER);
				return response;
			}
			WpUserNovicetaskRate bean = this.novicetaskRateService.selectWpUserNovicetaskRateByTaskidUid(uid, taskid);
			long nowTime = DateUtils.getNowSecond();
			if(bean == null){
				bean = new WpUserNovicetaskRate();
				bean.setTaskid(taskid);
				bean.setUid(uid);
				bean.setStatus(1);
				bean.setCreatetime(nowTime);
			}
			if(bean.getStatus() == 3){
				ErrorConstants.setResponse(response, ErrorConstants.REPEAT_OPERATION);
				return response;
			}
			bean.setStatus(3);
			bean.setFinishtime(nowTime);
			if(bean.getId() == null){
				this.novicetaskRateService.insertWpUserNovicetaskRate(bean);
			}else{
				this.novicetaskRateService.updateWpUserNovicetaskRate(bean);
			}
			return response;
		}
		catch (Exception e)
		{
			logger.error(e.getMessage(),e);
			ErrorConstants.setResponse(response, ErrorConstants.FAIL);
			return response;
		}
	}
	
	@ApiOperation("领取奖金")
	@PostMapping("receiveAward")
	@ResponseBody
	@Login
	@Sign
	public Response<Object> receiveAward() throws Exception
	{
		Response<Object> response = new Response<Object>();
		Integer uid = ThreadLocals.getUser().getUid();
		synchronized (uid) {
			this.novicetaskRateService.finishUserTask(uid, response);
		}
		return response;
	}
	
	@ApiOperation("聊天群组")
	@PostMapping("groupList")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "code", value = "code码", dataType = "String",required = true),
	})
	@ResponseBody
	@Login
	@Sign
	@NotNullParam("code")
	public Response<List<String>> groupList(String code) throws Exception
	{
		Response<List<String>> response = new Response<>();
		WpCommunicateGroup wpCommunicateGroup = new WpCommunicateGroup();
		wpCommunicateGroup.setStatus(0);
		wpCommunicateGroup.setCode(code);
		List<WpCommunicateGroup> list = this.groupService.selectWpCommunicateGroupList(wpCommunicateGroup);
		List<String> groups = new ArrayList<>();
		list.forEach(obj -> {
			groups.add(obj.getGroupurl());
		});
		response.setData(groups);
		return response;
	}
}
