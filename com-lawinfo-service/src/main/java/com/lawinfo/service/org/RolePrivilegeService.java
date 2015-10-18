package com.lawinfo.service.org;

import com.lawinfo.domain.org.RolePrivilege;
import com.lawinfo.domain.org.query.RolePrivilegeQuery;

import java.util.List;

/**
 * Created by wangrongtao on 15/10/14.
 */
public interface RolePrivilegeService {

    public List<RolePrivilege> findAll()throws Exception;

    public int save(RolePrivilege rolePrivilege)throws Exception;

    public RolePrivilege findById(long id)throws Exception;

    public List<RolePrivilege> findList(RolePrivilegeQuery rolePrivilegeQuery)throws Exception;

    public List<RolePrivilege> findListByPage(RolePrivilegeQuery rolePrivilegeQuery)throws Exception;

    public int count(RolePrivilegeQuery rolePrivilegeQuery)throws Exception;

    public int deleteById(long id)throws Exception;
    public int deleteByPrivilegeid(int privilegeid)throws Exception;
    public int deleteByRoleid(int roleid)throws Exception;
}
