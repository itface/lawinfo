package com.lawinfo.service.org.utils;

import com.lawinfo.domain.org.Dept;
import com.lawinfo.domain.org.Role;
import com.lawinfo.domain.org.User;
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
    public static User findByUserid(String userid){
        return userMap.get(userid);
    }
}
