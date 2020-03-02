package com.example.service.impl;

import com.example.controller.request.AddSysUserReq;
import com.example.dao.ISysUserRepository;
import com.example.entity.SysUserInfo;
import com.example.global.constants.SystemDefines;
import com.example.service.ISysUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author zhaolei
 * Create: 2020/2/25 13:45
 * Modified By:
 * Description:
 */
@Service
public class SysUserServiceImpl implements ISysUserService {

    private ISysUserRepository sysUserRepository;
    //private static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

    @Autowired
    public SysUserServiceImpl(ISysUserRepository sysUserRepository) {
        this.sysUserRepository = sysUserRepository;
    }

    @Override
    public SysUserInfo addSysUser(AddSysUserReq req) throws Exception {
        SysUserInfo sysUser = new SysUserInfo();
        BeanUtils.copyProperties(req, sysUser);
        sysUser.setCreateTime(new SimpleDateFormat(SystemDefines.SIMPLE_DATE_FORMAT).format(new Date()));
        sysUser.setUserType(SystemDefines.USER_TYPE_NORMAL_ADMIN);
        //  sysUser.setPassword(PASSWORD_ENCODER.encode(req.getPassword()));
        return sysUserRepository.save(sysUser);
    }

    @Override
    public List<SysUserInfo> findUserList() throws Exception {
        return (List<SysUserInfo>) sysUserRepository.findAll();
    }
}
