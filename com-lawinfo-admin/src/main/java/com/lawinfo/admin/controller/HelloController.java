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
    private String CASE_NODE = "[{\"id\":1,\"name\":\"银行委托恰谈\",\"index\":1,\"level\":1,\"level_name\":\"合约恰谈\",\"comments\":{1}},{\"id\":2,\"name\":\"合同已签订\",\"index\":2,\"level\":1,\"level_name\":\"合约恰谈\",\"comments\":{2}}]";
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
