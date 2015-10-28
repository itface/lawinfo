package com.lawinfo.admin.controller;

/**
 * Created by wangrongtao on 15/10/17.
 */
//@Controller
//@RequestMapping("/admin/dept")
public class DeptController {
/*
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
    }*/
}
