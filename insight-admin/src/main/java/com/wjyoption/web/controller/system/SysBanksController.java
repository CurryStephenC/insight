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
import com.wjyoption.system.domain.SysBanks;
import com.wjyoption.system.service.ISysBanksService;
import com.wjyoption.common.core.controller.BaseController;
import com.wjyoption.common.core.domain.AjaxResult;
import com.wjyoption.common.utils.poi.ExcelUtil;
import com.wjyoption.common.core.page.TableDataInfo;

/**
 * 银行信息Controller
 * 
 * @author hs @date 2021-03-29
 */
@Controller
@RequestMapping("/system/banks")
public class SysBanksController extends BaseController
{
    private String prefix = "system/banks";

    @Autowired
    private ISysBanksService sysBanksService;

    @RequiresPermissions("system:banks:view")
    @GetMapping()
    public String banks()
    {
        return prefix + "/banks";
    }

    /**
     * 查询银行信息列表
     */
    @RequiresPermissions("system:banks:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysBanks sysBanks)
    {
        startPage();
        List<SysBanks> list = sysBanksService.selectSysBanksList(sysBanks);
        return getDataTable(list);
    }

    /**
     * 导出银行信息列表
     */
    @RequiresPermissions("system:banks:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SysBanks sysBanks)
    {
        List<SysBanks> list = sysBanksService.selectSysBanksList(sysBanks);
        ExcelUtil<SysBanks> util = new ExcelUtil<SysBanks>(SysBanks.class);
        return util.exportExcel(list, "banks");
    }

    /**
     * 新增银行信息
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存银行信息
     */
    @RequiresPermissions("system:banks:add")
    @Log(title = "银行信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SysBanks sysBanks)
    {
        return toAjax(sysBanksService.insertSysBanks(sysBanks));
    }

    /**
     * 修改银行信息
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap)
    {
        SysBanks sysBanks = sysBanksService.selectSysBanksById(id);
        mmap.put("sysBanks", sysBanks);
        return prefix + "/edit";
    }

    /**
     * 修改保存银行信息
     */
    @RequiresPermissions("system:banks:edit")
    @Log(title = "银行信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SysBanks sysBanks)
    {
        return toAjax(sysBanksService.updateSysBanks(sysBanks));
    }

    /**
     * 删除银行信息
     */
    @RequiresPermissions("system:banks:remove")
    @Log(title = "银行信息", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(sysBanksService.deleteSysBanksByIds(ids));
    }
}
