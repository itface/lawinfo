package com.lawinfo.domain.front.vo;

import com.lawinfo.domain.front.CaseInfo;
import com.lawinfo.domain.front.CaseProgressComment;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wangrongtao on 15/11/25.
 */
public class CaseProgressViewVo implements Serializable{

    private List<CaseProgressComment> caseProgressCommentList;
    private List<CaseProgressTreeVo> caseProgressTreeVoList;
    private CaseInfo caseInfo;

    public List<CaseProgressComment> getCaseProgressCommentList() {
        return caseProgressCommentList;
    }

    public void setCaseProgressCommentList(List<CaseProgressComment> caseProgressCommentList) {
        this.caseProgressCommentList = caseProgressCommentList;
    }

    public List<CaseProgressTreeVo> getCaseProgressTreeVoList() {
        return caseProgressTreeVoList;
    }

    public void setCaseProgressTreeVoList(List<CaseProgressTreeVo> caseProgressTreeVoList) {
        this.caseProgressTreeVoList = caseProgressTreeVoList;
    }

    public CaseInfo getCaseInfo() {
        return caseInfo;
    }

    public void setCaseInfo(CaseInfo caseInfo) {
        this.caseInfo = caseInfo;
    }
}
