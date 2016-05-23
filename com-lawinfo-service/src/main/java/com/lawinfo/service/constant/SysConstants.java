package com.lawinfo.service.constant;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by wangrongtao on 15/11/5.
 */
public class SysConstants {
    public static String SUPER_ADMIN = "superadmin";
    public static String ORG_TREE_NODE_NAME_OF_LAW_OFFICE = "律所";
    public static int CASEINFO_TYPE_INIT = 0;
    public static int CASEINFO_STATUS_SS_FINISHED = 100;
    public static int CASEINFO_STATUS_EXE_FINISHED = 200;
    public static int CASEINFO_STATUS_ALL_FINISHED = 9999;
    public static int CASEINFO_CONTACT_TYPE = 1;
    public static int CASEINFO_SSLAWYER_TYPE = 2;
    public static int CASEINFO_EXELAWYER_TYPE = 3;

    public static Set<String> FRONT_TAGS = new HashSet<String>();
    public static Map<String,String> FRONT_TAGS_MAP = new HashMap<String,String>();
    public static Set<String> ADMIN_TAGS = new HashSet<String>();
    static {
        FRONT_TAGS_MAP.put("front-case-progress-add-btn","front-case-progress-add-btn");
        FRONT_TAGS_MAP.put("front-caseinfo-add-btn","front-caseinfo-add-btn");
        FRONT_TAGS_MAP.put("front-case-remove","front-case-remove");
        FRONT_TAGS_MAP.put("front-caseinfo-exelawyer-add","front-caseinfo-exelawyer-add");
        FRONT_TAGS_MAP.put("front-case-report-list","front-case-report-list");
        FRONT_TAGS_MAP.put("caseinfo-progress-edit-tab","caseinfo-progress-edit-tab");
        FRONT_TAGS_MAP.put("caseinfo-progress-query-tab","caseinfo-progress-query-tab");
        FRONT_TAGS_MAP.put("front-caseinfo-progress-rm","front-caseinfo-progress-rm");
        FRONT_TAGS_MAP.put("front-caseinfo-setstatus-finish","front-caseinfo-setstatus-finish");
        FRONT_TAGS_MAP.put("caseinfo-progress-ss-end-tab","caseinfo-progress-ss-end-tab");
        FRONT_TAGS_MAP.put("caseinfo-execute-progress-edit-tab","caseinfo-execute-progress-edit-tab");
        FRONT_TAGS_MAP.put("caseinfo-execute-progress-query-tab","caseinfo-execute-progress-query-tab");
    }
}
