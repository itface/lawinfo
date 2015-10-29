package com.lawinfo.domain.org;

import com.lawinfo.domain.common.BaseDomain;

/**
 * Created by wangrongtao on 15/10/26.
 */
public class Menu extends BaseDomain {
    private Long id;
    private String name;
    private String url;
    private Long parentmenuid;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getParentmenuid() {
        return parentmenuid;
    }

    public void setParentmenuid(Long parentmenuid) {
        this.parentmenuid = parentmenuid;
    }
}
