package com.wjyoption.web.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.wjyoption.common.vo.UserLogin;
import com.wjyoption.system.domain.WpSpeech;
import com.wjyoption.system.domain.WpSpeechComment;
import com.wjyoption.system.domain.WpSpeechCommentReply;
import com.wjyoption.system.domain.WpSpeechLike;
import com.wjyoption.system.service.IWpSpeechCommentReplyService;
import com.wjyoption.system.service.IWpSpeechCommentService;
import com.wjyoption.system.service.IWpSpeechLikeService;
import com.wjyoption.system.service.IWpSpeechService;
import com.wjyoption.system.vo.resp.SpeechCommentResp;
import com.wjyoption.system.vo.resp.SpeechReplyResp;
import com.wjyoption.system.vo.resp.SpeechResp;


@Api(tags={"言论模块"},value="speech")
@RestController
@RequestMapping("api/speech")
@CrossOrigin
public class SpeechApi  extends BaseController{

	@Autowired IWpSpeechService speechService;
	@Autowired IWpSpeechCommentService commentService;
	@Autowired IWpSpeechCommentReplyService replyService;
	@Autowired IWpSpeechLikeService likeService;
	
	@SuppressWarnings("rawtypes")
	@Autowired RedisTemplate redisTemplate;
	
	
	@SuppressWarnings("unchecked")
	@ApiOperation("言论列表")
	@RequestMapping(value = "speechList",method = {RequestMethod.POST})
	@ApiImplicitParams({
		@ApiImplicitParam(name = "pageNum", value = "当前页", dataType = "Integer"),
		@ApiImplicitParam(name = "pageSize", value = "每页记录", dataType = "Integer"),
		@ApiImplicitParam(name = "orderByColumn", value = "排序字段：如 id", dataType = "String"),
		@ApiImplicitParam(name = "isAsc", value = "排序方式->顺序：asc,倒序：desc", dataType = "String"),
	})
	@Sign
	public Response<List<SpeechResp>> speechList(){
		String token = super.getRequest().getHeader("Authorization");
		if(token != null){
			String key = RedisEnum.USER_LOGIN.getKeyPrefix() + token;
			if(redisTemplate.hasKey(key)){
				ThreadLocals.setUser((UserLogin)redisTemplate.boundValueOps(key).get());
			}
		}
		Response<List<SpeechResp>> response = new Response<>();
		PageDomain page = startPageApi();
		WpSpeech speech = new WpSpeech();
		speech.setStatus(1);
		List<SpeechResp> list = this.speechService.selectSpeechResp(speech);
		response.setData(list);
		
		if(page.getPageSize() != null){
			response.setTotal(new PageInfo<SpeechResp>(list, page.getPageSize()).getTotal());
		}else
			response.setTotal((long) list.size());
		return response;
	}
	
	@ApiOperation("我的言论列表")
	@RequestMapping(value = "speechListSelf",method = {RequestMethod.POST})
	@ApiImplicitParams({
		@ApiImplicitParam(name = "pageNum", value = "当前页", dataType = "Integer"),
		@ApiImplicitParam(name = "pageSize", value = "每页记录", dataType = "Integer"),
		@ApiImplicitParam(name = "orderByColumn", value = "排序字段：如 id", dataType = "String"),
		@ApiImplicitParam(name = "isAsc", value = "排序方式->顺序：asc,倒序：desc", dataType = "String"),
	})
	@Sign
	@Login
	public Response<List<SpeechResp>> speechListSelf(){
		Response<List<SpeechResp>> response = new Response<>();
		PageDomain page = startPageApi();
		WpSpeech speech = new WpSpeech();
		speech.setUid(ThreadLocals.getUser().getUid());
		List<SpeechResp> list = this.speechService.selectSpeechResp(speech);
		response.setData(list);
		if(page.getPageSize() != null){
			response.setTotal(new PageInfo<SpeechResp>(list, page.getPageSize()).getTotal());
		}else
			response.setTotal((long) list.size());
		return response;
	}
	
	
	@SuppressWarnings("unchecked")
	@ApiOperation("评论列表")
	@RequestMapping(value = "commentList",method = {RequestMethod.POST})
	@ApiImplicitParams({
		@ApiImplicitParam(name = "sid", value = "言论ID", dataType = "Integer",required = true),
		@ApiImplicitParam(name = "pageNum", value = "当前页", dataType = "Integer"),
		@ApiImplicitParam(name = "pageSize", value = "每页记录", dataType = "Integer"),
		@ApiImplicitParam(name = "orderByColumn", value = "排序字段：如 id", dataType = "String"),
		@ApiImplicitParam(name = "isAsc", value = "排序方式->顺序：asc,倒序：desc", dataType = "String"),
	})
	@Sign
	@NotNullParam("sid")
	public Response<List<SpeechCommentResp>> commentList(Integer sid){
		String token = super.getRequest().getHeader("Authorization");
		if(token != null){
			String key = RedisEnum.USER_LOGIN.getKeyPrefix() + token;
			if(redisTemplate.hasKey(key)){
				ThreadLocals.setUser((UserLogin)redisTemplate.boundValueOps(key).get());
			}
		}
		Response<List<SpeechCommentResp>> response = new Response<>();
		PageDomain page = startPageApi();
		WpSpeechComment speech = new WpSpeechComment();
		speech.setStatus(1);
		speech.setSid(sid);
		List<SpeechCommentResp> list = this.commentService.selectSpeechCommentResp(speech);
		response.setData(list);
		
		if(page.getPageSize() != null){
			response.setTotal(new PageInfo<SpeechCommentResp>(list, page.getPageSize()).getTotal());
		}else
			response.setTotal((long) list.size());
		return response;
	}
	
	@ApiOperation("我的评论列表")
	@RequestMapping(value = "commentListSelf",method = {RequestMethod.POST})
	@ApiImplicitParams({
		@ApiImplicitParam(name = "pageNum", value = "当前页", dataType = "Integer"),
		@ApiImplicitParam(name = "pageSize", value = "每页记录", dataType = "Integer"),
		@ApiImplicitParam(name = "orderByColumn", value = "排序字段：如 id", dataType = "String"),
		@ApiImplicitParam(name = "isAsc", value = "排序方式->顺序：asc,倒序：desc", dataType = "String"),
	})
	@Sign
	@Login
	public Response<List<SpeechCommentResp>> commentListSelf(){
		Response<List<SpeechCommentResp>> response = new Response<>();
		PageDomain page = startPageApi();
		WpSpeechComment speech = new WpSpeechComment();
		speech.setUid(ThreadLocals.getUser().getUid());
		List<SpeechCommentResp> list = this.commentService.selectSpeechCommentResp(speech);
		response.setData(list);
		if(page.getPageSize() != null){
			response.setTotal(new PageInfo<SpeechCommentResp>(list, page.getPageSize()).getTotal());
		}else
			response.setTotal((long) list.size());
		return response;
	}
	
	@SuppressWarnings("unchecked")
	@ApiOperation("回复列表")
	@RequestMapping(value = "replyList",method = {RequestMethod.POST})
	@ApiImplicitParams({
		@ApiImplicitParam(name = "cid", value = "评论表ID", dataType = "Integer",required = true),
		@ApiImplicitParam(name = "pageNum", value = "当前页", dataType = "Integer"),
		@ApiImplicitParam(name = "pageSize", value = "每页记录", dataType = "Integer"),
		@ApiImplicitParam(name = "orderByColumn", value = "排序字段：如 id", dataType = "String"),
		@ApiImplicitParam(name = "isAsc", value = "排序方式->顺序：asc,倒序：desc", dataType = "String"),
	})
	@Sign
	@NotNullParam("cid")
	public Response<List<SpeechReplyResp>> replyList(Integer cid){
		String token = super.getRequest().getHeader("Authorization");
		if(token != null){
			String key = RedisEnum.USER_LOGIN.getKeyPrefix() + token;
			if(redisTemplate.hasKey(key)){
				ThreadLocals.setUser((UserLogin)redisTemplate.boundValueOps(key).get());
			}
		}
		Response<List<SpeechReplyResp>> response = new Response<>();
		PageDomain page = startPageApi();
		WpSpeechCommentReply speech = new WpSpeechCommentReply();
		speech.setStatus(1);
		speech.setCid(cid);
		List<SpeechReplyResp> list = this.replyService.selectSpeechReplyResp(speech);
		response.setData(list);
		if(page.getPageSize() != null){
			response.setTotal(new PageInfo<SpeechReplyResp>(list, page.getPageSize()).getTotal());
		}else
			response.setTotal((long) list.size());
		return response;
	}
	
	@ApiOperation("我的回复列表")
	@RequestMapping(value = "replyListSelf",method = {RequestMethod.POST})
	@ApiImplicitParams({
		@ApiImplicitParam(name = "pageNum", value = "当前页", dataType = "Integer"),
		@ApiImplicitParam(name = "pageSize", value = "每页记录", dataType = "Integer"),
		@ApiImplicitParam(name = "orderByColumn", value = "排序字段：如 id", dataType = "String"),
		@ApiImplicitParam(name = "isAsc", value = "排序方式->顺序：asc,倒序：desc", dataType = "String"),
	})
	@Sign
	@Login
	public Response<List<SpeechReplyResp>> replyListSelf(){
		Response<List<SpeechReplyResp>> response = new Response<>();
		PageDomain page = startPageApi();
		WpSpeechCommentReply speech = new WpSpeechCommentReply();
		speech.setUid(ThreadLocals.getUser().getUid());
		List<SpeechReplyResp> list = this.replyService.selectSpeechReplyResp(speech);
		response.setData(list);
		if(page.getPageSize() != null){
			response.setTotal(new PageInfo<SpeechReplyResp>(list, page.getPageSize()).getTotal());
		}else
			response.setTotal((long) list.size());
		return response;
	}
	
	@ApiOperation("点赞或取消")
	@RequestMapping(value = "like",method = {RequestMethod.POST})
	@ApiImplicitParams({
		@ApiImplicitParam(name = "refid", value = "关联ID", dataType = "Integer",required = true),
		@ApiImplicitParam(name = "type", value = "类型：1、言论，2、言论评论，3、言论评论回复", dataType = "Integer",required = true),
	})
	@Sign
	@Login
	@NotNullParam("refid||type")
	public Response<Object> like(Integer refid,Integer type){
		Response<Object> response = new Response<>();
		UserLogin user = ThreadLocals.getUser();
		synchronized (user.getUid()) {
			WpSpeechLike like = new WpSpeechLike();
			like.setRefid(refid);
			like.setType(type);
			like.setUid(user.getUid());
			this.likeService.like(like);
		}
		return response;
	}
	
	
	@ApiOperation("发布言论")
	@RequestMapping(value = "releaseSpeech",method = {RequestMethod.POST})
	@ApiImplicitParams({
		@ApiImplicitParam(name = "content", value = "发布内容", dataType = "String",required = true),
	})
	@Sign
	@Login
	@NotNullParam("content")
	public Response<Object> releaseSpeech(String content){
		Response<Object> response = new Response<>();
		UserLogin user = ThreadLocals.getUser();
		synchronized (user.getUid()) {
			this.speechService.releaseSpeech(content,response);
		}
		return response;
	}
	
	@ApiOperation("评论")
	@RequestMapping(value = "comment",method = {RequestMethod.POST})
	@ApiImplicitParams({
		@ApiImplicitParam(name = "sid", value = "言论ID", dataType = "Integer",required = true),
		@ApiImplicitParam(name = "content", value = "发布内容", dataType = "String",required = true),
	})
	@Sign
	@Login
	@NotNullParam("content||sid")
	public Response<Object> comment(String content,Integer sid){
		Response<Object> response = new Response<>();
		UserLogin user = ThreadLocals.getUser();
		synchronized (user.getUid()) {
			WpSpeech bean = this.speechService.selectWpSpeechById(sid);
			if(bean == null || bean.getStatus() != 1){
				ErrorConstants.setResponse(response, ErrorConstants.RECORD_NOT_EXISTS);
			}else{
				this.commentService.comment(content,sid,response);
			}
		}
		return response;
	}
	
	@ApiOperation("回复")
	@RequestMapping(value = "reply",method = {RequestMethod.POST})
	@ApiImplicitParams({
		@ApiImplicitParam(name = "cid", value = "评论ID", dataType = "Integer",required = true),
		@ApiImplicitParam(name = "content", value = "发布内容", dataType = "String",required = true),
		@ApiImplicitParam(name = "toreplyid", value = "回复记录ID", dataType = "Integer"),
	})
	@Sign
	@Login
	@NotNullParam("content||cid")
	public Response<Object> reply(String content,Integer cid,Integer toreplyid){
		Response<Object> response = new Response<>();
		UserLogin user = ThreadLocals.getUser();
		synchronized (user.getUid()) {
			this.replyService.reply(content,cid,toreplyid,response);
		}
		return response;
	}
	
	
	
	
}
