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
import com.wjyoption.system.domain.WpCommunicateGroup;
import com.wjyoption.system.service.IWpCommunicateGroupService;
import com.wjyoption.common.core.controller.BaseController;
import com.wjyoption.common.core.domain.AjaxResult;
import com.wjyoption.common.utils.poi.ExcelUtil;
import com.wjyoption.common.core.page.TableDataInfo;

/**
 * 交流推广群Controller
 * 
 * @author wjyoption
 * @date 2021-07-28
 */
@Controller
@RequestMapping("/system/group")
public class WpCommunicateGroupController extends BaseController
{
    private String prefix = "system/group";

    @Autowired
    private IWpCommunicateGroupService wpCommunicateGroupService;

    @RequiresPermissions("system:group:view")
    @GetMapping()
    public String group()
    {
        return prefix + "/group";
    }

    /**
     * 查询交流推广群列表
     */
    @RequiresPermissions("system:group:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(WpCommunicateGroup wpCommunicateGroup)
    {
        startPage();
        List<WpCommunicateGroup> list = wpCommunicateGroupService.selectWpCommunicateGroupList(wpCommunicateGroup);
        return getDataTable(list);
    }

    /**
     * 导出交流推广群列表
     */
    @RequiresPermissions("system:group:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(WpCommunicateGroup wpCommunicateGroup)
    {
        List<WpCommunicateGroup> list = wpCommunicateGroupService.selectWpCommunicateGroupList(wpCommunicateGroup);
        ExcelUtil<WpCommunicateGroup> util = new ExcelUtil<WpCommunicateGroup>(WpCommunicateGroup.class);
        return util.exportExcel(list, "group");
    }

    /**
     * 新增交流推广群
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存交流推广群
     */
    @RequiresPermissions("system:group:add")
    @Log(title = "交流推广群", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(WpCommunicateGroup wpCommunicateGroup)
    {
        return toAjax(wpCommunicateGroupService.insertWpCommunicateGroup(wpCommunicateGroup));
    }

    /**
     * 修改交流推广群
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        WpCommunicateGroup wpCommunicateGroup = wpCommunicateGroupService.selectWpCommunicateGroupById(id);
        mmap.put("wpCommunicateGroup", wpCommunicateGroup);
        return prefix + "/edit";
    }

    /**
     * 修改保存交流推广群
     */
    @RequiresPermissions("system:group:edit")
    @Log(title = "交流推广群", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(WpCommunicateGroup wpCommunicateGroup)
    {
        return toAjax(wpCommunicateGroupService.updateWpCommunicateGroup(wpCommunicateGroup));
    }

    /**
     * 删除交流推广群
     */
    @RequiresPermissions("system:group:remove")
    @Log(title = "交流推广群", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(wpCommunicateGroupService.deleteWpCommunicateGroupByIds(ids));
    }
}
