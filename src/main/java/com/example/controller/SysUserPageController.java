package com.example.controller;

import io.swagger.models.auth.In;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * @author zhaolei
 * Create: 2020/2/25 17:13
 * Modified By:
 * Description:
 */
@Controller
@RequestMapping(value = "sysUserPage")
public class SysUserPageController {

    @GetMapping("/")
    public String test(Map<String,Object> paramMap) throws Exception {
        paramMap.put("name","zhaolei");
        return "test";
    }
}
