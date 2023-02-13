package com.wjyoption.web.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.wjyoption.common.annotation.NotNullParam;
import com.wjyoption.common.annotation.Sign;
import com.wjyoption.common.core.controller.BaseController;
import com.wjyoption.common.core.domain.Response;
import com.wjyoption.common.core.page.PageDomain;
import com.wjyoption.common.enums.ErrorConstants;
import com.wjyoption.system.service.ISysArticleDetailService;
import com.wjyoption.system.service.ISysArticleTypeService;
import com.wjyoption.system.vo.ArticleDetailVo;
import com.wjyoption.system.vo.param.ArticleParam;


@Api(value="文章模块",tags={"文章模块"})
@RestController
@RequestMapping("api/article")
@CrossOrigin
public class ArticleApi extends BaseController{

	@Autowired ISysArticleDetailService articleDetailService;
	@Autowired ISysArticleTypeService articleTypeService;
	
//	@ApiOperation("获取文章类型列表")
//	@RequestMapping(value = "queryArticleTypeList",method = {RequestMethod.POST})
//	@Sign
//	public Response<List<ArticleTypeVo>> queryArticleTypeList(){
//		Response<List<ArticleTypeVo>> response = new Response<>();
//		SysArticleType param = new SysArticleType();
//		param.setStatus(0);//正常状态
//		List<SysArticleType> list = this.articleTypeService.selectSysArticleTypeList(param);
//		List<ArticleTypeVo> reList = new ArrayList<>();
//		for(SysArticleType bean : list){
//			ArticleTypeVo v = new ArticleTypeVo();
//			BeanUtils.copyProperties(bean, v);
//			reList.add(v);
//		}
//		response.setData(reList);
//		return response;
//	}
	
	
	@ApiOperation("获取文章列表")
	@RequestMapping(value = "queryArticleList",method = {RequestMethod.POST})
	@Sign
	@NotNullParam("type")
	public Response<List<ArticleDetailVo>> queryArticleList(ArticleParam param){
		Response<List<ArticleDetailVo>> response = new Response<List<ArticleDetailVo>>();
		PageDomain page = startPageApi();
		List<ArticleDetailVo> list = this.articleDetailService.queryArticleList(param);
		if(param.getType() != null && param.getType() > 4){
		}else{
			list.forEach(obj -> obj.setContent(""));
		}
		response.setData(list);
		if(page.getPageSize() != null){
			response.setTotal(new PageInfo<ArticleDetailVo>(list, page.getPageSize()).getTotal());
		}else
			response.setTotal((long) list.size());
		return response;
	}
	
	@ApiOperation("文章详情（id、type必传）")
	@RequestMapping(value = "queryArticleDetails",method = {RequestMethod.POST})
	@Sign
	@NotNullParam("id||type")
	public Response<List<ArticleDetailVo>> queryArticleDetails(ArticleParam param){
		Response<List<ArticleDetailVo>> response = new Response<>();
		if(param.getId() == null || param.getType() == null){
			ErrorConstants.setResponse(response, ErrorConstants.PARAMS_ERROR);
			return response;
		}
		List<ArticleDetailVo> list = this.articleDetailService.queryArticleDetails(param);
		response.setData(list);
		return response;
	}
	
	
}
