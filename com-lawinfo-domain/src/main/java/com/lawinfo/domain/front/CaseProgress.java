package com.lawinfo.domain.front;

import com.lawinfo.domain.common.BaseDomain;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;
import java.io.Serializable;
import java.util.List;

/**
 * Created by wangrongtao on 15/11/3.
 */
public class CaseProgress implements Serializable, Comparable{
    private long id;
    private long caseinfoid;
    private int processnodeid;
    private String processnodename;
    private int processnodeindex;
    private int parentprocessnodeid;
    private String backColor;
    private List<CaseProgressComment> caseProgressCommentList;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCaseinfoid() {
        return caseinfoid;
    }

    public void setCaseinfoid(long caseinfoid) {
        this.caseinfoid = caseinfoid;
    }

    public int getProcessnodeid() {
        return processnodeid;
    }

    public void setProcessnodeid(int processnodeid) {
        this.processnodeid = processnodeid;
    }

    public List<CaseProgressComment> getCaseProgressCommentList() {
        return caseProgressCommentList;
    }

    public void setCaseProgressCommentList(List<CaseProgressComment> caseProgressCommentList) {
        this.caseProgressCommentList = caseProgressCommentList;
    }

    public String getProcessnodename() {
        return processnodename;
    }

    public void setProcessnodename(String processnodename) {
        this.processnodename = processnodename;
    }

    public int getProcessnodeindex() {
        return processnodeindex;
    }

    public void setProcessnodeindex(int processnodeindex) {
        this.processnodeindex = processnodeindex;
    }

    public int getParentprocessnodeid() {
        return parentprocessnodeid;
    }

    public void setParentprocessnodeid(int parentprocessnodeid) {
        this.parentprocessnodeid = parentprocessnodeid;
    }

    public String getBackColor() {
        return backColor;
    }

    public void setBackColor(String backColor) {
        this.backColor = backColor;
    }

    @Override
    public int compareTo(Object o) {
        if (o!=null) {
            return (((CaseProgress) o).getProcessnodeindex())-this.processnodeindex;
        }
        return 0;
    }
}
