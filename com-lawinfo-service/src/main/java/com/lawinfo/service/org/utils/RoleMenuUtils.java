package com.lawinfo.service.org.utils;

import com.lawinfo.domain.org.RoleMenu;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * Created by wangrongtao on 15/10/20.
 */
public class RoleMenuUtils {
    /**
     * Hashtable线程安全
     */
   private static Map<Long, RoleMenu> roleMenuMap = new Hashtable<Long, RoleMenu>();


    public static void init() {
        roleMenuMap.clear();
    }
    public static boolean add(RoleMenu roleMenu) {
        if (roleMenu != null) {
            roleMenuMap.put(roleMenu.getId(),roleMenu);
            return true;
        }
        return false;
    }
    public static boolean remove(long id) {
        roleMenuMap.remove(id);
        return true;
    }
    public static List<RoleMenu> findAll(){
        List<RoleMenu> list = new ArrayList<RoleMenu>();
        list.addAll(roleMenuMap.values());
        return list;
    }
    public static List<RoleMenu> findByMenuid(long menuid) {
        List<RoleMenu> list = new ArrayList<RoleMenu>();
        Collection<RoleMenu> RoleMenus = roleMenuMap.values();
        for (RoleMenu roleMenu : RoleMenus) {
            if (roleMenu.getMenuid()==menuid) {
                list.add(roleMenu);
            }
        }
        return list;
    }
    public static List<RoleMenu> findByRoleid(long roleid) {
        List<RoleMenu> list = new ArrayList<RoleMenu>();
        Collection<RoleMenu> roleMenus = roleMenuMap.values();
        for (RoleMenu roleMenu : roleMenus) {
            if (roleid == roleMenu.getRoleid()) {
                list.add(roleMenu);
            }
        }
        return list;
    }
}
