package com.lawinfo.admin.controller;

import com.lawinfo.admin.system.login.LoginInfo;
import com.lawinfo.domain.org.OrgInfo;
import com.lawinfo.domain.org.query.OrgInfoQuery;
import com.lawinfo.service.org.CaseProgressService;
import com.lawinfo.service.org.OrgInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by wangrongtao on 15/10/12.
 */
//@Controller
public class HelloController {
    /*private String CASE_NODE = "[{\"id\":1,\"name\":\"银行委托恰谈\",\"index\":1,\"level\":1,\"level_name\":\"合约恰谈\",\"comments\":{1}},{\"id\":2,\"name\":\"合同已签订\",\"index\":2,\"level\":1,\"level_name\":\"合约恰谈\",\"comments\":{2}}]";
    @Resource
    private OrgInfoService orgInfoService;
    @Resource
    private CaseProgressService caseProgressService;
    @RequestMapping("/admin/test")
    public String index() {
        return "index";
    }
    @RequestMapping("/error")
    public String error() {
        return "/error/error";
    }
    @RequestMapping("/find")
    public String find(ModelMap modelMap,OrgInfoQuery orgInfoQuery, BindingResult result)throws Exception{
//        if (result.hasErrors())
        List<OrgInfo> list = orgInfoService.findList(orgInfoQuery);
        modelMap.put("list", list);
        return "index";
    }
    @RequestMapping("/login")
    @ResponseBody
    public String login(HttpServletRequest request) {
        boolean flag = LoginInfo.addUseridToSession(request.getSession(),"itface");
        return flag?"success":"fail";
    }
    @RequestMapping("/query")
    @ResponseBody
    public String query() {
        return caseProgressService.findCaseProgressWithNodeInfo("cs001");
    }*/
}
