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
import com.wjyoption.system.domain.WpCashFlow;
import com.wjyoption.system.service.ISysUserService;
import com.wjyoption.system.service.IWpCashFlowService;
import com.wjyoption.common.core.controller.BaseController;
import com.wjyoption.common.core.domain.AjaxResult;
import com.wjyoption.common.utils.poi.ExcelUtil;
import com.wjyoption.common.core.page.TableDataInfo;
import com.wjyoption.framework.util.ShiroUtils;

/**
 * 流水Controller
 * 
 * @author wjyoption
 * @date 2021-06-04
 */
@Controller
@RequestMapping("/system/cashflow")
public class WpCashFlowController extends BaseController
{
    private String prefix = "system/cashflow";

    @Autowired
    private IWpCashFlowService wpCashFlowService;
    @Autowired ISysUserService userService;

    @RequiresPermissions("system:cashflow:view")
    @GetMapping()
    public String cashflow()
    {
        return prefix + "/cashflow";
    }

    /**
     * 查询流水列表
     */
    @RequiresPermissions("system:cashflow:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(WpCashFlow wpCashFlow)
    {
        listParam(wpCashFlow);
    	startPage();
        List<WpCashFlow> list = wpCashFlowService.selectWpCashFlowList(wpCashFlow);
        return getDataTable(list);
    }

	private void listParam(WpCashFlow wpCashFlow) {
		boolean permitted = ShiroUtils.getSubject().isPermitted("system:cashflow:alluser");
    	if(!permitted){
    		permitted = ShiroUtils.getSubject().isPermitted("system:cashflow:dept");
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
    			wpCashFlow.setTopids(topids);
    		}else{
    			wpCashFlow.setTopid(ShiroUtils.getSysUser().getUid());
    		}
    	}
	}

    /**
     * 导出流水列表
     */
    @RequiresPermissions("system:cashflow:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(WpCashFlow wpCashFlow)
    {
    	listParam(wpCashFlow);
        List<WpCashFlow> list = wpCashFlowService.selectWpCashFlowList(wpCashFlow);
        ExcelUtil<WpCashFlow> util = new ExcelUtil<WpCashFlow>(WpCashFlow.class);
        return util.exportExcel(list, "cashflow");
    }

    /**
     * 新增流水
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存流水
     */
    @RequiresPermissions("system:cashflow:add")
    @Log(title = "流水", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(WpCashFlow wpCashFlow)
    {
        return toAjax(wpCashFlowService.insertWpCashFlow(wpCashFlow));
    }

    /**
     * 修改流水
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap)
    {
        WpCashFlow wpCashFlow = wpCashFlowService.selectWpCashFlowById(id);
        mmap.put("wpCashFlow", wpCashFlow);
        return prefix + "/edit";
    }

    /**
     * 修改保存流水
     */
    @RequiresPermissions("system:cashflow:edit")
    @Log(title = "流水", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(WpCashFlow wpCashFlow)
    {
        return toAjax(wpCashFlowService.updateWpCashFlow(wpCashFlow));
    }

    /**
     * 删除流水
     */
    @RequiresPermissions("system:cashflow:remove")
    @Log(title = "流水", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(wpCashFlowService.deleteWpCashFlowByIds(ids));
    }
}
