package com.lawinfo.admin.controller;

import com.lawinfo.domain.org.Role;
import com.lawinfo.service.org.RoleService;
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
@RequestMapping("/admin/role")
public class RoleController {

    @Resource
    private RoleService roleService;


    @ResponseBody
    @RequestMapping("/add")
    public String save(Role role,BindingResult result)throws Exception{
        int rows = roleService.save(role);
        return rows+"";
    }
    @ResponseBody
    @RequestMapping("/remove/{id}")
    public String remove(@PathVariable long id)throws Exception{
        int rows = roleService.deleteById(id);
        return rows+"";
    }
}
