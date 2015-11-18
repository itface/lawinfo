package com.lawinfo.service.org.utils;

import com.lawinfo.domain.org.*;
import com.lawinfo.domain.org.vo.UserTreeVo;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * Created by wangrongtao on 15/10/20.
 */
public class UserUtils {
    /**
     * Hashtable线程安全
     */
   private static Map<String, User> userMap = new Hashtable<String, User>();


    public static void init() {
        userMap.clear();
    }
    public static boolean add(User user) {
        if (user != null) {
            userMap.put(user.getUserid(),user);
            return true;
        }
        return false;
    }
    public static boolean remove(String userid) {
        if (!StringUtils.isEmpty(userid)) {
            userMap.remove(userid);
            return true;
        }
        return false;
    }
    public static List<User> findAll(){
        List<User> list = new ArrayList<User>();
        list.addAll(userMap.values());
        return list;
    }
    public static List<User> findByOrgid(long orgid){
        Collection<User> users = userMap.values();
        List<User> userList = new ArrayList<User>();
        for (User user : users) {
            if (user.getOrgid() == orgid) {
                userList.add(user);
            }
        }
        return userList;
    }
    public static User findById(long id){
        Collection<User> users = userMap.values();
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }
    public static List<UserRole> findRolesByUserid(String userid) {
        return UserRoleUtils.findByUserid(userid);
    }
    public static List<Menu> findMenusByUserid(String userid) {
        List<UserRole> roles = findRolesByUserid(userid);
        if (!CollectionUtils.isEmpty(roles)) {
            List<RoleMenu> roleMenus = new ArrayList<RoleMenu>();
            for (UserRole userRole : roles) {
                List<RoleMenu> roleMenus2 = RoleMenuUtils.findByRoleid(userRole.getRoleid());
                if (!CollectionUtils.isEmpty(roleMenus2)) {
                    roleMenus.addAll(roleMenus2);
                }
            }
            return MenuUtils.findMenusByRoleMenu(roleMenus);
        }
        return null;
    }
    public static List<Action> findActionsByUserid(String userid) {
        List<UserRole> roles = findRolesByUserid(userid);
        if (!CollectionUtils.isEmpty(roles)) {
            List<RoleAction> roleActions = new ArrayList<RoleAction>();
            for (UserRole userRole : roles) {
                List<RoleAction>  roleActions1= RoleActionUtils.findByRoleid(userRole.getRoleid());
                if (!CollectionUtils.isEmpty(roleActions1)) {
                    roleActions.addAll(roleActions1);
                }
            }
            return ActionUtils.findActionsByRoleAction(roleActions);
        }
        return null;
    }
    public static void findSubordinate(long superiorOrgid,List<String> allSubordinate) {
        if (allSubordinate != null) {
            List<Org> orgs = OrgUtils.findSubOrg(superiorOrgid);
            if (!CollectionUtils.isEmpty(orgs)) {
                Collection<User> users = userMap.values();
                for (Org org : orgs) {
                    for (User user : users) {
                        if (user.getOrgid() == org.getId()) {
                            allSubordinate.add(user.getUserid());
                        }
                    }
                    findSubordinate(org.getId(),allSubordinate);
                }
            }
        }
    }
    public static User findByUserid(String userid){
        return userMap.get(userid);
    }
    public static boolean haveAction(String userid,String url) {
        return RoleUtils.haveAction(UserRoleUtils.findByUserid(userid),url);
    }
    public static boolean haveMenu(String userid,String url) {
        return RoleUtils.haveMenu(UserRoleUtils.findByUserid(userid),url);
    }
}
