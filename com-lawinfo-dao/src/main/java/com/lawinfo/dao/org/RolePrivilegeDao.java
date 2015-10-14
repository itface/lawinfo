package com.lawinfo.dao.org;

import com.lawinfo.domain.org.RolePrivilege;
import com.lawinfo.domain.org.query.RolePrivilegeQuery;

import java.util.List;

/**
 * Created by wangrongtao on 15/10/12.
 */
public interface RolePrivilegeDao {

    public List<RolePrivilege> findAll();

    public int save(RolePrivilege rolePrivilege);

    public RolePrivilege findById(long id);

    public List<RolePrivilege> findList(RolePrivilegeQuery rolePrivilegeQuery);

    public List<RolePrivilege> findListByPage(RolePrivilegeQuery rolePrivilegeQuery);

    public int count(RolePrivilegeQuery rolePrivilegeQuery);

    public int deleteById(long id);


}
