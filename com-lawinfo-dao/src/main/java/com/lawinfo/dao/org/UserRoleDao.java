package com.lawinfo.dao.org;

import com.lawinfo.domain.org.UserRole;
import com.lawinfo.domain.org.query.UserRoleQuery;

import java.util.List;

/**
 * Created by wangrongtao on 15/10/12.
 */
public interface UserRoleDao {

    public List<UserRole> findAll();

    public int save(UserRole userRole);

    public UserRole findById(long id);

    public List<UserRole> findList(UserRoleQuery userRoleQuery);

    public List<UserRole> findListByPage(UserRoleQuery userRoleQuery);

    public int count(UserRoleQuery userRoleQuery);

    public int deleteById(long id);
    public int deleteByUserid(String userid);
    public int deleteByRoleid(long roleid);
    public List<UserRole> findByUserid(String userid);
    public List<UserRole> findByRoleid(long roleid);


}
