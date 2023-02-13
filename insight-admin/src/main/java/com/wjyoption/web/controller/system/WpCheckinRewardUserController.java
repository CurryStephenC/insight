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
import com.wjyoption.system.domain.WpCheckinRewardUser;
import com.wjyoption.system.service.IWpCheckinRewardUserService;
import com.wjyoption.common.core.controller.BaseController;
import com.wjyoption.common.core.domain.AjaxResult;
import com.wjyoption.common.utils.poi.ExcelUtil;
import com.wjyoption.common.core.page.TableDataInfo;
import com.wjyoption.framework.util.ShiroUtils;

/**
 * 用户累计签到奖励Controller
 * 
 * @author wjyoption
 * @date 2021-06-12
 */
@Controller
@RequestMapping("/system/checkinrewarduser")
public class WpCheckinRewardUserController extends BaseController
{
    private String prefix = "system/checkinrewarduser";

    @Autowired
    private IWpCheckinRewardUserService wpCheckinRewardUserService;

    @RequiresPermissions("system:checkinrewarduser:view")
    @GetMapping()
    public String checkinrewarduser()
    {
        return prefix + "/checkinrewarduser";
    }

    /**
     * 查询用户累计签到奖励列表
     */
    @RequiresPermissions("system:checkinrewarduser:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(WpCheckinRewardUser wpCheckinRewardUser)
    {
        startPage();
        boolean permitted = ShiroUtils.getSubject().isPermitted("system:checkinrewarduser:alluser");
    	if(!permitted){
    		wpCheckinRewardUser.setTopid(ShiroUtils.getSysUser().getUid());
    	}
        List<WpCheckinRewardUser> list = wpCheckinRewardUserService.selectWpCheckinRewardUserList(wpCheckinRewardUser);
        return getDataTable(list);
    }

    /**
     * 导出用户累计签到奖励列表
     */
    @RequiresPermissions("system:checkinrewarduser:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(WpCheckinRewardUser wpCheckinRewardUser)
    {
        List<WpCheckinRewardUser> list = wpCheckinRewardUserService.selectWpCheckinRewardUserList(wpCheckinRewardUser);
        ExcelUtil<WpCheckinRewardUser> util = new ExcelUtil<WpCheckinRewardUser>(WpCheckinRewardUser.class);
        return util.exportExcel(list, "checkinrewarduser");
    }

    /**
     * 新增用户累计签到奖励
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存用户累计签到奖励
     */
    @RequiresPermissions("system:checkinrewarduser:add")
    @Log(title = "用户累计签到奖励", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(WpCheckinRewardUser wpCheckinRewardUser)
    {
        return toAjax(wpCheckinRewardUserService.insertWpCheckinRewardUser(wpCheckinRewardUser));
    }

    /**
     * 修改用户累计签到奖励
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap)
    {
        WpCheckinRewardUser wpCheckinRewardUser = wpCheckinRewardUserService.selectWpCheckinRewardUserById(id);
        mmap.put("wpCheckinRewardUser", wpCheckinRewardUser);
        return prefix + "/edit";
    }

    /**
     * 修改保存用户累计签到奖励
     */
    @RequiresPermissions("system:checkinrewarduser:edit")
    @Log(title = "用户累计签到奖励", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(WpCheckinRewardUser wpCheckinRewardUser)
    {
        return toAjax(wpCheckinRewardUserService.updateWpCheckinRewardUser(wpCheckinRewardUser));
    }

    /**
     * 删除用户累计签到奖励
     */
    @RequiresPermissions("system:checkinrewarduser:remove")
    @Log(title = "用户累计签到奖励", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(wpCheckinRewardUserService.deleteWpCheckinRewardUserByIds(ids));
    }
}
