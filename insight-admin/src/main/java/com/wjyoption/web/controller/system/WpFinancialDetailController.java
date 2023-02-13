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
import com.wjyoption.system.domain.WpFinancialDetail;
import com.wjyoption.system.service.IWpFinancialDetailService;
import com.wjyoption.common.core.controller.BaseController;
import com.wjyoption.common.core.domain.AjaxResult;
import com.wjyoption.common.utils.poi.ExcelUtil;
import com.wjyoption.common.core.page.TableDataInfo;

/**
 * 理财收益详情Controller
 * 
 * @author wjyoption
 * @date 2021-06-07
 */
@Controller
@RequestMapping("/system/financialrecorddetail")
public class WpFinancialDetailController extends BaseController
{
    private String prefix = "system/financialrecorddetail";

    @Autowired
    private IWpFinancialDetailService wpFinancialDetailService;

    @RequiresPermissions("system:financialrecorddetail:view")
    @GetMapping()
    public String financialrecorddetail()
    {
        return prefix + "/financialrecorddetail";
    }

    /**
     * 查询理财收益详情列表
     */
    @RequiresPermissions("system:financialrecorddetail:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(WpFinancialDetail wpFinancialDetail)
    {
        startPage();
        List<WpFinancialDetail> list = wpFinancialDetailService.selectWpFinancialDetailList(wpFinancialDetail);
        return getDataTable(list);
    }

    /**
     * 导出理财收益详情列表
     */
    @RequiresPermissions("system:financialrecorddetail:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(WpFinancialDetail wpFinancialDetail)
    {
        List<WpFinancialDetail> list = wpFinancialDetailService.selectWpFinancialDetailList(wpFinancialDetail);
        ExcelUtil<WpFinancialDetail> util = new ExcelUtil<WpFinancialDetail>(WpFinancialDetail.class);
        return util.exportExcel(list, "financialrecorddetail");
    }

    /**
     * 新增理财收益详情
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存理财收益详情
     */
    @RequiresPermissions("system:financialrecorddetail:add")
    @Log(title = "理财收益详情", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(WpFinancialDetail wpFinancialDetail)
    {
        return toAjax(wpFinancialDetailService.insertWpFinancialDetail(wpFinancialDetail));
    }

    /**
     * 修改理财收益详情
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap)
    {
        WpFinancialDetail wpFinancialDetail = wpFinancialDetailService.selectWpFinancialDetailById(id);
        mmap.put("wpFinancialDetail", wpFinancialDetail);
        return prefix + "/edit";
    }

    /**
     * 修改保存理财收益详情
     */
    @RequiresPermissions("system:financialrecorddetail:edit")
    @Log(title = "理财收益详情", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(WpFinancialDetail wpFinancialDetail)
    {
        return toAjax(wpFinancialDetailService.updateWpFinancialDetail(wpFinancialDetail));
    }

    /**
     * 删除理财收益详情
     */
    @RequiresPermissions("system:financialrecorddetail:remove")
    @Log(title = "理财收益详情", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(wpFinancialDetailService.deleteWpFinancialDetailByIds(ids));
    }
}
