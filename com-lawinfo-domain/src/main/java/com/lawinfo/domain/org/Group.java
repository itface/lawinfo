package com.lawinfo.domain.org;

import com.lawinfo.domain.common.BaseDomain;

/**
 * Created by wangrongtao on 15/10/26.
 */
public class Group extends BaseDomain {
    private long id;
    private String name;
    private long parentgroupid;

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

    public long getParentgroupid() {
        return parentgroupid;
    }

    public void setParentgroupid(long parentgroupid) {
        this.parentgroupid = parentgroupid;
    }
}
