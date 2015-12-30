package com.lawinfo.domain.org.query;

import com.lawinfo.domain.common.BaseQuery;

/**
 * Created by wangrongtao on 15/10/13.
 */
public class OrgQuery extends BaseQuery{

    private Long id;
    private String name;
    private String orgtag;
    /**
     * 机构类型：1律所、2银行、3非银
     */
    private Long parentorgid;

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

    public Long getParentorgid() {
        return parentorgid;
    }

    public void setParentorgid(Long parentorgid) {
        this.parentorgid = parentorgid;
    }

    public String getOrgtag() {
        return orgtag;
    }

    public void setOrgtag(String orgtag) {
        this.orgtag = orgtag;
    }

    public String toLogString() {
        StringBuilder sb = new StringBuilder();
        sb.append("id").append(":").append(id).append(",");
        sb.append("name").append(":").append(name).append(",");
        sb.append("parentorgid").append(":").append(parentorgid).append(",");
        sb.append("startRow").append(":").append(startRow).append(",");
        sb.append("pageSize").append(":").append(pageSize).append(",");
        return sb.toString();
    }
}
