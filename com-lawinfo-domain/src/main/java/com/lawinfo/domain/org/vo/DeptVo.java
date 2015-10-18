package com.lawinfo.domain.org.vo;

import com.lawinfo.domain.common.BaseDomain;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;

/**
 * Created by wangrongtao on 15/10/13.
 */
public class DeptVo extends BaseDomain {
    private long id;
    /**
     * 部门名称，
     * 律所：上诉部门、执行部门
     * 银行：某某支行
     * 非银：某某部门
     */
    private String name;
    /**
     * 机构id
     */
    private long orgid;
    private String orgname;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getOrgid() {
        return orgid;
    }

    public void setOrgid(long orgid) {
        this.orgid = orgid;
    }

    public String getOrgname() {
        return orgname;
    }

    public void setOrgname(String orgname) {
        this.orgname = orgname;
    }
}
