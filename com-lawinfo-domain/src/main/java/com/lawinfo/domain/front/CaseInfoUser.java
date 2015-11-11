package com.lawinfo.domain.front;

import java.io.Serializable;

/**
 * Created by wangrongtao on 15/11/9.
 */
public class CaseInfoUser implements Serializable {
    private long id;
    private long caseinfoid;
    private String userid;
    /**
     * 用户类型，1代表案件所属机构的联系人，2代表诉讼律师，3代表执行律师
     */
    private int usertype;

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

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public int getUsertype() {
        return usertype;
    }

    public void setUsertype(int usertype) {
        this.usertype = usertype;
    }
}
