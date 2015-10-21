package com.lawinfo.service.org.utils;

import com.lawinfo.domain.org.Dept;
import com.lawinfo.domain.org.OrgInfo;
import com.lawinfo.domain.org.Privilege;
import com.lawinfo.domain.org.User;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/**
 * Created by wangrongtao on 15/10/20.
 */
public class OrgInfoUtils {
    /**
     * Hashtable线程安全
     */
   private static Map<Long, OrgInfo> orginfoMap = new Hashtable<Long, OrgInfo>();


    public static boolean add(OrgInfo orgInfo) {
        if (orgInfo != null) {
            orginfoMap.put(orgInfo.getId(),orgInfo);
            return true;
        }
        return false;
    }
    public static boolean remove(long id) {
        if (id>0) {
            orginfoMap.remove(id);
            return true;
        }
        return false;
    }
    public static List<OrgInfo> findAll(){
        List<OrgInfo> list = new ArrayList<OrgInfo>();
        list.addAll(orginfoMap.values());
        return list;
    }
    public static OrgInfo findById(long id){
        return orginfoMap.get(id);
    }
}
