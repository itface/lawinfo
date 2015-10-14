package com.lawinfo.service.org;

import com.lawinfo.domain.org.Dept;
import com.lawinfo.domain.org.Privilege;
import com.lawinfo.domain.org.query.DeptQuery;
import com.lawinfo.domain.org.query.PrivilegeQuery;

import java.util.List;

/**
 * Created by wangrongtao on 15/10/14.
 */
public interface PrivilegeService {

    public List<Privilege> findAll()throws Exception;

    public int save(Privilege privilege)throws Exception;

    public Privilege findById(long id)throws Exception;

    public List<Privilege> findList(PrivilegeQuery privilegeQuery)throws Exception;

    public List<Privilege> findListByPage(PrivilegeQuery privilegeQuery)throws Exception;

    public int count(PrivilegeQuery privilegeQuery)throws Exception;

    public int deleteById(long id)throws Exception;
}
