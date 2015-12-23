package com.lawinfo.domain.org.enumtype;

/**
 * Created by wangrongtao on 15/12/22.
 */
public enum RoleTagEnum {
    ADMIN_COMMON("普通管理员","admin_common"),
    LAWYER_COMPANY_MANAGER("团队负责人","lawyer_company_manager"),
    LAWYER_EXECUTE_DEPT_MANAGER("执行部门负责人","lawyer_execute_dept_manager"),
    LAWYER_SS_DEPT_MANAGER("诉讼部门负责人","lawyer_ss_dept_manager"),
    LAWYER("主办律师","lawyer"),
    CUSTOM_COMPANY_MANAGER("总行","custom_company_manager"),
    CUSTOM_DEPT_MANAGER("分、支行","custom_dept_manager"),
    CUSTOM("客户经理","custom");

    private String rolename;
    private String roletag;

    RoleTagEnum(String rolename, String roletag) {
        this.rolename = rolename;
        this.roletag = roletag;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public String getRoletag() {
        return roletag;
    }

    public void setRoletag(String roletag) {
        this.roletag = roletag;
    }
}
