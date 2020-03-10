package com.example.security.myhandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @author zhaolei
 * Create: 2020/3/10 22:36
 * Modified By:
 * Description: session失效监听
 */

@WebListener
public class OpOnlineUserSessionListener implements HttpSessionListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(OpOnlineUserSessionListener.class);

    @Override
    public void sessionCreated(HttpSessionEvent event) {

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        HttpSession session = event.getSession();
        //获取当前用户信息
        System.out.println(session+ "失效！！！！！！！！");
        //移除在线用户

    }
}
