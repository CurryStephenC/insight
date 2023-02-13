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
import com.wjyoption.system.domain.SysDeviceVersion;
import com.wjyoption.system.service.ISysDeviceVersionService;
import com.wjyoption.common.core.controller.BaseController;
import com.wjyoption.common.core.domain.AjaxResult;
import com.wjyoption.common.utils.poi.ExcelUtil;
import com.wjyoption.common.core.page.TableDataInfo;

/**
 * 设备版本信息Controller
 * 
 * @author wjyoption
 * @date 2021-06-11
 */
@Controller
@RequestMapping("/system/deviceversion")
public class SysDeviceVersionController extends BaseController
{
    private String prefix = "system/deviceversion";

    @Autowired
    private ISysDeviceVersionService sysDeviceVersionService;

    @RequiresPermissions("system:deviceversion:view")
    @GetMapping()
    public String deviceversion()
    {
        return prefix + "/deviceversion";
    }

    /**
     * 查询设备版本信息列表
     */
    @RequiresPermissions("system:deviceversion:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysDeviceVersion sysDeviceVersion)
    {
        startPage();
        List<SysDeviceVersion> list = sysDeviceVersionService.selectSysDeviceVersionList(sysDeviceVersion);
        return getDataTable(list);
    }

    /**
     * 导出设备版本信息列表
     */
    @RequiresPermissions("system:deviceversion:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SysDeviceVersion sysDeviceVersion)
    {
        List<SysDeviceVersion> list = sysDeviceVersionService.selectSysDeviceVersionList(sysDeviceVersion);
        ExcelUtil<SysDeviceVersion> util = new ExcelUtil<SysDeviceVersion>(SysDeviceVersion.class);
        return util.exportExcel(list, "deviceversion");
    }

    /**
     * 新增设备版本信息
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存设备版本信息
     */
    @RequiresPermissions("system:deviceversion:add")
    @Log(title = "设备版本信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SysDeviceVersion sysDeviceVersion)
    {
        return toAjax(sysDeviceVersionService.insertSysDeviceVersion(sysDeviceVersion));
    }

    /**
     * 修改设备版本信息
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap)
    {
        SysDeviceVersion sysDeviceVersion = sysDeviceVersionService.selectSysDeviceVersionById(id);
        mmap.put("sysDeviceVersion", sysDeviceVersion);
        return prefix + "/edit";
    }

    /**
     * 修改保存设备版本信息
     */
    @RequiresPermissions("system:deviceversion:edit")
    @Log(title = "设备版本信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SysDeviceVersion sysDeviceVersion)
    {
        return toAjax(sysDeviceVersionService.updateSysDeviceVersion(sysDeviceVersion));
    }

    /**
     * 删除设备版本信息
     */
    @RequiresPermissions("system:deviceversion:remove")
    @Log(title = "设备版本信息", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(sysDeviceVersionService.deleteSysDeviceVersionByIds(ids));
    }
}
