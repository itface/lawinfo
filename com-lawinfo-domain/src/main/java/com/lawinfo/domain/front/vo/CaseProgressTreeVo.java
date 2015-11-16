package com.lawinfo.domain.front.vo;

import com.lawinfo.domain.front.CaseProgressComment;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wangrongtao on 15/11/12.
 */
public class CaseProgressTreeVo implements Serializable,Comparable<CaseProgressTreeVo> {
    private int id;
    private long caseinfoid;
//    private int processnodeid;
    private String text;
    private int processnodeindex;
    private int parentprocessnodeid;
    private String backColor;
    private List<CaseProgressComment> caseProgressCommentList;
    private List<CaseProgressTreeVo> nodes;


    public long getCaseinfoid() {
        return caseinfoid;
    }

    public void setCaseinfoid(long caseinfoid) {
        this.caseinfoid = caseinfoid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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

    public List<CaseProgressComment> getCaseProgressCommentList() {
        return caseProgressCommentList;
    }

    public void setCaseProgressCommentList(List<CaseProgressComment> caseProgressCommentList) {
        this.caseProgressCommentList = caseProgressCommentList;
    }

    public List<CaseProgressTreeVo> getNodes() {
        return nodes;
    }

    public void setNodes(List<CaseProgressTreeVo> nodes) {
        this.nodes = nodes;
    }

    @Override
    public int compareTo(CaseProgressTreeVo o) {
        if (o != null) {
            return processnodeindex-o.getProcessnodeindex();
        }
        return 0;
    }
}
