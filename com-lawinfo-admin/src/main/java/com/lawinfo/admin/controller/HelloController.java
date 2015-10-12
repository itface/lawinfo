package com.lawinfo.admin.controller;

import com.lawinfo.service.org.OrgInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by wangrongtao on 15/10/12.
 */
@Controller
public class HelloController {
    @Resource
    private OrgInfoService orgInfoService;
    @RequestMapping("/test")
    public String index() {
        return "index";
    }
    @RequestMapping("/find")
    @ResponseBody
    public String find() {
        return orgInfoService.findAll();
    }
}
