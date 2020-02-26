package com.example.service;

import com.example.controller.request.AddSysUserReq;
import com.example.entity.SysUserInfo;

/**
 * @author zhaolei
 * Create: 2020/2/25 13:45
 * Modified By:
 * Description:
 */
public interface ISysUserService {

    SysUserInfo addSysUser(AddSysUserReq req) throws Exception;
}
