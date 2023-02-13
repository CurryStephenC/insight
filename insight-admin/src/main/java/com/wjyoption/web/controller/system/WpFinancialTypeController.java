package com.wjyoption.web.controller.system;

import java.util.List;

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
import com.wjyoption.system.domain.WpFinancialType;
import com.wjyoption.system.domain.WpFinancialTypeDetail;
import com.wjyoption.system.service.IWpFinancialTypeDetailService;
import com.wjyoption.system.service.IWpFinancialTypeService;

/**
 * 理财类型Controller
 * 
 * @author wjyoption
 * @date 2021-06-04
 */
@Controller
@RequestMapping("/system/financialtype")
public class WpFinancialTypeController extends BaseController
{
    private String prefix = "system/financialtype";

    @Autowired
    private IWpFinancialTypeService wpFinancialTypeService;
    
    @Autowired IWpFinancialTypeDetailService wpFinancialTypeDetailService;

    @RequiresPermissions("system:financialtype:view")
    @GetMapping()
    public String financialtype()
    {
        return prefix + "/financialtype";
    }

    /**
     * 查询理财类型列表
     */
    @RequiresPermissions("system:financialtype:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(WpFinancialType wpFinancialType)
    {
        startPage();
        List<WpFinancialType> list = wpFinancialTypeService.selectWpFinancialTypeList(wpFinancialType);
        return getDataTable(list);
    }

    /**
     * 导出理财类型列表
     */
    @RequiresPermissions("system:financialtype:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(WpFinancialType wpFinancialType)
    {
        List<WpFinancialType> list = wpFinancialTypeService.selectWpFinancialTypeList(wpFinancialType);
        ExcelUtil<WpFinancialType> util = new ExcelUtil<WpFinancialType>(WpFinancialType.class);
        return util.exportExcel(list, "financialtype");
    }

    /**
     * 新增理财类型
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存理财类型
     */
    @RequiresPermissions("system:financialtype:add")
    @Log(title = "理财类型", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(WpFinancialType wpFinancialType)
    {
        return toAjax(wpFinancialTypeService.insertWpFinancialType(wpFinancialType));
    }

    /**
     * 修改理财类型
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap)
    {
        WpFinancialType wpFinancialType = wpFinancialTypeService.selectWpFinancialTypeById(id);
        mmap.put("wpFinancialType", wpFinancialType);
        return prefix + "/edit";
    }

    /**
     * 修改保存理财类型
     */
    @RequiresPermissions("system:financialtype:edit")
    @Log(title = "理财类型", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(WpFinancialType wpFinancialType)
    {
        return toAjax(wpFinancialTypeService.updateWpFinancialType(wpFinancialType));
    }

    /**
     * 删除理财类型
     */
    @RequiresPermissions("system:financialtype:remove")
    @Log(title = "理财类型", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
    	if("1".equals(ids) || ids.contains("1,")){
    		return AjaxResult.error();
    	}
        return toAjax(wpFinancialTypeService.deleteWpFinancialTypeByIds(ids));
    }
    
    /**
     * 修改平台
     */
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Integer id, ModelMap mmap)
    {
        WpFinancialTypeDetail detail = wpFinancialTypeDetailService.selectDetailBytypeId(id);
        if(detail == null){
        	mmap.put("typeid", id);
        	return prefix + "/adddetail";
        }else {
        	mmap.put("detail", detail);
        	return prefix + "/editdetail";
        }
    }
}
