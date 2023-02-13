package com.wjyoption.web.controller.system;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wjyoption.common.config.Global;
import com.wjyoption.common.core.controller.BaseController;
import com.wjyoption.common.core.domain.AjaxResult;
import com.wjyoption.common.utils.ServletUtils;
import com.wjyoption.common.utils.StringUtils;
import com.wjyoption.framework.util.ShiroUtils;
import com.wjyoption.system.domain.SysUser;

/**
 * 登录验证
 * 
 * @author hs
 */
@Controller
public class SysLoginController extends BaseController
{
    @GetMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response,ModelMap mmap)
    {
        // 如果是Ajax请求，返回Json字符串。
        if (ServletUtils.isAjaxRequest(request))
        {
            return ServletUtils.renderString(response, "{\"code\":\"1\",\"msg\":\"未登录或登录超时。请重新登录\"}");
        }
        mmap.put("copyrightYear", Global.getCopyrightYear());
        mmap.put("name", Global.getName());

        return "login";
    }

    @PostMapping("/login")
    @ResponseBody
    public AjaxResult ajaxLogin(String username, String password, Boolean rememberMe)
    {
        UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMe);
        Subject subject = SecurityUtils.getSubject();
        try
        {
            subject.login(token);
            SysUser user = ShiroUtils.getSysUser();
            Long userId = user.getUserId();
            Long deptId = user.getDeptId();
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("userId", userId);
            map.put("deptId", deptId);
//            System.out.println("-----------------");
//            System.out.println(JSON.toJSONString(user));
            return AjaxResult.success(map);
        }
        catch (AuthenticationException e)
        {
            String msg = "用户或密码错误";
            if (StringUtils.isNotEmpty(e.getMessage()))
            {
                msg = e.getMessage();
            }
            return error(msg);
        }
    }

    @GetMapping("/unauth")
    public String unauth()
    {
        return "error/unauth";
    }
}
