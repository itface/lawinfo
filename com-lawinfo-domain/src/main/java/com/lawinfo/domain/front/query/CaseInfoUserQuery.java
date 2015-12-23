package com.lawinfo.domain.front.query;

import com.lawinfo.domain.common.BaseQuery;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wangrongtao on 15/11/9.
 */
public class CaseInfoUserQuery extends BaseQuery{
    private Long id;
    private Long caseinfoid;
    private List<String> userids;
    /**
     * 用户类型，1代表案件所属机构的联系人，2代表诉讼律师，3代表执行律师
     */
    private Integer usertype;

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

    public List<String> getUserids() {
        return userids;
    }

    public void setUserids(List<String> userids) {
        this.userids = userids;
    }

    public Integer getUsertype() {
        return usertype;
    }

    public void setUsertype(Integer usertype) {
        this.usertype = usertype;
    }

}
