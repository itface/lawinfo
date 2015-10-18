package com.lawinfo.domain.org.query;

import com.lawinfo.domain.common.BaseQuery;

/**
 * Created by wangrongtao on 15/10/13.
 */
public class RolePrivilegeQuery extends BaseQuery {
    private long id;
    /**
     * 手工设置（1、团队负责人2、诉讼部门负责人3、执行部门负责人4、主办律师5、总行6、分、支行7客户经理8非银负责人9非银部门负责人10非银业务员）
     */
    private int roldid;

    private int privilegeid;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getRoldid() {
        return roldid;
    }

    public void setRoldid(int roldid) {
        this.roldid = roldid;
    }

    public int getPrivilegeid() {
        return privilegeid;
    }

    public void setPrivilegeid(int privilegeid) {
        this.privilegeid = privilegeid;
    }

    public String toLogString() {
        StringBuilder sb = new StringBuilder();
        sb.append("id").append(":").append(id).append(",");
        sb.append("privilegeid").append(":").append(privilegeid).append(",");
        sb.append("roldid").append(":").append(roldid).append(",");
        sb.append("startRow").append(":").append(startRow).append(",");
        sb.append("pageSize").append(":").append(pageSize).append(",");
        return sb.toString();
    }
}
