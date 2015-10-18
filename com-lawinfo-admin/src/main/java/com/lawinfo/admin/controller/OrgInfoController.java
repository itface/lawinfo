package com.lawinfo.admin.controller;

import com.lawinfo.admin.system.login.LoginInfo;
import com.lawinfo.domain.org.OrgInfo;
import com.lawinfo.service.org.OrgInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
    public String save(HttpServletRequest request,OrgInfo orgInfo,BindingResult result)throws Exception{
        String userid = LoginInfo.getUseridFromSession(request.getSession());
        if (orgInfo != null) {
            orgInfo.setOptuserid(userid);
            int rows = orgInfoService.save(orgInfo);
            return rows+"";
        }
        return null;
    }
    @ResponseBody
    @RequestMapping("/find")
    public List<OrgInfo> find()throws Exception{
        List<OrgInfo> list = orgInfoService.findAll();
        return list;
    }
    @ResponseBody
    @RequestMapping("/remove/{id}")
    public String remove(@PathVariable long id)throws Exception{
        int rows = orgInfoService.deleteById(id);
        return rows+"";
    }
}
