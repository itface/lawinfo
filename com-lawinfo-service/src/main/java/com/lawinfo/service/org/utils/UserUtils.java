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
    /*private static void findSubordinateTree(String userid,UserTreeVo subordinateTree,List<User> allSubordinate) {
        if (!StringUtils.isEmpty(userid)&&subordinateTree!=null&&allSubordinate!=null) {
            User superior = userMap.get(userid);
            if (superior != null) {
                long superiorOrgid = superior.getOrgid();
                Collection<User> users = userMap.values();
                for (User user : users) {
                    long orgid = user.getOrgid();
                    Org org = OrgUtils.findById(orgid);
                    if (org != null) {
                        long parentorgid = org.getParentorgid();
                        if (parentorgid == superiorOrgid) {
                            allSubordinate.add(user);
                            Set<UserTreeVo> subTreeNode = subordinateTree.getNodes();
                            if (subTreeNode == null) {
                                subordinateTree.setNodes(new HashSet<UserTreeVo>());
                            }
                            UserTreeVo temp = new UserTreeVo();
                            temp.setUserid(String.valueOf(orgid));
                            Set<UserTreeVo> set = subordinateTree.getNodes();
                            if (!set.contains(temp)) {
                                UserTreeVo root = new UserTreeVo();
                                root.setId(org.getId());
                                root.setText(org.getName());
                                root.setUserid(String.valueOf(org.getId()));
                                root.setNodetype(1);
                                root.setParentid(org.getParentorgid());
                                findSubordinateTree(user.getUserid(), root, allSubordinate);
                                set.add(root);
                            }else{
                                findSubordinateTree(user.getUserid(), set., allSubordinate);
                                subordinateTree.getNodes().add(root);
                            }

                            if (subordinateTree.size() == 0) {

                            }

                            UserTreeVo userTreeVo = new UserTreeVo();
                            userTreeVo.setId(user.getId());
                            userTreeVo.setUserid(user.getUserid());
                            userTreeVo.setText(user.getName());
                            userTreeVo.setParentid(user.getOrgid());
                            List<UserTreeVo> sons = new ArrayList<UserTreeVo>();
                            sons.addAll(findSubordinateTree(user.getUserid()));
                            userTreeVo.setNodes(sons);
                            root.getNodes().add(userTreeVo);
                        }
                    }
                }
            }
        }
    }*/
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
