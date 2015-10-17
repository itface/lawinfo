package com.lawinfo.dao.org;

import com.lawinfo.domain.org.Dept;
import com.lawinfo.domain.org.Privilege;
import com.lawinfo.domain.org.query.DeptQuery;
import com.lawinfo.domain.org.query.PrivilegeQuery;

import java.util.List;

/**
 * Created by wangrongtao on 15/10/12.
 */
public interface PrivilegeDao {

    public List<Privilege> findAll();

    public int save(Privilege privilege);

    public Privilege findById(long id);

    public List<Privilege> findList(PrivilegeQuery privilegeQuery);

    public List<Privilege> findListByPage(PrivilegeQuery privilegeQuery);

    public int count(PrivilegeQuery deptQuery);

    public int deleteById(long id);


}
