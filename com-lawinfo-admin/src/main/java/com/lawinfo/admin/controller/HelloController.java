package com.lawinfo.admin.controller;

import com.lawinfo.admin.system.login.LoginInfo;
import com.lawinfo.service.org.OrgInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by wangrongtao on 15/10/12.
 */
@Controller
public class HelloController {
    @Resource
    private OrgInfoService orgInfoService;
    @RequestMapping("/admin/test")
    public String index() {
        return "index";
    }
    @RequestMapping("/error")
    public String error() {
        return "/error/error";
    }
    @RequestMapping("/find")
    @ResponseBody
    public String find() {
        return orgInfoService.findAll();
    }
    @RequestMapping("/login")
    @ResponseBody
    public String login(HttpServletRequest request) {
        boolean flag = LoginInfo.addUseridToSession(request.getSession(),"itface");
        return flag?"success":"fail";
    }
}
