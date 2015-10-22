package com.lawinfo.service.login.impl;

import com.lawinfo.domain.org.Sms;
import com.lawinfo.domain.org.query.SmsQuery;
import com.lawinfo.service.login.LoginService;
import com.lawinfo.service.login.enumtype.LoginResultEnum;
import com.lawinfo.service.org.SmsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by wangrongtao on 15/10/16.
 */
public class LoginServiceImpl implements LoginService {

    private static Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);

    private  int maxloginfailcount;
    private long expiretime;
    @Resource
    private SmsService smsService;


    /**
     * 先判断用户是否有效（userid是否存在），如果有效,则按userid+当前时间不超过expiretime查询出有效的验证码，可能有多条，
     * 然后判断有没有pwd是否相等，如果没有相等的，直接返回false，并用更新满足条件的这几条记录的loginfailcount+1（synchronized）,如果有相等的，再判断这条记录的loginfailcount是否小于规定次数（不直接在sql中查询，提高效率）,
     * 如果超过了最大失败次数，则返回失败
     * @param userid
     * @param password
     * @return
     */
    @Override
    public LoginResultEnum login(String userid, String password){

        LoginResultEnum loginResultEnum = null;
        if (!StringUtils.isEmpty(userid)&&!StringUtils.isEmpty(password)) {
            long now = new Date().getTime();
            SmsQuery smsQuery = new SmsQuery();
            smsQuery.setExpiretime(now);
            smsQuery.setPhoneno(userid);
            try {
                List<Sms> list = smsService.findList(smsQuery);
                if (!CollectionUtils.isEmpty(list)) {
                    for (Sms sms : list) {
                        long id = sms.getId();
                        String pwd = sms.getPwd();
                        if (password.equals(pwd)) {
                            int failCount =  sms.getLoginfailcount();
                            if (maxloginfailcount<failCount) {
                                loginResultEnum = LoginResultEnum.MAX_LOGIN_FAIL;
                            }else{
                                //只要有一条符合条件的，就算登录成功
                                return LoginResultEnum.SUCCESS;
                            }
                        }else{
                            loginResultEnum = LoginResultEnum.PWD_INCORRECT;
                            //更新当前id记录登录失败次数
                            smsService.updateLoginfailcount(id);
                        }
                    }
                }else {
                    loginResultEnum=LoginResultEnum.PWD_NOT_EXIST;
                }
            } catch (Exception e) {
                logger.error("login exception",e);
                loginResultEnum=LoginResultEnum.LOGIN_EXCEPTION;
            }
        }else {
            loginResultEnum=LoginResultEnum.UID_PWD_EMPTY;
        }
        return loginResultEnum;
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
}
