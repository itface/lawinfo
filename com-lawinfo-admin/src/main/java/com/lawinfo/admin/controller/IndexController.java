package com.lawinfo.admin.controller;

import com.lawinfo.admin.system.login.LoginInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by wangrongtao on 15/10/18.
 */
@Controller
@RequestMapping("/admin")
public class IndexController {

    @RequestMapping("/index")
    public String index() {
        return "/admin/admin";
    }

}
