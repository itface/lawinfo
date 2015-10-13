package com.lawinfo.domain.org;

import com.lawinfo.domain.common.BaseDomain;

import java.io.Serializable;

/**
 * Created by wangrongtao on 15/10/13.
 */
public class Dept extends BaseDomain {
    private long id;
    private String name;
    private long orgid;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getOrgid() {
        return orgid;
    }

    public void setOrgid(long orgid) {
        this.orgid = orgid;
    }

}
