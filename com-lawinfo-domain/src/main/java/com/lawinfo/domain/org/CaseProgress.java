package com.lawinfo.domain.org;

import com.lawinfo.domain.common.BaseDomain;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;

/**
 * Created by wangrongtao on 15/10/13.
 */
public class CaseProgress extends BaseDomain {
    private long id;
    @Min(1)
    private long caseid;
    @Min(1)
    private int casenodeid;
    @NotBlank
    @Length(max=200)
    private String comment;
    @NotBlank
    @Length(max=200)
    private String nexttask;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCaseid() {
        return caseid;
    }

    public void setCaseid(long caseid) {
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
