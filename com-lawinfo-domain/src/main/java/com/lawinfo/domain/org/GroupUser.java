package com.lawinfo.domain.org;

import com.lawinfo.domain.common.BaseDomain;

/**
 * Created by wangrongtao on 15/10/26.
 */
public class GroupUser extends BaseDomain {
    private long id;
    private long groupid;
    private long userid;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getGroupid() {
        return groupid;
    }

    public void setGroupid(long groupid) {
        this.groupid = groupid;
    }

    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }
}
