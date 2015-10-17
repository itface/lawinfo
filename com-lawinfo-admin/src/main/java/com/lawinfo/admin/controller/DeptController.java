package com.lawinfo.admin.controller;

import com.lawinfo.domain.org.Dept;
import com.lawinfo.service.org.DeptService;
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
@RequestMapping("/admin/dept")
public class DeptController {

    @Resource
    private DeptService deptService;


    @ResponseBody
    @RequestMapping("/add")
    public String save(Dept dept,BindingResult result)throws Exception{
        int rows = deptService.save(dept);
        return rows+"";
    }
    @ResponseBody
    @RequestMapping("/remove/{id}")
    public String save(@PathVariable long id)throws Exception{
        int rows = deptService.deleteById(id);
        return rows+"";
    }
}
