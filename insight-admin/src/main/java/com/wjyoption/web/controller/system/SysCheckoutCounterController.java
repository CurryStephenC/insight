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
import com.wjyoption.framework.util.ShiroUtils;
import com.wjyoption.system.domain.SysCheckoutCounter;
import com.wjyoption.system.service.ISysCheckoutCounterService;

/**
 * 收银台Controller
 * 
 * @author hs
 * @date 2021-06-25
 */
@Controller
@RequestMapping("/system/checkoutcounter")
public class SysCheckoutCounterController extends BaseController
{
    private String prefix = "system/checkoutcounter";

    @Autowired
    private ISysCheckoutCounterService sysCheckoutCounterService;

    @RequiresPermissions("system:checkoutcounter:view")
    @GetMapping()
    public String checkoutcounter(ModelMap mmap)
    {
        return prefix + "/checkoutcounter";
    }

    /**
     * 查询收银台列表
     */
    @RequiresPermissions("system:checkoutcounter:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysCheckoutCounter sysCheckoutCounter)
    {
        startPage();
        List<SysCheckoutCounter> list = sysCheckoutCounterService.selectSysCheckoutCounterList(sysCheckoutCounter);
        return getDataTable(list);
    }

    /**
     * 导出收银台列表
     */
    @RequiresPermissions("system:checkoutcounter:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SysCheckoutCounter sysCheckoutCounter)
    {
        List<SysCheckoutCounter> list = sysCheckoutCounterService.selectSysCheckoutCounterList(sysCheckoutCounter);
        ExcelUtil<SysCheckoutCounter> util = new ExcelUtil<SysCheckoutCounter>(SysCheckoutCounter.class);
        return util.exportExcel(list, "checkoutcounter");
    }

    /**
     * 新增收银台
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存收银台
     */
    @RequiresPermissions("system:checkoutcounter:add")
    @Log(title = "收银台", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SysCheckoutCounter sysCheckoutCounter)
    {
        return toAjax(sysCheckoutCounterService.insertSysCheckoutCounter(sysCheckoutCounter));
    }

    /**
     * 修改收银台
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        SysCheckoutCounter sysCheckoutCounter = sysCheckoutCounterService.selectSysCheckoutCounterById(id);
        mmap.put("sysCheckoutCounter", sysCheckoutCounter);
        return prefix + "/edit";
    }

    /**
     * 修改保存收银台
     */
    @RequiresPermissions("system:checkoutcounter:edit")
    @Log(title = "收银台", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SysCheckoutCounter sysCheckoutCounter)
    {
    	int count = 0;
    	synchronized (sysCheckoutCounter.getId()) {
    		if(sysCheckoutCounter.getStatus() == null || sysCheckoutCounter.getStatus() <= 1){
    			return AjaxResult.error("状态必须改变");
    		}
    		SysCheckoutCounter bean = this.sysCheckoutCounterService.selectSysCheckoutCounterById(sysCheckoutCounter.getId());
    		if(bean.getStatus() != 1){
    			return AjaxResult.error("状态不对，无法处理");
    		}
    		sysCheckoutCounter.setUpdateBy(ShiroUtils.getUserId());
    		count = sysCheckoutCounterService.updateSysCheckoutCounter(sysCheckoutCounter);
		}
        return toAjax(count);
    }

    /**
     * 删除收银台
     */
    @RequiresPermissions("system:checkoutcounter:remove")
    @Log(title = "收银台", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(sysCheckoutCounterService.deleteSysCheckoutCounterByIds(ids));
    }
    
}
