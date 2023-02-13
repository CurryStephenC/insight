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
import com.wjyoption.system.domain.WpSpeechComment;
import com.wjyoption.system.service.IWpSpeechCommentService;
import com.wjyoption.common.core.controller.BaseController;
import com.wjyoption.common.core.domain.AjaxResult;
import com.wjyoption.common.utils.poi.ExcelUtil;
import com.wjyoption.common.core.page.TableDataInfo;

/**
 * 言论评论Controller
 * 
 * @author wjyoption
 * @date 2021-06-17
 */
@Controller
@RequestMapping("/system/speechcomment")
public class WpSpeechCommentController extends BaseController
{
    private String prefix = "system/speechcomment";

    @Autowired
    private IWpSpeechCommentService wpSpeechCommentService;

    @RequiresPermissions("system:speechcomment:view")
    @GetMapping()
    public String speechcomment(ModelMap mmap)
    {
    	mmap.put("sid", "");
        return prefix + "/speechcomment";
    }
    
    @RequiresPermissions("system:speechcomment:view")
    @GetMapping("{id}")
    public String speechcomment2(@PathVariable("id") Integer sid,ModelMap mmap)
    {
    	mmap.put("sid", sid);
    	return prefix + "/speechcomment";
    }

    /**
     * 查询言论评论列表
     */
    @RequiresPermissions("system:speechcomment:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(WpSpeechComment wpSpeechComment)
    {
        startPage();
        List<WpSpeechComment> list = wpSpeechCommentService.selectWpSpeechCommentList(wpSpeechComment);
        return getDataTable(list);
    }

    /**
     * 导出言论评论列表
     */
    @RequiresPermissions("system:speechcomment:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(WpSpeechComment wpSpeechComment)
    {
        List<WpSpeechComment> list = wpSpeechCommentService.selectWpSpeechCommentList(wpSpeechComment);
        ExcelUtil<WpSpeechComment> util = new ExcelUtil<WpSpeechComment>(WpSpeechComment.class);
        return util.exportExcel(list, "speechcomment");
    }

    /**
     * 新增言论评论
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存言论评论
     */
    @RequiresPermissions("system:speechcomment:add")
    @Log(title = "言论评论", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(WpSpeechComment wpSpeechComment)
    {
        return toAjax(wpSpeechCommentService.insertWpSpeechComment(wpSpeechComment));
    }

    /**
     * 修改言论评论
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap)
    {
        WpSpeechComment wpSpeechComment = wpSpeechCommentService.selectWpSpeechCommentById(id);
        mmap.put("wpSpeechComment", wpSpeechComment);
        return prefix + "/edit";
    }

    /**
     * 修改保存言论评论
     */
    @RequiresPermissions("system:speechcomment:edit")
    @Log(title = "言论评论", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(WpSpeechComment wpSpeechComment)
    {
        return toAjax(wpSpeechCommentService.updateWpSpeechComment(wpSpeechComment));
    }

    /**
     * 删除言论评论
     */
    @RequiresPermissions("system:speechcomment:remove")
    @Log(title = "言论评论", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(wpSpeechCommentService.deleteWpSpeechCommentByIds(ids));
    }
}
