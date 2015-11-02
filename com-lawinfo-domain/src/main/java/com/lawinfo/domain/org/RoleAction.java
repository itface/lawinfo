package com.lawinfo.domain.org;

import com.lawinfo.domain.common.BaseDomain;

/**
 * Created by wangrongtao on 15/10/26.
 */
public class RoleAction extends BaseDomain {
    private long id;
    private long roleid;
    private long actionid;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getRoleid() {
        return roleid;
    }

    public void setRoleid(long roleid) {
        this.roleid = roleid;
    }

    public long getActionid() {
        return actionid;
    }

    public void setActionid(long actionid) {
        this.actionid = actionid;
    }
}
