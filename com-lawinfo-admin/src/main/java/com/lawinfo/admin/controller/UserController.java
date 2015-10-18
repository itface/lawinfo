package com.lawinfo.admin.controller;

import com.lawinfo.admin.system.login.LoginInfo;
import com.lawinfo.domain.org.Dept;
import com.lawinfo.domain.org.User;
import com.lawinfo.service.org.UserService;
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
@RequestMapping("/admin/user")
public class UserController {

    @Resource
    private UserService userService;


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
    public List<User> find()throws Exception{
        List<User> list = userService.findAll();
        return list;
    }
}
