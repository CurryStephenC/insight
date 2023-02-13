package com.wjyoption.web.controller.system;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.wjyoption.common.core.controller.BaseController;
import com.wjyoption.common.core.domain.AjaxResult;
import com.wjyoption.common.core.domain.Ztree;
import com.wjyoption.common.core.page.TableDataInfo;
import com.wjyoption.common.enums.BusinessType;
import com.wjyoption.common.utils.DateUtils;
import com.wjyoption.common.utils.poi.ExcelUtil;
import com.wjyoption.common.utils.security.Md5Util;
import com.wjyoption.framework.util.ShiroUtils;
import com.wjyoption.system.domain.SysUser;
import com.wjyoption.system.domain.WpUserinfo;
import com.wjyoption.system.domain.WpuserinfoLow;
import com.wjyoption.system.service.ISysUserService;
import com.wjyoption.system.service.IWpRedEnvelopeService;
import com.wjyoption.system.service.IWpUserinfoService;
import com.wjyoption.system.vo.UserinfoTotal;

/**
 * 前端用户Controller
 * 
 * @author hs
 * @date 2021-06-03
 */
@Controller
@RequestMapping("/system/userinfo")
public class WpUserinfoController extends BaseController
{
    private String prefix = "system/userinfo";

    @Autowired
    private IWpUserinfoService wpUserinfoService;
    @Autowired IWpRedEnvelopeService redEnvelopeService;
    @Autowired ISysUserService userService;

    @RequiresPermissions("system:userinfo:view")
    @GetMapping()
    public String userinfo()
    {
        return prefix + "/userinfo";
    }

    /**
     * 查询前端用户列表
     */
    @RequiresPermissions("system:userinfo:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(WpUserinfo wpUserinfo)
    {
        listParam(wpUserinfo);
    	startPage();
        List<WpUserinfo> list = wpUserinfoService.selectWpUserinfoList(wpUserinfo);
        for(WpUserinfo u : list){
        	u.setRedmoney(this.redEnvelopeService.selectRedMoney(u.getUid()));
        }
        return getDataTable(list);
    }

    /**
     * 查询前端用户列表
     */
    @RequiresPermissions("system:userinfo:list")
    @PostMapping("/listTotal")
    @ResponseBody
    public AjaxResult listTotal()
    {
    	UserinfoTotal total = new UserinfoTotal();
    	WpUserinfo wpUserinfo = new WpUserinfo();
    	listParam(wpUserinfo);
    	Map<String, Object> params = new HashMap<>();
    	Calendar calendar = Calendar.getInstance();
    	String now = DateUtils.dateTimeNow(DateUtils.YYYYMMDD);
    	params.put("beginTime", now);
    	params.put("endTime", now);
    	wpUserinfo.setParams(params);
    	int today = this.wpUserinfoService.selectDailyCount(wpUserinfo);
    	calendar.add(Calendar.DATE, -1);
    	String yes = DateUtils.dateTime(calendar.getTime(),DateUtils.YYYYMMDD);
    	params.put("beginTime", yes);
    	params.put("endTime", yes);
    	wpUserinfo.setParams(params);
    	int yestoday = this.wpUserinfoService.selectDailyCount(wpUserinfo);
    	total.setTodayUserNum(today);
    	total.setYesUserNum(yestoday);
    	return AjaxResult.success(total);
    }
    
    
    public void listParam(WpUserinfo wpUserinfo) {
		boolean permitted = ShiroUtils.getSubject().isPermitted("system:userinfo:alluser");
    	if(!permitted){
    		permitted = ShiroUtils.getSubject().isPermitted("system:userinfo:dept");
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
    			wpUserinfo.setTopids(topids);
    		}else{
    			wpUserinfo.setTopid(ShiroUtils.getSysUser().getUid());
    		}
    	}
	}
    
    @GetMapping("/treeData")
    @ResponseBody
    public List<Ztree> treeData()
    {
    	boolean permitted = ShiroUtils.getSubject().isPermitted("system:userinfo:alluser");
    	WpUserinfo wpUserinfo = new WpUserinfo();
    	if(!permitted){
    		wpUserinfo.setTopid(ShiroUtils.getSysUser().getUid());
    	}
        List<Ztree> ztrees = wpUserinfoService.selectUserinfoTree(wpUserinfo);
        return ztrees;
    }
    /**
     * 查看下级用户
     */
    @GetMapping("/lower/{oid}")
    public String lower(@PathVariable("oid") Integer oid, ModelMap mmap)
    {
        mmap.put("oid", oid);
        return prefix + "/lower";
    }
    
    @RequiresPermissions("system:userinfo:lower")
    @GetMapping("/treeData/{oid}")
    @ResponseBody
    public List<Ztree> treeData_oid(@PathVariable("oid") Integer oid)
    {
    	List<Ztree> ztrees = wpUserinfoService.selectUserinfoTree(oid);
    	return ztrees;
    }

    /**
     * 导出前端用户列表
     */
    @PostMapping("/exportLow")
    @ResponseBody
    public AjaxResult exportLow(Integer oid)
    {
    	List<WpuserinfoLow> list = wpUserinfoService.exportUserinfoTree(oid);
    	ExcelUtil<WpuserinfoLow> util = new ExcelUtil<WpuserinfoLow>(WpuserinfoLow.class);
    	return util.exportExcel(list, "下级用户");
    }
    
    /**
     * 导出前端用户列表
     */
    @RequiresPermissions("system:userinfo:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(WpUserinfo wpUserinfo)
    {
    	listParam(wpUserinfo);
        List<WpUserinfo> list = wpUserinfoService.selectWpUserinfoList(wpUserinfo);
        ExcelUtil<WpUserinfo> util = new ExcelUtil<WpUserinfo>(WpUserinfo.class);
        return util.exportExcel(list, "userinfo");
    }

    /**
     * 新增前端用户
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存前端用户
     */
    @RequiresPermissions("system:userinfo:add")
    @Log(title = "前端用户", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(WpUserinfo wpUserinfo)
    {
    	wpUserinfo.setOid(1);
    	wpUserinfo.setManagername("admin");
    	wpUserinfo.setOtype(101);
    	wpUserinfo.setIstop(1);
    	wpUserinfo.setNormaltype(2);
    	wpUserinfo.setNickname(wpUserinfo.getUsername());
    	wpUserinfo.setUtime(DateUtils.getNowSecond());
    	wpUserinfo.setUpwd(Md5Util.MD5(wpUserinfo.getUpwd() + wpUserinfo.getUpwd()));
    	int count = wpUserinfoService.insertWpUserinfo(wpUserinfo);
    	wpUserinfo.setTopid(wpUserinfo.getUid());
    	this.wpUserinfoService.updateWpUserinfo(wpUserinfo);
        return toAjax(count);
    }

    /**
     * 修改前端用户
     */
    @GetMapping("/edit/{uid}")
    public String edit(@PathVariable("uid") Integer uid, ModelMap mmap)
    {
    	WpUserinfo userinfo = new WpUserinfo();
    	userinfo.setUstatus(0);
    	listParam(userinfo);
    	
    	userinfo.setOtype(101);
        List<WpUserinfo> topList = wpUserinfoService.selectWpUserinfoList(userinfo);
        WpUserinfo wpUserinfo = wpUserinfoService.selectWpUserinfoById(uid);
        
        boolean isexist = false;
        for(WpUserinfo obj : topList){
        	if(obj.getUid().intValue() == wpUserinfo.getTopid().intValue()){
        		isexist = true;
        		break;
        	}
        }
        if(!isexist){
        	WpUserinfo addInfo = new WpUserinfo();
        	addInfo.setUid(wpUserinfo.getTopid());
        	addInfo.setUtel(wpUserinfo.getToputel());
        	topList.add(0,addInfo);
        }
        
        mmap.put("wpUserinfo", wpUserinfo);
        mmap.put("top", topList);
        return prefix + "/edit";
    }

    /**
     * 修改保存前端用户
     */
    @RequiresPermissions("system:userinfo:edit")
    @Log(title = "前端用户", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(WpUserinfo wpUserinfo)
    {
    	if(StringUtils.isNotBlank(wpUserinfo.getUpwd())){
    		WpUserinfo userinfo = this.wpUserinfoService.selectWpUserinfoById(wpUserinfo.getUid());
    		wpUserinfo.setUpwd(Md5Util.MD5(wpUserinfo.getUpwd() + userinfo.getUtime()));
    	}
    	if(StringUtils.isNotBlank(wpUserinfo.getOutel())){
    		WpUserinfo ouser = this.wpUserinfoService.selectUserByPhone(wpUserinfo.getOutel());
    		if(ouser == null){
    			return AjaxResult.error("上线不存在");
    		}
    		wpUserinfo.setOid(ouser.getUid());
    	}
    	if(wpUserinfo.getTopid()!=null){
    		WpUserinfo topuser = this.wpUserinfoService.selectWpUserinfoById(wpUserinfo.getTopid());
    		if(topuser == null || (topuser.getOtype() != 101 && topuser.getOtype() != 3)){
    			return AjaxResult.error("销售不存在");
    		}
    	}
//    	if(StringUtils.isNotBlank(wpUserinfo.getToputel())){
//    		WpUserinfo topuser = this.wpUserinfoService.selectUserByPhone(wpUserinfo.getToputel());
//    		if(topuser == null || (topuser.getOtype() != 101 && topuser.getOtype() != 3)){
//    			return AjaxResult.error("销售不存在");
//    		}
//    		wpUserinfo.setTopid(topuser.getUid());
//    	}
        return toAjax(wpUserinfoService.updateWpUserinfo(wpUserinfo));
    }

    /**
     * 删除前端用户
     */
    @RequiresPermissions("system:userinfo:remove")
    @Log(title = "前端用户", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(wpUserinfoService.deleteWpUserinfoByIds(ids));
    }
    
    /**
     * 修改前端用户状态
     */
    @RequiresPermissions("system:userinfo:status")
    @Log(title = "前端用户状态", businessType = BusinessType.UPDATE)
    @PostMapping( "status")
    @ResponseBody
    public AjaxResult updateStatus(Integer uid)
    {
    	WpUserinfo bean = this.wpUserinfoService.selectWpUserinfoById(uid);
    	if(bean.getUstatus() == 0){
    		bean.setUstatus(1);
    	}else{
    		bean.setUstatus(0);
    	}
    	return toAjax(wpUserinfoService.updateUserStatus(uid,bean.getUstatus()));
    }
    
    /**
     * 删除前端用户
     */
    @RequiresPermissions("system:userinfo:freeze")
    @Log(title = "前端用户冻结", businessType = BusinessType.UPDATE)
    @PostMapping( "freeze")
    @ResponseBody
    public AjaxResult freeze(Integer uid)
    {
    	wpUserinfoService.freeze(uid);
    	return toAjax(1);
    }
    
    
    
}
