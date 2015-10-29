package com.lawinfo.domain.org.query;

import com.lawinfo.domain.common.BaseQuery;

/**
 * Created by wangrongtao on 15/10/13.
 */
public class ActionQuery extends BaseQuery{

    private long id;
    private String name;
    private String actionkey;
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

    public String getActionkey() {
        return actionkey;
    }

    public void setActionkey(String actionkey) {
        this.actionkey = actionkey;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String toLogString() {
        StringBuilder sb = new StringBuilder();
        sb.append("id").append(":").append(id).append(",");
        sb.append("name").append(":").append(name).append(",");
        sb.append("actionkey").append(":").append(actionkey).append(",");
        sb.append("tag").append(":").append(tag).append(",");
        sb.append("startRow").append(":").append(startRow).append(",");
        sb.append("pageSize").append(":").append(pageSize).append(",");
        return sb.toString();
    }
}
