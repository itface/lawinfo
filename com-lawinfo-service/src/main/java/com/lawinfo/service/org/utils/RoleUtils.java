package com.lawinfo.service.org.utils;

import com.lawinfo.domain.org.Role;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/**
 * Created by wangrongtao on 15/10/20.
 */
public class RoleUtils {
    /**
     * Hashtable线程安全
     */
   /*private static Map<Integer, Role> roleMap = new Hashtable<Integer, Role>();


    public static boolean add(Role role) {
        if (role != null) {
            int roleid = role.getRoleid();
            roleMap.put(roleid,role);
            return true;
        }
        return false;
    }
    public static boolean remove(int id) {
        if (id>0) {
            roleMap.remove(id);
            return true;
        }
        return false;
    }
    public static List<Role> findAll(){
        List<Role> list = new ArrayList<Role>();
        list.addAll(roleMap.values());
        return list;
    }
    public static Role findByRoleid(int roleid){
        return roleMap.get(roleid);
    }*/
}
