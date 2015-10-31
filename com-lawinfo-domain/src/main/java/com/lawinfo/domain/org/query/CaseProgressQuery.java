package com.lawinfo.domain.org.query;

import com.lawinfo.domain.common.BaseQuery;

/**
 * Created by wangrongtao on 15/10/13.
 */
public class CaseProgressQuery extends BaseQuery {
    private Long id;
    private String caseid;
    private Long casenodeid;
    private String comments;
    private String nexttask;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCaseid() {
        return caseid;
    }

    public void setCaseid(String caseid) {
        this.caseid = caseid;
    }

    public Long getCasenodeid() {
        return casenodeid;
    }

    public void setCasenodeid(Long casenodeid) {
        this.casenodeid = casenodeid;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getNexttask() {
        return nexttask;
    }

    public void setNexttask(String nexttask) {
        this.nexttask = nexttask;
    }
    public String toLogString() {
        StringBuilder sb = new StringBuilder();
        sb.append("id").append(":").append(id).append(",");
        sb.append("caseid").append(":").append(caseid).append(",");
        sb.append("casenodeid").append(":").append(casenodeid).append(",");
        sb.append("startRow").append(":").append(startRow).append(",");
        sb.append("pageSize").append(":").append(pageSize).append(",");
        return sb.toString();
    }
}
