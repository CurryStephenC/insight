package com.wjyoption.web.controller.system;

import java.util.ArrayList;
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
import com.wjyoption.common.enums.BusinessType;
import com.wjyoption.system.domain.SysUser;
import com.wjyoption.system.domain.WpOrder;
import com.wjyoption.system.service.ISysUserService;
import com.wjyoption.system.service.IWpOrderService;
import com.wjyoption.common.core.controller.BaseController;
import com.wjyoption.common.core.domain.AjaxResult;
import com.wjyoption.common.utils.poi.ExcelUtil;
import com.wjyoption.common.core.page.TableDataInfo;
import com.wjyoption.framework.util.ShiroUtils;

/**
 * 订单模块Controller
 * 
 * @author wjyoption
 * @date 2021-07-08
 */
@Controller
@RequestMapping("/system/order")
public class WpOrderController extends BaseController
{
    private String prefix = "system/order";

    @Autowired
    private IWpOrderService wpOrderService;
    @Autowired ISysUserService userService;

    @RequiresPermissions("system:order:view")
    @GetMapping()
    public String order()
    {
        return prefix + "/order";
    }

    /**
     * 查询订单模块列表
     */
    @RequiresPermissions("system:order:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(WpOrder wpOrder)
    {
    	listParam(wpOrder);
//    	wpOrder.setNormaltype(1);
    	startPage();
        List<WpOrder> list = wpOrderService.selectWpOrderList(wpOrder);
        return getDataTable(list);
    }
    @RequiresPermissions("system:order:list")
    @PostMapping("/listTotal")
    @ResponseBody
    public AjaxResult listTotal(WpOrder wpOrder)
    {
    	listParam(wpOrder);
//    	wpOrder.setNormaltype(1);
    	wpOrder.setOstaus(1);//已平仓订单
    	//totalNum,totalPloss,userNum,totalFee
    	Map<String, Object> map = wpOrderService.selectWpOrderListTotal(wpOrder);
    	return AjaxResult.success(map);
    }

	public void listParam(WpOrder wpOrder) {
		boolean permitted = ShiroUtils.getSubject().isPermitted("system:order:alldata");
		if(!permitted){
    		permitted = ShiroUtils.getSubject().isPermitted("system:order:dept");
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
    			wpOrder.setTopids(topids);
    		}else{
    			wpOrder.setTopid(ShiroUtils.getSysUser().getUid());
    		}
    	}
	}

    /**
     * 导出订单模块列表
     */
    @RequiresPermissions("system:order:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(WpOrder wpOrder)
    {
    	listParam(wpOrder);
        List<WpOrder> list = wpOrderService.selectWpOrderList(wpOrder);
        ExcelUtil<WpOrder> util = new ExcelUtil<WpOrder>(WpOrder.class);
        return util.exportExcel(list, "order");
    }

    /**
     * 新增订单模块
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存订单模块
     */
    @RequiresPermissions("system:order:add")
    @Log(title = "订单模块", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(WpOrder wpOrder)
    {
        return toAjax(wpOrderService.insertWpOrder(wpOrder));
    }

    /**
     * 修改订单模块
     */
    @GetMapping("/edit/{oid}")
    public String edit(@PathVariable("oid") Integer oid, ModelMap mmap)
    {
        WpOrder wpOrder = wpOrderService.selectWpOrderById(oid);
        mmap.put("wpOrder", wpOrder);
        return prefix + "/edit";
    }

    /**
     * 修改保存订单模块
     */
    @RequiresPermissions("system:order:edit")
    @Log(title = "订单模块", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(WpOrder wpOrder)
    {
        return toAjax(wpOrderService.updateWpOrder(wpOrder));
    }
    
    /**
     * 修改保存订单模块
     */
    @RequiresPermissions("system:order:kong")
    @Log(title = "订单模块单控", businessType = BusinessType.UPDATE)
    @PostMapping("/kong")
    @ResponseBody
    public AjaxResult kong(String oids,Integer kong_type)
    {
    	StringBuilder error = new StringBuilder();
    	String[] oidList = oids.split(",");
    	for(String id : oidList){
    		Integer oid = Integer.valueOf(id);
    		WpOrder bean = this.wpOrderService.selectWpOrderById(oid);
    		if(bean.getOstaus() == 1){
    			error.append(",").append(bean.getUtel());
    			continue;
    		}
    		
    		WpOrder wpOrder = new WpOrder();
    		wpOrder.setOid(oid);
    		wpOrder.setKongType(kong_type);
    		wpOrderService.updateWpOrder(wpOrder);
    	}
    	if(error.length() == 0){
    		return AjaxResult.success();
    	}
    	return AjaxResult.error(error.append("：已平仓").substring(1));
    }

    /**
     * 删除订单模块
     */
    @RequiresPermissions("system:order:remove")
    @Log(title = "订单模块", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(wpOrderService.deleteWpOrderByIds(ids));
    }
}
