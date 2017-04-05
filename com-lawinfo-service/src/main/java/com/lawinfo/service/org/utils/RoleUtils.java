package com.lawinfo.service.org.utils;

import com.lawinfo.domain.org.*;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * Created by wangrongtao on 15/10/20.
 */
public class RoleUtils {
    /**
     * Hashtable线程安全
     */
   private static Map<Long, Role> roleMap = new Hashtable<Long, Role>();


    public static void init() {
        roleMap.clear();
    }
    public static boolean add(Role role) {
        if (role != null) {
            long id = role.getId();
            roleMap.put(id,role);
            return true;
        }
        return false;
    }
    public static boolean remove(long id) {
        if (id>0) {
            roleMap.remove(id);
            return true;
        }
        return false;
    }
    public static List<Role> findAll(){
        List<Role> list = new ArrayList<Role>();
        list.addAll(roleMap.values());
        if (!CollectionUtils.isEmpty(list)) {
            Collections.sort(list);
        }
        return list;
    }
    public static Role findByRoletag(String tag){
        Collection<Role> roles = roleMap.values();
        for (Role role : roles) {
            if (role.getRoletag().equals(tag)) {
                return role;
            }
        }
        return null;
    }
    public static Role findByRoleid(long id){
        return roleMap.get(id);
    }
    public static boolean haveAction(List<UserRole> userRoles,String url) {
        if (!CollectionUtils.isEmpty(userRoles)) {
            for (UserRole userRole : userRoles) {
                long roleid = userRole.getRoleid();
                List<RoleAction> roleActions = RoleActionUtils.findByRoleid(roleid);
                if(ActionUtils.haveAction(roleActions, url)){
                    return true;
                }
            }
        }
        return false;
    }
    public static boolean haveMenu(List<UserRole> userRoles,String url) {
        if (!CollectionUtils.isEmpty(userRoles)) {
            for (UserRole userRole : userRoles) {
                long roleid = userRole.getRoleid();
                List<RoleMenu> roleMenus = RoleMenuUtils.findByRoleid(roleid);
                if(MenuUtils.haveMenu(roleMenus, url)){
                    return true;
                }
            }
        }
        return false;
    }
}
