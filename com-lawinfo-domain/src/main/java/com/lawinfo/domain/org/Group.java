package com.lawinfo.domain.org;

import com.lawinfo.domain.common.BaseDomain;

/**
 * Created by wangrongtao on 15/10/26.
 */
public class Group extends BaseDomain {
    private Long id;
    private String name;
    private Long parentgroupid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getParentgroupid() {
        return parentgroupid;
    }

    public void setParentgroupid(Long parentgroupid) {
        this.parentgroupid = parentgroupid;
    }
}
