package com.wjyoption.web.controller.system;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
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
import com.wjyoption.system.domain.WpConfig;
import com.wjyoption.system.service.IWpConfigService;

/**
 * 系统配置Controller
 * 
 * @author wjyoption
 * @date 2021-06-08
 */
@Controller
@RequestMapping("/system/wpconfig")
public class WpConfigController extends BaseController
{
    private String prefix = "system/wpconfig";

    @Autowired
    private IWpConfigService wpConfigService;
    
    @SuppressWarnings("rawtypes")
	@Autowired RedisTemplate redisTemplate;

    @RequiresPermissions("system:wpconfig:view")
    @GetMapping()
    public String wpconfig()
    {
        return prefix + "/wpconfig";
    }

    /**
     * 查询系统配置列表
     */
    @RequiresPermissions("system:wpconfig:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(WpConfig wpConfig)
    {
        startPage();
        List<WpConfig> list = wpConfigService.selectWpConfigList(wpConfig);
        return getDataTable(list);
    }

    /**
     * 导出系统配置列表
     */
    @RequiresPermissions("system:wpconfig:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(WpConfig wpConfig)
    {
        List<WpConfig> list = wpConfigService.selectWpConfigList(wpConfig);
        ExcelUtil<WpConfig> util = new ExcelUtil<WpConfig>(WpConfig.class);
        return util.exportExcel(list, "wpconfig");
    }

    /**
     * 新增系统配置
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存系统配置
     */
    @RequiresPermissions("system:wpconfig:add")
    @Log(title = "系统配置", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(WpConfig wpConfig)
    {
        return toAjax(wpConfigService.insertWpConfig(wpConfig));
    }

    /**
     * 修改系统配置
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap)
    {
        WpConfig wpConfig = wpConfigService.selectWpConfigById(id);
        mmap.put("wpConfig", wpConfig);
        return prefix + "/edit";
    }

    /**
     * 修改保存系统配置
     */
    @RequiresPermissions("system:wpconfig:edit")
    @Log(title = "系统配置", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(WpConfig wpConfig)
    {
        return toAjax(wpConfigService.updateWpConfig(wpConfig));
    }

    /**
     * 删除系统配置
     */
    @RequiresPermissions("system:wpconfig:remove")
    @Log(title = "系统配置", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(wpConfigService.deleteWpConfigByIds(ids));
    }
    
    /**
     * 删除缓存
     */
    @SuppressWarnings("unchecked")
    @RequiresPermissions("system:wpconfig:removecache")
	@PostMapping( "/removeCache")
    @ResponseBody
    public AjaxResult removeCache(String key)
    {
    	if(redisTemplate.hasKey(key)){
    		redisTemplate.delete(key);
    	}
    	return AjaxResult.success();
    }
    
    /**
     * 删除缓存
     */
    @SuppressWarnings("unchecked")
    @RequiresPermissions("system:wpconfig:removecacheprefix")
    @PostMapping( "/removeCachePrefix")
    @ResponseBody
    public AjaxResult removeCachePrefix(String key)
    {
    	this.redisTemplate.delete(this.redisTemplate.keys(key + "*"));
    	return AjaxResult.success();
    }
}
