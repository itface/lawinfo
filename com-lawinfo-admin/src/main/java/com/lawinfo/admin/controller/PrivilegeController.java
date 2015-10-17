package com.lawinfo.admin.controller;

import com.lawinfo.domain.org.Privilege;
import com.lawinfo.service.org.PrivilegeService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by wangrongtao on 15/10/17.
 */
@Controller
@RequestMapping("/admin/privilege")
public class PrivilegeController {

    @Resource
    private PrivilegeService privilegeService;


    @ResponseBody
    @RequestMapping("/add")
    public String save(Privilege privilege,BindingResult result)throws Exception{
        int rows = privilegeService.save(privilege);
        return rows+"";
    }
    @ResponseBody
    @RequestMapping("/remove/{id}")
    public String save(@PathVariable long id)throws Exception{
        int rows = privilegeService.deleteById(id);
        return rows+"";
    }
}
