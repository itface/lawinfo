package com.lawinfo.domain.org;

import java.io.Serializable;

/**
 * Created by wangrongtao on 15/10/13.
 */
public class RolePrivilege implements Serializable {
    private long id;
    private int roleid;
    private int privilegeid;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getRoleid() {
        return roleid;
    }

    public void setRoleid(int roleid) {
        this.roleid = roleid;
    }

    public int getPrivilegeid() {
        return privilegeid;
    }

    public void setPrivilegeid(int privilegeid) {
        this.privilegeid = privilegeid;
    }
}
