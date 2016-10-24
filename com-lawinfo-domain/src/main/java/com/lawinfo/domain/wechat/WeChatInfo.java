package com.lawinfo.domain.wechat;

/**
 * Created by wangrongtao on 2016/10/24.
 */
public class WeChatInfo {
    private String token;
    private String appId;
    private String appSecret;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }
}
