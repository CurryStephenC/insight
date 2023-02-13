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
import com.wjyoption.system.domain.WpFinancialBuy;
import com.wjyoption.system.service.IWpFinancialBuyService;
import com.wjyoption.common.core.controller.BaseController;
import com.wjyoption.common.core.domain.AjaxResult;
import com.wjyoption.common.utils.poi.ExcelUtil;
import com.wjyoption.common.core.page.TableDataInfo;

/**
 * 老师购买记录Controller
 * 
 * @author wjyoption
 * @date 2021-06-07
 */
@Controller
@RequestMapping("/system/financialbuy")
public class WpFinancialBuyController extends BaseController
{
    private String prefix = "system/financialbuy";

    @Autowired
    private IWpFinancialBuyService wpFinancialBuyService;

    @RequiresPermissions("system:financialbuy:view")
    @GetMapping()
    public String financialbuy()
    {
        return prefix + "/financialbuy";
    }

    /**
     * 查询老师购买记录列表
     */
    @RequiresPermissions("system:financialbuy:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(WpFinancialBuy wpFinancialBuy)
    {
        startPage();
        List<WpFinancialBuy> list = wpFinancialBuyService.selectWpFinancialBuyList(wpFinancialBuy);
        return getDataTable(list);
    }

    /**
     * 导出老师购买记录列表
     */
    @RequiresPermissions("system:financialbuy:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(WpFinancialBuy wpFinancialBuy)
    {
        List<WpFinancialBuy> list = wpFinancialBuyService.selectWpFinancialBuyList(wpFinancialBuy);
        ExcelUtil<WpFinancialBuy> util = new ExcelUtil<WpFinancialBuy>(WpFinancialBuy.class);
        return util.exportExcel(list, "financialbuy");
    }

    /**
     * 新增老师购买记录
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存老师购买记录
     */
    @RequiresPermissions("system:financialbuy:add")
    @Log(title = "老师购买记录", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(WpFinancialBuy wpFinancialBuy)
    {
        return toAjax(wpFinancialBuyService.insertWpFinancialBuy(wpFinancialBuy));
    }

    /**
     * 修改老师购买记录
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap)
    {
        WpFinancialBuy wpFinancialBuy = wpFinancialBuyService.selectWpFinancialBuyById(id);
        mmap.put("wpFinancialBuy", wpFinancialBuy);
        return prefix + "/edit";
    }

    /**
     * 修改保存老师购买记录
     */
    @RequiresPermissions("system:financialbuy:edit")
    @Log(title = "老师购买记录", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(WpFinancialBuy wpFinancialBuy)
    {
        return toAjax(wpFinancialBuyService.updateWpFinancialBuy(wpFinancialBuy));
    }

    /**
     * 删除老师购买记录
     */
    @RequiresPermissions("system:financialbuy:remove")
    @Log(title = "老师购买记录", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(wpFinancialBuyService.deleteWpFinancialBuyByIds(ids));
    }
}
