package com.lawinfo.domain.org;

import java.io.Serializable;

/**
 * Created by wangrongtao on 15/10/13.
 */
public class RoleMenu implements Serializable {
    private Long id;
    private Long roleid;
    private Long menuid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoleid() {
        return roleid;
    }

    public void setRoleid(Long roleid) {
        this.roleid = roleid;
    }

    public Long getMenuid() {
        return menuid;
    }

    public void setMenuid(Long menuid) {
        this.menuid = menuid;
    }
}
