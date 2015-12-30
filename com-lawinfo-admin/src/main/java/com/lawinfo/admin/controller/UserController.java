package com.lawinfo.admin.controller;

import com.lawinfo.admin.system.login.LoginInfo;
import com.lawinfo.domain.org.User;
import com.lawinfo.domain.org.vo.OrgVo;
import com.lawinfo.domain.org.vo.UserVo;
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
@RequestMapping("/lawinfo/admin/user")
public class UserController {
    @Resource
    private UserService userService;


    @ResponseBody
    @RequestMapping("/add")
    public int save(HttpServletRequest request,UserVo userVo,BindingResult result)throws Exception{
//        String userid = LoginInfo.getUseridFromSession(request.getSession());
        if (userVo != null) {
//            user.setOptuserid(userid);
            int rows = 0;
            if (userVo.getId()>0) {
                rows = userService.updatebyUservo(userVo);
            }else{
                rows = userService.saveByUservo(userVo);
            }
            return rows;
        }
        return 0;
    }
    @ResponseBody
    @RequestMapping("/remove")
    public int remove(long id)throws Exception{
        int rows = userService.deleteById(id);
        return rows;
    }
    @ResponseBody
    @RequestMapping("/find")
    public List<UserVo> find()throws Exception{
        List<UserVo> list = userService.findAllUservo();
        return list;
    }
    @ResponseBody
    @RequestMapping("/findbyid")
    public UserVo findbyid(long id)throws Exception{
        UserVo user = userService.findUservoById(id);
        return user;
    }
    @ResponseBody
    @RequestMapping("/findtree")
    public List<OrgVo> findtree()throws Exception{
        List<OrgVo> list = userService.findUserTreeVo();
        return list;
    }
}
