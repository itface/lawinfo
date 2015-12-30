package com.lawinfo.admin.controller;

import com.lawinfo.admin.system.login.LoginInfo;
import com.lawinfo.domain.org.Action;
import com.lawinfo.service.constant.SysConstants;
import com.lawinfo.service.login.LoginService;
import com.lawinfo.service.login.enumtype.LoginResultEnum;
import com.lawinfo.service.org.ActionService;
import com.lawinfo.service.org.utils.UserUtils;
import com.lawinfo.service.sms.EnSendSmsService;
import com.lawinfo.service.sms.enumtype.EnSendSmsResultEnum;
import com.lawinfo.service.sys.CacheService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by wangrongtao on 15/10/18.
 */
@Controller
@RequestMapping("/lawinfo/admin/main")
public class MainController {

    private final String[] MAIN_TAGS = {"admin-org-man","admin-org-man-add","admin-org-man-remove","admin-user-man","admin-user-man-add","admin-user-man-remove","admin-menu-man",
            "admin-action-man","admin-role-man","admin-group-man","admin-org-man-update","admin-user-man-update"};
    @Resource
    private ActionService actionService;
    @Resource
    private CacheService cacheService;

    @RequestMapping("")
    public String index(ModelMap modelMap,HttpServletRequest request)throws Exception{
        String userid = LoginInfo.getUseridFromSession(request.getSession());
        if (SysConstants.SUPER_ADMIN.equals(userid)) {
            StringBuilder sb = new StringBuilder();
            for (int i=0;i<MAIN_TAGS.length;i++) {
                sb.append(MAIN_TAGS[i]).append(",");
            }
            modelMap.put("actionList", sb.substring(0,sb.length()-1));
        }else{
            List<Action> actions = UserUtils.findActionsByUserid(userid);
            if (!CollectionUtils.isEmpty(actions)) {
                StringBuilder sb = new StringBuilder();
                for (Action action : actions) {
                    sb.append(action.getTag()).append(",");
                }
                modelMap.put("actionList", sb.substring(0,sb.length()-1));
            }
        }
        return "/admin/main";
    }
    @RequestMapping("/refreshcache")
    @ResponseBody
    public boolean refreshcache()throws Exception{
        return cacheService.initCache();
    }
    @RequestMapping("/onlineuser")
    @ResponseBody
    public List<String> getAllOnlineUsers()throws Exception{
        return LoginInfo.getAllOnlineUsers();
    }
}
