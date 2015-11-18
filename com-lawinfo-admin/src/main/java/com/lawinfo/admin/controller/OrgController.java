package com.lawinfo.admin.controller;

import com.lawinfo.domain.org.Org;
import com.lawinfo.domain.org.vo.OrgVo;
import com.lawinfo.service.org.OrgService;
import com.lawinfo.service.org.OrgUserService;
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
@RequestMapping("/lawinfo/admin/org")
public class OrgController {

    @Resource
    private OrgService orgService;
    @Resource
    private OrgUserService orgUserService;


    @ResponseBody
    @RequestMapping("/add")
    public int save(HttpServletRequest request,Org org,BindingResult result)throws Exception{
        if (org != null) {
            int rows = orgService.save(org);
            return rows;
        }
        return 0;
    }
    @ResponseBody
    @RequestMapping("/findtree")
    public List<OrgVo> findtree()throws Exception{
        List<OrgVo> list = orgService.findOrgTree();
        return list;
    }
    @ResponseBody
    @RequestMapping("/remove")
    public int remove(long id)throws Exception{
        int rows = orgUserService.deleteOrgAndUserByOrgid(id);
        return rows;
    }
}
