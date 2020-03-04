package com.example.security.myHandler;

import com.example.global.constants.SystemDefines;
import com.example.security.userDetails.MyUserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zhaolei
 * Create: 2019/11/20 17:03
 * Modified By:
 * Description: 用户登录系统成功后 需要做的业务操作 explain 当用户登录系统成功后则会进入到此类并执行相关业务
 */
@Component
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyAuthenticationSuccessHandler.class);

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        // 获得授权后可得到用户信息
        MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
        httpServletRequest.getSession().setAttribute(SystemDefines.SESSION_USER_NAME, userDetails.getUsername());

        /*
         * RequestDispatcher.forward() 方法	        HttpServletResponse.sendRedirect()方法
         * 运行在服务端	                            运行在客户端
         * 浏览器url地址不变化                         浏览器url地址变化　
         */
        //RequestDispatcher dispatcher = httpServletRequest.getRequestDispatcher("/index");
        //dispatcher.forward(httpServletRequest, httpServletResponse);
        //根据业务需求哪种方法
        httpServletResponse.sendRedirect("/index");
    }
}
