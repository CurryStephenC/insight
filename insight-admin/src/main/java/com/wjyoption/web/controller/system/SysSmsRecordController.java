package com.wjyoption.web.controller.system;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.wjyoption.common.core.controller.BaseController;
import com.wjyoption.common.core.domain.AjaxResult;
import com.wjyoption.common.core.page.TableDataInfo;
import com.wjyoption.common.utils.poi.ExcelUtil;
import com.wjyoption.framework.util.ShiroUtils;
import com.wjyoption.system.domain.SysSmsRecord;
import com.wjyoption.system.service.ISysSmsRecordService;

/**
 * 短信发送记录Controller
 * 
 * @author hs
 * @date 2020-04-29
 */
@Controller
@RequestMapping("/system/smsrecord")
public class SysSmsRecordController extends BaseController
{
    private String prefix = "system/smsrecord";

    @Autowired
    private ISysSmsRecordService sysSmsRecordService;

    @RequiresPermissions("system:smsrecord:view")
    @GetMapping()
    public String smsrecord()
    {
        return prefix + "/smsrecord";
    }

    /**
     * 查询短信发送记录列表
     */
    @RequiresPermissions("system:smsrecord:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysSmsRecord sysSmsRecord)
    {
        startPage();
        List<SysSmsRecord> list = sysSmsRecordService.selectSysSmsRecordList(sysSmsRecord);
        boolean permitted = ShiroUtils.getSubject().isPermitted("system:smsrecord:sensitive");
        if(permitted && CollectionUtils.isNotEmpty(list)){
        	list.forEach(obj -> {
        		String sensitive = obj.getSensitive();
        		if(StringUtils.isNotBlank(sensitive)){
        			try {
						obj.setContent(String.format(obj.getContent(), JSON.parseArray(sensitive).toArray()));
					} catch (Exception e) {
					}
        		}
        	});
        }
        return getDataTable(list);
    }

    /**
     * 导出短信发送记录列表
     */
    @RequiresPermissions("system:smsrecord:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SysSmsRecord sysSmsRecord)
    {
        List<SysSmsRecord> list = sysSmsRecordService.selectSysSmsRecordList(sysSmsRecord);
        ExcelUtil<SysSmsRecord> util = new ExcelUtil<SysSmsRecord>(SysSmsRecord.class);
        return util.exportExcel(list, "smsrecord");
    }

}
