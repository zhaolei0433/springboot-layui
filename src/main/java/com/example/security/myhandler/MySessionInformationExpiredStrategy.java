package com.example.security.myhandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zhaolei
 * Create: 2020/3/11 12:07
 * Modified By:
 * Description: session失效策略,这里的失效一般为被挤下线
 */
@Component
public class MySessionInformationExpiredStrategy implements SessionInformationExpiredStrategy {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyOpOnlineUserSessionListener.class);

    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {
        HttpServletResponse httpServletResponse = event.getResponse();
        httpServletResponse.sendRedirect("/login");
    }
}
