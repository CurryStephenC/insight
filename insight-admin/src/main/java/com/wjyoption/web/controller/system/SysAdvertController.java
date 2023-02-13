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
import com.wjyoption.system.domain.SysAdvert;
import com.wjyoption.system.service.ISysAdvertService;

/**
 * 广告Controller
 * 
 * @author hs
 * @date 2019-09-19
 */
@Controller
@RequestMapping("/system/advert")
public class SysAdvertController extends BaseController
{
    private String prefix = "system/advert";

    @Autowired
    private ISysAdvertService sysAdvertService;

    @RequiresPermissions("system:advert:view")
    @GetMapping()
    public String advert()
    {
        return prefix + "/advert";
    }

    /**
     * 查询广告列表
     */
    @RequiresPermissions("system:advert:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysAdvert sysAdvert)
    {
        startPage();
        List<SysAdvert> list = sysAdvertService.selectSysAdvertList(sysAdvert);
        return getDataTable(list);
    }

    /**
     * 新增广告
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存广告
     */
    @RequiresPermissions("system:advert:add")
    @Log(title = "广告", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SysAdvert sysAdvert)
    {
        return toAjax(sysAdvertService.insertSysAdvert(sysAdvert));
    }

    /**
     * 修改广告
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        SysAdvert sysAdvert = sysAdvertService.selectSysAdvertById(id);
        mmap.put("sysAdvert", sysAdvert);
        return prefix + "/edit";
    }

    /**
     * 修改保存广告
     */
    @RequiresPermissions("system:advert:edit")
    @Log(title = "广告", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SysAdvert sysAdvert)
    {
        return toAjax(sysAdvertService.updateSysAdvert(sysAdvert));
    }

    /**
     * 删除广告
     */
    @RequiresPermissions("system:advert:remove")
    @Log(title = "广告", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(sysAdvertService.deleteSysAdvertByIds(ids));
    }
}
