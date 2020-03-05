package com.example;

import com.example.global.constants.SystemDefines;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@SpringBootApplication
public class SpringbootLayuiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootLayuiApplication.class, args);
    }

    @RequestMapping("/index")
    public String index(Model model, HttpServletRequest httpServletRequest) throws Exception {
        model.addAttribute("name","layui后台系统");
        model.addAttribute("username",httpServletRequest.getSession().getAttribute(SystemDefines.SESSION_USER_NAME));
        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() throws Exception {
        return "login1_1";
    }

    @RequestMapping("/login_failure")
    public String loginFailure(Model model){
        model.addAttribute("login_error", "用户名或密码错误");
        return "login1_1";
    }

    @RequestMapping("/register")
    public String registerPage() throws Exception {
        return "register1";
    }
}
