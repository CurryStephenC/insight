package com.wjyoption.web.controller.system;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wjyoption.common.annotation.Log;
import com.wjyoption.common.core.controller.BaseController;
import com.wjyoption.common.core.domain.AjaxResult;
import com.wjyoption.common.enums.BusinessType;
import com.wjyoption.system.domain.WpFinancialTypeDetail;
import com.wjyoption.system.service.IWpFinancialTypeDetailService;

/**
 * 理财类型详情Controller
 * 
 * @author wjyoption
 * @date 2021-06-04
 */
@Controller
@RequestMapping("/system/financialtypedetail")
public class WpFinancialTypeDetailController extends BaseController
{
	
    @Autowired
    private IWpFinancialTypeDetailService wpFinancialTypeDetailService;

    /**
     * 新增保存理财类型详情
     */
    @RequiresPermissions("system:financialtypedetail:edit")
    @Log(title = "理财类型详情", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(WpFinancialTypeDetail wpFinancialTypeDetail)
    {
        return toAjax(wpFinancialTypeDetailService.insertWpFinancialTypeDetail(wpFinancialTypeDetail));
    }

    /**
     * 修改保存理财类型详情
     */
    @RequiresPermissions("system:financialtypedetail:edit")
    @Log(title = "理财类型详情", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(WpFinancialTypeDetail wpFinancialTypeDetail)
    {
        return toAjax(wpFinancialTypeDetailService.updateWpFinancialTypeDetail(wpFinancialTypeDetail));
    }

}
