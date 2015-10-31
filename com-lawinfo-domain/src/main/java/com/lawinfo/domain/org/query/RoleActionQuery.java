package com.lawinfo.domain.org.query;

import com.lawinfo.domain.common.BaseQuery;

/**
 * Created by wangrongtao on 15/10/13.
 */
public class RoleActionQuery extends BaseQuery {
    private Long id;

    private Long roldid;

    private Long actionid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoldid() {
        return roldid;
    }

    public void setRoldid(Long roldid) {
        this.roldid = roldid;
    }

    public Long getActionid() {
        return actionid;
    }

    public void setActionid(Long actionid) {
        this.actionid = actionid;
    }

    public String toLogString() {
        StringBuilder sb = new StringBuilder();
        sb.append("id").append(":").append(id).append(",");
        sb.append("actionid").append(":").append(actionid).append(",");
        sb.append("roldid").append(":").append(roldid).append(",");
        sb.append("startRow").append(":").append(startRow).append(",");
        sb.append("pageSize").append(":").append(pageSize).append(",");
        return sb.toString();
    }
}
