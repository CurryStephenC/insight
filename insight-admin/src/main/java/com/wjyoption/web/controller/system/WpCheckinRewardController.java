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
import com.wjyoption.system.domain.WpCheckinReward;
import com.wjyoption.system.service.IWpCheckinRewardService;
import com.wjyoption.common.core.controller.BaseController;
import com.wjyoption.common.core.domain.AjaxResult;
import com.wjyoption.common.utils.poi.ExcelUtil;
import com.wjyoption.common.core.page.TableDataInfo;

/**
 * 累计签到奖项Controller
 * 
 * @author wjyoption
 * @date 2021-06-11
 */
@Controller
@RequestMapping("/system/checkinreward")
public class WpCheckinRewardController extends BaseController
{
    private String prefix = "system/checkinreward";

    @Autowired
    private IWpCheckinRewardService wpCheckinRewardService;

    @RequiresPermissions("system:checkinreward:view")
    @GetMapping()
    public String checkinreward()
    {
        return prefix + "/checkinreward";
    }

    /**
     * 查询累计签到奖项列表
     */
    @RequiresPermissions("system:checkinreward:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(WpCheckinReward wpCheckinReward)
    {
        startPage();
        List<WpCheckinReward> list = wpCheckinRewardService.selectWpCheckinRewardList(wpCheckinReward);
        return getDataTable(list);
    }

    /**
     * 导出累计签到奖项列表
     */
    @RequiresPermissions("system:checkinreward:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(WpCheckinReward wpCheckinReward)
    {
        List<WpCheckinReward> list = wpCheckinRewardService.selectWpCheckinRewardList(wpCheckinReward);
        ExcelUtil<WpCheckinReward> util = new ExcelUtil<WpCheckinReward>(WpCheckinReward.class);
        return util.exportExcel(list, "checkinreward");
    }

    /**
     * 新增累计签到奖项
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存累计签到奖项
     */
    @RequiresPermissions("system:checkinreward:add")
    @Log(title = "累计签到奖项", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(WpCheckinReward wpCheckinReward)
    {
        return toAjax(wpCheckinRewardService.insertWpCheckinReward(wpCheckinReward));
    }

    /**
     * 修改累计签到奖项
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap)
    {
        WpCheckinReward wpCheckinReward = wpCheckinRewardService.selectWpCheckinRewardById(id);
        mmap.put("wpCheckinReward", wpCheckinReward);
        return prefix + "/edit";
    }

    /**
     * 修改保存累计签到奖项
     */
    @RequiresPermissions("system:checkinreward:edit")
    @Log(title = "累计签到奖项", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(WpCheckinReward wpCheckinReward)
    {
        return toAjax(wpCheckinRewardService.updateWpCheckinReward(wpCheckinReward));
    }

    /**
     * 删除累计签到奖项
     */
    @RequiresPermissions("system:checkinreward:remove")
    @Log(title = "累计签到奖项", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(wpCheckinRewardService.deleteWpCheckinRewardByIds(ids));
    }
}
