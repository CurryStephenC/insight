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
import com.wjyoption.common.core.page.TableDataInfo;
import com.wjyoption.common.enums.BusinessType;
import com.wjyoption.common.utils.DateUtils;
import com.wjyoption.common.utils.poi.ExcelUtil;
import com.wjyoption.framework.util.ShiroUtils;
import com.wjyoption.system.domain.CodepayOrder;
import com.wjyoption.system.domain.SysUser;
import com.wjyoption.system.service.ICodepayOrderService;
import com.wjyoption.system.service.ISysConfigService;
import com.wjyoption.system.service.ISysUserService;
import com.wjyoption.system.vo.report.CodepayOrderTotal;

/**
 * 支付Controller
 * 
 * @author wjyoption
 * @date 2021-06-07
 */
@Controller
@RequestMapping("/system/payorder")
public class CodepayOrderController extends BaseController
{
    private String prefix = "system/payorder";

    @Autowired
    private ICodepayOrderService codepayOrderService;
    @Autowired ISysUserService userService;
    @Autowired ISysConfigService configService;

    @RequiresPermissions("system:payorder:view")
    @GetMapping()
    public String payorder()
    {
        return prefix + "/payorder";
    }

    /**
     * 查询支付列表
     */
    @RequiresPermissions("system:payorder:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CodepayOrder codepayOrder)
    {
        listPower(codepayOrder);
    	startPage();
        List<CodepayOrder> list = codepayOrderService.selectCodepayOrderList(codepayOrder);
        return getDataTable(list);
    }

	private void listPower(CodepayOrder codepayOrder) {
		boolean permitted = ShiroUtils.getSubject().isPermitted("system:payorder:alluser");
    	if(!permitted){
    		permitted = ShiroUtils.getSubject().isPermitted("system:payorder:dept");
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
    			codepayOrder.setTopids(topids);
    		}else{
    			codepayOrder.setTopid(ShiroUtils.getSysUser().getUid());
    		}
    	}
	}
    
    /**
     * 查询支付列表
     */
    @RequiresPermissions("system:payorder:list")
    @PostMapping("/listTotal")
    @ResponseBody
    public AjaxResult listTotal(CodepayOrder codepayOrder)
    {
    	listPower(codepayOrder);
    	codepayOrder.setNormaltype(1);
    	codepayOrder.setStatus(1);
    	CodepayOrderTotal total = codepayOrderService.selectCodepayOrderTotal(codepayOrder);
    	
    	
    	Map<String, Object> params = new HashMap<>();
    	CodepayOrder order = codepayOrder;
//    	CodepayOrder order = new CodepayOrder();
//    	listPower(codepayOrder);
//    	order.setStatus(1);
//    	order.setNormaltype(1);
    	Calendar calendar = Calendar.getInstance();
    	String now = DateUtils.dateTimeNow(DateUtils.YYYYMMDD);
    	params.put("beginTime", now);
    	params.put("endTime", now);
    	order.setParams(params);
    	CodepayOrderTotal today = codepayOrderService.selectCodepayOrderTotal(order);
    	total.setTodayMoney(today.getFinishTotalMoney());
    	total.setTodayNum(today.getTodayNum());
    	
    	calendar.add(Calendar.DATE, -1);
    	String yes = DateUtils.dateTime(calendar.getTime(),DateUtils.YYYYMMDD);
    	params.put("beginTime", yes);
    	params.put("endTime", yes);
    	order.setParams(params);
    	CodepayOrderTotal yestoday = codepayOrderService.selectCodepayOrderTotal(order);
    	total.setYesMoney(yestoday.getFinishTotalMoney());
    	total.setYesNum(yestoday.getTodayNum());
    	
    	return AjaxResult.success(total);
    }

    /**
     * 导出支付列表
     */
    @RequiresPermissions("system:payorder:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(CodepayOrder codepayOrder)
    {
    	listPower(codepayOrder);
        List<CodepayOrder> list = codepayOrderService.selectCodepayOrderList(codepayOrder);
        ExcelUtil<CodepayOrder> util = new ExcelUtil<CodepayOrder>(CodepayOrder.class);
        return util.exportExcel(list, "payorder");
    }

    /**
     * 新增支付
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存支付
     */
    @RequiresPermissions("system:payorder:add")
    @Log(title = "支付", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(CodepayOrder codepayOrder)
    {
        return toAjax(codepayOrderService.insertCodepayOrder(codepayOrder));
    }

    /**
     * 修改支付
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        CodepayOrder codepayOrder = codepayOrderService.selectCodepayOrderById(id);
        mmap.put("codepayOrder", codepayOrder);
        return prefix + "/edit";
    }

    /**
     * 修改保存支付
     */
    @RequiresPermissions("system:payorder:edit")
    @Log(title = "支付", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(CodepayOrder codepayOrder)
    {
        return toAjax(codepayOrderService.updateCodepayOrder(codepayOrder));
    }
    
    /**
     * 后台手动修改订单状态
     */
    @RequiresPermissions("system:payorder:updateStatus")
    @Log(title = "支付", businessType = BusinessType.UPDATE)
    @PostMapping("/updateStatus")
    @ResponseBody
    public AjaxResult updateStatus()
    {
    	String id = super.getRequest().getParameter("id");
    	String paypsd = super.getRequest().getParameter("paypsd");
    	String status = super.getRequest().getParameter("status");
    	if(StringUtils.isBlank(id) ||StringUtils.isBlank(paypsd) || StringUtils.isBlank(status)){
    		return AjaxResult.error("参数不能为空");
    	}
    	String realPsd = this.configService.selectConfigByKey("wp_codepay_updstatus_psd");
    	if(!paypsd.equals(StringUtils.defaultIfBlank(realPsd, "xi5ao@teaC1her!"))){
    		return AjaxResult.error("Error password");
    	}
    	CodepayOrder bean = this.codepayOrderService.selectCodepayOrderById(Long.valueOf(id));
    	if(bean == null){
    		return AjaxResult.error("不存在");
    	}
    	if(status.equals(bean.getStatus().toString())){
    		return AjaxResult.error("状态未改变，无需修改");
    	}
    	CodepayOrder codepayOrder = new CodepayOrder();
    	codepayOrder.setId(Long.valueOf(id));
    	codepayOrder.setStatus(Integer.valueOf(status));
    	codepayOrder.setParam(bean.getParam()+"|"+ShiroUtils.getUserId()+":"+bean.getStatus()+">"+status+":"+DateUtils.dateTimeNow(DateUtils.YYYYMMDDHHMMSS));
    	return toAjax(codepayOrderService.updateCodepayOrder(codepayOrder));
    }
    
    /**
     * 审核支付
     * money/price/id/status/password
     */
    @RequiresPermissions("system:payorder:audit")
    @Log(title = "支付审核", businessType = BusinessType.UPDATE)
    @PostMapping("/auditChongzhi")
    @ResponseBody
    public AjaxResult auditChongzhi(CodepayOrder codepayOrder)
    {
    	String psd = super.getRequest().getParameter("password");
    	String defaultPsd = this.configService.selectConfigByKey("wp_payorder_audit_psd");
    	if(!StringUtils.defaultIfBlank(defaultPsd, "z!3oz09>*Li.*s?").equals(psd)){
    		return AjaxResult.error("错误，重来");
    	}
    	codepayOrder.setOperatorid(ShiroUtils.getUserId().intValue());
    	return codepayOrderService.auditChongzhi(codepayOrder);
    }

    /**
     * 删除支付
     */
    @RequiresPermissions("system:payorder:remove")
    @Log(title = "支付", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(codepayOrderService.deleteCodepayOrderByIds(ids));
    }
}
