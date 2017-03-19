package com.lawinfo.admin.controller;

import com.lawinfo.admin.system.login.LoginInfo;
import com.lawinfo.domain.login.LoginResult;
import com.lawinfo.domain.org.Action;
import com.lawinfo.domain.org.User;
import com.lawinfo.service.constant.SysConstants;
import com.lawinfo.service.login.LoginService;
import com.lawinfo.service.org.UserService;
import com.lawinfo.service.org.utils.UserUtils;
import com.lawinfo.service.wechat.WeChatUserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wangrongtao on 15/10/18.
 */
@Controller
@RequestMapping("/login/mobile")
public class LoginMController {

    private static Logger logger = LoggerFactory.getLogger(LoginMController.class);

    @Resource
    private LoginService loginService;
    @Resource
    private UserService userService;
    @Resource
    private WeChatUserInfoService weChatUserInfoService;


    @RequestMapping("")
    public String newPage(ModelMap modelMap,HttpServletRequest request,String code) throws ExecutionException {
        if (!StringUtils.isEmpty(code)) {
            String wechatopenid = weChatUserInfoService.getOpenId(code);
            if (!StringUtils.isEmpty(wechatopenid)) {
                HttpSession session = request.getSession();
                User user = userService.findByWechatopenid(wechatopenid);
                if (user != null && !StringUtils.isEmpty(user.getUserid())) {
                    LoginInfo.addUseridToSession(request.getSession(), user.getUserid());
                    return "redirect:/lawinfo/mobile";
                }
                session.setAttribute("wechatopenid", wechatopenid);
            }
        }
        return "mobile/loginM";
    }
    @RequestMapping("/dologin")
    @ResponseBody
    public LoginResult dologin(HttpServletRequest request,String tel,String code) {
        HttpSession session = request.getSession();
        String wechatopenid = session.getAttribute("wechatopenid")==null?null:session.getAttribute("wechatopenid").toString();
        if (!isPositiveInteger(tel)) {
            LoginResult loginResult = new LoginResult();
            loginResult.setSuccess(false);
            loginResult.setDesc("请输入正确的手机号码");
            loginResult.setRedirecturl("/login/mobile");
            return loginResult;
        }
        LoginResult loginResult = loginService.login(tel, code);
        if (loginResult != null) {
            if (loginResult.isSuccess()) {
                LoginInfo.addUseridToSession(request.getSession(), tel);
                if (!StringUtils.isEmpty(wechatopenid)) {
                    User user = new User();
                    user.setUserid(tel);
                    user.setWechatopenid(wechatopenid);
                    userService.updateWechatopenid(user);
                }
                loginResult.setRedirecturl("/lawinfo/mobile");
            }else{
                loginResult.setRedirecturl("/login/mobile");
            }
        }
        return loginResult;
    }
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        LoginInfo.removeLoginUser(request.getSession());
        return "redirect:/login/mobile";
    }
    private boolean isMatch(String regex, String orginal){
        if (orginal == null || orginal.trim().equals("")) {
            return false;
        }
        Pattern pattern = Pattern.compile(regex);
        Matcher isNum = pattern.matcher(orginal);
        return isNum.matches();
    }

    private boolean isPositiveInteger(String orginal) {
        return isMatch("^\\+{0,1}[1-9]\\d*", orginal);
    }

    @RequestMapping(value = "/wechat/common/viewProgress/{caseid}")
    public String viewProgress(ModelMap modelMap, HttpServletRequest request, @PathVariable String caseid) throws Exception{
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
