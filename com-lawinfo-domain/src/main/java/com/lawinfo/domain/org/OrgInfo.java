package com.lawinfo.domain.org;

import com.lawinfo.domain.common.BaseDomain;

import java.io.Serializable;

/**
 * Created by wangrongtao on 15/10/12.
 */
public class OrgInfo extends BaseDomain {

    private long id;
    private String name;
    /**
     * 机构类型：1律所、2银行、3非银
     */
    private int orgtype;

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

    public int getOrgtype() {
        return orgtype;
    }

    public void setOrgtype(int orgtype) {
        this.orgtype = orgtype;
    }

}
