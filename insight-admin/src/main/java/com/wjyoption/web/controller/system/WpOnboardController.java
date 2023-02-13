package com.wjyoption.web.controller.system;

import java.util.ArrayList;
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
import com.wjyoption.system.domain.SysUser;
import com.wjyoption.system.domain.WpOnboard;
import com.wjyoption.system.service.ISysUserService;
import com.wjyoption.system.service.IWpOnboardService;
import com.wjyoption.common.core.controller.BaseController;
import com.wjyoption.common.core.domain.AjaxResult;
import com.wjyoption.common.utils.poi.ExcelUtil;
import com.wjyoption.common.core.page.TableDataInfo;
import com.wjyoption.framework.util.ShiroUtils;

/**
 * 用户签到Controller
 * 
 * @author wjyoption
 * @date 2021-06-11
 */
@Controller
@RequestMapping("/system/onboard")
public class WpOnboardController extends BaseController
{
    private String prefix = "system/onboard";

    @Autowired
    private IWpOnboardService wpOnboardService;
    @Autowired ISysUserService userService;

    @RequiresPermissions("system:onboard:view")
    @GetMapping()
    public String onboard()
    {
        return prefix + "/onboard";
    }

    /**
     * 查询用户签到列表
     */
    @RequiresPermissions("system:onboard:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(WpOnboard wpOnboard)
    {
        boolean permitted = ShiroUtils.getSubject().isPermitted("system:onboard:alluser");
    	if(!permitted){
    		permitted = ShiroUtils.getSubject().isPermitted("system:onboard:dept");
    		if(permitted){
    			List<Integer> topids = new ArrayList<>();
    			SysUser currUser = ShiroUtils.getSysUser();
    			if(currUser.getTopids() == null){
    				SysUser user = new SysUser();
    				user.setDeptId(ShiroUtils.getSysUser().getDeptId());
    				List<SysUser> list = this.userService.selectUserList(user);
    				for(SysUser su : list){
    					if(su.getUid() != null) topids.add(su.getUid());
    				}
    				currUser.setTopids(topids);
    				ShiroUtils.setSysUser(currUser);
    			}else{
    				topids = currUser.getTopids();
    			}
    			wpOnboard.setTopids(topids);
    		}else{
    			wpOnboard.setTopid(ShiroUtils.getSysUser().getUid());
    		}
    	}
    	wpOnboard.setNormaltype(1);
    	startPage();
        List<WpOnboard> list = wpOnboardService.selectWpOnboardList(wpOnboard);
        return getDataTable(list);
    }

    /**
     * 导出用户签到列表
     */
    @RequiresPermissions("system:onboard:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(WpOnboard wpOnboard)
    {
        List<WpOnboard> list = wpOnboardService.selectWpOnboardList(wpOnboard);
        ExcelUtil<WpOnboard> util = new ExcelUtil<WpOnboard>(WpOnboard.class);
        return util.exportExcel(list, "onboard");
    }

    /**
     * 新增用户签到
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存用户签到
     */
    @RequiresPermissions("system:onboard:add")
    @Log(title = "用户签到", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(WpOnboard wpOnboard)
    {
        return toAjax(wpOnboardService.insertWpOnboard(wpOnboard));
    }

    /**
     * 修改用户签到
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap)
    {
        WpOnboard wpOnboard = wpOnboardService.selectWpOnboardById(id);
        mmap.put("wpOnboard", wpOnboard);
        return prefix + "/edit";
    }

    /**
     * 修改保存用户签到
     */
    @RequiresPermissions("system:onboard:edit")
    @Log(title = "用户签到", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(WpOnboard wpOnboard)
    {
        return toAjax(wpOnboardService.updateWpOnboard(wpOnboard));
    }

    /**
     * 删除用户签到
     */
    @RequiresPermissions("system:onboard:remove")
    @Log(title = "用户签到", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(wpOnboardService.deleteWpOnboardByIds(ids));
    }
}
