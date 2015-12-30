package com.lawinfo.domain.org;

import com.lawinfo.domain.common.BaseDomain;

/**
 * Created by wangrongtao on 15/10/26.
 */
public class Menu extends BaseDomain implements Comparable<Menu> {
    private long id;
    private String name;
    private String url;
    private long parentmenuid;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getParentmenuid() {
        return parentmenuid;
    }

    public void setParentmenuid(long parentmenuid) {
        this.parentmenuid = parentmenuid;
    }
    @Override
    public int compareTo(Menu o) {
        if (o != null) {
            return id > o.getId()?1:-1;
        }
        return 0;
    }
}
