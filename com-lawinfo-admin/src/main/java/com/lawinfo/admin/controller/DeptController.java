package com.lawinfo.admin.controller;

import com.lawinfo.admin.system.login.LoginInfo;
import com.lawinfo.domain.org.Dept;
import com.lawinfo.domain.org.OrgInfo;
import com.lawinfo.domain.org.query.DeptQuery;
import com.lawinfo.domain.org.vo.DeptVo;
import com.lawinfo.service.org.DeptService;
import com.lawinfo.service.org.OrgInfoDeptService;
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
@RequestMapping("/admin/dept")
public class DeptController {

    @Resource
    private DeptService deptService;
    @Resource
    private OrgInfoDeptService orgInfoDeptService;


    @ResponseBody
    @RequestMapping("/add")
    public String save(HttpServletRequest request,Dept dept,BindingResult result)throws Exception {
        String userid = LoginInfo.getUseridFromSession(request.getSession());
        if (dept != null) {
            dept.setOptuserid(userid);
            int rows = deptService.save(dept);
            return rows + "";
        }
        return null;
    }
    @ResponseBody
    @RequestMapping("/findbyorgid/{orgid}")
    public List<Dept> findDeptByOrgid(@PathVariable long orgid)throws Exception{
        DeptQuery deptQuery = new DeptQuery();
        deptQuery.setOrgid(orgid);
        List<Dept> list = deptService.findList(deptQuery);
        return list;
    }
    @ResponseBody
    @RequestMapping("/remove/{id}")
    public String remove(@PathVariable long id)throws Exception{
        int rows = deptService.deleteById(id);
        return rows+"";
    }
    @ResponseBody
    @RequestMapping("/find")
    public List<DeptVo> find()throws Exception{
        List<DeptVo> list = orgInfoDeptService.findDeptVo();
        return list;
    }
}
