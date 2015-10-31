package com.lawinfo.dao.org;

import com.lawinfo.domain.org.RoleMenu;
import com.lawinfo.domain.org.query.RoleMenuQuery;

import java.util.List;

/**
 * Created by wangrongtao on 15/10/12.
 */
public interface RoleMenuDao {

    public List<RoleMenu> findAll();

    public int save(RoleMenu roleMenu);

    public RoleMenu findById(long id);
    public List<RoleMenu> findByRoleid(long roleid);
    public List<RoleMenu> findByMenuid(long menuid);
    public List<RoleMenu> findList(RoleMenuQuery roleMenu);

    public List<RoleMenu> findListByPage(RoleMenuQuery roleMenu);

    public int count(RoleMenuQuery roleMenuQuery);

    public int deleteById(long id);
    public int deleteByMenuid(long menuid);
    public int deleteByRoleid(long roleid);

}
