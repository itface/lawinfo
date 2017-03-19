package com.lawinfo.admin.system.interceptor;

import com.lawinfo.admin.system.login.LoginInfo;
import com.lawinfo.domain.org.User;
import com.lawinfo.service.constant.SysConstants;
import com.lawinfo.service.login.LoginService;
import com.lawinfo.service.org.utils.UserUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by wangrongtao on 15/10/13.
 */
public class LoginInterceptor implements HandlerInterceptor {
    private static Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);
    @Resource
    private LoginService loginService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        logger.info("url is :"+uri);
        if ("/".equals(uri)) {
            response.sendRedirect("/login");
            return false;
        }
        HttpSession session = request.getSession();
        boolean isAvailableLoginUser = LoginInfo.isAvailableLoginUser(session);
        if (uri.indexOf("/login/mobile/wechat/common") == 0) {
            //不校验权限，只要是登录用户就可以防问
            if (isAvailableLoginUser){
                return true;
            }
            String code = request.getParameter("code");
            if (!StringUtils.isEmpty(code)) {
                User user = loginService.wechatOpenIdLogin(code);
                if (user != null) {
                    session.setAttribute("wechatopenid", user.getWechatopenid());
                    if (!StringUtils.isEmpty(user.getUserid())) {
                        LoginInfo.addUseridToSession(session, user.getUserid());
                        return true;
                    }
                }
            }
            response.sendRedirect("/login/mobile");
            return false;
        }
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
