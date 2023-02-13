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
import com.wjyoption.system.domain.SysArticleType;
import com.wjyoption.system.service.ISysArticleTypeService;
import com.wjyoption.common.core.controller.BaseController;
import com.wjyoption.common.core.domain.AjaxResult;
import com.wjyoption.common.utils.poi.ExcelUtil;
import com.wjyoption.common.core.page.TableDataInfo;

/**
 * 网站文章类型Controller
 * 
 * @author hs
 * @date 2020-02-06
 */
@Controller
@RequestMapping("/system/articletype")
public class SysArticleTypeController extends BaseController
{
    private String prefix = "system/articletype";

    @Autowired
    private ISysArticleTypeService sysArticleTypeService;

    @RequiresPermissions("system:articletype:view")
    @GetMapping()
    public String articletype()
    {
        return prefix + "/articletype";
    }

    /**
     * 查询网站文章类型列表
     */
    @RequiresPermissions("system:articletype:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysArticleType sysArticleType)
    {
        startPage();
        List<SysArticleType> list = sysArticleTypeService.selectSysArticleTypeList(sysArticleType);
        return getDataTable(list);
    }

    /**
     * 导出网站文章类型列表
     */
    @RequiresPermissions("system:articletype:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SysArticleType sysArticleType)
    {
        List<SysArticleType> list = sysArticleTypeService.selectSysArticleTypeList(sysArticleType);
        ExcelUtil<SysArticleType> util = new ExcelUtil<SysArticleType>(SysArticleType.class);
        return util.exportExcel(list, "articletype");
    }

    /**
     * 新增网站文章类型
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存网站文章类型
     */
    @RequiresPermissions("system:articletype:add")
    @Log(title = "网站文章类型", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SysArticleType sysArticleType)
    {
        return toAjax(sysArticleTypeService.insertSysArticleType(sysArticleType));
    }

    /**
     * 修改网站文章类型
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        SysArticleType sysArticleType = sysArticleTypeService.selectSysArticleTypeById(id);
        mmap.put("sysArticleType", sysArticleType);
        return prefix + "/edit";
    }

    /**
     * 修改保存网站文章类型
     */
    @RequiresPermissions("system:articletype:edit")
    @Log(title = "网站文章类型", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SysArticleType sysArticleType)
    {
        return toAjax(sysArticleTypeService.updateSysArticleType(sysArticleType));
    }

    /**
     * 删除网站文章类型
     */
    @RequiresPermissions("system:articletype:remove")
    @Log(title = "网站文章类型", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(sysArticleTypeService.deleteSysArticleTypeByIds(ids));
    }
}
