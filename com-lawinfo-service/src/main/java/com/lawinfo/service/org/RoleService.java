package com.lawinfo.service.org;

import com.lawinfo.domain.org.Role;
import com.lawinfo.domain.org.User;
import com.lawinfo.domain.org.query.RoleQuery;

import java.util.List;

/**
 * Created by wangrongtao on 15/10/14.
 */
public interface RoleService {

    public void initCache() throws Exception;
    public List<Role> findAll()throws Exception;
    public List<Role> findAllFromDb() throws Exception;
    public int save(Role Role)throws Exception;

    public Role findById(long id)throws Exception;

    public List<Role> findList(RoleQuery RoleQuery)throws Exception;

    public List<Role> findListByPage(RoleQuery RoleQuery)throws Exception;

    public int count(RoleQuery RoleQuery)throws Exception;

    public int deleteById(long id)throws Exception;
}
