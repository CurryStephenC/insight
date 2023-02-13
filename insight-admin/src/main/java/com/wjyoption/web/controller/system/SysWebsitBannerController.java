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
import com.wjyoption.system.domain.SysWebsitBanner;
import com.wjyoption.system.service.ISysWebsitBannerService;
import com.wjyoption.common.core.controller.BaseController;
import com.wjyoption.common.core.domain.AjaxResult;
import com.wjyoption.common.utils.poi.ExcelUtil;
import com.wjyoption.common.core.page.TableDataInfo;

/**
 * 网站bannerController
 * 
 * @author hs
 * @date 2020-02-07
 */
@Controller
@RequestMapping("/system/banner")
public class SysWebsitBannerController extends BaseController
{
    private String prefix = "system/banner";

    @Autowired
    private ISysWebsitBannerService sysWebsitBannerService;

    @RequiresPermissions("system:banner:view")
    @GetMapping()
    public String banner()
    {
        return prefix + "/banner";
    }

    /**
     * 查询网站banner列表
     */
    @RequiresPermissions("system:banner:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysWebsitBanner sysWebsitBanner)
    {
        startPage();
        List<SysWebsitBanner> list = sysWebsitBannerService.selectSysWebsitBannerList(sysWebsitBanner);
        return getDataTable(list);
    }

    /**
     * 导出网站banner列表
     */
    @RequiresPermissions("system:banner:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SysWebsitBanner sysWebsitBanner)
    {
        List<SysWebsitBanner> list = sysWebsitBannerService.selectSysWebsitBannerList(sysWebsitBanner);
        ExcelUtil<SysWebsitBanner> util = new ExcelUtil<SysWebsitBanner>(SysWebsitBanner.class);
        return util.exportExcel(list, "banner");
    }

    /**
     * 新增网站banner
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存网站banner
     */
    @RequiresPermissions("system:banner:add")
    @Log(title = "网站banner", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SysWebsitBanner sysWebsitBanner)
    {
        return toAjax(sysWebsitBannerService.insertSysWebsitBanner(sysWebsitBanner));
    }

    /**
     * 修改网站banner
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap)
    {
        SysWebsitBanner sysWebsitBanner = sysWebsitBannerService.selectSysWebsitBannerById(id);
        mmap.put("sysWebsitBanner", sysWebsitBanner);
        return prefix + "/edit";
    }

    /**
     * 修改保存网站banner
     */
    @RequiresPermissions("system:banner:edit")
    @Log(title = "网站banner", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SysWebsitBanner sysWebsitBanner)
    {
        return toAjax(sysWebsitBannerService.updateSysWebsitBanner(sysWebsitBanner));
    }

    /**
     * 删除网站banner
     */
    @RequiresPermissions("system:banner:remove")
    @Log(title = "网站banner", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(sysWebsitBannerService.deleteSysWebsitBannerByIds(ids));
    }
}
