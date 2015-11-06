package com.lawinfo.admin.controller;

import com.lawinfo.admin.system.login.LoginInfo;
import com.lawinfo.domain.org.Action;
import com.lawinfo.domain.org.vo.OrgVo;
import com.lawinfo.service.constant.SysConstants;
import com.lawinfo.service.org.ActionService;
import com.lawinfo.service.org.UserService;
import com.lawinfo.service.org.utils.UserUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by wangrongtao on 15/10/18.
 */
@Controller
@RequestMapping("/lawinfo/front")
public class FrontController {


    @Resource
    private UserService userService;

    @RequestMapping("")
    public String index(ModelMap modelMap,HttpServletRequest request)throws Exception{
        String userid = LoginInfo.getUseridFromSession(request.getSession());
        if (SysConstants.SUPER_ADMIN.equals(userid)) {
            StringBuilder sb = new StringBuilder();
            for(String tag : SysConstants.FRONT_TAGS){
                sb.append(tag).append(",");
            }
            modelMap.put("actionList", sb.substring(0,sb.length()-1));
        }else{
            List<Action> actions = UserUtils.findActionsByUserid(userid);
            if (!CollectionUtils.isEmpty(actions)) {
                StringBuilder sb = new StringBuilder();
                for (Action action : actions) {
                    if (SysConstants.FRONT_TAGS.contains(action.getTag())) {
                        sb.append(action.getTag()).append(",");
                    }
                }
                if (sb.length()>0) {
                    modelMap.put("actionList", sb.substring(0,sb.length()-1));
                }
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
}
