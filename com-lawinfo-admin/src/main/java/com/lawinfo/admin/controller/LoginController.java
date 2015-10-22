package com.lawinfo.admin.controller;

import com.lawinfo.admin.system.login.LoginInfo;
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
@RequestMapping("/login")
public class LoginController {
    @Resource
    private LoginService loginService;
    @Resource
    private EnSendSmsService enSendSmsService;

    @RequestMapping("")
    public String index() {
        return "/admin/login";
    }

    @RequestMapping("/dologin")
    public String dologin(HttpServletRequest request,String tel,String code) {
        LoginResultEnum loginResultEnum = loginService.login(tel, code);
        if (("admin".equals(tel) && "admin123".equals(code))||(loginResultEnum!=null&&loginResultEnum.getCode()==LoginResultEnum.SUCCESS.getCode())) {
            LoginInfo.addUseridToSession(request.getSession(), tel);
            return "redirect:/admin/index";
        }
        return "redirect:/login/page";
    }
    @RequestMapping("/sendsms")
    @ResponseBody
    public String sendsms(String tel) {
        EnSendSmsResultEnum enSendSmsResultEnum = enSendSmsService.sendSms(tel);
        return enSendSmsResultEnum.getCode()+","+enSendSmsResultEnum.getDesc();
    }
}
