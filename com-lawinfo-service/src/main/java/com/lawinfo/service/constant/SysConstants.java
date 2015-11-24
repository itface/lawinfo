package com.lawinfo.service.constant;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by wangrongtao on 15/11/5.
 */
public class SysConstants {
    public static String SUPER_ADMIN = "superadmin";
    public static String ORG_TREE_NODE_NAME_OF_LAW_OFFICE = "律所";
    public static int CASEINFO_TYPE_INIT = 0;
    public static int CASEINFO_STATUS_FINISHED = 9999;
    public static int CASEINFO_CONTACT_TYPE = 1;
    public static int CASEINFO_SSLAWYER_TYPE = 2;
    public static int CASEINFO_EXELAWYER_TYPE = 3;

    public static Set<String> FRONT_TAGS = new HashSet<String>();
    public static Set<String> ADMIN_TAGS = new HashSet<String>();
    static {
        FRONT_TAGS.add("front-case-progress-add-btn");
        FRONT_TAGS.add("front-caseinfo-add-btn");
        FRONT_TAGS.add("front-case-remove");
        FRONT_TAGS.add("front-caseinfo-exelawyer-add");
        FRONT_TAGS.add("front-case-report-list");
    }
}
