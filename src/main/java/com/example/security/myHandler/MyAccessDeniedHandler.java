package com.example.security.myHandler;

import com.example.global.constants.ResponseConstants;
import com.example.model.Result;
import com.example.utils.GsonUtil;
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
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        httpServletResponse.getWriter().write(GsonUtil.GsonString(new Result<>(ResponseConstants.ERROR_CODE_00403,ResponseConstants.ERROR_CODE_00403_MSG)));
    }
}
