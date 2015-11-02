package com.lawinfo.service.org.utils;

import com.lawinfo.domain.org.Action;
import com.lawinfo.domain.org.Menu;
import com.lawinfo.domain.org.RoleMenu;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * Created by wangrongtao on 15/10/20.
 */
public class MenuUtils {
    /**
     * Hashtable线程安全
     */
   private static Map<Long, Menu> menutMap = new Hashtable<Long, Menu>();


    public static boolean add(Menu menu) {
        if (menu != null) {
            menutMap.put(menu.getId(),menu);
            return true;
        }
        return false;
    }
    public static boolean remove(long id) {
        menutMap.remove(id);
        return true;
    }
    public static List<Menu> findAll(){
        List<Menu> list = new ArrayList<Menu>();
        list.addAll(menutMap.values());
        return list;
    }
    public static Menu findById(long id){
        return menutMap.get(id);
    }
    public static List<Menu> findMenusByRoleMenu(List<RoleMenu> roleMenus) {
        if (!CollectionUtils.isEmpty(roleMenus)) {
            List<Menu> list = new ArrayList<Menu>();
            for (RoleMenu roleMenu : roleMenus) {
                if (menutMap.get(roleMenu.getMenuid())!=null) {
                    list.add(menutMap.get(roleMenu.getMenuid()));
                }
            }
            return list;
        }
        return null;
    }
    public static boolean haveMenu(List<RoleMenu> roleMenus,String url) {
        if (!StringUtils.isEmpty(url)) {
            if (url.lastIndexOf("/")==url.length()-1) {
                url = url.substring(0, url.length() - 1);
            }
            if (!CollectionUtils.isEmpty(roleMenus)) {
                for (RoleMenu roleMenu : roleMenus) {
                    if (haveMenu(roleMenu.getMenuid(),url)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public static boolean haveMenu(long menuid,String url) {
        if (!StringUtils.isEmpty(url)) {
            if (url.lastIndexOf("/")==url.length()-1) {
                url = url.substring(0, url.length() - 1);
            }
            Menu menu = menutMap.get(menuid);
            if (menu!=null) {
                String menuUrl = menu.getUrl();
                if (!StringUtils.isEmpty(menuUrl)) {
                    if (menuUrl.lastIndexOf("/")==menuUrl.length()-1) {
                        menuUrl = menuUrl.substring(0, menuUrl.length() - 1);
                    }
                    if (url.equals(menuUrl)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
