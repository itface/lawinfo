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
import com.lawinfo.service.org.ActionService;
import com.lawinfo.service.org.OrgService;
import com.lawinfo.service.org.UserService;
import com.lawinfo.service.org.utils.UserUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by wangrongtao on 15/10/18.
 */
@Controller
@RequestMapping("/lawinfo/front")
public class FrontController {


    @Resource
    private CaseInfoService caseInfoService;
    @Resource
    private UserService userService;
    @Resource
    private EchartsService echartsService;


    @RequestMapping("")
    public String index(ModelMap modelMap,HttpServletRequest request)throws Exception{
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
        return "/front/front";
    }
    @ResponseBody
    @RequestMapping("/user/findtree")
    public List<OrgVo> findsubtree(HttpServletRequest request)throws Exception{
        String userid = LoginInfo.getUseridFromSession(request.getSession());
        List<OrgVo> list = userService.findSubordinateTree(userid);
        return list;
    }
    @ResponseBody
    @RequestMapping("/chart/get")
    public CaseInfoChart getChart(HttpServletRequest request,String userid,Integer currenttabtype)throws Exception {
        return echartsService.getCountCaseinfoMoneyChartData(userid);
    }
}
