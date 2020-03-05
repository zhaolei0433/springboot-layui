package com.example;

import com.example.entity.SysUserInfo;
import com.example.service.ISysUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootLayuiApplicationTests {

    @Resource
    private ISysUserService sysUserService;

    @Test
    public void contextLoads() throws Exception {
        System.out.println(sysUserService.getSysUser());
    }

}
