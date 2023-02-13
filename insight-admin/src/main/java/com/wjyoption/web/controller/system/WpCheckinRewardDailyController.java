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
import com.wjyoption.system.domain.WpCheckinRewardDaily;
import com.wjyoption.system.service.IWpCheckinRewardDailyService;
import com.wjyoption.common.core.controller.BaseController;
import com.wjyoption.common.core.domain.AjaxResult;
import com.wjyoption.common.utils.poi.ExcelUtil;
import com.wjyoption.common.core.page.TableDataInfo;

/**
 * 用户每日签到奖项Controller
 * 
 * @author wjyoption
 * @date 2021-06-11
 */
@Controller
@RequestMapping("/system/checkinrewarddaily")
public class WpCheckinRewardDailyController extends BaseController
{
    private String prefix = "system/checkinrewarddaily";

    @Autowired
    private IWpCheckinRewardDailyService wpCheckinRewardDailyService;

    @RequiresPermissions("system:checkinrewarddaily:view")
    @GetMapping()
    public String checkinrewarddaily()
    {
        return prefix + "/checkinrewarddaily";
    }

    /**
     * 查询用户每日签到奖项列表
     */
    @RequiresPermissions("system:checkinrewarddaily:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(WpCheckinRewardDaily wpCheckinRewardDaily)
    {
        startPage();
        List<WpCheckinRewardDaily> list = wpCheckinRewardDailyService.selectWpCheckinRewardDailyList(wpCheckinRewardDaily);
        return getDataTable(list);
    }

    /**
     * 导出用户每日签到奖项列表
     */
    @RequiresPermissions("system:checkinrewarddaily:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(WpCheckinRewardDaily wpCheckinRewardDaily)
    {
        List<WpCheckinRewardDaily> list = wpCheckinRewardDailyService.selectWpCheckinRewardDailyList(wpCheckinRewardDaily);
        ExcelUtil<WpCheckinRewardDaily> util = new ExcelUtil<WpCheckinRewardDaily>(WpCheckinRewardDaily.class);
        return util.exportExcel(list, "checkinrewarddaily");
    }

    /**
     * 新增用户每日签到奖项
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存用户每日签到奖项
     */
    @RequiresPermissions("system:checkinrewarddaily:add")
    @Log(title = "用户每日签到奖项", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(WpCheckinRewardDaily wpCheckinRewardDaily)
    {
        return toAjax(wpCheckinRewardDailyService.insertWpCheckinRewardDaily(wpCheckinRewardDaily));
    }

    /**
     * 修改用户每日签到奖项
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap)
    {
        WpCheckinRewardDaily wpCheckinRewardDaily = wpCheckinRewardDailyService.selectWpCheckinRewardDailyById(id);
        mmap.put("wpCheckinRewardDaily", wpCheckinRewardDaily);
        return prefix + "/edit";
    }

    /**
     * 修改保存用户每日签到奖项
     */
    @RequiresPermissions("system:checkinrewarddaily:edit")
    @Log(title = "用户每日签到奖项", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(WpCheckinRewardDaily wpCheckinRewardDaily)
    {
        return toAjax(wpCheckinRewardDailyService.updateWpCheckinRewardDaily(wpCheckinRewardDaily));
    }

    /**
     * 删除用户每日签到奖项
     */
    @RequiresPermissions("system:checkinrewarddaily:remove")
    @Log(title = "用户每日签到奖项", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(wpCheckinRewardDailyService.deleteWpCheckinRewardDailyByIds(ids));
    }
}
