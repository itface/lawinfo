package com.lawinfo.admin.system.login;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by wangrongtao on 15/10/13.
 */
public class LoginInfo {
    private static Set<String> loginUserSet = new HashSet<String>();
    private static final String SESSION_USERID_KEY = "userid";

    public synchronized static boolean addLoginUser(String userId) {
        if (userId!=null) {
            return loginUserSet.add(userId);
        }
        return false;
    }
    public synchronized static boolean removeLoginUser(String userId) {
        if (userId!=null) {
            return loginUserSet.remove(userId);
        }
        return false;
    }
    public synchronized static boolean removeLoginUser(HttpSession session) {
        if (session!=null) {
            String userid = getUseridFromSession(session);
            return removeLoginUser(userid);
        }
        return false;
    }
    public synchronized static boolean isAvailableLoginUser(String userId) {
        if (userId!=null) {
            return loginUserSet.contains(userId);
        }
        return false;
    }

    public static String getUseridFromSession(HttpSession session) {
        if (session!=null) {
            return session.getAttribute(SESSION_USERID_KEY)==null?null:(String)session.getAttribute(SESSION_USERID_KEY);
        }
        return null;
    }
    public synchronized static boolean addUseridToSession(HttpSession session,String userid) {
        if (session!=null) {
            session.setAttribute(SESSION_USERID_KEY,userid);
            loginUserSet.add(userid);
            return true;
        }
        return false;
    }
    public static boolean isAvailableLoginUser(HttpSession session) {
        String userid = getUseridFromSession(session);
        return isAvailableLoginUser(userid);
    }
    public static List<String> getAllOnlineUsers(){
        List<String> list = new ArrayList<String>();
        list.addAll(loginUserSet);
        return list;
    }
}
