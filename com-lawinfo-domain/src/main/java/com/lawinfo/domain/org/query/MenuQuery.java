package com.lawinfo.domain.org.query;

import com.lawinfo.domain.common.BaseDomain;
import com.lawinfo.domain.common.BaseQuery;

/**
 * Created by wangrongtao on 15/10/26.
 */
public class MenuQuery extends BaseQuery {
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

    public String toLogString() {
        StringBuilder sb = new StringBuilder();
        sb.append("id").append(":").append(id).append(",");
        sb.append("name").append(":").append(name).append(",");
        sb.append("name").append(":").append(name).append(",");
        sb.append("url").append(":").append(url).append(",");
        sb.append("startRow").append(":").append(startRow).append(",");
        sb.append("pageSize").append(":").append(pageSize).append(",");
        return sb.toString();
    }
}
