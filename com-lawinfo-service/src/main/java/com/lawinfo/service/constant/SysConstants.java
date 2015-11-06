package com.lawinfo.service.constant;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by wangrongtao on 15/11/5.
 */
public class SysConstants {
    public static String SUPER_ADMIN = "superadmin";
    public static Set<String> FRONT_TAGS = new HashSet<String>();
    public static Set<String> ADMIN_TAGS = new HashSet<String>();
    static {
        FRONT_TAGS.add("front-case-progress-add-btn");
        FRONT_TAGS.add("front-caseinfo-add-btn");
    }
}
