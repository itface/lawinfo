package com.lawinfo.domain.org;

import com.lawinfo.domain.common.BaseDomain;

/**
 * Created by wangrongtao on 15/10/26.
 */
public class PrivMenu extends BaseDomain{
    private long id;
    private long privilegeid;
    private long menuid;

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

    public long getMenuid() {
        return menuid;
    }

    public void setMenuid(long menuid) {
        this.menuid = menuid;
    }
}