package com.lawinfo.service.login;

import com.lawinfo.domain.login.LoginResult;
import com.lawinfo.domain.org.User;
import com.lawinfo.service.login.enumtype.LoginResultEnum;

import java.util.concurrent.ExecutionException;

/**
 * Created by wangrongtao on 15/10/16.
 */
public interface LoginService {
    public LoginResult login(String userid,String pwd);
    public User wechatOpenIdLogin(String code) throws Exception;
}
