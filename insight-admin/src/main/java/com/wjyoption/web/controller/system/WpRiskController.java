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
import com.wjyoption.system.domain.WpRisk;
import com.wjyoption.system.service.IWpRiskService;
import com.wjyoption.common.core.controller.BaseController;
import com.wjyoption.common.core.domain.AjaxResult;
import com.wjyoption.common.utils.poi.ExcelUtil;
import com.wjyoption.common.core.page.TableDataInfo;

/**
 * 风控Controller
 * 
 * @author wjyoption
 * @date 2021-07-08
 */
@Controller
@RequestMapping("/system/risk")
public class WpRiskController extends BaseController
{
    private String prefix = "system/risk";

    @Autowired
    private IWpRiskService wpRiskService;

    @RequiresPermissions("system:risk:view")
    @GetMapping()
    public String risk()
    {
        return prefix + "/risk";
    }

    /**
     * 查询风控列表
     */
    @RequiresPermissions("system:risk:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(WpRisk wpRisk)
    {
        startPage();
        List<WpRisk> list = wpRiskService.selectWpRiskList(wpRisk);
        return getDataTable(list);
    }

    /**
     * 导出风控列表
     */
    @RequiresPermissions("system:risk:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(WpRisk wpRisk)
    {
        List<WpRisk> list = wpRiskService.selectWpRiskList(wpRisk);
        ExcelUtil<WpRisk> util = new ExcelUtil<WpRisk>(WpRisk.class);
        return util.exportExcel(list, "risk");
    }

    /**
     * 新增风控
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存风控
     */
    @RequiresPermissions("system:risk:add")
    @Log(title = "风控", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(WpRisk wpRisk)
    {
        return toAjax(wpRiskService.insertWpRisk(wpRisk));
    }

    /**
     * 修改风控
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap)
    {
        WpRisk wpRisk = wpRiskService.selectWpRiskById(id);
        mmap.put("wpRisk", wpRisk);
        return prefix + "/edit";
    }

    /**
     * 修改保存风控
     */
    @RequiresPermissions("system:risk:edit")
    @Log(title = "风控", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(WpRisk wpRisk)
    {
        return toAjax(wpRiskService.updateWpRisk(wpRisk));
    }

    /**
     * 删除风控
     */
    @RequiresPermissions("system:risk:remove")
    @Log(title = "风控", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(wpRiskService.deleteWpRiskByIds(ids));
    }
}
