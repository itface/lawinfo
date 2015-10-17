package com.lawinfo.domain.org;

import com.lawinfo.domain.common.BaseDomain;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;

/**
 * Created by wangrongtao on 15/10/13.
 */
public class Role extends BaseDomain {
    private long id;
    @NotBlank
    @Length(max=100)
    private String name;
    /**
     * 手工设置（1、团队负责人2、诉讼部门负责人3、执行部门负责人4、主办律师5、总行6、分、支行7客户经理8非银负责人9非银部门负责人10非银业务员）
     */
    @Min(1)
    private int roldid;

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

    public int getRoldid() {
        return roldid;
    }

    public void setRoldid(int roldid) {
        this.roldid = roldid;
    }
}
