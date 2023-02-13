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
import com.wjyoption.common.enums.BusinessType;
import com.wjyoption.system.domain.WpFinancialType;
import com.wjyoption.system.domain.WpYieldRate;
import com.wjyoption.system.service.IWpFinancialTypeService;
import com.wjyoption.system.service.IWpYieldRateService;
import com.wjyoption.common.core.controller.BaseController;
import com.wjyoption.common.core.domain.AjaxResult;
import com.wjyoption.common.utils.DateUtils;
import com.wjyoption.common.utils.poi.ExcelUtil;
import com.wjyoption.common.core.page.TableDataInfo;

/**
 * 收益利率Controller
 * 
 * @author wjyoption
 * @date 2021-06-06
 */
@Controller
@RequestMapping("/system/yieldrate")
public class WpYieldRateController extends BaseController
{
    private String prefix = "system/yieldrate";

    @Autowired
    private IWpYieldRateService wpYieldRateService;
    @Autowired IWpFinancialTypeService typeService;

    @RequiresPermissions("system:yieldrate:view")
    @GetMapping()
    public String yieldrate(ModelMap map)
    {
    	map.put("typeList", this.typeService.selectWpFinancialTypeList(null));
        return prefix + "/yieldrate";
    }

    /**
     * 查询收益利率列表
     */
    @RequiresPermissions("system:yieldrate:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(WpYieldRate wpYieldRate)
    {
        startPage();
        List<WpYieldRate> list = wpYieldRateService.selectWpYieldRateList(wpYieldRate);
        return getDataTable(list);
    }

    /**
     * 导出收益利率列表
     */
    @RequiresPermissions("system:yieldrate:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(WpYieldRate wpYieldRate)
    {
        List<WpYieldRate> list = wpYieldRateService.selectWpYieldRateList(wpYieldRate);
        ExcelUtil<WpYieldRate> util = new ExcelUtil<WpYieldRate>(WpYieldRate.class);
        return util.exportExcel(list, "yieldrate");
    }

    /**
     * 新增收益利率
     */
    @GetMapping("/add")
    public String add(ModelMap mmap)
    {
    	mmap.put("typelist", this.typeService.selectWpFinancialTypeList(new WpFinancialType()));
        return prefix + "/add";
    }

    /**
     * 新增保存收益利率
     */
    @RequiresPermissions("system:yieldrate:add")
    @Log(title = "收益利率", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(WpYieldRate wpYieldRate)
    {
        return toAjax(wpYieldRateService.insertWpYieldRate(wpYieldRate));
    }

    /**
     * 修改收益利率
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap)
    {
        WpYieldRate wpYieldRate = wpYieldRateService.selectWpYieldRateById(id);
        mmap.put("wpYieldRate", wpYieldRate);
        return prefix + "/edit";
    }

    /**
     * 修改保存收益利率
     */
    @RequiresPermissions("system:yieldrate:edit")
    @Log(title = "收益利率", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(WpYieldRate wpYieldRate)
    {
        return toAjax(wpYieldRateService.updateWpYieldRate(wpYieldRate));
    }

    /**
     * 删除收益利率
     */
    @RequiresPermissions("system:yieldrate:remove")
    @Log(title = "收益利率", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
    	int daily = Integer.valueOf(DateUtils.dateTimeNow(DateUtils.YYYYMMDD));
    	String[] split = ids.split(",");
    	for(String s : split){
    		Integer id = Integer.valueOf(s);
    		WpYieldRate bean = this.wpYieldRateService.selectWpYieldRateById(id);
    		if(bean != null && bean.getDaily().intValue() <= daily){
    			return AjaxResult.error("已过期，无法删除");
    		}
    	}
        return toAjax(wpYieldRateService.deleteWpYieldRateByIds(ids));
    }
}
