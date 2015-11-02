package com.lawinfo.service.org.utils;

import com.lawinfo.domain.org.RoleAction;

import java.util.*;

/**
 * Created by wangrongtao on 15/10/20.
 */
public class RoleActionUtils {
    /**
     * Hashtable线程安全
     */
   private static Map<Long, RoleAction> roleActionMap = new Hashtable<Long, RoleAction>();


    public static boolean add(RoleAction roleAction) {
        if (roleAction != null) {
            roleActionMap.put(roleAction.getId(),roleAction);
            return true;
        }
        return false;
    }
    public static boolean remove(long id) {
        roleActionMap.remove(id);
        return true;
    }
    public static List<RoleAction> findAll(){
        List<RoleAction> list = new ArrayList<RoleAction>();
        list.addAll(roleActionMap.values());
        return list;
    }
    public static List<RoleAction> findByActionid(long actionid) {
        List<RoleAction> list = new ArrayList<RoleAction>();
        Collection<RoleAction> roleActions = roleActionMap.values();
        for (RoleAction roleAction : roleActions) {
            if (roleAction.getId()==actionid) {
                list.add(roleAction);
            }
        }
        return list;
    }
    public static List<RoleAction> findByRoleid(long roleid) {
        List<RoleAction> list = new ArrayList<RoleAction>();
        Collection<RoleAction> roleActions = roleActionMap.values();
        for (RoleAction roleAction : roleActions) {
            if (roleAction.getRoleid()==roleid) {
                list.add(roleAction);
            }
        }
        return list;
    }
}
