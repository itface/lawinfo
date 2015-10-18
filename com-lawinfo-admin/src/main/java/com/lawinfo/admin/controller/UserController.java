package com.lawinfo.admin.controller;

import com.lawinfo.domain.org.User;
import com.lawinfo.service.org.UserService;
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
@RequestMapping("/admin/user")
public class UserController {

    @Resource
    private UserService userService;


    @ResponseBody
    @RequestMapping("/add")
    public String save(User user,BindingResult result)throws Exception{
        int rows = userService.save(user);
        return rows+"";
    }
    @ResponseBody
    @RequestMapping("/remove/{id}")
    public String remove(@PathVariable long id)throws Exception{
        int rows = userService.deleteById(id);
        return rows+"";
    }
}
