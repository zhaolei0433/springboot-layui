package com.example.security.myHandler;

import com.example.global.constants.SystemDefines;
import com.example.security.userDetails.MyUserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zhaolei
 * Create: 2020/3/4 17:53
 * Modified By:
 * Description:
 */
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyAuthenticationSuccessHandler.class);

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setHeader("zhaolei","123");
        httpServletResponse.
        //httpServletResponse.sendRedirect("/login_failure");
    }
}
