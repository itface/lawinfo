package com.lawinfo.admin.controller;

import com.lawinfo.admin.system.login.LoginInfo;
import com.lawinfo.domain.common.PageVo;
import com.lawinfo.domain.front.CaseInfo;
import com.lawinfo.domain.front.charts.CaseInfoChart;
import com.lawinfo.domain.front.query.CaseInfoQuery;
import com.lawinfo.domain.org.Action;
import com.lawinfo.domain.org.User;
import com.lawinfo.domain.org.vo.OrgVo;
import com.lawinfo.service.constant.SysConstants;
import com.lawinfo.service.front.CaseInfoService;
import com.lawinfo.service.front.EchartsService;
import com.lawinfo.service.org.UserService;
import com.lawinfo.service.org.utils.UserUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangrongtao on 15/10/18.
 */
@Controller
@RequestMapping("/lawinfo/mobile")
public class MobileController {

    @Resource
    private CaseInfoService caseInfoService;

    @RequestMapping("")
    public String index(ModelMap modelMap,HttpServletRequest request,Integer page)throws Exception{
        String userid = LoginInfo.getUseridFromSession(request.getSession());
        modelMap.put("loginuser", userid);
        User user = UserUtils.findByUserid(userid);
        String username = user==null?null:user.getName();
        modelMap.put("loginusername", username);
        if (SysConstants.SUPER_ADMIN.equals(userid)) {
            modelMap.put("actionmap", SysConstants.FRONT_TAGS_MAP);
        }else{
            List<Action> actions = UserUtils.findActionsByUserid(userid);
            if (!CollectionUtils.isEmpty(actions)) {
                Map<String,String> map = new HashMap<String,String>();
                for (Action action : actions) {
                    map.put(action.getTag(), action.getTag());
                }
                modelMap.put("actionmap", map);
            }
        }
        CaseInfoQuery caseInfoQuery = new CaseInfoQuery();
        if (page == null || page < 1) {
            page = 1;
        }
        caseInfoQuery.setPage(page);
        caseInfoQuery.setPageSize(15);
        PageVo<CaseInfo> pageVo = caseInfoService.findListByPage(caseInfoQuery,userid);
        modelMap.put("pageVo", pageVo);
        return "/mobile/main";
    }
    @ResponseBody
    @RequestMapping("/getcaselist")
    public PageVo<CaseInfo>  getcaselist(ModelMap modelMap,HttpServletRequest request,Integer page)throws Exception{
        String userid = LoginInfo.getUseridFromSession(request.getSession());
        CaseInfoQuery caseInfoQuery = new CaseInfoQuery();
        if (page == null || page < 1) {
            page = 1;
        }
        caseInfoQuery.setPage(page);
        caseInfoQuery.setPageSize(15);
        PageVo<CaseInfo> pageVo = caseInfoService.findListByPage(caseInfoQuery,userid);
        return pageVo;
    }
    @RequestMapping("/addview")
    public String addview(ModelMap modelMap,HttpServletRequest request)throws Exception{
        String userid = LoginInfo.getUseridFromSession(request.getSession());
        modelMap.put("loginuser", userid);
        User user = UserUtils.findByUserid(userid);
        String username = user==null?null:user.getName();
        modelMap.put("loginusername", username);
        if (SysConstants.SUPER_ADMIN.equals(userid)) {
            modelMap.put("actionmap", SysConstants.FRONT_TAGS_MAP);
        }else{
            List<Action> actions = UserUtils.findActionsByUserid(userid);
            if (!CollectionUtils.isEmpty(actions)) {
                Map<String,String> map = new HashMap<String,String>();
                for (Action action : actions) {
                    map.put(action.getTag(), action.getTag());
                }
                modelMap.put("actionmap", map);
            }
        }
        return "/mobile/add";
    }
    @RequestMapping("/caseinfo")
    public String caseinfo(ModelMap modelMap,HttpServletRequest request,long caseid)throws Exception{
        String userid = LoginInfo.getUseridFromSession(request.getSession());
        modelMap.put("caseid", caseid);
        modelMap.put("loginuser", userid);
        User user = UserUtils.findByUserid(userid);
        String username = user==null?null:user.getName();
        modelMap.put("loginusername", username);
        if (SysConstants.SUPER_ADMIN.equals(userid)) {
            modelMap.put("actionmap", SysConstants.FRONT_TAGS_MAP);
        }else{
            List<Action> actions = UserUtils.findActionsByUserid(userid);
            if (!CollectionUtils.isEmpty(actions)) {
                Map<String,String> map = new HashMap<String,String>();
                for (Action action : actions) {
                    map.put(action.getTag(), action.getTag());
                }
                modelMap.put("actionmap", map);
            }
        }
        return "/mobile/caseinfo";
    }
}
