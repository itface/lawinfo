package com.lawinfo.domain.org.query;

import com.lawinfo.domain.common.BaseDomain;
import com.lawinfo.domain.common.BaseQuery;

/**
 * Created by wangrongtao on 15/10/13.
 */
public class LoginLogQuery extends BaseQuery {
    private Long id;
    private String userid;
    private String username;
    private Long logintime;
    private String logintimestr;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getLogintime() {
        return logintime;
    }

    public void setLogintime(Long logintime) {
        this.logintime = logintime;
    }

    public String getLogintimestr() {
        return logintimestr;
    }

    public void setLogintimestr(String logintimestr) {
        this.logintimestr = logintimestr;
    }

    public String toLogString() {
        StringBuilder sb = new StringBuilder();
        sb.append("id").append(":").append(id).append(",");
        sb.append("userid").append(":").append(userid).append(",");
        sb.append("logintimestr").append(":").append(logintimestr).append(",");
        sb.append("startRow").append(":").append(startRow).append(",");
        sb.append("pageSize").append(":").append(pageSize).append(",");
        return sb.toString();
    }
}
