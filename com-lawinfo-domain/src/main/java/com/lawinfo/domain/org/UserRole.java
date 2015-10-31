package com.lawinfo.domain.org;

import com.lawinfo.domain.common.BaseDomain;

import java.io.Serializable;

/**
 * Created by wangrongtao on 15/10/13.
 */
public class UserRole implements Serializable{

    private Long id;
    private String userid;
    private Long roleid;

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

    public Long getRoleid() {
        return roleid;
    }

    public void setRoleid(Long roleid) {
        this.roleid = roleid;
    }
}
