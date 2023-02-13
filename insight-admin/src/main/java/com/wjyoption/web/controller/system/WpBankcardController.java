package com.wjyoption.web.controller.system;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
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
import com.wjyoption.system.domain.WpBankcard;
import com.wjyoption.system.service.ISysUserService;
import com.wjyoption.system.service.IWpBankcardService;
import com.wjyoption.common.core.controller.BaseController;
import com.wjyoption.common.core.domain.AjaxResult;
import com.wjyoption.common.utils.poi.ExcelUtil;
import com.wjyoption.common.core.page.TableDataInfo;
import com.wjyoption.framework.util.ShiroUtils;

/**
 * 用户银行信息Controller
 * 
 * @author wjyoption
 * @date 2021-06-08
 */
@Controller
@RequestMapping("/system/bankcard")
public class WpBankcardController extends BaseController
{
    private String prefix = "system/bankcard";

    @Autowired
    private IWpBankcardService wpBankcardService;
    @Autowired ISysUserService userService;

    @RequiresPermissions("system:bankcard:view")
    @GetMapping()
    public String bankcard()
    {
        return prefix + "/bankcard";
    }

    /**
     * 查询用户银行信息列表
     */
    @RequiresPermissions("system:bankcard:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(WpBankcard wpBankcard)
    {
        boolean permitted = ShiroUtils.getSubject().isPermitted("system:bankcard:alluser");
    	if(!permitted){
    		permitted = ShiroUtils.getSubject().isPermitted("system:bankcard:dept");
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
    			wpBankcard.setTopids(topids);
    		}else{
    			wpBankcard.setTopid(ShiroUtils.getSysUser().getUid());
    		}
    	}
    	wpBankcard.setNormaltype(1);
    	startPage();
        List<WpBankcard> list = wpBankcardService.selectWpBankcardList(wpBankcard);
        return getDataTable(list);
    }

    /**
     * 导出用户银行信息列表
     */
    @RequiresPermissions("system:bankcard:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(WpBankcard wpBankcard)
    {
        List<WpBankcard> list = wpBankcardService.selectWpBankcardList(wpBankcard);
        ExcelUtil<WpBankcard> util = new ExcelUtil<WpBankcard>(WpBankcard.class);
        return util.exportExcel(list, "bankcard");
    }

    /**
     * 新增用户银行信息
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存用户银行信息
     */
    @RequiresPermissions("system:bankcard:add")
    @Log(title = "用户银行信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(WpBankcard wpBankcard)
    {
        return toAjax(wpBankcardService.insertWpBankcard(wpBankcard));
    }

    /**
     * 修改用户银行信息
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        WpBankcard wpBankcard = wpBankcardService.selectWpBankcardById(id);
        mmap.put("wpBankcard", wpBankcard);
        return prefix + "/edit";
    }
    
    @PostMapping("/user/{uid}")
    @ResponseBody
    public AjaxResult user(@PathVariable("uid") Integer uid)
    {
    	WpBankcard wpBankcard = wpBankcardService.selectBankByUid(uid);
    	if(wpBankcard == null || StringUtils.isBlank(wpBankcard.getWalletaddr())){
    		return AjaxResult.error("钱包不存在");
    	}
    	return AjaxResult.success(wpBankcard);
    }

    /**
     * 修改保存用户银行信息
     */
    @RequiresPermissions("system:bankcard:edit")
    @Log(title = "用户银行信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(WpBankcard wpBankcard)
    {
        return toAjax(wpBankcardService.updateWpBankcard(wpBankcard));
    }

    /**
     * 删除用户银行信息
     */
    @RequiresPermissions("system:bankcard:remove")
    @Log(title = "用户银行信息", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(wpBankcardService.deleteWpBankcardByIds(ids));
    }
}
