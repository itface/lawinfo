package com.lawinfo.admin.system;

import com.lawinfo.admin.system.login.LoginInfo;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Created by wangrongtao on 15/10/13.
 */
public class SessionManagerListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        HttpSession session= httpSessionEvent.getSession();
        String userid = LoginInfo.getUseridFromSession(session);
        if (userid != null) {
            LoginInfo.removeLoginUser(userid);
        }
    }
}
