package com.lawinfo.admin.controller;

import com.lawinfo.domain.common.PageVo;
import com.lawinfo.domain.front.query.CaseInfoQuery;
import com.lawinfo.domain.org.Action;
import com.lawinfo.domain.org.query.ActionQuery;
import com.lawinfo.service.org.ActionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by wangrongtao on 15/10/17.
 */
@Controller
@RequestMapping("/lawinfo/front/caseinfo")
public class CaseInfoNewController {

    @Resource
    private ActionService actionService;

    @RequestMapping()
    public String view(CaseInfoQuery caseInfoQuery,ModelMap model){
        if (caseInfoQuery.getPage() < 1) {
            caseInfoQuery.setPage(1);
        }
        model.put("queryobj", caseInfoQuery);
        return "front/frontmain";
    }
    /*@ResponseBody
    @RequestMapping("/login/org/findbyid")
    public Action findbyid(long id)throws Exception{
        Action action = actionService.findById(id);
        return action;
    }
    @ResponseBody
    @RequestMapping("/login/org/remove")
    public int remove(long id)throws Exception{
        int rows = actionService.deleteById(id);
        return rows;
    }
    @ResponseBody
    @RequestMapping("/login/org/edit")
    public String save(HttpServletRequest request, @Valid Action action, BindingResult result)throws Exception{
        StringBuilder stringBuilder = new StringBuilder();
        if (result.hasErrors()) {
            List<ObjectError>  list = result.getAllErrors();
            for (ObjectError objectError : list) {
                stringBuilder.append(objectError.getDefaultMessage()).append("<br>");
            }
        }else{
            if (action.getId() < 1) {
                actionService.save(action);
            }else{
                actionService.update(action);
            }
        }
        return stringBuilder.toString();
    }*/
/*
    @ResponseBody
    @RequestMapping("/add")
    public int save(HttpServletRequest request,Action action,BindingResult result)throws Exception{
        if (action != null) {
            int rows = 0;
            if (action.getId() < 1) {
                rows = actionService.save(action);
            }else{
                rows = actionService.update(action);
            }
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
    @RequestMapping("/findbyid")
    public Action findbyid(long id)throws Exception{
        Action action = actionService.findById(id);
        return action;
    }
    @ResponseBody
    @RequestMapping("/findtree")
    public List<ActionTreeVo> findtree()throws Exception{
        List<ActionTreeVo> list = actionService.findAllTree();
        return list;
    }
    @ResponseBody
    @RequestMapping("/findeutree")
    public List<EasyuiTree> findeutree(String actionids)throws Exception{
        List<EasyuiTree> list = actionService.findEuTree(actionids);
        return list;
    }
    @ResponseBody
    @RequestMapping("/remove")
    public int remove(long id)throws Exception{
        int rows = actionService.deleteById(id);
        return rows;
    }*/
}