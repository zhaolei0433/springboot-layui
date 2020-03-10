package com.example.security.myhandler;

import com.example.global.constants.ResponseConstants;
import com.example.model.Result;
import com.example.utils.GsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zhaolei
 * Create: 2020/3/4 17:53
 * Modified By:
 * Description: 用户登录失败处理
 */
@Component
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyAuthenticationFailureHandler.class);

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException exception) throws IOException, ServletException {
        httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        Result<String> result = new Result<>();

        if (exception instanceof UsernameNotFoundException || exception instanceof BadCredentialsException) {
            result.setCode(ResponseConstants.ERROR_CODE_0000401);
            result.setMsg(ResponseConstants.ERROR_CODE_0000401_MSG);
        }if (exception instanceof AccountExpiredException || exception instanceof LockedException) {
            result.setCode(ResponseConstants.ERROR_CODE_00004011);
            result.setMsg(ResponseConstants.ERROR_CODE_00004011_MSG);
        }if (exception instanceof DisabledException) {
            result.setCode(ResponseConstants.ERROR_CODE_00004012);
            result.setMsg(ResponseConstants.ERROR_CODE_00004012_MSG);
        }if (exception instanceof SessionAuthenticationException) {
            result.setCode(ResponseConstants.ERROR_CODE_00004013);
            result.setMsg(ResponseConstants.ERROR_CODE_00004013_MSG);
        }

        httpServletResponse.getWriter().write(GsonUtil.GsonString(result));
        LOGGER.error("登陆失败，"+exception);
    }
}
