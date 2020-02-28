package com.example.controller;

import com.example.entity.SysUserInfo;
import com.example.service.ISysUserService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author zhaolei
 * Create: 2020/2/25 17:13
 * Modified By:
 * Description:
 */
@Controller
@RequestMapping(value = "systemManagePage")
public class SysUserPageController {

    private ISysUserService sysUserService;

    @Autowired
    public SysUserPageController(ISysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }

    /**
     * 进入用户列表页面，并返回用户列表数据
     * @param model
     * @return
     * @throws Exception
     */
    @GetMapping("/sysUserList")
    public String sysUserList(Model model) throws Exception {
        //List<SysUserInfo> sysUserInfos = sysUserService.findUserList();
        /*Map<String,SysUserInfo> sysUserInfoMap = sysUserInfos.stream().collect(Collectors.toMap(sysUserInfo -> sysUserInfo.getId().toString(), sysUserInfo -> sysUserInfo));
        model.addAllAttributes(sysUserInfoMap);*/
        //model.addAttribute("sysUserInfo",sysUserInfos.get(0));
        return "systemManage/sysUserList";
    }

    @GetMapping("/sysRoleList")
    public String sysRoleList() throws Exception {

        return "systemManage/sysRoleList";
    }

    @GetMapping("/sysAuthList")
    public String sysAuthList() throws Exception {

        return "systemManage/sysAuthList";
    }

    @GetMapping("/sysLogList")
    public String sysLogList() throws Exception {

        return "systemManage/sysLogList";
    }
}
