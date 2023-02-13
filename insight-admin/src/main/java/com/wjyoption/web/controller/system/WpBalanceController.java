package com.wjyoption.web.controller.system;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.wjyoption.system.domain.WpBalance;
import com.wjyoption.system.domain.WpUserinfo;
import com.wjyoption.system.service.ISysUserService;
import com.wjyoption.system.service.IWpBalanceService;
import com.wjyoption.system.service.IWpUserinfoService;

/**
 * 提现手动充值Controller
 * 
 * @author wjyoption
 * @date 2021-06-08
 */
@Controller
@RequestMapping("/system/balance")
public class WpBalanceController extends BaseController
{
    private String prefix = "system/balance";

    @Autowired
    private IWpBalanceService wpBalanceService;
    @Autowired IWpUserinfoService userinfoService;
    @Autowired ISysUserService userService;

    @RequiresPermissions("system:balance:view")
    @GetMapping()
    public String balance()
    {
        return prefix + "/balance";
    }

    /**
     * 查询提现手动充值列表
     */
    @RequiresPermissions("system:balance:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(WpBalance wpBalance)
    {
        listPower(wpBalance);
    	startPage();
        List<WpBalance> list = wpBalanceService.selectWpBalanceList(wpBalance);
        return getDataTable(list);
    }
    /**
     * 统计
     */
    @RequiresPermissions("system:balance:list")
    @PostMapping("/listTotal")
    @ResponseBody
    public AjaxResult listTotal(WpBalance wpBalance)
    {
    	listPower(wpBalance);
    	wpBalance.setBptype(null);
    	wpBalance.setIsverified(1);
    	//withdraw,recharge
    	Map<String, String> total = wpBalanceService.selectBalanceTotal(wpBalance);
    	if(total == null) total = new HashMap<>();
    	wpBalance.setBptype(0);
    	Map<String, Object> params = new HashMap<>();
    	Calendar calendar = Calendar.getInstance();
    	String now = DateUtils.dateTimeNow(DateUtils.YYYYMMDD);
    	params.put("beginTime", now);
    	params.put("endTime", now);
    	wpBalance.setParams(params);
    	//totalMoney,userNum
    	Map<String,String> todayMap = this.wpBalanceService.selectDailyTotal(wpBalance);
    	total.put("todayMoney", todayMap != null ? String.valueOf(todayMap.get("totalMoney")) : "0");
    	total.put("todayNum", todayMap != null ? String.valueOf(todayMap.get("userNum")) : "0");
    	
    	calendar.add(Calendar.DATE, -1);
    	String yes = DateUtils.dateTime(calendar.getTime(),DateUtils.YYYYMMDD);
    	params.put("beginTime", yes);
    	params.put("endTime", yes);
    	wpBalance.setParams(params);
    	//totalMoney,userNum
    	Map<String,String> yesMap = this.wpBalanceService.selectDailyTotal(wpBalance);
    	total.put("yesMoney", yesMap.get("totalMoney"));
    	total.put("yesNum", String.valueOf(yesMap.get("userNum")));
    	
    	return AjaxResult.success(total);
    }

	private void listPower(WpBalance wpBalance) {
		boolean permitted = ShiroUtils.getSubject().isPermitted("system:balance:alluser");
    	if(!permitted){
    		permitted = ShiroUtils.getSubject().isPermitted("system:balance:dept");
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
    			wpBalance.setTopids(topids);
    		}else{
    			wpBalance.setTopid(ShiroUtils.getSysUser().getUid());
    		}
    	}
//    	wpBalance.setNormaltype(1);
	}

    /**
     * 导出提现手动充值列表
     */
    @RequiresPermissions("system:balance:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(WpBalance wpBalance)
    {
    	listPower(wpBalance);
        List<WpBalance> list = wpBalanceService.selectWpBalanceList(wpBalance);
        ExcelUtil<WpBalance> util = new ExcelUtil<WpBalance>(WpBalance.class);
        return util.exportExcel(list, "balance");
    }

    /**
     * 新增提现手动充值
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存提现手动充值
     */
    @RequiresPermissions("system:balance:add")
    @Log(title = "提现手动充值", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(WpBalance wpBalance)
    {
    	WpUserinfo userinfo = this.userinfoService.selectUserByPhone(wpBalance.getUtel());
    	if(userinfo == null){
    		return AjaxResult.error("用户不存在");
    	}
    	wpBalance.setUid(userinfo.getUid());
    	wpBalance.setUserMoney(userinfo.getUsermoney());
    	wpBalance.setBptype(2);
    	wpBalance.setBptime(DateUtils.getNowSecond());
    	wpBalance.setRealprice(wpBalance.getBpprice());
    	wpBalance.setIsverified(1);
    	wpBalance.setCltime(wpBalance.getBptime());
    	wpBalance.setBpbalance(userinfo.getUsermoney().add(wpBalance.getBpprice()).toString());
    	wpBalance.setBtime(wpBalance.getBptime());
    	wpBalance.setThirdid("");
        return toAjax(wpBalanceService.insertWpBalance(wpBalance));
    }

    /**
     * 修改提现手动充值
     */
    @GetMapping("/edit/{bpid}")
    public String edit(@PathVariable("bpid") Integer bpid, ModelMap mmap)
    {
        WpBalance wpBalance = wpBalanceService.selectWpBalanceById(bpid);
        mmap.put("wpBalance", wpBalance);
        return prefix + "/edit";
    }

    /**
     * 修改保存提现手动充值
     */
    @RequiresPermissions("system:balance:edit")
    @Log(title = "提现手动充值", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(WpBalance wpBalance)
    {
    	WpBalance vo = this.wpBalanceService.selectWpBalanceById(wpBalance.getBpid());
    	if(vo.getIspush() != 0 || vo.getIsverified() != 0 || vo.getBptype() == 2){
    		return AjaxResult.error("无法操作");
    	}
    	vo.setRealprice(wpBalance.getRealprice());
    	vo.setRemarks(wpBalance.getRemarks());
        return toAjax(wpBalanceService.updateWpBalance(vo));
    }
    
    /**
     * 审核订单
     */
    @RequiresPermissions("system:balance:submit")
    @Log(title = "提现手动充值", businessType = BusinessType.UPDATE)
    @PostMapping("/auditOrder")
    @ResponseBody
    public AjaxResult auditOrder(Integer bpid,Integer ispush,Integer isverified,String remarks)
    {
    	if(bpid == null || (ispush == null && isverified == null)){
    		return AjaxResult.error("少了东西");
    	}
    	WpBalance vo = this.wpBalanceService.selectWpBalanceById(bpid);
    	if(vo.getIspush() != 0 || vo.getIsverified() != 0 || vo.getBptype() == 2){
    		return AjaxResult.error("无法操作");
    	}
    	synchronized (bpid) {
    		vo.setIspush(ispush);
    		vo.setIsverified(isverified);
    		vo.setRemarks(remarks);
    		return wpBalanceService.submit(vo);
		}
//    	return wpBalanceService.submit(vo);
    }

    /**
     * 删除提现手动充值
     */
    @RequiresPermissions("system:balance:remove")
    @Log(title = "提现手动充值", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(wpBalanceService.deleteWpBalanceByIds(ids));
    }
}
