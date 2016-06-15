package com.lawinfo.admin.controller;

import com.lawinfo.admin.system.login.LoginInfo;
import com.lawinfo.domain.login.LoginResult;
import com.lawinfo.service.login.LoginService;
import com.lawinfo.service.sms.EnSendSmsService;
import com.lawinfo.service.sms.enumtype.EnSendSmsResultEnum;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wangrongtao on 15/10/18.
 */
@Controller
@RequestMapping("/login/mobile")
public class LoginMController {
    @Resource
    private LoginService loginService;

    @RequestMapping("")
    public String index() {
        return "/mobile/loginM";
    }
    @RequestMapping("/dologin")
    @ResponseBody
    public LoginResult dologin(HttpServletRequest request,String tel,String code) {
        if (!isPositiveInteger(tel)) {
            LoginResult loginResult = new LoginResult();
            loginResult.setSuccess(false);
            loginResult.setDesc("请输入正确的手机号码");
            loginResult.setRedirecturl("/login/mobile");
            return loginResult;
        }
        LoginResult loginResult = loginService.login(tel, code);
        if (loginResult != null) {
            if (loginResult.isSuccess()) {
                LoginInfo.addUseridToSession(request.getSession(), tel);
                loginResult.setRedirecturl("/lawinfo/mobile");
            }else{
                loginResult.setRedirecturl("/login/mobile");
            }
        }
        return loginResult;
    }
    private boolean isMatch(String regex, String orginal){
        if (orginal == null || orginal.trim().equals("")) {
            return false;
        }
        Pattern pattern = Pattern.compile(regex);
        Matcher isNum = pattern.matcher(orginal);
        return isNum.matches();
    }

    private boolean isPositiveInteger(String orginal) {
        return isMatch("^\\+{0,1}[1-9]\\d*", orginal);
    }
}
