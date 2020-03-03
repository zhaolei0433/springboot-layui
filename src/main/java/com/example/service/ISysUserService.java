package com.example.service;

import com.example.controller.request.AddSysUserReq;
import com.example.entity.PermissionInfo;
import com.example.entity.SysUserInfo;

import java.util.List;

/**
 * @author zhaolei
 * Create: 2020/2/25 13:45
 * Modified By:
 * Description:
 */
public interface ISysUserService {

    SysUserInfo addSysUser(AddSysUserReq req) throws Exception;

    List<SysUserInfo> findUserList() throws Exception;

    /**
     * 获取所有的资源数据
     * @return
     */
    public List<PermissionInfo> queryAllData();

    /**
     * 根据资源id获取权限名称
     * @param id
     * @return
     */
    public List<String> getRoleNameByPermissionInfoId(Integer id);
}
