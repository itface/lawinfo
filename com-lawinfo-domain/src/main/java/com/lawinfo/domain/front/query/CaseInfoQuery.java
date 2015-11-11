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

    public String toLogString() {
        StringBuilder sb = new StringBuilder();
        sb.append("id").append(":").append(id).append(",");
        sb.append("summary").append(":").append(summary).append(",");
        sb.append("startRow").append(":").append(startRow).append(",");
        sb.append("pageSize").append(":").append(pageSize).append(",");
        return sb.toString();
    }
}
