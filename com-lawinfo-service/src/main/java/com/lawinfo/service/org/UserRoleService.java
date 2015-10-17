package com.lawinfo.service.org;

import com.lawinfo.domain.org.UserRole;
import com.lawinfo.domain.org.query.UserRoleQuery;

import java.util.List;

/**
 * Created by wangrongtao on 15/10/14.
 */
public interface UserRoleService {

    public List<UserRole> findAll()throws Exception;

    public int save(UserRole userRole)throws Exception;

    public UserRole findById(long id)throws Exception;

    public List<UserRole> findList(UserRoleQuery userRoleQuery)throws Exception;

    public List<UserRole> findListByPage(UserRoleQuery userRoleQuery)throws Exception;

    public int count(UserRoleQuery userRoleQuery)throws Exception;

    public int deleteById(long id)throws Exception;
}
