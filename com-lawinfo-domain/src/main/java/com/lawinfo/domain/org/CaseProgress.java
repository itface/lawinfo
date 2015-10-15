package com.lawinfo.domain.org;

import com.lawinfo.domain.common.BaseDomain;

/**
 * Created by wangrongtao on 15/10/13.
 */
public class CaseProgress extends BaseDomain {
    private long id;
    private String caseid;
    private int casenodeid;
    private String comment;
    private String nexttask;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCaseid() {
        return caseid;
    }

    public void setCaseid(String caseid) {
        this.caseid = caseid;
    }

    public int getCasenodeid() {
        return casenodeid;
    }

    public void setCasenodeid(int casenodeid) {
        this.casenodeid = casenodeid;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getNexttask() {
        return nexttask;
    }

    public void setNexttask(String nexttask) {
        this.nexttask = nexttask;
    }
}
