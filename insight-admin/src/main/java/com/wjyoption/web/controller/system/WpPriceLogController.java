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
import com.wjyoption.system.domain.WpPriceLog;
import com.wjyoption.system.service.ISysUserService;
import com.wjyoption.system.service.IWpPriceLogService;
import com.wjyoption.common.core.controller.BaseController;
import com.wjyoption.common.core.domain.AjaxResult;
import com.wjyoption.common.utils.poi.ExcelUtil;
import com.wjyoption.common.core.page.TableDataInfo;
import com.wjyoption.framework.util.ShiroUtils;

/**
 * 资金日志Controller
 * 
 * @author wjyoption
 * @date 2021-06-05
 */
@Controller
@RequestMapping("/system/pricelog")
public class WpPriceLogController extends BaseController
{
    private String prefix = "system/pricelog";

    @Autowired
    private IWpPriceLogService wpPriceLogService;
    @Autowired ISysUserService userService;

    @RequiresPermissions("system:pricelog:view")
    @GetMapping()
    public String pricelog()
    {
        return prefix + "/pricelog";
    }

    /**
     * 查询资金日志列表
     */
    @RequiresPermissions("system:pricelog:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(WpPriceLog wpPriceLog)
    {
        boolean permitted = ShiroUtils.getSubject().isPermitted("system:pricelog:alluser");
    	if(!permitted){
    		permitted = ShiroUtils.getSubject().isPermitted("system:pricelog:dept");
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
    			wpPriceLog.setTopids(topids);
    		}else{
    			wpPriceLog.setTopid(ShiroUtils.getSysUser().getUid());
    		}
    	}
    	startPage();
        List<WpPriceLog> list = wpPriceLogService.selectWpPriceLogList(wpPriceLog);
        return getDataTable(list);
    }

    /**
     * 导出资金日志列表
     */
    @RequiresPermissions("system:pricelog:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(WpPriceLog wpPriceLog)
    {
        List<WpPriceLog> list = wpPriceLogService.selectWpPriceLogList(wpPriceLog);
        ExcelUtil<WpPriceLog> util = new ExcelUtil<WpPriceLog>(WpPriceLog.class);
        return util.exportExcel(list, "pricelog");
    }

    /**
     * 新增资金日志
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存资金日志
     */
    @RequiresPermissions("system:pricelog:add")
    @Log(title = "资金日志", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(WpPriceLog wpPriceLog)
    {
        return toAjax(wpPriceLogService.insertWpPriceLog(wpPriceLog));
    }

    /**
     * 修改资金日志
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap)
    {
        WpPriceLog wpPriceLog = wpPriceLogService.selectWpPriceLogById(id);
        mmap.put("wpPriceLog", wpPriceLog);
        return prefix + "/edit";
    }

    /**
     * 修改保存资金日志
     */
    @RequiresPermissions("system:pricelog:edit")
    @Log(title = "资金日志", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(WpPriceLog wpPriceLog)
    {
        return toAjax(wpPriceLogService.updateWpPriceLog(wpPriceLog));
    }

    /**
     * 删除资金日志
     */
    @RequiresPermissions("system:pricelog:remove")
    @Log(title = "资金日志", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(wpPriceLogService.deleteWpPriceLogByIds(ids));
    }
}
