package com.lawinfo.service.org.utils;

import com.lawinfo.domain.org.Action;
import com.lawinfo.domain.org.Menu;
import com.lawinfo.domain.org.RoleAction;
import com.lawinfo.domain.org.RoleMenu;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * Created by wangrongtao on 15/10/20.
 */
public class ActionUtils {
    /**
     * Hashtable线程安全
     */
   private static Map<Long, Action> actionMap = new Hashtable<Long, Action>();


    public static void init() {
        actionMap.clear();
    }
    public static boolean add(Action action) {
        if (action != null) {
            actionMap.put(action.getId(),action);
            return true;
        }
        return false;
    }
    public static boolean remove(long id) {
        if (id>0) {
            actionMap.remove(id);
            return true;
        }
        return false;
    }
    public static List<Action> findAll(){
        List<Action> list = new ArrayList<Action>();
        list.addAll(actionMap.values());
        return list;
    }
    public static Action findByActionid(long id){
        return actionMap.get(id);
    }

    public static boolean haveAction(List<RoleAction> roleActions,String url) {
        if (!StringUtils.isEmpty(url)) {
            if (url.lastIndexOf("/")==url.length()-1) {
                url = url.substring(0, url.length() - 1);
            }
            if (!CollectionUtils.isEmpty(roleActions)) {
                for (RoleAction roleAction : roleActions) {
                    if(haveAction(roleAction.getActionid(),url)){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public static boolean haveAction(long actionid,String url) {
        if (!StringUtils.isEmpty(url)) {
            if (url.lastIndexOf("/")==url.length()-1) {
                url = url.substring(0, url.length() - 1);
            }
            Action action = actionMap.get(actionid);
            if (action != null) {
                String key = action.getActionkey();
                if (!StringUtils.isEmpty(key)) {
                    if (key.lastIndexOf("/") == key.length() - 1) {
                        key = key.substring(0, key.length() - 1);
                    }
                    if (url.equals(key)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public static List<Action> findActionsByRoleAction(List<RoleAction> roleActions) {
        if (!CollectionUtils.isEmpty(roleActions)) {
            List<Action> list = new ArrayList<Action>();
            for (RoleAction roleAction : roleActions) {
                if (actionMap.get(roleAction.getActionid())!=null) {
                    list.add(actionMap.get(roleAction.getActionid()));
                }
            }
            return list;
        }
        return null;
    }
    public static void main(String[] args) {
        String s ="/a/b/c/d";
        System.out.println(s.lastIndexOf("/")==s.length()-1);
    }
}
