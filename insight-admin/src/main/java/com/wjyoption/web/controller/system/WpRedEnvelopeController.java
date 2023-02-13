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
import com.wjyoption.system.domain.WpRedEnvelope;
import com.wjyoption.system.domain.WpUserinfo;
import com.wjyoption.system.service.ISysUserService;
import com.wjyoption.system.service.IWpRedEnvelopeService;
import com.wjyoption.system.service.IWpUserinfoService;
import com.wjyoption.common.core.controller.BaseController;
import com.wjyoption.common.core.domain.AjaxResult;
import com.wjyoption.common.utils.DateUtils;
import com.wjyoption.common.utils.poi.ExcelUtil;
import com.wjyoption.common.core.page.TableDataInfo;
import com.wjyoption.framework.util.ShiroUtils;

/**
 * 理财红包Controller
 * 
 * @author wjyoption
 * @date 2021-06-05
 */
@Controller
@RequestMapping("/system/redenvelope")
public class WpRedEnvelopeController extends BaseController
{
    private String prefix = "system/redenvelope";

    @Autowired
    private IWpRedEnvelopeService wpRedEnvelopeService;
    @Autowired IWpUserinfoService userinfoService;
    @Autowired ISysUserService userService;

    @RequiresPermissions("system:redenvelope:view")
    @GetMapping()
    public String redenvelope()
    {
        return prefix + "/redenvelope";
    }

    /**
     * 查询理财红包列表
     */
    @RequiresPermissions("system:redenvelope:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(WpRedEnvelope wpRedEnvelope)
    {
        boolean permitted = ShiroUtils.getSubject().isPermitted("system:redenvelope:alluser");
    	if(!permitted){
    		permitted = ShiroUtils.getSubject().isPermitted("system:redenvelope:dept");
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
    			wpRedEnvelope.setTopids(topids);
    		}else{
    			wpRedEnvelope.setTopid(ShiroUtils.getSysUser().getUid());
    		}
    	}
    	startPage();
        List<WpRedEnvelope> list = wpRedEnvelopeService.selectWpRedEnvelopeList(wpRedEnvelope);
        return getDataTable(list);
    }

    /**
     * 导出理财红包列表
     */
    @RequiresPermissions("system:redenvelope:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(WpRedEnvelope wpRedEnvelope)
    {
        List<WpRedEnvelope> list = wpRedEnvelopeService.selectWpRedEnvelopeList(wpRedEnvelope);
        ExcelUtil<WpRedEnvelope> util = new ExcelUtil<WpRedEnvelope>(WpRedEnvelope.class);
        return util.exportExcel(list, "redenvelope");
    }

    /**
     * 新增理财红包
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存理财红包
     */
    @RequiresPermissions("system:redenvelope:add")
    @Log(title = "理财红包", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(WpRedEnvelope wpRedEnvelope)
    {
    	WpUserinfo userinfo = this.userinfoService.selectUserByPhone(wpRedEnvelope.getUtel());
    	if(userinfo == null){
    		return AjaxResult.error("用户不存在");
    	}
    	wpRedEnvelope.setUid(userinfo.getUid());
    	wpRedEnvelope.setLavemoney(wpRedEnvelope.getMoney());
    	wpRedEnvelope.setType(1);
    	wpRedEnvelope.setCreatetime(System.currentTimeMillis()/1000);
    	wpRedEnvelope.setEndtime(DateUtils.dateTime(DateUtils.YYYY_MM_DD, DateUtils.dateTimeNow(DateUtils.YYYY_MM_DD)).getTime() / 1000 + 86400*30);
        return toAjax(wpRedEnvelopeService.insertWpRedEnvelope(wpRedEnvelope));
    }

    /**
     * 修改理财红包
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap)
    {
        WpRedEnvelope wpRedEnvelope = wpRedEnvelopeService.selectWpRedEnvelopeById(id);
        mmap.put("wpRedEnvelope", wpRedEnvelope);
        return prefix + "/edit";
    }

    /**
     * 修改保存理财红包
     */
    @RequiresPermissions("system:redenvelope:edit")
    @Log(title = "理财红包", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(WpRedEnvelope wpRedEnvelope)
    {
        return toAjax(wpRedEnvelopeService.updateWpRedEnvelope(wpRedEnvelope));
    }

    /**
     * 删除理财红包
     */
    @RequiresPermissions("system:redenvelope:remove")
    @Log(title = "理财红包", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(wpRedEnvelopeService.deleteWpRedEnvelopeByIds(ids));
    }
}
