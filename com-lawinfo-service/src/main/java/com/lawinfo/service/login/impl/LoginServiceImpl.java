package com.lawinfo.service.login.impl;

import com.lawinfo.service.login.LoginService;
import com.lawinfo.service.org.SmsService;

/**
 * Created by wangrongtao on 15/10/16.
 */
public class LoginServiceImpl implements LoginService {
    private SmsService smsService;


    /**
     * 先判断用户是否有效（userid是否存在），如果有效,则按userid+当前时间不超过expiretime查询出有效的验证码，可能有多条，
     * 然后判断有没有pwd是否相等，如果没有相等的，直接返回false，并用更新满足条件的这几条记录的loginfailcount+1（synchronized）,如果有相等的，再判断这条记录的loginfailcount是否小于规定次数（不直接在sql中查询，提高效率）,
     * 如果超过了最大失败次数，则返回失败
     * @param userid
     * @param pwd
     * @return
     */
    @Override
    public String login(String userid, String pwd) {
        return null;
    }
}
