package com.lawinfo.domain.org;

import java.io.Serializable;

/**
 * Created by wangrongtao on 15/10/13.
 */
public class RoleMenu implements Serializable {
    private long id;
    private long roleid;
    private long menuid;

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

    public long getMenuid() {
        return menuid;
    }

    public void setMenuid(long menuid) {
        this.menuid = menuid;
    }
}
