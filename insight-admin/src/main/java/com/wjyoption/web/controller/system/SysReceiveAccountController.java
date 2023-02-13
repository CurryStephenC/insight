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
import com.wjyoption.system.domain.SysReceiveAccount;
import com.wjyoption.system.service.ISysReceiveAccountService;
import com.wjyoption.common.core.controller.BaseController;
import com.wjyoption.common.core.domain.AjaxResult;
import com.wjyoption.common.utils.poi.ExcelUtil;
import com.wjyoption.common.core.page.TableDataInfo;

/**
 * 收款账号Controller
 * 
 * @author hs
 * @date 2021-06-25
 */
@Controller
@RequestMapping("/system/receiveaccount")
public class SysReceiveAccountController extends BaseController
{
    private String prefix = "system/receiveaccount";

    @Autowired
    private ISysReceiveAccountService sysReceiveAccountService;

    @RequiresPermissions("system:receiveaccount:view")
    @GetMapping()
    public String receiveaccount()
    {
        return prefix + "/receiveaccount";
    }

    /**
     * 查询收款账号列表
     */
    @RequiresPermissions("system:receiveaccount:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysReceiveAccount sysReceiveAccount)
    {
        startPage();
        List<SysReceiveAccount> list = sysReceiveAccountService.selectSysReceiveAccountList(sysReceiveAccount);
        return getDataTable(list);
    }

    /**
     * 导出收款账号列表
     */
    @RequiresPermissions("system:receiveaccount:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SysReceiveAccount sysReceiveAccount)
    {
        List<SysReceiveAccount> list = sysReceiveAccountService.selectSysReceiveAccountList(sysReceiveAccount);
        ExcelUtil<SysReceiveAccount> util = new ExcelUtil<SysReceiveAccount>(SysReceiveAccount.class);
        return util.exportExcel(list, "receiveaccount");
    }

    /**
     * 新增收款账号
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存收款账号
     */
    @RequiresPermissions("system:receiveaccount:add")
    @Log(title = "收款账号", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SysReceiveAccount sysReceiveAccount)
    {
        return toAjax(sysReceiveAccountService.insertSysReceiveAccount(sysReceiveAccount));
    }

    /**
     * 修改收款账号
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        SysReceiveAccount sysReceiveAccount = sysReceiveAccountService.selectSysReceiveAccountById(id);
        mmap.put("sysReceiveAccount", sysReceiveAccount);
        return prefix + "/edit";
    }

    /**
     * 修改保存收款账号
     */
    @RequiresPermissions("system:receiveaccount:edit")
    @Log(title = "收款账号", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SysReceiveAccount sysReceiveAccount)
    {
        return toAjax(sysReceiveAccountService.updateSysReceiveAccount(sysReceiveAccount));
    }

    /**
     * 删除收款账号
     */
    @RequiresPermissions("system:receiveaccount:remove")
    @Log(title = "收款账号", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(sysReceiveAccountService.deleteSysReceiveAccountByIds(ids));
    }
}
