package com.example.controller;

import com.example.controller.request.AddSysUserReq;
import com.example.entity.SysUserInfo;
import com.example.global.constants.SwaggerUIConstants;
import com.example.global.constants.SystemDefines;
import com.example.model.Result;
import com.example.service.ISysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhaolei
 * Create: 2020/2/25 13:46
 * Modified By:
 * Description:
 */
@Api(tags = SwaggerUIConstants.SYSUSER_PC_API)
@RestController
@RequestMapping(value = "sysUser")
public class SysUserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SysUserController.class);

    private ISysUserService sysUserService;

    @Autowired
    public SysUserController(ISysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }

    @ApiOperation(value = "添加系统用户")
    @ApiImplicitParam(name = "req", value = "系统用户参数", dataType = "AddSysUserReq", required = true, paramType = "body")
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public Result<SysUserInfo> addSysUser(@RequestBody AddSysUserReq req) throws Exception {
        return new Result<>(sysUserService.addSysUser(req));
    }
}
