package com.lawinfo.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by wangrongtao on 15/10/18.
 */
@Controller
@RequestMapping("/lawinfo/admin/menu")
public class MenuController {


    @RequestMapping("")
    public String index() {
        return "/admin/menu";
    }
}
