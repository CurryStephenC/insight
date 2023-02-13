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
import com.wjyoption.common.annotation.Log;
import com.wjyoption.common.enums.BusinessType;
import com.wjyoption.system.domain.WpSpeechCommentReply;
import com.wjyoption.system.service.IWpSpeechCommentReplyService;
import com.wjyoption.common.core.controller.BaseController;
import com.wjyoption.common.core.domain.AjaxResult;
import com.wjyoption.common.utils.poi.ExcelUtil;
import com.wjyoption.common.core.page.TableDataInfo;

/**
 * 言论评论回复Controller
 * 
 * @author wjyoption
 * @date 2021-06-17
 */
@Controller
@RequestMapping("/system/speechreply")
public class WpSpeechCommentReplyController extends BaseController
{
    private String prefix = "system/speechreply";

    @Autowired
    private IWpSpeechCommentReplyService wpSpeechCommentReplyService;

    @RequiresPermissions("system:speechreply:view")
    @GetMapping()
    public String speechreply(ModelMap mmap)
    {
    	mmap.put("cid", "");
        return prefix + "/speechreply";
    }
    @RequiresPermissions("system:speechreply:view")
    @GetMapping("{id}")
    public String speechreply2(@PathVariable("id") Integer cid, ModelMap mmap)
    {
    	mmap.put("cid", cid);
    	return prefix + "/speechreply";
    }

    /**
     * 查询言论评论回复列表
     */
    @RequiresPermissions("system:speechreply:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(WpSpeechCommentReply wpSpeechCommentReply)
    {
        startPage();
        List<WpSpeechCommentReply> list = wpSpeechCommentReplyService.selectWpSpeechCommentReplyList(wpSpeechCommentReply);
        return getDataTable(list);
    }

    /**
     * 导出言论评论回复列表
     */
    @RequiresPermissions("system:speechreply:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(WpSpeechCommentReply wpSpeechCommentReply)
    {
        List<WpSpeechCommentReply> list = wpSpeechCommentReplyService.selectWpSpeechCommentReplyList(wpSpeechCommentReply);
        ExcelUtil<WpSpeechCommentReply> util = new ExcelUtil<WpSpeechCommentReply>(WpSpeechCommentReply.class);
        return util.exportExcel(list, "speechreply");
    }

    /**
     * 新增言论评论回复
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存言论评论回复
     */
    @RequiresPermissions("system:speechreply:add")
    @Log(title = "言论评论回复", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(WpSpeechCommentReply wpSpeechCommentReply)
    {
        return toAjax(wpSpeechCommentReplyService.insertWpSpeechCommentReply(wpSpeechCommentReply));
    }

    /**
     * 修改言论评论回复
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap)
    {
        WpSpeechCommentReply wpSpeechCommentReply = wpSpeechCommentReplyService.selectWpSpeechCommentReplyById(id);
        mmap.put("wpSpeechCommentReply", wpSpeechCommentReply);
        return prefix + "/edit";
    }

    /**
     * 修改保存言论评论回复
     */
    @RequiresPermissions("system:speechreply:edit")
    @Log(title = "言论评论回复", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(WpSpeechCommentReply wpSpeechCommentReply)
    {
        return toAjax(wpSpeechCommentReplyService.updateWpSpeechCommentReply(wpSpeechCommentReply));
    }

    /**
     * 删除言论评论回复
     */
    @RequiresPermissions("system:speechreply:remove")
    @Log(title = "言论评论回复", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(wpSpeechCommentReplyService.deleteWpSpeechCommentReplyByIds(ids));
    }
}
