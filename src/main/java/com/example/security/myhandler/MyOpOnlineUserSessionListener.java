package com.example.security.myhandler;

import com.example.global.constants.SystemDefines;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @author zhaolei
 * Create: 2020/3/10 22:36
 * Modified By:
 * Description: session失效监听
 */
@Component
public class MyOpOnlineUserSessionListener implements HttpSessionListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyOpOnlineUserSessionListener.class);

    private SessionRegistry sessionRegistry;

    @Autowired
    public MyOpOnlineUserSessionListener(SessionRegistry sessionRegistry) {
        this.sessionRegistry = sessionRegistry;
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        HttpSession session = event.getSession();
        String username = (String) session.getAttribute(SystemDefines.SESSION_USER_NAME);
        if (username != null && !username.equals("")) {
            LOGGER.info("servlet中session失效，设置当前"+username+"在security注册器（sessionRegistry）中session失效:"+session.getId());
            sessionRegistry.removeSessionInformation(session.getId());
        }
    }

    @Override
    public void sessionCreated(HttpSessionEvent event) {

    }
}
