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
import com.wjyoption.system.domain.WpSpeech;
import com.wjyoption.system.service.IWpSpeechService;
import com.wjyoption.common.core.controller.BaseController;
import com.wjyoption.common.core.domain.AjaxResult;
import com.wjyoption.common.utils.poi.ExcelUtil;
import com.wjyoption.common.core.page.TableDataInfo;

/**
 * 言论Controller
 * 
 * @author wjyoption
 * @date 2021-06-15
 */
@Controller
@RequestMapping("/system/speech")
public class WpSpeechController extends BaseController
{
    private String prefix = "system/speech";

    @Autowired
    private IWpSpeechService wpSpeechService;

    @RequiresPermissions("system:speech:view")
    @GetMapping()
    public String speech()
    {
        return prefix + "/speech";
    }

    /**
     * 查询言论列表
     */
    @RequiresPermissions("system:speech:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(WpSpeech wpSpeech)
    {
        startPage();
        List<WpSpeech> list = wpSpeechService.selectWpSpeechList(wpSpeech);
        return getDataTable(list);
    }

    /**
     * 导出言论列表
     */
    @RequiresPermissions("system:speech:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(WpSpeech wpSpeech)
    {
        List<WpSpeech> list = wpSpeechService.selectWpSpeechList(wpSpeech);
        ExcelUtil<WpSpeech> util = new ExcelUtil<WpSpeech>(WpSpeech.class);
        return util.exportExcel(list, "speech");
    }

    /**
     * 新增言论
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存言论
     */
    @RequiresPermissions("system:speech:add")
    @Log(title = "言论", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(WpSpeech wpSpeech)
    {
        return toAjax(wpSpeechService.insertWpSpeech(wpSpeech));
    }

    /**
     * 修改言论
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap)
    {
        WpSpeech wpSpeech = wpSpeechService.selectWpSpeechById(id);
        mmap.put("wpSpeech", wpSpeech);
        return prefix + "/edit";
    }

    /**
     * 修改保存言论
     */
    @RequiresPermissions("system:speech:edit")
    @Log(title = "言论", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(WpSpeech wpSpeech)
    {
        return toAjax(wpSpeechService.updateWpSpeech(wpSpeech));
    }

    /**
     * 删除言论
     */
    @RequiresPermissions("system:speech:remove")
    @Log(title = "言论", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(wpSpeechService.deleteWpSpeechByIds(ids));
    }
}
