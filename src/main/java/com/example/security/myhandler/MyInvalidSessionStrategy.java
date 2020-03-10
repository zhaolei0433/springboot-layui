package com.example.security.myhandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.stereotype.Component;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zhaolei
 * Create: 2020/3/10 16:30
 * Modified By:
 * Description: session失效处理
 */
@Component
public class MyInvalidSessionStrategy implements InvalidSessionStrategy {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyAccessDeniedHandler.class);

    @Override
    public void onInvalidSessionDetected(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException, ServletException {
        /*RequestDispatcher dispatcher = httpServletRequest.getRequestDispatcher("/login");
        dispatcher.forward(httpServletRequest, httpServletResponse);*/
        httpServletResponse.sendRedirect("/login");
        LOGGER.error("session 失效");
    }
}
