package com.lawinfo.domain.org;

import com.lawinfo.domain.common.BaseDomain;

/**
 * Created by wangrongtao on 15/10/26.
 */
public class Action extends BaseDomain {
    private long id;
    private String name;
    /**
     * url，拦截器拦载url，判断权限
     */
    private String key;
    /**
     * 用于授权时归类，分组，没有特珠含义
     */
    private String tag;

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

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
