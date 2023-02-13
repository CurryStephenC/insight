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
import com.wjyoption.common.core.controller.BaseController;
import com.wjyoption.common.core.domain.AjaxResult;
import com.wjyoption.common.core.page.TableDataInfo;
import com.wjyoption.common.enums.BusinessType;
import com.wjyoption.common.utils.poi.ExcelUtil;
import com.wjyoption.system.domain.WpProductinfo;
import com.wjyoption.system.service.IWpProductinfoService;

/**
 * 产品列表Controller
 * 
 * @author wjyoption
 * @date 2021-06-19
 */
@Controller
@RequestMapping("/system/productinfo")
public class WpProductinfoController extends BaseController
{
    private String prefix = "system/productinfo";

    @Autowired
    private IWpProductinfoService wpProductinfoService;
//    @Autowired WebsocketClient websocketClient;

    @RequiresPermissions("system:productinfo:view")
    @GetMapping()
    public String productinfo()
    {
        return prefix + "/productinfo";
    }

    /**
     * 查询产品列表列表
     */
    @RequiresPermissions("system:productinfo:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(WpProductinfo wpProductinfo)
    {
        startPage();
        List<WpProductinfo> list = wpProductinfoService.selectWpProductinfoList(wpProductinfo);
        return getDataTable(list);
    }

    /**
     * 导出产品列表列表
     */
    @RequiresPermissions("system:productinfo:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(WpProductinfo wpProductinfo)
    {
        List<WpProductinfo> list = wpProductinfoService.selectWpProductinfoList(wpProductinfo);
        ExcelUtil<WpProductinfo> util = new ExcelUtil<WpProductinfo>(WpProductinfo.class);
        return util.exportExcel(list, "productinfo");
    }

    /**
     * 新增产品列表
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存产品列表
     */
    @RequiresPermissions("system:productinfo:add")
    @Log(title = "产品列表", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(WpProductinfo wpProductinfo)
    {
    	wpProductinfo.setOpentime(wpProductinfo.getOpentime1() + "-" + wpProductinfo.getOpentime2()
    			+ "-" + wpProductinfo.getOpentime3() + "-" + wpProductinfo.getOpentime4()
    			+ "-" + wpProductinfo.getOpentime5() + "-" + wpProductinfo.getOpentime6() 
    			+ "-" + wpProductinfo.getOpentime7()
    			);
    	int count = wpProductinfoService.insertWpProductinfo(wpProductinfo);
//    	if(count > 0){
//    		this.websocketClient.updateProductMap();
//    	}
        return toAjax(count);
    }

    /**
     * 修改产品列表
     */
    @GetMapping("/edit/{pid}")
    public String edit(@PathVariable("pid") Integer pid, ModelMap mmap)
    {
        WpProductinfo wpProductinfo = wpProductinfoService.selectWpProductinfoById(pid);
        mmap.put("wpProductinfo", wpProductinfo);
        return prefix + "/edit";
    }

    /**
     * 修改保存产品列表
     */
    @RequiresPermissions("system:productinfo:edit")
    @Log(title = "产品列表", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(WpProductinfo wpProductinfo)
    {
    	wpProductinfo.setOpentime(wpProductinfo.getOpentime1() + "-" + wpProductinfo.getOpentime2()
    			+ "-" + wpProductinfo.getOpentime3() + "-" + wpProductinfo.getOpentime4()
    			+ "-" + wpProductinfo.getOpentime5() + "-" + wpProductinfo.getOpentime6() 
    			+ "-" + wpProductinfo.getOpentime7()
    			);
    	int count = wpProductinfoService.updateWpProductinfo(wpProductinfo);
//    	if(count > 0){
//    		this.websocketClient.updateProductMap();
//    	}
        return toAjax(count);
    }

    /**
     * 删除产品列表
     */
    @RequiresPermissions("system:productinfo:remove")
    @Log(title = "产品列表", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
    	int count = wpProductinfoService.deleteWpProductinfoByIds(ids);
//    	if(count > 0){
//    		this.websocketClient.updateProductMap();
//    	}
        return toAjax(count);
    }
}
