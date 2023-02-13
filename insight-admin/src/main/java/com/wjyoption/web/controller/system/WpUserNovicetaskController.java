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
import com.wjyoption.system.domain.WpUserNovicetask;
import com.wjyoption.system.service.IWpUserNovicetaskService;
import com.wjyoption.common.core.controller.BaseController;
import com.wjyoption.common.core.domain.AjaxResult;
import com.wjyoption.common.utils.poi.ExcelUtil;
import com.wjyoption.common.core.page.TableDataInfo;

/**
 * 用户新手任务Controller
 * 
 * @author wjyoption
 * @date 2021-07-28
 */
@Controller
@RequestMapping("/system/novicetask")
public class WpUserNovicetaskController extends BaseController
{
    private String prefix = "system/novicetask";

    @Autowired
    private IWpUserNovicetaskService wpUserNovicetaskService;

    @RequiresPermissions("system:novicetask:view")
    @GetMapping()
    public String novicetask()
    {
        return prefix + "/novicetask";
    }

    /**
     * 查询用户新手任务列表
     */
    @RequiresPermissions("system:novicetask:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(WpUserNovicetask wpUserNovicetask)
    {
        startPage();
        List<WpUserNovicetask> list = wpUserNovicetaskService.selectWpUserNovicetaskList(wpUserNovicetask);
        return getDataTable(list);
    }

    /**
     * 导出用户新手任务列表
     */
    @RequiresPermissions("system:novicetask:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(WpUserNovicetask wpUserNovicetask)
    {
        List<WpUserNovicetask> list = wpUserNovicetaskService.selectWpUserNovicetaskList(wpUserNovicetask);
        ExcelUtil<WpUserNovicetask> util = new ExcelUtil<WpUserNovicetask>(WpUserNovicetask.class);
        return util.exportExcel(list, "novicetask");
    }

    /**
     * 新增用户新手任务
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存用户新手任务
     */
    @RequiresPermissions("system:novicetask:add")
    @Log(title = "用户新手任务", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(WpUserNovicetask wpUserNovicetask)
    {
        return toAjax(wpUserNovicetaskService.insertWpUserNovicetask(wpUserNovicetask));
    }

    /**
     * 修改用户新手任务
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap)
    {
        WpUserNovicetask wpUserNovicetask = wpUserNovicetaskService.selectWpUserNovicetaskById(id);
        mmap.put("wpUserNovicetask", wpUserNovicetask);
        return prefix + "/edit";
    }

    /**
     * 修改保存用户新手任务
     */
    @RequiresPermissions("system:novicetask:edit")
    @Log(title = "用户新手任务", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(WpUserNovicetask wpUserNovicetask)
    {
        return toAjax(wpUserNovicetaskService.updateWpUserNovicetask(wpUserNovicetask));
    }

    /**
     * 删除用户新手任务
     */
    @RequiresPermissions("system:novicetask:remove")
    @Log(title = "用户新手任务", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(wpUserNovicetaskService.deleteWpUserNovicetaskByIds(ids));
    }
}
