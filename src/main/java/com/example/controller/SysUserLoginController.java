package com.example.controller;

import com.example.global.constants.SystemDefines;
import com.example.security.userdetails.MyUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zhaolei
 * Create: 2020/3/10 15:16
 * Modified By:
 * Description:
 */
@Controller
public class SysUserLoginController {

    private MyUserDetailService myUserDetailService;

    @Autowired
    public SysUserLoginController(MyUserDetailService myUserDetailService) {
        this.myUserDetailService = myUserDetailService;
    }

    @RequestMapping("/index")
    public String index(Model model, HttpServletRequest httpServletRequest) throws Exception {
        model.addAttribute("name","layui后台系统");
        model.addAttribute("username",httpServletRequest.getSession().getAttribute(SystemDefines.SESSION_USER_NAME));
        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() throws Exception {
        return "login1";
    }

    /**
     * session失效策略
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/sessionInvalid", method = RequestMethod.GET)
    public String sessionInvalid() throws Exception {
        return "login1";
    }

    /*@RequestMapping("/login_failure")
    public String loginFailure(Model model){
        model.addAttribute("login_error", "用户名或密码错误");
        System.out.println("haaaaaaaaaaaaaa");
        return "loginPage";
    }*/

    @RequestMapping("/register")
    public String registerPage() throws Exception {
        return "register1";
    }
}
