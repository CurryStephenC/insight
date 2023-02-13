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
import com.wjyoption.common.utils.poi.ExcelUtil;
import com.wjyoption.framework.util.ShiroUtils;
import com.wjyoption.system.domain.SysUser;
import com.wjyoption.system.domain.WpTopStat;
import com.wjyoption.system.service.ISysUserService;
import com.wjyoption.system.service.IWpTopStatService;

/**
 * 销售统计Controller
 * 
 * @author wjyoption
 * @date 2021-09-15
 */
@Controller
@RequestMapping("/system/topstat")
public class WpTopStatController extends BaseController
{
    private String prefix = "system/topstat";

    @Autowired
    private IWpTopStatService wpTopStatService;
    @Autowired ISysUserService userService;

    @RequiresPermissions("system:topstat:view")
    @GetMapping()
    public String topstat()
    {
        return prefix + "/topstat";
    }

    /**
     * 查询销售统计列表
     */
    @RequiresPermissions("system:topstat:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(WpTopStat wpTopStat)
    {
    	
    	startPage();
        List<WpTopStat> list = wpTopStatService.selectTotalList(wpTopStat);
        return getDataTable(list);
    }
    
    public void listParam(WpTopStat wpTopStat) {
		boolean permitted = ShiroUtils.getSubject().isPermitted("system:topstat:all");
    	if(!permitted){
    		permitted = ShiroUtils.getSubject().isPermitted("system:topstat:dept");
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
    			wpTopStat.setUids(topids);
    		}else{
    			wpTopStat.setUid(ShiroUtils.getSysUser().getUid());
    		}
    	}
	}
    
    /**
     * 销售统计详情
     */
    @RequiresPermissions("system:topstat:detail")
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Integer id, ModelMap mmap)
    {
        mmap.put("uid", id);
        return prefix + "/detail";
    }
    
    /**
     * 查询销售统计详情列表
     */
    @RequiresPermissions("system:topstat:detail")
    @PostMapping("/detailList")
    @ResponseBody
    public TableDataInfo detailList(WpTopStat wpTopStat)
    {
    	startPage();
    	List<WpTopStat> list = wpTopStatService.selectWpTopStatList(wpTopStat);
    	return getDataTable(list);
    }

    /**
     * 导出销售统计列表
     */
    @RequiresPermissions("system:topstat:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(WpTopStat wpTopStat)
    {
        List<WpTopStat> list = wpTopStatService.selectWpTopStatList(wpTopStat);
        ExcelUtil<WpTopStat> util = new ExcelUtil<WpTopStat>(WpTopStat.class);
        return util.exportExcel(list, "topstat");
    }

    /**
     * 新增销售统计
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存销售统计
     */
    @RequiresPermissions("system:topstat:add")
    @Log(title = "销售统计", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(WpTopStat wpTopStat)
    {
        return toAjax(wpTopStatService.insertWpTopStat(wpTopStat));
    }

    /**
     * 修改销售统计
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap)
    {
        WpTopStat wpTopStat = wpTopStatService.selectWpTopStatById(id);
        mmap.put("wpTopStat", wpTopStat);
        return prefix + "/edit";
    }

    /**
     * 修改保存销售统计
     */
    @RequiresPermissions("system:topstat:edit")
    @Log(title = "销售统计", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(WpTopStat wpTopStat)
    {
        return toAjax(wpTopStatService.updateWpTopStat(wpTopStat));
    }

    /**
     * 删除销售统计
     */
    @RequiresPermissions("system:topstat:remove")
    @Log(title = "销售统计", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(wpTopStatService.deleteWpTopStatByIds(ids));
    }
}
