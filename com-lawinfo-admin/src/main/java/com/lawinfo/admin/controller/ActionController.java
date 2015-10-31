package com.lawinfo.admin.controller;

import com.lawinfo.domain.org.Action;
import com.lawinfo.domain.org.Org;
import com.lawinfo.domain.org.vo.ActionTreeVo;
import com.lawinfo.domain.org.vo.OrgVo;
import com.lawinfo.service.org.ActionService;
import com.lawinfo.service.org.OrgService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by wangrongtao on 15/10/17.
 */
@Controller
@RequestMapping("/lawinfo/admin/action")
public class ActionController {

    @Resource
    private ActionService actionService;


    @ResponseBody
    @RequestMapping("/add")
    public int save(HttpServletRequest request,Action action,BindingResult result)throws Exception{
        if (action != null) {
            int rows = actionService.save(action);
            return rows;
        }
        return 0;
    }
    @ResponseBody
    @RequestMapping("/find")
    public List<Action> find()throws Exception{
        List<Action> list = actionService.findAll();
        return list;
    }
    @ResponseBody
    @RequestMapping("/findtree")
    public List<ActionTreeVo> findtree()throws Exception{
        List<ActionTreeVo> list = actionService.findAllTree();
        return list;
    }
    @ResponseBody
    @RequestMapping("/remove")
    public int remove(long id)throws Exception{
        int rows = actionService.deleteById(id);
        return rows;
    }
}
