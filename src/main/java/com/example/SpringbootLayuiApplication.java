package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@SpringBootApplication
public class SpringbootLayuiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootLayuiApplication.class, args);
    }

    @RequestMapping("/")
    public String index(Model model) throws Exception {
        model.addAttribute("name","layui后台系统");
        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() throws Exception {
        return "login1";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam("userName") String userName, @RequestParam("password") Integer password) throws Exception {
        System.out.println(userName);
        System.out.println(password);
        return "/index";
    }

    @RequestMapping("/register")
    public String registerPage() throws Exception {
        return "register1";
    }
}
