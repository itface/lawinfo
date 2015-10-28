package com.lawinfo.domain.org;

import com.lawinfo.domain.common.BaseDomain;

/**
 * Created by wangrongtao on 15/10/26.
 */
public class PrivilegeAction extends BaseDomain {
    private long id;
    private long privilegeid;
    private long actionid;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPrivilegeid() {
        return privilegeid;
    }

    public void setPrivilegeid(long privilegeid) {
        this.privilegeid = privilegeid;
    }

    public long getActionid() {
        return actionid;
    }

    public void setActionid(long actionid) {
        this.actionid = actionid;
    }
}
