package com.lawinfo.domain.org.query;

import com.lawinfo.domain.common.BaseQuery;

import java.io.Serializable;

/**
 * Created by wangrongtao on 15/10/13.
 */
public class UserRoleQuery extends BaseQuery{

    private long id;
    private String userid;
    private int roleid;

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

    public int getRoleid() {
        return roleid;
    }

    public void setRoleid(int roleid) {
        this.roleid = roleid;
    }
    public String toLogString() {
        StringBuilder sb = new StringBuilder();
        sb.append("id").append(":").append(id).append(",");
        sb.append("userid").append(":").append(userid).append(",");
        sb.append("roleid").append(":").append(roleid).append(",");
        sb.append("startRow").append(":").append(startRow).append(",");
        sb.append("pageSize").append(":").append(pageSize).append(",");
        return sb.toString();
    }
}
