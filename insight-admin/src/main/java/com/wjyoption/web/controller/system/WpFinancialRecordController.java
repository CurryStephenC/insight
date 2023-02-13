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
import com.wjyoption.system.domain.WpFinancialRecord;
import com.wjyoption.system.service.ISysUserService;
import com.wjyoption.system.service.IWpFinancialRecordService;
import com.wjyoption.system.service.IWpFinancialTypeService;
import com.wjyoption.common.core.controller.BaseController;
import com.wjyoption.common.core.domain.AjaxResult;
import com.wjyoption.common.utils.poi.ExcelUtil;
import com.wjyoption.common.core.page.TableDataInfo;
import com.wjyoption.framework.util.ShiroUtils;

/**
 * 理财记录Controller
 * 
 * @author wjyoption
 * @date 2021-06-05
 */
@Controller
@RequestMapping("/system/financialrecord")
public class WpFinancialRecordController extends BaseController
{
    private String prefix = "system/financialrecord";

    @Autowired
    private IWpFinancialRecordService wpFinancialRecordService;
    @Autowired IWpFinancialTypeService typeService;
    @Autowired ISysUserService userService;

    @RequiresPermissions("system:financialrecord:view")
    @GetMapping()
    public String financialrecord(ModelMap mmp)
    {
    	
    	mmp.put("typelist", this.typeService.selectWpFinancialTypeList(null));
        return prefix + "/financialrecord";
    }

    /**
     * 查询理财记录列表
     */
    @RequiresPermissions("system:financialrecord:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(WpFinancialRecord wpFinancialRecord)
    {
        listPower(wpFinancialRecord);
    	startPage();
        List<WpFinancialRecord> list = wpFinancialRecordService.selectWpFinancialRecordList(wpFinancialRecord);
        return getDataTable(list);
    }

	private void listPower(WpFinancialRecord wpFinancialRecord) {
		boolean permitted = ShiroUtils.getSubject().isPermitted("system:financialrecord:alluser");
    	if(!permitted){
    		permitted = ShiroUtils.getSubject().isPermitted("system:financialrecord:dept");
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
    			wpFinancialRecord.setTopids(topids);
    		}else{
    			wpFinancialRecord.setTopid(ShiroUtils.getSysUser().getUid());
    		}
    	}
	}

    /**
     * 导出理财记录列表
     */
    @RequiresPermissions("system:financialrecord:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(WpFinancialRecord wpFinancialRecord)
    {
    	listPower(wpFinancialRecord);
        List<WpFinancialRecord> list = wpFinancialRecordService.selectWpFinancialRecordList(wpFinancialRecord);
        ExcelUtil<WpFinancialRecord> util = new ExcelUtil<WpFinancialRecord>(WpFinancialRecord.class);
        return util.exportExcel(list, "financialrecord");
    }

    /**
     * 新增理财记录
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存理财记录
     */
    @RequiresPermissions("system:financialrecord:add")
    @Log(title = "理财记录", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(WpFinancialRecord wpFinancialRecord)
    {
        return toAjax(wpFinancialRecordService.insertWpFinancialRecord(wpFinancialRecord));
    }
    
    /**
     * 新增理财记录
     */
    @GetMapping("/createdata")
    public String createdata(ModelMap mmp)
    {
    	mmp.put("typelist", this.typeService.selectWpFinancialTypeList(null));
        return prefix + "/createdata";
    }
    
    /**
     * 生成多条数据
     */
    @RequiresPermissions("system:financialrecord:createdata")
    @Log(title = "理财记录", businessType = BusinessType.INSERT)
    @PostMapping("/createdata")
    @ResponseBody
    public AjaxResult createdata(WpFinancialRecord wpFinancialRecord)
    {
    	return wpFinancialRecordService.createdata(wpFinancialRecord);
    }

    /**
     * 修改理财记录
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap)
    {
        WpFinancialRecord wpFinancialRecord = wpFinancialRecordService.selectWpFinancialRecordById(id);
        mmap.put("wpFinancialRecord", wpFinancialRecord);
        return prefix + "/edit";
    }

    /**
     * 修改保存理财记录
     */
    @RequiresPermissions("system:financialrecord:edit")
    @Log(title = "理财记录", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(WpFinancialRecord wpFinancialRecord)
    {
        return toAjax(wpFinancialRecordService.updateWpFinancialRecord(wpFinancialRecord));
    }

    /**
     * 删除理财记录
     */
    @RequiresPermissions("system:financialrecord:remove")
    @Log(title = "理财记录", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(wpFinancialRecordService.deleteWpFinancialRecordByIds(ids));
    }
    
    /**
     * 结束订单
     * @param id
     * @return
     */
    @RequiresPermissions("system:financialrecord:over")
    @Log(title = "理财记录", businessType = BusinessType.UPDATE)
    @PostMapping( "/over")
    @ResponseBody
    public AjaxResult over(Integer id){
    	return this.wpFinancialRecordService.over(id);
    }
    
    @RequiresPermissions("system:financialrecorddetail:list")
    @GetMapping("/detail/{refid}")
    public String detail(@PathVariable("refid") Integer refid, ModelMap mmap)
    {
    	mmap.put("refid", refid);
    	return prefix + "/detail";
    }
}
