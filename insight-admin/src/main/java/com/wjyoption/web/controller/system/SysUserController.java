package com.wjyoption.web.controller.system;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.wjyoption.common.annotation.Log;
import com.wjyoption.common.constant.UserConstants;
import com.wjyoption.common.core.controller.BaseController;
import com.wjyoption.common.core.domain.AjaxResult;
import com.wjyoption.common.core.page.TableDataInfo;
import com.wjyoption.common.enums.BusinessType;
import com.wjyoption.common.utils.DateUtils;
import com.wjyoption.common.utils.StringUtils;
import com.wjyoption.common.utils.poi.ExcelUtil;
import com.wjyoption.common.utils.security.Md5Util;
import com.wjyoption.framework.shiro.service.SysPasswordService;
import com.wjyoption.framework.util.ShiroUtils;
import com.wjyoption.system.domain.SysUser;
import com.wjyoption.system.domain.WpUserinfo;
import com.wjyoption.system.service.ISysConfigService;
import com.wjyoption.system.service.ISysPostService;
import com.wjyoption.system.service.ISysRoleService;
import com.wjyoption.system.service.ISysUserService;
import com.wjyoption.system.service.IWpUserinfoService;

/**
 * 用户信息
 * 
 * @author hs
 */
@Controller
@RequestMapping("/system/user")
public class SysUserController extends BaseController
{
    private String prefix = "system/user";

    @Autowired
    private ISysUserService userService;

    @Autowired
    private ISysRoleService roleService;

    @Autowired
    private ISysPostService postService;
    
    @Autowired ISysConfigService configService;

    @Autowired
    private SysPasswordService passwordService;
    
    @Autowired IWpUserinfoService userinfoService;

    @RequiresPermissions("system:user:view")
    @GetMapping()
    public String user()
    {
        return prefix + "/user";
    }

    @RequiresPermissions("system:user:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysUser user)
    {
        startPage();
        List<SysUser> list = userService.selectUserList(user);
        return getDataTable(list);
    }

    @Log(title = "用户管理", businessType = BusinessType.EXPORT)
    @RequiresPermissions("system:user:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SysUser user)
    {
        List<SysUser> list = userService.selectUserList(user);
        ExcelUtil<SysUser> util = new ExcelUtil<SysUser>(SysUser.class);
        return util.exportExcel(list, "用户数据");
    }

    @Log(title = "用户管理", businessType = BusinessType.IMPORT)
    @RequiresPermissions("system:user:import")
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<SysUser> util = new ExcelUtil<SysUser>(SysUser.class);
        List<SysUser> userList = util.importExcel(file.getInputStream());
        Long userid = ShiroUtils.getSysUser().getUserId();
        String message = userService.importUser(userList, updateSupport, userid);
        return AjaxResult.success(message);
    }

    @RequiresPermissions("system:user:view")
    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate()
    {
        ExcelUtil<SysUser> util = new ExcelUtil<SysUser>(SysUser.class);
        return util.importTemplateExcel("用户数据");
    }

    /**
     * 新增用户
     */
    @GetMapping("/add")
    public String add(ModelMap mmap)
    {
        mmap.put("roles", roleService.selectRoleAll());
        mmap.put("posts", postService.selectPostAll());
        return prefix + "/add";
    }

    /**
     * 新增保存用户
     */
    @RequiresPermissions("system:user:add")
    @Log(title = "用户管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@Validated SysUser user)
    {
        if (UserConstants.USER_NAME_NOT_UNIQUE.equals(userService.checkLoginNameUnique(user.getLoginName())))
        {
            return error("新增用户'" + user.getLoginName() + "'失败，登录账号已存在");
        }
        else if (UserConstants.USER_PHONE_NOT_UNIQUE.equals(userService.checkPhoneUnique(user)))
        {
            return error("新增用户'" + user.getLoginName() + "'失败，手机号码已存在");
        }
        else if (UserConstants.USER_EMAIL_NOT_UNIQUE.equals(userService.checkEmailUnique(user)))
        {
            return error("新增用户'" + user.getLoginName() + "'失败，邮箱账号已存在");
        }
        if(user.getUid() != null){
        	WpUserinfo userinfo = this.userinfoService.selectWpUserinfoById(user.getUid());
        	if(userinfo == null || userinfo.getOtype() != 101){
        		return error("用户不存在或不是代理商");
        	}
        }
        String password = user.getPassword();
        user.setSalt(ShiroUtils.randomSalt());
        user.setPassword(passwordService.encryptPassword(user.getLoginName(), user.getPassword(), user.getSalt()));
        user.setCreateBy(ShiroUtils.getUserId());
        if(org.apache.commons.lang3.StringUtils.isBlank(user.getPhonenumber())){
        	user.setPhonenumber(user.getLoginName());
        }
        if(user.getUid() == null){
        	WpUserinfo userinfo = this.userinfoService.selectUserByPhone(user.getLoginName());
        	if(userinfo != null){
        		return AjaxResult.error("销售已存在");
        	}
        	WpUserinfo wpUserinfo = new WpUserinfo();
        	wpUserinfo.setUsername(user.getUserName());
        	wpUserinfo.setUtel(user.getLoginName());
        	wpUserinfo.setEmail(user.getEmail());
        	wpUserinfo.setOid(1);
        	wpUserinfo.setManagername("admin");
        	wpUserinfo.setOtype(101);
        	wpUserinfo.setIstop(1);
        	wpUserinfo.setNormaltype(2);
        	wpUserinfo.setNickname(wpUserinfo.getUsername());
        	wpUserinfo.setUtime(DateUtils.getNowSecond());
        	wpUserinfo.setUpwd(Md5Util.MD5(password + wpUserinfo.getUtime()));
        	this.userinfoService.insertWpUserinfo(wpUserinfo);
        	wpUserinfo.setTopid(wpUserinfo.getUid());
        	this.userinfoService.updateWpUserinfo(wpUserinfo);
        	user.setUid(wpUserinfo.getUid());
        }
        return toAjax(userService.insertUser(user));
    }

    /**
     * 修改用户
     */
    @GetMapping("/edit/{userId}")
    public String edit(@PathVariable("userId") Long userId, ModelMap mmap)
    {
        mmap.put("user", userService.selectUserById(userId));
        mmap.put("roles", roleService.selectRolesByUserId(userId));
        mmap.put("posts", postService.selectPostsByUserId(userId));
        return prefix + "/edit";
    }

    /**
     * 修改保存用户
     */
    @RequiresPermissions("system:user:edit")
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated SysUser user)
    {
        if (StringUtils.isNotNull(user.getUserId()) && SysUser.isAdmin(user.getUserId()))
        {
            return error("不允许修改超级管理员用户");
        }
        else if (UserConstants.USER_PHONE_NOT_UNIQUE.equals(userService.checkPhoneUnique(user)))
        {
            return error("修改用户'" + user.getLoginName() + "'失败，手机号码已存在");
        }
        else if (UserConstants.USER_EMAIL_NOT_UNIQUE.equals(userService.checkEmailUnique(user)))
        {
            return error("修改用户'" + user.getLoginName() + "'失败，邮箱账号已存在");
        }
        if(user.getUid() != null){
        	WpUserinfo userinfo = this.userinfoService.selectWpUserinfoById(user.getUid());
        	if(userinfo == null || userinfo.getOtype() != 101){
        		return error("用户不存在或不是代理商");
        	}
        }
        user.setUpdateBy(ShiroUtils.getUserId());
        return toAjax(userService.updateUser(user));
    }

    @RequiresPermissions("system:user:resetPwd")
    @Log(title = "重置密码", businessType = BusinessType.UPDATE)
    @GetMapping("/resetPwd/{userId}")
    public String resetPwd(@PathVariable("userId") Long userId, ModelMap mmap)
    {
        mmap.put("user", userService.selectUserById(userId));
        return prefix + "/resetPwd";
    }

    @RequiresPermissions("system:user:resetPwd")
    @Log(title = "重置密码", businessType = BusinessType.UPDATE)
    @PostMapping("/resetPwd")
    @ResponseBody
    public AjaxResult resetPwdSave(SysUser user)
    {
        user.setSalt(ShiroUtils.randomSalt());
        user.setPassword(passwordService.encryptPassword(user.getLoginName(), user.getPassword(), user.getSalt()));
        if (userService.resetUserPwd(user) > 0)
        {
            if (ShiroUtils.getUserId() == user.getUserId())
            {
                ShiroUtils.setSysUser(userService.selectUserById(user.getUserId()));
            }
            return success();
        }
        return error();
    }

    @RequiresPermissions("system:user:remove")
    @Log(title = "用户管理", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        try
        {
            return toAjax(userService.deleteUserByIds(ids));
        }
        catch (Exception e)
        {
            return error(e.getMessage());
        }
    }

    /**
     * 校验用户名
     */
    @PostMapping("/checkLoginNameUnique")
    @ResponseBody
    public String checkLoginNameUnique(SysUser user)
    {
        return userService.checkLoginNameUnique(user.getLoginName());
    }

    /**
     * 校验手机号码
     */
    @PostMapping("/checkPhoneUnique")
    @ResponseBody
    public String checkPhoneUnique(SysUser user)
    {
        return userService.checkPhoneUnique(user);
    }

    /**
     * 校验email邮箱
     */
    @PostMapping("/checkEmailUnique")
    @ResponseBody
    public String checkEmailUnique(SysUser user)
    {
        return userService.checkEmailUnique(user);
    }

    /**
     * 用户状态修改
     */
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("system:user:edit")
    @PostMapping("/changeStatus")
    @ResponseBody
    public AjaxResult changeStatus(SysUser user)
    {
        return toAjax(userService.changeStatus(user));
    }
    
    
    
    
    
//    /**
//     * 查询用户是否有团队1/2权限
//     */
//    @PostMapping("/haveTeamPower")
//    @ResponseBody
//    public AjaxResult haveTeamPower()
//    {
//    	String val = this.configService.selectConfigByKey("sys.index.ignorenotifyuserid");
//    	if(StringUtils.contains(val, ","+ShiroUtils.getUserId()+",")){
//    		return AjaxResult.error();
//    	}
//    	boolean permitted1 = ShiroUtils.getSubject().isPermitted(Constants.TEAM1_MENU_PERMS);
//    	boolean permitted2 = ShiroUtils.getSubject().isPermitted(Constants.TEAM2_MENU_PERMS);
//    	if(permitted1 || permitted2){
//    		return AjaxResult.success();
//    	}
//    	return AjaxResult.error();
//    }
    
}