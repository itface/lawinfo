package com.lawinfo.service.login;

import com.lawinfo.domain.login.LoginResult;
import com.lawinfo.service.login.enumtype.LoginResultEnum;

/**
 * Created by wangrongtao on 15/10/16.
 */
public interface LoginService {
    public LoginResult login(String userid,String pwd);
}
