package com.lawinfo.admin.controller;

/**
 * Created by wangrongtao on 15/10/17.
 */
/*@Controller
@RequestMapping("/admin/role")*/
public class RoleController {
/*
    @Resource
    private RoleService roleService;


    @ResponseBody
    @RequestMapping("/add")
    public String save(HttpServletRequest request,Role role,BindingResult result)throws Exception{
        if (result.hasErrors()){
            return null;
        }
        String userid = LoginInfo.getUseridFromSession(request.getSession());
        if (role != null) {
            role.setOptuserid(userid);
            int rows = roleService.save(role);
            return rows + "";
        }
        return null;
    }
    @ResponseBody
    @RequestMapping("/remove/{id}")
    public String remove(@PathVariable long id)throws Exception{
        int rows = roleService.deleteById(id);
        return rows+"";
    }
    @ResponseBody
    @RequestMapping("/find")
    public List<Role> find()throws Exception{
        List<Role> list = roleService.findAll();
        return list;
    }*/
}
