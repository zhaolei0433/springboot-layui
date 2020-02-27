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
@RequestMapping(value = "systemManagePage")
public class SysUserPageController {

    @GetMapping("/sysUserList")
    public String sysUserList() throws Exception {

        return "systemManage/sysUserList";
    }

    @GetMapping("/sysRoleList")
    public String sysRoleList() throws Exception {

        return "systemManage/sysRoleList";
    }

    @GetMapping("/sysAuthList")
    public String test() throws Exception {

        return "systemManage/sysAuthList";
    }
}
