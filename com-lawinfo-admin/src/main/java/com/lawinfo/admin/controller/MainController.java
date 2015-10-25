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
@RequestMapping("/lawinfo")
public class MainController {


    @RequestMapping("")
    public String index() {
        return "/admin/login";
    }
}
