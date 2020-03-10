package com.example.security.myhandler;

import com.example.global.constants.ResponseConstants;
import com.example.global.constants.SystemDefines;
import com.example.model.Result;
import com.example.utils.GsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zhaolei
 * Create: 2020/3/9 15:05
 * Modified By:
 * Description: 无权限处理
 */
@Component
public class MyAccessDeniedHandler implements AccessDeniedHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyAccessDeniedHandler.class);

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException exception) throws IOException, ServletException {
        httpServletResponse.setStatus(HttpStatus.OK.value());
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        httpServletResponse.getWriter().write(GsonUtil.GsonString(new Result<>(ResponseConstants.ERROR_CODE_00403,ResponseConstants.ERROR_CODE_00403_MSG)));
        LOGGER.error(httpServletRequest.getSession().getAttribute(SystemDefines.SESSION_USER_NAME)+" ：无权限访问，"+exception.getMessage());
    }
}
