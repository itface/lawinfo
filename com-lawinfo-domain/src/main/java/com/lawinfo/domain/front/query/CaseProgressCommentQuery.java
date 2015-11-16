package com.lawinfo.domain.front.query;

import com.lawinfo.domain.common.BaseDomain;
import com.lawinfo.domain.common.BaseQuery;

/**
 * Created by wangrongtao on 15/11/3.
 */
public class CaseProgressCommentQuery extends BaseQuery {
    private Long id;
    private Long caseinfoid;
    private Integer processnodeid;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCaseinfoid() {
        return caseinfoid;
    }

    public void setCaseinfoid(Long caseinfoid) {
        this.caseinfoid = caseinfoid;
    }

    public Integer getProcessnodeid() {
        return processnodeid;
    }

    public void setProcessnodeid(Integer processnodeid) {
        this.processnodeid = processnodeid;
    }
}
