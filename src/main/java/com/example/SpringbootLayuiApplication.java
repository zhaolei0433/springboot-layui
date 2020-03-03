package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @RequestMapping("/login")
    public String login() throws Exception {
        return "login";
    }

    @RequestMapping("/register")
    public String register() throws Exception {
        return "register";
    }
}
