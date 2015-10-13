package com.lawinfo.domain.org;

import com.lawinfo.domain.common.BaseDomain;

import java.io.Serializable;

/**
 * Created by wangrongtao on 15/10/13.
 */
public class LoginLog extends BaseDomain {
    private long id;
    private String userid;
    private long logintime;
    private String logintimestr;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public long getLogintime() {
        return logintime;
    }

    public void setLogintime(long logintime) {
        this.logintime = logintime;
    }

    public String getLogintimestr() {
        return logintimestr;
    }

    public void setLogintimestr(String logintimestr) {
        this.logintimestr = logintimestr;
    }
}
