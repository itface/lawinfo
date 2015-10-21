package com.lawinfo.service.org.utils;

import com.lawinfo.domain.org.Dept;
import com.lawinfo.domain.org.Privilege;
import com.lawinfo.domain.org.User;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/**
 * Created by wangrongtao on 15/10/20.
 */
public class PrivilegeUtils {
    /**
     * Hashtable线程安全
     */
   private static Map<Integer, Privilege> privilegeMap = new Hashtable<Integer, Privilege>();


    public static boolean add(Privilege privilege) {
        if (privilege != null) {
            privilegeMap.put(privilege.getPrivilegeid(),privilege);
            return true;
        }
        return false;
    }
    public static boolean remove(int id) {
        if (id>0) {
            privilegeMap.remove(id);
            return true;
        }
        return false;
    }
    public static List<Privilege> findAll(){
        List<Privilege> list = new ArrayList<Privilege>();
        list.addAll(privilegeMap.values());
        return list;
    }
    public static Privilege findByPrivilegeid(int privilegeid){
        return privilegeMap.get(privilegeid);
    }
}
