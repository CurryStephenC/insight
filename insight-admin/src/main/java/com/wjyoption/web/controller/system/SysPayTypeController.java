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
import com.wjyoption.system.domain.SysPayType;
import com.wjyoption.system.service.ISysPayTypeService;
import com.wjyoption.common.core.controller.BaseController;
import com.wjyoption.common.core.domain.AjaxResult;
import com.wjyoption.common.utils.poi.ExcelUtil;
import com.wjyoption.common.core.page.TableDataInfo;

/**
 * 支付方式Controller
 * 
 * @author wjyoption
 * @date 2021-06-26
 */
@Controller
@RequestMapping("/system/paytype")
public class SysPayTypeController extends BaseController
{
    private String prefix = "system/paytype";

    @Autowired
    private ISysPayTypeService sysPayTypeService;

    @RequiresPermissions("system:paytype:view")
    @GetMapping()
    public String paytype()
    {
        return prefix + "/paytype";
    }

    /**
     * 查询支付方式列表
     */
    @RequiresPermissions("system:paytype:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysPayType sysPayType)
    {
        startPage();
        List<SysPayType> list = sysPayTypeService.selectSysPayTypeList(sysPayType);
        return getDataTable(list);
    }

    /**
     * 导出支付方式列表
     */
    @RequiresPermissions("system:paytype:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SysPayType sysPayType)
    {
        List<SysPayType> list = sysPayTypeService.selectSysPayTypeList(sysPayType);
        ExcelUtil<SysPayType> util = new ExcelUtil<SysPayType>(SysPayType.class);
        return util.exportExcel(list, "paytype");
    }

    /**
     * 新增支付方式
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存支付方式
     */
    @RequiresPermissions("system:paytype:add")
    @Log(title = "支付方式", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SysPayType sysPayType)
    {
        return toAjax(sysPayTypeService.insertSysPayType(sysPayType));
    }

    /**
     * 修改支付方式
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        SysPayType sysPayType = sysPayTypeService.selectSysPayTypeById(id);
        mmap.put("sysPayType", sysPayType);
        return prefix + "/edit";
    }

    /**
     * 修改保存支付方式
     */
    @RequiresPermissions("system:paytype:edit")
    @Log(title = "支付方式", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SysPayType sysPayType)
    {
        return toAjax(sysPayTypeService.updateSysPayType(sysPayType));
    }

    /**
     * 删除支付方式
     */
    @RequiresPermissions("system:paytype:remove")
    @Log(title = "支付方式", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(sysPayTypeService.deleteSysPayTypeByIds(ids));
    }
}
