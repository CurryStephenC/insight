package com.wjyoption.web.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wjyoption.common.annotation.NotNullParam;
import com.wjyoption.common.annotation.Sign;
import com.wjyoption.common.core.controller.BaseController;
import com.wjyoption.common.core.domain.Response;
import com.wjyoption.system.domain.SysNotice;
import com.wjyoption.system.service.ISysNoticeService;
import com.wjyoption.system.vo.resp.NoticeResp;


@Api(value="消息公告模块",tags={"消息公告模块"})
@RestController
@RequestMapping("api/notice")
@CrossOrigin
public class NoticeApi extends BaseController{

	@Autowired ISysNoticeService noticeService;
	
	
	@ApiOperation("获取消息列表")
	@RequestMapping(value = "queryNotice",method = {RequestMethod.POST})
	@ApiImplicitParams({
		@ApiImplicitParam(name = "noticeType", value = "公告类型（1通知 2公告）", dataType = "String",required = true),
	})
	@Sign
	@NotNullParam("noticeType")
	public Response<List<NoticeResp>> queryNotice(String noticeType){
		Response<List<NoticeResp>> response = new Response<>();
		SysNotice notice = new SysNotice();
		notice.setNoticeType(noticeType);
		notice.setStatus("0");
		response.setData(this.noticeService.selectList(notice));
		return response;
	}
}
