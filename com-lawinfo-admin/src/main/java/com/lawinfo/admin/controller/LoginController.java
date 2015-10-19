package com.lawinfo.admin.controller;

import com.lawinfo.admin.system.login.LoginInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by wangrongtao on 15/10/18.
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    @RequestMapping("")
    public String index() {
        return "/admin/login";
    }

    @RequestMapping("/dologin")
    public String dologin(HttpServletRequest request,String tel,String code) {
        if ("admin".equals(tel)&&"admin123".equals(code)) {
            LoginInfo.addUseridToSession(request.getSession(),tel);
            return "redirect:/admin/index";
        }
        return "redirect:/login/page";
    }
}
