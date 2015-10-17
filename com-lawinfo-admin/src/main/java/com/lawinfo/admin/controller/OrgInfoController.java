package com.lawinfo.admin.controller;

import com.lawinfo.domain.org.OrgInfo;
import com.lawinfo.service.org.OrgInfoService;
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
@RequestMapping("/admin/orginfo")
public class OrgInfoController {

    @Resource
    private OrgInfoService orgInfoService;


    @ResponseBody
    @RequestMapping("/add")
    public String save(OrgInfo orgInfo,BindingResult result)throws Exception{
        int rows = orgInfoService.save(orgInfo);
        return rows+"";
    }
    @ResponseBody
    @RequestMapping("/remove/{id}")
    public String save(@PathVariable long id)throws Exception{
        int rows = orgInfoService.deleteById(id);
        return rows+"";
    }
}
