package com.lawinfo.domain.front.query;

import java.io.Serializable;

/**
 * Created by wangrongtao on 15/11/9.
 */
public class CaseInfoUserQuery implements Serializable {
    private Long id;
    private Long caseinfoid;
    private String userid;
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

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public Integer getUsertype() {
        return usertype;
    }

    public void setUsertype(Integer usertype) {
        this.usertype = usertype;
    }
}
