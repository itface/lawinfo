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
public class DeptUtils {
    /**
     * Hashtable线程安全
     */
   private static Map<Long, Dept> deptMap = new Hashtable<Long, Dept>();


    public static boolean add(Dept dept) {
        if (dept != null) {
            deptMap.put(dept.getId(),dept);
            return true;
        }
        return false;
    }
    public static boolean remove(long id) {
        deptMap.remove(id);
        return true;
    }
    public static List<Dept> findAll(){
        List<Dept> list = new ArrayList<Dept>();
        list.addAll(deptMap.values());
        return list;
    }
    public static Dept findById(long id){
        return deptMap.get(id);
    }
}
