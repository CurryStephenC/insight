package com.wjyoption.web.controller.system;

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
import com.wjyoption.common.enums.BusinessType;
import com.wjyoption.system.domain.SysNavigationBar;
import com.wjyoption.system.service.ISysNavigationBarService;
import com.wjyoption.common.core.controller.BaseController;
import com.wjyoption.common.core.domain.AjaxResult;
import com.wjyoption.common.utils.poi.ExcelUtil;
import com.wjyoption.common.core.page.TableDataInfo;

/**
 * 导航栏Controller
 * 
 * @author hs
 * @date 2020-07-01
 */
@Controller
@RequestMapping("/system/bar")
public class SysNavigationBarController extends BaseController
{
    private String prefix = "system/bar";

    @Autowired
    private ISysNavigationBarService sysNavigationBarService;

    @RequiresPermissions("system:bar:view")
    @GetMapping()
    public String bar()
    {
        return prefix + "/bar";
    }

    /**
     * 查询导航栏列表
     */
    @RequiresPermissions("system:bar:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysNavigationBar sysNavigationBar)
    {
        startPage();
        List<SysNavigationBar> list = sysNavigationBarService.selectSysNavigationBarList(sysNavigationBar);
        return getDataTable(list);
    }

    /**
     * 导出导航栏列表
     */
    @RequiresPermissions("system:bar:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SysNavigationBar sysNavigationBar)
    {
        List<SysNavigationBar> list = sysNavigationBarService.selectSysNavigationBarList(sysNavigationBar);
        ExcelUtil<SysNavigationBar> util = new ExcelUtil<SysNavigationBar>(SysNavigationBar.class);
        return util.exportExcel(list, "bar");
    }

    /**
     * 新增导航栏
     */
    @GetMapping("/add")
    public String add(ModelMap mmap)
    {
    	SysNavigationBar para = new SysNavigationBar();
    	para.setBarlevel(1);
    	List<SysNavigationBar> bar1List = this.sysNavigationBarService.selectSysNavigationBarList(para);
    	para.setBarlevel(2);
    	List<SysNavigationBar> bar2List = this.sysNavigationBarService.selectSysNavigationBarList(para);
    	
        mmap.put("bar1List", bar1List);
        mmap.put("bar2List", bar2List);
        return prefix + "/add";
    }

    /**
     * 新增保存导航栏
     */
    @RequiresPermissions("system:bar:add")
    @Log(title = "导航栏", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SysNavigationBar sysNavigationBar)
    {
    	if(StringUtils.isNotBlank(sysNavigationBar.getTourl())){
    		sysNavigationBar.setTourl(super.getRequest().getParameter("tourl"));
    	}
        return toAjax(sysNavigationBarService.insertSysNavigationBar(sysNavigationBar));
    }

    /**
     * 修改导航栏
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
    	SysNavigationBar para = new SysNavigationBar();
    	para.setBarlevel(1);
    	List<SysNavigationBar> bar1List = this.sysNavigationBarService.selectSysNavigationBarList(para);
    	para.setBarlevel(2);
    	List<SysNavigationBar> bar2List = this.sysNavigationBarService.selectSysNavigationBarList(para);
    	
        mmap.put("bar1List", bar1List);
        mmap.put("bar2List", bar2List);
        SysNavigationBar sysNavigationBar = sysNavigationBarService.selectSysNavigationBarById(id);
        mmap.put("sysNavigationBar", sysNavigationBar);
        return prefix + "/edit";
    }

    /**
     * 修改保存导航栏
     */
    @RequiresPermissions("system:bar:edit")
    @Log(title = "导航栏", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SysNavigationBar sysNavigationBar)
    {
    	if(StringUtils.isNotBlank(sysNavigationBar.getTourl())){
    		sysNavigationBar.setTourl(super.getRequest().getParameter("tourl"));
    	}
        return toAjax(sysNavigationBarService.updateSysNavigationBar(sysNavigationBar));
    }

    /**
     * 删除导航栏
     */
    @RequiresPermissions("system:bar:remove")
    @Log(title = "导航栏", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(sysNavigationBarService.deleteSysNavigationBarByIds(ids));
    }
}
