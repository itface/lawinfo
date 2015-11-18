package com.lawinfo.service.org.utils;

import com.lawinfo.domain.org.Org;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * Created by wangrongtao on 15/10/20.
 */
public class OrgUtils {
    /**
     * Hashtable线程安全
     */
   private static Map<Long, Org> orgMap = new Hashtable<Long, Org>();


    public static void init() {
        orgMap.clear();
    }
    public static boolean add(Org org) {
        if (org != null) {
            orgMap.put(org.getId(), org);
            return true;
        }
        return false;
    }
    public static boolean remove(long id) {
        if (id>0) {
            orgMap.remove(id);
            return true;
        }
        return false;
    }

    public static List<Org> findSubOrg(long orgid){
        List<Org> list = new ArrayList<Org>();
        Collection<Org> orgs = orgMap.values();
        if (!CollectionUtils.isEmpty(orgs)) {
            for (Org org : orgs) {
                if (org.getParentorgid()==orgid) {
                    list.add(org);
                }
            }
        }
        return list;
    }
    public static List<Org> findAll(){
        List<Org> list = new ArrayList<Org>();
        list.addAll(orgMap.values());
        return list;
    }
    public static Org findById(long id){
        return orgMap.get(id);
    }
}
