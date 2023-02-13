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

import com.wjyoption.common.core.controller.BaseController;
import com.wjyoption.common.core.page.TableDataInfo;
import com.wjyoption.system.service.IStatUserService;
import com.wjyoption.system.vo.report.StatuserReport;

@Controller
@RequestMapping("/system/statuser")
public class StatUserController extends BaseController {

	
	private String prefix = "system/statuser";
	@Autowired IStatUserService statUserService;
	
	
	@RequiresPermissions("system:statuser:view")
    @GetMapping()
    public String statuser()
    {
        return prefix + "/list";
    }
	
    @RequiresPermissions("system:statuser:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(StatuserReport report)
    {
    	List<StatuserReport> list = this.statUserService.selectDeptStat(report);
        return getDataTable(list);
    }
    
    @RequiresPermissions("system:statuser:detail")
    @PostMapping("/detaillist")
    @ResponseBody
    public TableDataInfo detaillist(StatuserReport report)
    {
    	List<StatuserReport> list = this.statUserService.selectDeptDetail(report);
    	return getDataTable(list);
    }
    
    @RequiresPermissions("system:statuser:detail")
    @GetMapping("/detail/{refid}")
    public String detail(@PathVariable("refid") Integer refid, ModelMap mmap)
    {
    	mmap.put("refid", refid);
    	return prefix + "/detail";
    }
}
