package com.wjyoption.web.controller.system;

import java.net.URLDecoder;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wjyoption.common.annotation.Log;
import com.wjyoption.common.core.controller.BaseController;
import com.wjyoption.common.core.domain.AjaxResult;
import com.wjyoption.common.core.page.TableDataInfo;
import com.wjyoption.common.enums.BusinessType;
import com.wjyoption.common.utils.poi.ExcelUtil;
import com.wjyoption.system.domain.SysArticleDetail;
import com.wjyoption.system.service.ISysArticleDetailService;
import com.wjyoption.system.service.ISysArticleTypeService;

/**
 * 文章详情Controller
 * 
 * @author hs
 * @date 2020-02-06
 */
@Controller
@RequestMapping("/system/articledetail")
public class SysArticleDetailController extends BaseController
{
    private String prefix = "system/articledetail";

    @Autowired
    private ISysArticleDetailService sysArticleDetailService;
    
    @Autowired ISysArticleTypeService sysArticleTypeService;

    @RequiresPermissions("system:articledetail:view")
    @GetMapping()
    public String articledetail(ModelMap mmap)
    {
    	mmap.put("typelist", this.sysArticleTypeService.selectSysArticleTypeList(null));
        return prefix + "/articledetail";
    }

    /**
     * 查询文章详情列表
     */
    @RequiresPermissions("system:articledetail:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysArticleDetail sysArticleDetail)
    {
        startPage();
        List<SysArticleDetail> list = sysArticleDetailService.selectSysArticleDetailList(sysArticleDetail);
        return getDataTable(list);
    }

    /**
     * 导出文章详情列表
     */
    @RequiresPermissions("system:articledetail:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SysArticleDetail sysArticleDetail)
    {
        List<SysArticleDetail> list = sysArticleDetailService.selectSysArticleDetailList(sysArticleDetail);
        ExcelUtil<SysArticleDetail> util = new ExcelUtil<SysArticleDetail>(SysArticleDetail.class);
        return util.exportExcel(list, "articledetail");
    }

    /**
     * 新增文章详情
     */
    @GetMapping("/add")
    public String add(ModelMap mmap)
    {
    	mmap.put("typelist", this.sysArticleTypeService.selectSysArticleTypeList(null));
        return prefix + "/add";
    }

    /**
     * 新增保存文章详情
     */
    @RequiresPermissions("system:articledetail:add")
    @Log(title = "文章详情", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SysArticleDetail sysArticleDetail)
    {
    	try {
    		if(StringUtils.isNotBlank(sysArticleDetail.getContent()))
    			sysArticleDetail.setContent(URLDecoder.decode(sysArticleDetail.getContent(), "utf-8"));
		} catch (Exception e) {
			e.printStackTrace();
		}
        return toAjax(sysArticleDetailService.insertSysArticleDetail(sysArticleDetail));
    }

    /**
     * 修改文章详情
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap)
    {
        SysArticleDetail sysArticleDetail = sysArticleDetailService.selectSysArticleDetailById(id);
        mmap.put("sysArticleDetail", sysArticleDetail);
        mmap.put("typelist", this.sysArticleTypeService.selectSysArticleTypeList(null));
        return prefix + "/edit";
    }

    /**
     * 修改保存文章详情
     */
    @RequiresPermissions("system:articledetail:edit")
    @Log(title = "文章详情", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SysArticleDetail sysArticleDetail)
    {
    	try {
    		if(StringUtils.isNotBlank(sysArticleDetail.getContent()))
    			sysArticleDetail.setContent(URLDecoder.decode(sysArticleDetail.getContent(), "utf-8"));
		} catch (Exception e) {
			e.printStackTrace();
		}
        return toAjax(sysArticleDetailService.updateSysArticleDetail(sysArticleDetail));
    }

    /**
     * 删除文章详情
     */
    @RequiresPermissions("system:articledetail:remove")
    @Log(title = "文章详情", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(sysArticleDetailService.deleteSysArticleDetailByIds(ids));
    }
}
