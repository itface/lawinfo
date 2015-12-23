package com.lawinfo.domain.front.query;

import com.lawinfo.domain.common.BaseQuery;

import java.util.List;

/**
 * Created by wangrongtao on 15/10/13.
 */
public class CaseInfoQuery extends BaseQuery {
    private Long id;

    private String summary;
    private List<Long> caseinfoids;
    private Integer currenttabtype;
    private String lawyer;
    private String contact;
    private long caseorgid;
    private long startcreatetime;
    private long endcreatetime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }


    public Integer getCurrenttabtype() {
        return currenttabtype;
    }

    public void setCurrenttabtype(Integer currenttabtype) {
        this.currenttabtype = currenttabtype;
    }

    public List<Long> getCaseinfoids() {
        return caseinfoids;
    }

    public void setCaseinfoids(List<Long> caseinfoids) {
        this.caseinfoids = caseinfoids;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getLawyer() {
        return lawyer;
    }

    public void setLawyer(String lawyer) {
        this.lawyer = lawyer;
    }

    public long getCaseorgid() {
        return caseorgid;
    }

    public void setCaseorgid(long caseorgid) {
        this.caseorgid = caseorgid;
    }

    public long getStartcreatetime() {
        return startcreatetime;
    }

    public void setStartcreatetime(long startcreatetime) {
        this.startcreatetime = startcreatetime;
    }

    public long getEndcreatetime() {
        return endcreatetime;
    }

    public void setEndcreatetime(long endcreatetime) {
        this.endcreatetime = endcreatetime;
    }

    public String toLogString() {
        StringBuilder sb = new StringBuilder();
        sb.append("id").append(":").append(id).append(",");
        sb.append("summary").append(":").append(summary).append(",");
        sb.append("startRow").append(":").append(startRow).append(",");
        sb.append("pageSize").append(":").append(pageSize).append(",");
        return sb.toString();
    }
}
