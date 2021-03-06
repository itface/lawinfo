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
    private int roleid;
    /**
     * 权限码id
     */
    private String privilegeids;

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

    public int getRoleid() {
        return roleid;
    }

    public void setRoleid(int roleid) {
        this.roleid = roleid;
    }

    public String getPrivilegeids() {
        return privilegeids;
    }

    public void setPrivilegeids(String privilegeids) {
        this.privilegeids = privilegeids;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Role role = (Role) o;

        return roleid == role.roleid;

    }

    @Override
    public int hashCode() {
        return roleid;
    }
}
