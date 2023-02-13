package com.wjyoption.web.controller.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.wjyoption.common.config.Global;
import com.wjyoption.common.core.controller.BaseController;
import com.wjyoption.framework.util.ShiroUtils;
import com.wjyoption.system.domain.SysMenu;
import com.wjyoption.system.domain.SysUser;
import com.wjyoption.system.service.ISysMenuService;
import com.wjyoption.system.service.ISysUserService;

/**
 * 首页 业务处理
 * 
 * @author hs
 */
@Controller
public class SysIndexController extends BaseController
{
    @Autowired
    private ISysMenuService menuService;
    
    @Autowired ISysUserService userService;

    // 系统首页
    @GetMapping("/index")
    public String index(ModelMap mmap)
    {
        // 取身份信息
        SysUser user = ShiroUtils.getSysUser();
        // 根据用户id取出菜单
        List<SysMenu> menus = menuService.selectMenusByUser(user);
        mmap.put("menus", menus);
        mmap.put("user", user);
        mmap.put("name", Global.getName());
        mmap.put("copyrightYear", Global.getCopyrightYear());
        mmap.put("demoEnabled", Global.isDemoEnabled());
        return "index";
    }

    // 系统介绍
    @GetMapping("/system/main")
    public String main(ModelMap mmap)
    {
        mmap.put("version", Global.getVersion());
        mmap.put("name", Global.getName());
        return "main";
    }
    // 系统介绍
    @GetMapping("/system/homepage")
    public String homepage(ModelMap mmap)
    {
//    	SysDeptUser dept = this.deptUserService.selectDeptByUserId(ShiroUtils.getUserId());
    	SysUser dept = this.userService.selectUserById(ShiroUtils.getUserId());
//    	if(dept.getLoginName().contains("-")){
//    		dept.setLoginName1(dept.getLoginName().substring(0,3));
//    		dept.setLoginName(dept.getLoginName().substring(3));
//    	}
    	mmap.put("user", dept);
    	return "homepage";
    }
}
