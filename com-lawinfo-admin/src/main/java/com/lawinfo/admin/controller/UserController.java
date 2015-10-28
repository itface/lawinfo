package com.lawinfo.admin.controller;

/**
 * Created by wangrongtao on 15/10/17.
 */
/*@Controller
@RequestMapping("/admin/user")*/
public class UserController {
/*
    @Resource
    private UserService userService;
    @Resource
    private UserOrginfoDeptRoleService userOrginfoDeptRoleService;


    @ResponseBody
    @RequestMapping("/add")
    public String save(HttpServletRequest request,User user,BindingResult result)throws Exception{
        String userid = LoginInfo.getUseridFromSession(request.getSession());
        if (user != null) {
            user.setOptuserid(userid);
            int rows = userService.save(user);
            return rows + "";
        }
        return null;
    }
    @ResponseBody
    @RequestMapping("/remove/{id}")
    public String remove(@PathVariable long id)throws Exception{
        int rows = userService.deleteById(id);
        return rows+"";
    }
    @ResponseBody
    @RequestMapping("/find")
    public List<UserVo> find()throws Exception{
        List<UserVo> list = userOrginfoDeptRoleService.find();
        return list;
    }*/
}
