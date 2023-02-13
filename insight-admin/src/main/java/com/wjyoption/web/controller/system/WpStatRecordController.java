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
import com.wjyoption.system.domain.WpStatRecord;
import com.wjyoption.system.service.IWpStatRecordService;
import com.wjyoption.system.vo.report.StatRecordTotal;
import com.wjyoption.common.core.controller.BaseController;
import com.wjyoption.common.core.domain.AjaxResult;
import com.wjyoption.common.utils.poi.ExcelUtil;
import com.wjyoption.common.core.page.TableDataInfo;

/**
 * 统计记录Controller
 * 
 * @author wjyoption
 * @date 2021-06-21
 */
@Controller
@RequestMapping("/system/statrecord")
public class WpStatRecordController extends BaseController
{
    private String prefix = "system/statrecord";

    @Autowired
    private IWpStatRecordService wpStatRecordService;

    @RequiresPermissions("system:statrecord:view")
    @GetMapping()
    public String statrecord()
    {
        return prefix + "/statrecord";
    }

    /**
     * 查询统计记录列表
     */
    @RequiresPermissions("system:statrecord:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(WpStatRecord wpStatRecord)
    {
        startPage();
        List<WpStatRecord> list = wpStatRecordService.selectWpStatRecordList(wpStatRecord);
        return getDataTable(list);
    }
    
    /**
     * 查询统计记录列表
     */
    @RequiresPermissions("system:statrecord:list")
    @PostMapping("/listTotal")
    @ResponseBody
    public AjaxResult listTotal(WpStatRecord wpStatRecord)
    {
    	StatRecordTotal total = this.wpStatRecordService.selectWpStatRecordTotal(wpStatRecord);
    	return AjaxResult.success(total);
    }

    /**
     * 导出统计记录列表
     */
    @RequiresPermissions("system:statrecord:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(WpStatRecord wpStatRecord)
    {
        List<WpStatRecord> list = wpStatRecordService.selectWpStatRecordList(wpStatRecord);
        ExcelUtil<WpStatRecord> util = new ExcelUtil<WpStatRecord>(WpStatRecord.class);
        return util.exportExcel(list, "statrecord");
    }

    /**
     * 新增统计记录
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存统计记录
     */
    @RequiresPermissions("system:statrecord:add")
    @Log(title = "统计记录", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(WpStatRecord wpStatRecord)
    {
        return toAjax(wpStatRecordService.insertWpStatRecord(wpStatRecord));
    }

    /**
     * 修改统计记录
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap)
    {
        WpStatRecord wpStatRecord = wpStatRecordService.selectWpStatRecordById(id);
        mmap.put("wpStatRecord", wpStatRecord);
        return prefix + "/edit";
    }

    /**
     * 修改保存统计记录
     */
    @RequiresPermissions("system:statrecord:edit")
    @Log(title = "统计记录", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(WpStatRecord wpStatRecord)
    {
        return toAjax(wpStatRecordService.updateWpStatRecord(wpStatRecord));
    }

    /**
     * 删除统计记录
     */
    @RequiresPermissions("system:statrecord:remove")
    @Log(title = "统计记录", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(wpStatRecordService.deleteWpStatRecordByIds(ids));
    }
}
