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
import com.wjyoption.common.core.controller.BaseController;
import com.wjyoption.common.core.domain.AjaxResult;
import com.wjyoption.common.core.page.TableDataInfo;
import com.wjyoption.common.enums.BusinessType;
import com.wjyoption.common.utils.DateUtils;
import com.wjyoption.common.utils.poi.ExcelUtil;
import com.wjyoption.framework.util.ShiroUtils;
import com.wjyoption.system.domain.SysUser;
import com.wjyoption.system.domain.WpUserNovicetask;
import com.wjyoption.system.domain.WpUserNovicetaskRate;
import com.wjyoption.system.service.ISysUserService;
import com.wjyoption.system.service.IWpUserNovicetaskRateService;
import com.wjyoption.system.service.IWpUserNovicetaskService;

/**
 * 用户新手任务进度Controller
 * 
 * @author wjyoption
 * @date 2021-07-28
 */
@Controller
@RequestMapping("/system/rate")
public class WpUserNovicetaskRateController extends BaseController
{
    private String prefix = "system/rate";

    @Autowired
    private IWpUserNovicetaskRateService wpUserNovicetaskRateService;
    @Autowired ISysUserService userService;
    @Autowired IWpUserNovicetaskService novicetaskService;

    @RequiresPermissions("system:rate:view")
    @GetMapping()
    public String rate(ModelMap mmap)
    {
    	mmap.put("typelist", this.novicetaskService.selectWpUserNovicetaskList(new WpUserNovicetask()));
        return prefix + "/rate";
    }

    /**
     * 查询用户新手任务进度列表
     */
    @RequiresPermissions("system:rate:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(WpUserNovicetaskRate wpUserNovicetaskRate)
    {
    	listParam(wpUserNovicetaskRate);
        startPage();
        List<WpUserNovicetaskRate> list = wpUserNovicetaskRateService.selectWpUserNovicetaskRateList(wpUserNovicetaskRate);
        return getDataTable(list);
    }
    
    public void listParam(WpUserNovicetaskRate wpUserNovicetaskRate) {
		boolean permitted = ShiroUtils.getSubject().isPermitted("system:rate:alluser");
    	if(!permitted){
    		permitted = ShiroUtils.getSubject().isPermitted("system:rate:dept");
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
    			wpUserNovicetaskRate.setTopids(topids);
    		}else{
    			wpUserNovicetaskRate.setTopid(ShiroUtils.getSysUser().getUid());
    		}
    	}
	}

    /**
     * 导出用户新手任务进度列表
     */
    @RequiresPermissions("system:rate:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(WpUserNovicetaskRate wpUserNovicetaskRate)
    {
        List<WpUserNovicetaskRate> list = wpUserNovicetaskRateService.selectWpUserNovicetaskRateList(wpUserNovicetaskRate);
        ExcelUtil<WpUserNovicetaskRate> util = new ExcelUtil<WpUserNovicetaskRate>(WpUserNovicetaskRate.class);
        return util.exportExcel(list, "rate");
    }

    /**
     * 新增用户新手任务进度
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存用户新手任务进度
     */
    @RequiresPermissions("system:rate:add")
    @Log(title = "用户新手任务进度", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(WpUserNovicetaskRate wpUserNovicetaskRate)
    {
        return toAjax(wpUserNovicetaskRateService.insertWpUserNovicetaskRate(wpUserNovicetaskRate));
    }

    /**
     * 修改用户新手任务进度
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap)
    {
        WpUserNovicetaskRate wpUserNovicetaskRate = wpUserNovicetaskRateService.selectWpUserNovicetaskRateById(id);
        mmap.put("wpUserNovicetaskRate", wpUserNovicetaskRate);
        return prefix + "/edit";
    }

    /**
     * 修改保存用户新手任务进度
     */
    @RequiresPermissions("system:rate:edit")
    @Log(title = "用户新手任务进度", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(WpUserNovicetaskRate wpUserNovicetaskRate)
    {
        return toAjax(wpUserNovicetaskRateService.updateWpUserNovicetaskRate(wpUserNovicetaskRate));
    }
    
    /**
     * 审核用户新手任务进度
     * @param status 状态（1待完成，2已提交，3已完成，4已驳回）
     */
    @RequiresPermissions("system:rate:audit")
    @Log(title = "用户新手任务进度审核", businessType = BusinessType.UPDATE)
    @PostMapping("/audit")
    @ResponseBody
    public AjaxResult audit(Integer id,Integer status)
    {
    	if(status < 3){
    		return AjaxResult.error("状态错误");
    	}
    	WpUserNovicetaskRate bean = this.wpUserNovicetaskRateService.selectWpUserNovicetaskRateById(id);
    	if(bean.getStatus() != 2){
    		return AjaxResult.error("状态无法更新");
    	}
    	WpUserNovicetaskRate wpUserNovicetaskRate = new WpUserNovicetaskRate();
    	wpUserNovicetaskRate.setId(id);
    	wpUserNovicetaskRate.setStatus(status);
    	if(status == 3)
    	wpUserNovicetaskRate.setFinishtime(DateUtils.getNowSecond());
    	return toAjax(wpUserNovicetaskRateService.updateWpUserNovicetaskRate(wpUserNovicetaskRate));
    }

    /**
     * 删除用户新手任务进度
     */
    @RequiresPermissions("system:rate:remove")
    @Log(title = "用户新手任务进度", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(wpUserNovicetaskRateService.deleteWpUserNovicetaskRateByIds(ids));
    }
}
