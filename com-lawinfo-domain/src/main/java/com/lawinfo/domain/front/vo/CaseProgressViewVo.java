package com.lawinfo.domain.front.vo;

import com.lawinfo.domain.front.CaseInfo;
import com.lawinfo.domain.front.CaseProgressComment;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wangrongtao on 15/11/25.
 */
public class CaseProgressViewVo implements Serializable{

    private List<CaseProgressComment> ssCaseProgressCommentList;
    private List<CaseProgressComment> executeCaseProgressCommentList;
    private List<CaseProgressTreeVo> ssCaseProgressTreeVoList;
    private List<CaseProgressTreeVo> executeCaseProgressTreeVoList;
    private CaseInfo caseInfo;
    private boolean ifsslawyer;

    public List<CaseProgressComment> getSsCaseProgressCommentList() {
        return ssCaseProgressCommentList;
    }

    public void setSsCaseProgressCommentList(List<CaseProgressComment> ssCaseProgressCommentList) {
        this.ssCaseProgressCommentList = ssCaseProgressCommentList;
    }

    public List<CaseProgressComment> getExecuteCaseProgressCommentList() {
        return executeCaseProgressCommentList;
    }

    public void setExecuteCaseProgressCommentList(List<CaseProgressComment> executeCaseProgressCommentList) {
        this.executeCaseProgressCommentList = executeCaseProgressCommentList;
    }

    public List<CaseProgressTreeVo> getSsCaseProgressTreeVoList() {
        return ssCaseProgressTreeVoList;
    }

    public void setSsCaseProgressTreeVoList(List<CaseProgressTreeVo> ssCaseProgressTreeVoList) {
        this.ssCaseProgressTreeVoList = ssCaseProgressTreeVoList;
    }

    public List<CaseProgressTreeVo> getExecuteCaseProgressTreeVoList() {
        return executeCaseProgressTreeVoList;
    }

    public void setExecuteCaseProgressTreeVoList(List<CaseProgressTreeVo> executeCaseProgressTreeVoList) {
        this.executeCaseProgressTreeVoList = executeCaseProgressTreeVoList;
    }

    public CaseInfo getCaseInfo() {
        return caseInfo;
    }

    public void setCaseInfo(CaseInfo caseInfo) {
        this.caseInfo = caseInfo;
    }

    public boolean isIfsslawyer() {
        return ifsslawyer;
    }

    public void setIfsslawyer(boolean ifsslawyer) {
        this.ifsslawyer = ifsslawyer;
    }
}
