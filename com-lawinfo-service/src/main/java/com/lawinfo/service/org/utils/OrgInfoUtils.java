package com.lawinfo.service.org.utils;

import com.lawinfo.domain.org.Org;

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
   private static Map<Long, Org> orginfoMap = new Hashtable<Long, Org>();


    public static void init() {
        orginfoMap.clear();
    }
    public static boolean add(Org org) {
        if (org != null) {
            orginfoMap.put(org.getId(), org);
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
    public static List<Org> findAll(){
        List<Org> list = new ArrayList<Org>();
        list.addAll(orginfoMap.values());
        return list;
    }
    public static Org findById(long id){
        return orginfoMap.get(id);
    }
}
