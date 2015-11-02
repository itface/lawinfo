package com.lawinfo.service.org;

import com.lawinfo.domain.org.RoleMenu;
import com.lawinfo.domain.org.query.RoleMenuQuery;

import java.util.List;

/**
 * Created by wangrongtao on 15/10/14.
 */
public interface RoleMenuService {
    public void initCache()throws Exception;

    public List<RoleMenu> findAll()throws Exception;

    public int save(RoleMenu roleMenu)throws Exception;

    public RoleMenu findById(long id)throws Exception;
    public List<RoleMenu> findByRoleid(long roleid)throws Exception;
    public List<RoleMenu> findByMenuid(long menuid)throws Exception;

    public List<RoleMenu> findList(RoleMenuQuery roleMenuQuery)throws Exception;

    public List<RoleMenu> findListByPage(RoleMenuQuery roleMenuQuery)throws Exception;

    public int count(RoleMenuQuery roleMenuQuery)throws Exception;

    public int deleteById(long id)throws Exception;
    public int deleteByMenuid(long menuid)throws Exception;
    public int deleteByRoleid(long roleid)throws Exception;
}
