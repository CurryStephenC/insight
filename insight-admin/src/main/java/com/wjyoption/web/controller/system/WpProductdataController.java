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
import com.wjyoption.system.domain.WpProductdata;
import com.wjyoption.system.service.IWpProductdataService;
import com.wjyoption.common.core.controller.BaseController;
import com.wjyoption.common.core.domain.AjaxResult;
import com.wjyoption.common.utils.poi.ExcelUtil;
import com.wjyoption.common.core.page.TableDataInfo;

/**
 * 产品数据Controller
 * 
 * @author wjyoption
 * @date 2021-07-10
 */
@Controller
@RequestMapping("/system/productdata")
public class WpProductdataController extends BaseController
{
    private String prefix = "system/productdata";

    @Autowired
    private IWpProductdataService wpProductdataService;

    @RequiresPermissions("system:productdata:view")
    @GetMapping()
    public String productdata()
    {
        return prefix + "/productdata";
    }

    /**
     * 查询产品数据列表
     */
    @RequiresPermissions("system:productdata:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(WpProductdata wpProductdata)
    {
        startPage();
        List<WpProductdata> list = wpProductdataService.selectWpProductdataList(wpProductdata);
        return getDataTable(list);
    }

    /**
     * 导出产品数据列表
     */
    @RequiresPermissions("system:productdata:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(WpProductdata wpProductdata)
    {
        List<WpProductdata> list = wpProductdataService.selectWpProductdataList(wpProductdata);
        ExcelUtil<WpProductdata> util = new ExcelUtil<WpProductdata>(WpProductdata.class);
        return util.exportExcel(list, "productdata");
    }

    /**
     * 新增产品数据
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存产品数据
     */
    @RequiresPermissions("system:productdata:add")
    @Log(title = "产品数据", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(WpProductdata wpProductdata)
    {
        return toAjax(wpProductdataService.insertWpProductdata(wpProductdata));
    }

    /**
     * 修改产品数据
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap)
    {
        WpProductdata wpProductdata = wpProductdataService.selectWpProductdataById(id);
        mmap.put("wpProductdata", wpProductdata);
        return prefix + "/edit";
    }

    /**
     * 修改保存产品数据
     */
    @RequiresPermissions("system:productdata:edit")
    @Log(title = "产品数据", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(WpProductdata wpProductdata)
    {
        return toAjax(wpProductdataService.updateWpProductdata(wpProductdata));
    }

    /**
     * 删除产品数据
     */
    @RequiresPermissions("system:productdata:remove")
    @Log(title = "产品数据", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(wpProductdataService.deleteWpProductdataByIds(ids));
    }
}
