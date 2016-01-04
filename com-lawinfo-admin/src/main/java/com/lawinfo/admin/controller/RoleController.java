package com.lawinfo.admin.controller;

import com.lawinfo.domain.common.EasyuiTree;
import com.lawinfo.domain.org.Role;
import com.lawinfo.domain.org.vo.MenuVo;
import com.lawinfo.domain.org.vo.RoleTreeVo;
import com.lawinfo.domain.org.vo.RoleVo;
import com.lawinfo.service.org.RoleService;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
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
@RequestMapping("/lawinfo/admin/role")
public class RoleController {
    @Resource
    private RoleService roleService;

    @ResponseBody
    @RequestMapping("/add")
    public int save(HttpServletRequest request,Long id,String rolename,String roletag,String menuids,String actionids)throws Exception{
        if (!StringUtils.isEmpty(rolename)) {
            int rows = 0;
            if (id==null||id==0) {
                rows = roleService.save(rolename,roletag,menuids,actionids);
            }else{
                rows = roleService.update(id,rolename,roletag,menuids,actionids);
            }
            return rows;
        }
        return 0;
    }
    @ResponseBody
    @RequestMapping("/remove")
    public int remove(long id)throws Exception{
        int rows = roleService.deleteById(id);
        return rows;
    }
    @ResponseBody
    @RequestMapping("/find")
    public List<RoleVo> find()throws Exception{
        List<RoleVo> list = roleService.findAllVo();
        return list;
    }
    @ResponseBody
    @RequestMapping("/findbyid")
    public RoleVo findbyid(long id)throws Exception{
        RoleVo roleVo = roleService.findVoById(id);
        return roleVo;
    }
    @ResponseBody
    @RequestMapping("/findtree")
    public List<RoleTreeVo> findtree()throws Exception{
        List<RoleTreeVo> list = roleService.findAllTree();
        return list;
    }
    @ResponseBody
    @RequestMapping("/findeutree")
    public List<EasyuiTree> findeutree()throws Exception{
        List<EasyuiTree> list = roleService.findAllEuTree();
        return list;
    }
}
