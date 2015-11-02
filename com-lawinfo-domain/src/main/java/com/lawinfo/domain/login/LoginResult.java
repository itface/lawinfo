package com.lawinfo.domain.login;

import java.io.Serializable;

/**
 * Created by wangrongtao on 15/11/2.
 */
public class LoginResult implements Serializable {
    private boolean success;
    private String userid;
    private String redirecturl;
    private String desc;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getRedirecturl() {
        return redirecturl;
    }

    public void setRedirecturl(String redirecturl) {
        this.redirecturl = redirecturl;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
