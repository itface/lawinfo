package com.lawinfo.service.org.utils;

import com.lawinfo.domain.org.User;
import com.lawinfo.domain.org.UserRole;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * Created by wangrongtao on 15/10/20.
 */
public class UserRoleUtils {
    /**
     * Hashtable线程安全
     */
   private static Map<Long, UserRole> userRoleMap = new Hashtable<Long, UserRole>();


    public static boolean add(UserRole userRole) {
        if (userRole != null) {
            userRoleMap.put(userRole.getId(),userRole);
            return true;
        }
        return false;
    }
    public static boolean remove(long id) {
        userRoleMap.remove(id);
        return true;
    }
    public static List<UserRole> findAll(){
        List<UserRole> list = new ArrayList<UserRole>();
        list.addAll(userRoleMap.values());
        return list;
    }
    public static List<UserRole> findByUserid(String userid) {
        List<UserRole> list = new ArrayList<UserRole>();
        if (!StringUtils.isEmpty(userid)) {
            Collection<UserRole> userRoles = userRoleMap.values();
            for (UserRole userRole : userRoles) {
                if (userRole.getUserid().equals(userid)) {
                    list.add(userRole);
                }
            }
        }
        return list;
    }
    public static List<UserRole> findByRoleid(long roleid) {
        List<UserRole> list = new ArrayList<UserRole>();
        Collection<UserRole> userRoles = userRoleMap.values();
        for (UserRole userRole : userRoles) {
            if (userRole.getRoleid()==roleid) {
                list.add(userRole);
            }
        }
        return list;
    }
}
