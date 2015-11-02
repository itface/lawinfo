package com.lawinfo.admin.controller;

import com.lawinfo.admin.system.login.LoginInfo;
import com.lawinfo.domain.login.LoginResult;
import com.lawinfo.service.login.LoginService;
import com.lawinfo.service.login.enumtype.LoginResultEnum;
import com.lawinfo.service.sms.EnSendSmsService;
import com.lawinfo.service.sms.enumtype.EnSendSmsResultEnum;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by wangrongtao on 15/10/18.
 */
@Controller
public class LoginController {
    @Resource
    private LoginService loginService;
    @Resource
    private EnSendSmsService enSendSmsService;

    @RequestMapping("/login")
    public String index() {
        return "/login";
    }
    @RequestMapping("/login/dologin")
    @ResponseBody
    public LoginResult dologin(HttpServletRequest request,String tel,String code) {
        LoginResult loginResult = loginService.login(tel, code);
        if (loginResult != null && loginResult.isSuccess()) {
            LoginInfo.addUseridToSession(request.getSession(), tel);
        }
        return loginResult;
    }
    @RequestMapping("/login/sendsms")
    @ResponseBody
    public String sendsms(String tel) {
        EnSendSmsResultEnum enSendSmsResultEnum = enSendSmsService.sendSms(tel);
        return EnSendSmsResultEnum.toJsonStr(enSendSmsResultEnum);
    }
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        LoginInfo.removeLoginUser(request.getSession());
        return "/login";
    }
}
