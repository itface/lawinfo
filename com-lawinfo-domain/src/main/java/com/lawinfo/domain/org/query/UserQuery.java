package com.lawinfo.domain.org.query;

import com.lawinfo.domain.common.BaseQuery;

/**
 * Created by wangrongtao on 15/10/13.
 */
public class UserQuery extends BaseQuery {
    private Long id;
    private String name;
    /**
     * 手机号
     */
    private String userid;
    /**
     * 帐号状态，1是正常，-1锁定
     */
    private Integer status;
    private Long orgid;
    private Long deptid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getOrgid() {
        return orgid;
    }

    public void setOrgid(Long orgid) {
        this.orgid = orgid;
    }

    public Long getDeptid() {
        return deptid;
    }

    public void setDeptid(Long deptid) {
        this.deptid = deptid;
    }

    public String toLogString() {
        StringBuilder sb = new StringBuilder();
        sb.append("id").append(":").append(id).append(",");
        sb.append("userid").append(":").append(userid).append(",");
        sb.append("name").append(":").append(name).append(",");
        sb.append("orgid").append(":").append(orgid).append(",");
        sb.append("deptid").append(":").append(deptid).append(",");
        sb.append("startRow").append(":").append(startRow).append(",");
        sb.append("pageSize").append(":").append(pageSize).append(",");
        return sb.toString();
    }
}
