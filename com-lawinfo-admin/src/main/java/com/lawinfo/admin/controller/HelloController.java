package com.lawinfo.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by wangrongtao on 15/10/12.
 */
@Controller
public class HelloController {
    @RequestMapping("/test")
    public String index() {
        return "index";
    }
}
