package com.lawinfo.service.login.impl;

import com.lawinfo.domain.login.LoginResult;
import com.lawinfo.domain.org.LoginLog;
import com.lawinfo.domain.org.Sms;
import com.lawinfo.domain.org.User;
import com.lawinfo.domain.org.query.SmsQuery;
import com.lawinfo.service.login.LoginService;
import com.lawinfo.service.login.enumtype.LoginResultEnum;
import com.lawinfo.service.org.LoginLogService;
import com.lawinfo.service.org.SmsService;
import com.lawinfo.service.org.UserService;
import com.lawinfo.service.util.DateUtils;
import com.lawinfo.service.util.StrUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by wangrongtao on 15/10/16.
 */
public class LoginServiceImpl implements LoginService{
    private static Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);

    private  int maxloginfailcount;
    private long expiretime;
    private long maxallowrelogintime;
    @Resource
    private SmsService smsService;
    @Resource
    private UserService userService;
    @Resource
    private LoginLogService loginLogService;



     /** 先判断用户是否有效（userid是否存在），如果有效,则按userid+当前时间不超过expiretime查询出有效的验证码，可能有多条，
     * 然后判断有没有pwd是否相等，如果没有相等的，直接返回false，并用更新满足条件的这几条记录的loginfailcount+1（synchronized）,如果有相等的，再判断这条记录的loginfailcount是否小于规定次数（不直接在sql中查询，提高效率）,
     * 如果超过了最大失败次数，则返回失败
     * @param userid
     * @param password
     * @return
     * */

    @Override
    public LoginResult login(String userid, String password){
        LoginResult loginResult = new LoginResult();
        loginResult.setUserid(userid);
        loginResult.setSuccess(false);
        long now = new Date().getTime();
        String nowtimestr = DateUtils.formatDatetime(now);
        SmsQuery smsQuery = new SmsQuery();
        smsQuery.setExpiretime(now);
        smsQuery.setPhoneno(userid);
        LoginLog loginLog = new LoginLog();
        loginLog.setUserid(userid);
        loginLog.setLogintime(now);
        loginLog.setLogintimestr(nowtimestr);
        if (!StringUtils.isEmpty(userid)&&!StringUtils.isEmpty(password)) {
            try {
                User user = userService.findByUserid(userid);
                if (user!=null) {
                    String url = "/lawinfo/front";
                    if (user.getOrgid()<1) {
                        url = "/lawinfo/admin/main";
                    }
                    if (0 == user.getLogintype()) {
                        boolean loginsuccess = StrUtils.MD5(password).equals(user.getPwd());
                        Integer loginfailcount = user.getLoginfailcount();
                        Long lastlogintime = user.getLastlogintime();
                        User user1 = new User();
                        user1.setUserid(userid);
                        user1.setLastlogintime(now);
                        user1.setLastlogintimestr(nowtimestr);
                        if (loginfailcount > maxloginfailcount && (now - lastlogintime) < maxallowrelogintime) {
                            user1.setLoginfailcount(user.getLoginfailcount() + 1);
                            loginResult.setSuccess(false);
                            loginResult.setDesc(LoginResultEnum.MAX_LOGIN_FAIL.getDesc());
                        } else {
                            user1.setLoginfailcount(loginsuccess ? 0 : user.getLoginfailcount() + 1);
                            loginResult.setSuccess(loginsuccess);
                            loginResult.setRedirecturl(url);
                            loginResult.setDesc(loginsuccess ? LoginResultEnum.SUCCESS.getDesc() : LoginResultEnum.PWD_INCORRECT.getDesc());
                        }
                        userService.updateLoginStatus(user1);
                    } else {
                        List<Sms> list = smsService.findList(smsQuery);
                        if (!CollectionUtils.isEmpty(list)) {
                            for (Sms sms : list) {
                                long id = sms.getId();
                                String pwd = sms.getPwd();
                                if (password.equals(pwd)) {
                                    int failCount = sms.getLoginfailcount();
                                    if (maxloginfailcount < failCount) {
                                        loginResult.setDesc(LoginResultEnum.MAX_LOGIN_FAIL.getDesc());
                                    } else {
                                        //只要有一条符合条件的，就算登录成功
                                        loginResult.setSuccess(true);
                                        loginResult.setRedirecturl(url);
                                        loginResult.setDesc(LoginResultEnum.SUCCESS.getDesc());
                                    }
                                } else {
                                    loginResult.setDesc(LoginResultEnum.PWD_INCORRECT.getDesc());
                                    //更新当前id记录登录失败次数
                                    smsService.updateLoginfailcount(id);
                                }
                            }
                        } else {
                            loginResult.setDesc(LoginResultEnum.PWD_NOT_EXIST.getDesc());
                        }
                    }
                }else{
                    loginResult.setDesc(LoginResultEnum.USER_NOT_EXISTS.getDesc());
                }
            } catch (Exception e) {
                logger.error("login exception",e);
                loginResult.setDesc(LoginResultEnum.LOGIN_EXCEPTION.getDesc());

            }
        }else {
            loginResult.setDesc(LoginResultEnum.UID_PWD_EMPTY.getDesc());
        }
        loginLog.setMsg(loginResult.getDesc());
        saveLoginLog(loginLog);
        return loginResult;
    }

    private int saveLoginLog(LoginLog loginLog) {
        try {
            if (loginLog != null) {
                return loginLogService.save(loginLog);
            }
        } catch (Exception e) {
            logger.error("saveLoginLog exception",e);
        }
        return 0;
    }
    public void setExpiretime(long expiretime) {
        this.expiretime = expiretime;
    }

    public long getExpiretime() {
        return expiretime;
    }

    public int getMaxloginfailcount() {
        return maxloginfailcount;
    }

    public void setMaxloginfailcount(int maxloginfailcount) {
        this.maxloginfailcount = maxloginfailcount;
    }

    public long getMaxallowrelogintime() {
        return maxallowrelogintime;
    }

    public void setMaxallowrelogintime(long maxallowrelogintime) {
        this.maxallowrelogintime = maxallowrelogintime;
    }
}
