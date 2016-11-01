package com.lawinfo.admin.system.interceptor;

import com.lawinfo.admin.system.login.LoginInfo;
import com.lawinfo.service.constant.SysConstants;
import com.lawinfo.service.org.utils.UserUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by wangrongtao on 15/10/13.
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        if ("/".equals(uri)) {
            response.sendRedirect("/login");
            return false;
        }
        HttpSession session = request.getSession();
        boolean isAvailableLoginUser = LoginInfo.isAvailableLoginUser(session);
        if (!isAvailableLoginUser) {
            if (uri.indexOf("/lawinfo/mobile") == 0) {
                response.sendRedirect("/login/mobile");
            }else{
                response.sendRedirect("/login");
            }
            return false;
        }
        String userid = LoginInfo.getUseridFromSession(session);
        if (SysConstants.SUPER_ADMIN.equals(userid)) {
            return true;
        }
        if (!UserUtils.haveAction(userid,uri)&&!UserUtils.haveMenu(userid, uri)) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
